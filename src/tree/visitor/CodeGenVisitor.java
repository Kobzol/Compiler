/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.visitor;

import tree.expression.Literal;
import tree.statement.ExpressionStatement;
import tree.statement.ReadStatement;
import tree.statement.DeclarationStatement;
import tree.statement.WriteStatement;
import tree.expression.AssignmentExpression;
import tree.expression.ConditionalExpression;
import tree.expression.IdentifierExpression;
import tree.expression.BinaryExpression;
import tree.expression.UnaryExpression;
import interpret.Instruction;
import interpret.InstructionType;
import java.util.List;
import interpret.InstructionGenerator;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import parser.Symbol;
import tree.Function;
import tree.SemanticException;
import tree.SymbolTable;
import tree.SymbolTable.VariableInfo;
import tree.Variable;
import tree.expression.BinaryOperator;
import tree.expression.DataType;
import tree.expression.Expression;
import tree.expression.FunctionCallExpression;
import tree.expression.UnaryOperator;
import static tree.expression.UnaryOperator.DECREMENT;
import static tree.expression.UnaryOperator.INCREMENT;
import static tree.expression.UnaryOperator.MINUS;
import static tree.expression.UnaryOperator.NEGATE;
import tree.statement.BlockStatement;
import tree.statement.ForStatement;
import tree.statement.FunctionStatement;
import tree.statement.IfStatement;
import tree.statement.ReturnStatement;
import tree.statement.WhileStatement;
import util.ErrorManager;

/**
 *
 * @author Jakub
 */
public class CodeGenVisitor extends AbstractVisitor {
    private final InstructionGenerator instructionGenerator = new InstructionGenerator();
    private final SymbolTable symbolTable = new SymbolTable();
    private final Map<String, Integer> functionLocations = new HashMap<String, Integer>();

    public CodeGenVisitor(ErrorManager errorManager)
    {
       super(errorManager);
    }
    
    public List<Instruction> getInstructions()
    {
        return this.instructionGenerator.getInstructions();
    }

    @Override
    public void visit(DeclarationStatement declarationStatement)
    {
        try
        {
            for (int i = 0; i < declarationStatement.getIdentifiers().size(); i++)
            {
                String identifier = declarationStatement.getIdentifiers().get(i);
                Expression expression = declarationStatement.getInitializers().get(i);
                
                VariableInfo variable = this.symbolTable.addVariable(identifier, declarationStatement.getDataType());
                this.instructionGenerator.declareVariable(declarationStatement.getDataType());
                
                if (expression != null)
                {
                    expression.accept(this);
                    this.instructionGenerator.saveVariable(variable.getAddress());
                }
            } 
        }
        catch (SemanticException e)
        {
           e.printStackTrace();
        }
    }

    @Override
    public void visit(ReadStatement readStatement)
    {
        try
        {
            for (String identifier : readStatement.getIdentifiers())
            {
                this.instructionGenerator.readVariable(this.symbolTable.getVariable(identifier).getAddress());
            }
        }
        catch (SemanticException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(WriteStatement writeStatement)
    {
        this.instructionGenerator.writeVariable(writeStatement.getExpressions().size());
    }
    
    @Override
    public void visit(ExpressionStatement expressionStatement)
    {
        if (expressionStatement.getExpression().hasSideEffects())
        {
            expressionStatement.getExpression().accept(this);
        }
    }
    
    @Override
    public void visit(IfStatement ifStatement)
    {
        try
        {
            UUID afterLoop = UUID.randomUUID();
            UUID nextUUID = UUID.randomUUID();
            
            for (int i = 0; i < ifStatement.getConditions().size(); i++)
            {
                if (i > 0)
                {
                    this.instructionGenerator.label(nextUUID);
                    nextUUID = UUID.randomUUID();
                }
                
                Expression condition = ifStatement.getConditions().get(i);
                BlockStatement branch = ifStatement.getStatementBlocks().get(i);

                if (condition.isConstant())
                {
                    boolean conditionValue = Boolean.parseBoolean(condition.getValue(this.symbolTable).toString());
                    
                    if (conditionValue)
                    {
                        branch.accept(this);
                        this.instructionGenerator.jump(afterLoop, false);
                        break;
                    }
                }
                else
                {
                    condition.accept(this);
                    this.instructionGenerator.jump(nextUUID, true);
                    branch.accept(this);
                    this.instructionGenerator.jump(afterLoop, false);
                }
            }
            
            this.instructionGenerator.label(nextUUID);
            
            if (ifStatement.getElseBlock() != null)
            {
                ifStatement.getElseBlock().accept(this);
            }
            
            this.instructionGenerator.label(afterLoop);
        }
        catch (Exception e)
        {
           this.reportError(ifStatement, e.getMessage());
        }
    }

    @Override
    public void visit(ForStatement forStatement)
    {
        if (forStatement.getInitializer() != null && forStatement.getInitializer().hasSideEffects())
        {
            forStatement.getInitializer().accept(this);
        }
        
        if (forStatement.getCondition() != null && forStatement.getCondition().isConstant())
        {
            try
            {
                boolean conditionValue = Boolean.parseBoolean(forStatement.getCondition().getValue(this.symbolTable).toString());
                
                if (!conditionValue)
                {
                    return;
                }
            }
            catch (SemanticException e)
            {
                e.printStackTrace();
            }
        }
        
        int instructionIndex = this.instructionGenerator.getInstructionIndex();
        UUID afterLoop = UUID.randomUUID();

        if (forStatement.getCondition() != null)
        {
            forStatement.getCondition().accept(this);
            this.instructionGenerator.jump(afterLoop, true);
        }
        
        forStatement.getStatements().accept(this);
        
        if (forStatement.getIncrement() != null)
        {
            forStatement.getIncrement().accept(this);
        }

        this.instructionGenerator.jump(instructionIndex, false);
        
        if (forStatement.getCondition() != null)
        {
            this.instructionGenerator.label(afterLoop);
        }
    }
    
    @Override
    public void visit(FunctionStatement functionStatement)
    {
        UUID skipFunction = UUID.randomUUID();
        this.instructionGenerator.jump(skipFunction, false);
        
        int instructionIndex = this.instructionGenerator.getInstructionIndex();
        this.functionLocations.put(functionStatement.getFunctionName(), instructionIndex);
        
        Function function = new Function(functionStatement.getFunctionName(), functionStatement.getReturnType(), functionStatement.getParameters());
        
        function.getParameters().add(0, new Symbol("return", DataType.INTEGER));
        this.symbolTable.addFunctionParameters(function);
        
        if (functionStatement.getBlockStatement() != null)
        {
            functionStatement.getBlockStatement().accept(this);
        }
        
        this.instructionGenerator.generateReturn();
        
        this.symbolTable.removeFunctionParameters(function);
        
        this.instructionGenerator.label(skipFunction);
    }
    
    @Override
    public void visit(ReturnStatement returnStatement)
    {
        returnStatement.getExpression().accept(this);
        this.instructionGenerator.generateReturn();
    }
    
    @Override
    public void visit(WhileStatement whileStatement)
    {
        try
        {
            if (whileStatement.getCondition().isConstant())
            {
                boolean conditionValue = Boolean.parseBoolean(whileStatement.getCondition().getValue(this.symbolTable).toString());
                
                if (!conditionValue)
                {
                    return;
                }
            }
            
            UUID afterLoop = UUID.randomUUID();
            int instructionIndex = this.instructionGenerator.getInstructionIndex();
            
            whileStatement.getCondition().accept(this);
            this.instructionGenerator.jump(afterLoop, true);
            
            whileStatement.getStatements().accept(this);
            this.instructionGenerator.jump(instructionIndex, false);
            
            this.instructionGenerator.label(afterLoop);
        }
        catch (SemanticException e)
        {
            this.reportError(whileStatement, e.getMessage());
        }
    }

    @Override
    public void visit(AssignmentExpression expression)
    {
        try
        {
            this.instructionGenerator.saveVariable(this.symbolTable.getVariable(expression.getIdentifier()).getAddress());
        }
        catch (SemanticException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(BinaryExpression binaryExpression)
    {
        if (binaryExpression.getRightOperand().isConstant())    // check for zero division
        {
            try
            {
                DataType type = binaryExpression.getRightOperand().getDataType(this.symbolTable);
                
                if (type == DataType.INTEGER || type == DataType.FLOAT)
                {
                    float value = Float.parseFloat(binaryExpression.getRightOperand().getValue(this.symbolTable).toString());
                    
                    if (value == 0.0 && binaryExpression.getBinaryOperator() == BinaryOperator.DIVIDE)
                    {
                        this.reportError(binaryExpression, "Division by zero");
                    }
                }
            }
            catch (SemanticException e)
            {
                e.printStackTrace();
            }
        }
        
        if (binaryExpression.isConstant())  // constant folding
        {
            try
            {
                this.instructionGenerator.pushLiteral(
                        binaryExpression.getDataType(this.symbolTable),
                        binaryExpression.getValue(this.symbolTable)
                );
            }
            catch (SemanticException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            binaryExpression.getLeftOperand().accept(this);
            binaryExpression.getRightOperand().accept(this);

            InstructionType instructionType = InstructionType.ADD;
            switch (binaryExpression.getBinaryOperator())
            {
                case MINUS: instructionType = InstructionType.SUB; break;
                case DIVIDE: instructionType = InstructionType.DIV; break;
                case MULTIPLY: instructionType = InstructionType.MUL; break;
                case REMAINDER: instructionType = InstructionType.REM; break;
                case CONCAT: instructionType = InstructionType.CONCAT; break;
                case AND: instructionType = InstructionType.AND; break;
                case OR: instructionType = InstructionType.OR; break;
                case EQ: instructionType = InstructionType.EQ; break;
                case NE: instructionType = InstructionType.NE; break;
                case LT: instructionType = InstructionType.LT; break;
                case LE: instructionType = InstructionType.LE; break;
                case GT: instructionType = InstructionType.GT; break;
                case GE: instructionType = InstructionType.GE; break;
            }

            this.instructionGenerator.addOperator(instructionType);
        }
    }

    @Override
    public void visit(UnaryExpression unaryExpression)
    {
        try
        {
            if (unaryExpression.isConstant())
            {
                this.instructionGenerator.pushLiteral(unaryExpression.getDataType(this.symbolTable), unaryExpression.getValue(this.symbolTable));
            }
            else
            {
                unaryExpression.getExpression().accept(this);
                UnaryOperator operator = unaryExpression.getUnaryOperator();
                
                switch (operator)
                {
                    case MINUS:
                    {
                        this.instructionGenerator.addOperator(InstructionType.UNARY_MINUS);
                        break;
                    }
                    case NEGATE:
                    {
                        this.instructionGenerator.addOperator(InstructionType.NEGATE);
                        break;
                    }
                    case INCREMENT:
                    case DECREMENT:
                    {
                        IdentifierExpression identifier = (IdentifierExpression) unaryExpression.getExpression();
                        VariableInfo variable = this.symbolTable.getVariable(identifier.getIdentifier());
                        
                        this.instructionGenerator.pushLiteral(DataType.INTEGER, operator == UnaryOperator.INCREMENT ? 1 : -1);
                        this.instructionGenerator.addOperator(InstructionType.ADD);
                        this.instructionGenerator.saveVariable(variable.getAddress());
                    }
                }
            }
        }
        catch (SemanticException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(Literal literal)
    {
        this.instructionGenerator.pushLiteral(literal.getDataType(), literal.getValue());
    }

    @Override
    public void visit(ConditionalExpression conditionalExpression)
    {
        if (conditionalExpression.isConstant())
        {
            try
            {
                if (conditionalExpression.getFirstAlternative() == null)
                {
                    this.instructionGenerator.pushLiteral(
                            conditionalExpression.getExpression().getDataType(this.symbolTable),
                            conditionalExpression.getExpression().getValue(this.symbolTable)
                    );
                }
                else
                {
                    this.instructionGenerator.pushLiteral(
                            conditionalExpression.getFirstAlternative().getDataType(this.symbolTable),
                            conditionalExpression.getValue(this.symbolTable)
                    );
                }
            }
            catch (SemanticException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            conditionalExpression.getExpression().accept(this);
        
            if (conditionalExpression.getFirstAlternative() != null)
            {
                conditionalExpression.getFirstAlternative().accept(this);
                conditionalExpression.getSecondAlternative().accept(this);
            }
        }
    }

    @Override
    public void visit(IdentifierExpression identifierExpression)
    {
        try
        {
            this.instructionGenerator.loadVariable(this.symbolTable.getVariable(identifierExpression.getIdentifier()).getAddress());
        }
        catch (SemanticException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void visit(FunctionCallExpression functionCallExpression)
    {
        int instructionIndex = this.instructionGenerator.callerAddress();
        for (Expression expression : functionCallExpression.getParameters()) // put parameters to stack
        {
            expression.accept(this);
        }
        
        this.instructionGenerator.createStackFrame(functionCallExpression.getParameters().size() + 1);
        
        this.instructionGenerator.jump(this.functionLocations.get(functionCallExpression.getFunctionName()), false);
        
        Variable returnAddress = (Variable) this.instructionGenerator.getInstruction(instructionIndex).arguments.get(0);
        returnAddress.setValue(this.instructionGenerator.getInstructionIndex());
        this.instructionGenerator.destroyStackFrame();
    }
}
