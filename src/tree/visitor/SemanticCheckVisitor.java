/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.visitor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import parser.Symbol;
import tree.Function;
import tree.Node;
import tree.SemanticException;
import tree.SymbolTable;
import tree.statement.ReadStatement;
import tree.statement.EmptyStatement;
import tree.statement.DeclarationStatement;
import tree.expression.AssignmentExpression;
import tree.expression.ConditionalExpression;
import tree.expression.BinaryOperator;
import tree.expression.IdentifierExpression;
import tree.expression.DataType;
import tree.expression.UnaryOperator;
import tree.expression.BinaryExpression;
import tree.expression.Expression;
import tree.expression.FunctionCallExpression;
import tree.expression.UnaryExpression;
import tree.statement.BlockStatement;
import tree.statement.ExpressionStatement;
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
public class SemanticCheckVisitor extends AbstractVisitor {
    private final SymbolTable symbolTable = new SymbolTable();
    private final Stack<String> functions = new Stack<String>();
    private Node root;
    
    public SemanticCheckVisitor(ErrorManager errorManager)
    {
        super(errorManager);
    }
    
    private void pushFunction(String name)
    {
        this.functions.push(name);
    }
    private void popFunction()
    {
        this.functions.pop();
    }
    private boolean isInFunction()
    {
        return !this.functions.isEmpty();
    }
    private String getFunction()
    {
        return this.functions.peek();
    }
    
    public void Start(Node node)
    {
        this.root = node;
        node.accept(this);
    }

    @Override
    public void visit(DeclarationStatement declarationStatement)
    {
        DataType dataType = declarationStatement.getDataType();
        
        for (int i = 0; i < declarationStatement.getIdentifiers().size(); i++)
        {
            String identifier = declarationStatement.getIdentifiers().get(i);
            Expression expression = declarationStatement.getInitializers().get(i);
            
            try
            {
                if (expression != null)
                {
                    DataType actualType = expression.getDataType(this.symbolTable);
                    
                    if (dataType != actualType)
                    {
                        this.reportError(declarationStatement, "Trying to assign " + actualType + " into " + dataType);
                        continue;
                    }
                    else expression.accept(this);
                }
                
                this.symbolTable.addVariable(identifier, dataType);
            }
            catch (SemanticException e)
            {
                this.reportError(declarationStatement, e.getMessage());
            }
        }
    }

    @Override
    public void visit(ReadStatement readStatement)
    {
        for (String identifier : readStatement.getIdentifiers())
        {
            if (!this.symbolTable.hasIdentifier(identifier))
            {
                this.reportError(readStatement, "No identifier with name " + identifier + " to read into");
            }
        }
    }
    
    @Override
    public void visit(EmptyStatement emptyStatement)
    {
        this.reportError(emptyStatement, "Empty statement found");
    }
    
    @Override
    public void visit(ExpressionStatement expressionStatement)
    {
        expressionStatement.getExpression().accept(this);
    }
    
    @Override
    public void visit(IfStatement ifStatement)
    {
        try
        {
            for (Expression condition : ifStatement.getConditions())
            {
                 DataType conditionType = condition.getDataType(this.symbolTable);
        
                if (conditionType != DataType.BOOLEAN)
                {
                    this.reportError(ifStatement, "Condition expression in if statement must have boolean type");
                }
            }
            
            for (BlockStatement branch : ifStatement.getStatementBlocks())
            {
                branch.accept(this);
            }
            
            if (ifStatement.getElseBlock() != null)
            {
                ifStatement.getElseBlock().accept(this);
            }
        }
        catch (SemanticException e)
        {
            this.reportError(ifStatement, e.getMessage());
        }
    }

    @Override
    public void visit(ForStatement forStatement)
    {
        try
        {
            if (forStatement.getCondition() != null)
            {
                DataType conditionType = forStatement.getCondition().getDataType(this.symbolTable);

                if (conditionType != DataType.BOOLEAN)
                {
                    throw new SemanticException("Condition expression in for statement must have boolean type");
                }

                if (forStatement.getCondition().isConstant())
                {
                    boolean conditionValue = Boolean.parseBoolean(forStatement.getCondition().getValue(this.symbolTable).toString());

                    if (conditionValue && !forStatement.containsFlowBreak())
                    {
                        throw new SemanticException("Endless for loop found");
                    }
                }
            }
            else if (!forStatement.containsFlowBreak())
            {
                throw new SemanticException("Endless for loop found");
            }
        }
        catch (SemanticException e)
        {
            this.reportError(forStatement, e.getMessage());
        }
    }
    
    @Override
    public void visit(FunctionStatement functionStatement)
    {
        if (this.isInFunction())
        {
            this.reportError(functionStatement, "Cannot define function inside another function");
            return;
        }
        
        try
        {
            DataType returnType = functionStatement.getReturnType();
            Function function = new Function(functionStatement.getFunctionName(), returnType, functionStatement.getParameters());
            
            this.symbolTable.addFunction(function);      
            this.symbolTable.addFunctionParameters(function);
            this.pushFunction(function.getName());
            
            Set<String> parameterNames = new HashSet<String>();
            for (Symbol parameter : functionStatement.getParameters())
            {
                if (parameterNames.contains(parameter.identifier))
                {
                    this.reportError(functionStatement, "Found duplicate parameter name " + parameter.identifier + " in function " + function.getName());
                }
                else parameterNames.add(parameter.identifier);
            }
            
            if (functionStatement.getBlockStatement() != null)
            {
                functionStatement.getBlockStatement().accept(this);
            }
            
            this.symbolTable.removeFunctionParameters(function);
            this.popFunction();
        }
        catch (SemanticException e)
        {
            this.reportError(functionStatement, e.getMessage());
        }
    }
    
    @Override
    public void visit(ReturnStatement returnStatement)
    {
        if (this.isInFunction())
        {
            try
            {
                Function function = this.symbolTable.getFunction(this.getFunction());
                DataType expectedType = function.getReturnType();
                DataType actualType = returnStatement.getExpression().getDataType(this.symbolTable);
                
                if (expectedType != actualType)
                {
                    this.reportError(returnStatement,
                                     "Function " + function.getName() +
                                     " expects return type " + expectedType.name() + 
                                     ", but " + actualType.name() + " was given");
                }
            }
            catch (SemanticException e)
            {
                this.reportError(returnStatement, e.getMessage());
            }
        }
        else this.reportError(returnStatement, "Return used outside of function");
    }
    
    @Override
    public void visit(WhileStatement whileStatement)
    {
        try
        {
            DataType conditionType = whileStatement.getCondition().getDataType(this.symbolTable);
            
            if (conditionType != DataType.BOOLEAN)
            {
                throw new SemanticException("Condition expression in while statement must have boolean type");
            }
            
            if (whileStatement.getCondition().isConstant())
            {
                boolean conditionValue = Boolean.parseBoolean(whileStatement.getCondition().getValue(this.symbolTable).toString());
                
                if (conditionValue && !whileStatement.containsFlowBreak())
                {
                    throw new SemanticException("Endless while loop found");
                }
            }
            
            whileStatement.getStatements().accept(this);
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
            DataType dataType = this.symbolTable.getVariable(expression.getIdentifier()).getVariable().getDataType();
            DataType assignedType = expression.getExpression().getDataType(this.symbolTable);
            
            if (dataType != assignedType)
            {
                this.reportError(expression, "Data type " + assignedType.name() + " cannot be assigned to " + dataType.name());
            }
        }
        catch (SemanticException e)
        {
            this.reportError(expression, e.getMessage());
        }
    }

    @Override
    public void visit(BinaryExpression binaryExpression)
    {
        binaryExpression.getLeftOperand().accept(this);
        binaryExpression.getRightOperand().accept(this);
        
        try
        {
            DataType ltype = binaryExpression.getLeftOperand().getDataType(this.symbolTable);
            DataType rtype = binaryExpression.getRightOperand().getDataType(this.symbolTable);
        
            if (ltype != rtype)
            {
                this.reportError(binaryExpression, "Operand types do not match");
                return;
            }

            BinaryOperator operator = binaryExpression.getBinaryOperator();
            DataType type = ltype;

            switch (operator)
            {
                case PLUS:
                case MINUS:
                case MULTIPLY:
                case DIVIDE:
                {
                    if (!type.isNumeric())
                    {
                        this.reportError(binaryExpression, "Cannot apply binary operator " + operator.name() + " to type " + type.name());
                    }
                    break;
                }
                case REMAINDER:
                {
                    if (type != DataType.INTEGER)
                    {
                        this.reportError(binaryExpression, "Cannot apply binary operator REMAINDER to type " + type.name());
                    }
                    break;
                }
                case CONCAT:
                {
                    if (type != DataType.STRING)
                    {
                        this.reportError(binaryExpression, "Cannot apply binary operator CONCAT to type " + type.name());
                    }
                    break;
                }
                case AND:
                case OR:
                {
                    if (type != DataType.BOOLEAN)
                    {
                        this.reportError(binaryExpression, "Cannot apply binary operator " + operator.name() +" to type " + type.name());
                    }
                    break;
                }
            }
        }
        catch (SemanticException e)
        {
            this.reportError(binaryExpression, e.getMessage());
        }
    }

    @Override
    public void visit(UnaryExpression unaryExpression)
    {
        unaryExpression.getExpression().accept(this);
        
        try
        {
            if (unaryExpression.hasTwoOperators())
            {
                throw new SemanticException("Unary expression cannot have both prefix and postfix unary operators");
            }
            
            UnaryOperator operator = unaryExpression.getUnaryOperator();
            DataType type = unaryExpression.getDataType(this.symbolTable);

            switch (operator)
            {
                case MINUS:
                {
                    if (!type.isNumeric())
                    {
                        this.reportError(unaryExpression, "Cannot apply unary operator - to type " + type.name());
                    }
                    break;
                }
                case NEGATE:
                {
                    if (type != DataType.BOOLEAN)
                    {
                        this.reportError(unaryExpression, "Cannot apply unary operator ! to type " + type.name());
                    }
                    break;
                }
                case INCREMENT:
                case DECREMENT:
                {
                    if (type != DataType.INTEGER)
                    {
                        this.reportError(unaryExpression, "Cannot apply unary operator " + operator.name() + " to type " + type.name());
                    }
                    break;
                }
            }
        }
        catch (SemanticException e)
        {
            this.reportError(unaryExpression, e.getMessage());
        }
    }

    @Override
    public void visit(ConditionalExpression conditionalExpression)
    {
        conditionalExpression.getExpression().accept(this);
        
        if (conditionalExpression.getFirstAlternative() != null)
        {
            conditionalExpression.getFirstAlternative().accept(this);
            conditionalExpression.getSecondAlternative().accept(this);
        }
        
        try
        {
             if (conditionalExpression.getFirstAlternative() != null)
            {
                DataType conditionType = conditionalExpression.getExpression().getDataType(this.symbolTable);

                if (conditionType != DataType.BOOLEAN)
                {
                    this.reportError(conditionalExpression, "Condition in ternary operator must have type boolean");
                }

                DataType firstAlternativeType = conditionalExpression.getFirstAlternative().getDataType(this.symbolTable);
                DataType secondAlternativeType = conditionalExpression.getSecondAlternative().getDataType(this.symbolTable);

                if (firstAlternativeType != secondAlternativeType)
                {
                    this.reportError(conditionalExpression,
                            "Alternatives in ternary operator must have the same type (found "
                            + firstAlternativeType.name() + " and " + secondAlternativeType + ")"
                    );
                }
            }
        }
        catch (SemanticException e)
        {
            this.reportError(conditionalExpression, e.getMessage());
        }
    }

    @Override
    public void visit(IdentifierExpression identifierExpression)
    {
        String identifier = identifierExpression.getIdentifier();
        if (!this.symbolTable.hasIdentifier(identifier))
        {
            this.reportError(identifierExpression, "Identifier " + identifier + " not found");
        }
    }
    
    @Override
    public void visit(FunctionCallExpression functionCallExpression)
    {
        try
        {
            Function function = this.symbolTable.getFunction(functionCallExpression.getFunctionName());
            List<Expression> actualParameters = functionCallExpression.getParameters();
            List<Symbol> expectedParameters = function.getParameters();
            
            if (actualParameters.size() != expectedParameters.size())
            {
                this.reportError(functionCallExpression, "Expected " + expectedParameters.size() + " parameters, got " + actualParameters.size() +
                                 " for function " + function.getName());
                return;
            }
            
            for (int i = 0; i < expectedParameters.size(); i++)
            {               
                DataType actualType = actualParameters.get(i).getDataType(this.symbolTable);
                
                if (actualType != expectedParameters.get(i).type)
                {
                    this.reportError(functionCallExpression,
                                     "Expected type " + expectedParameters.get(i).type.name() + " for parameter " + (i + 1) +
                                     ", got " + actualType.name());
                }
            }
        }
        catch (SemanticException e)
        {
            this.reportError(functionCallExpression, e.getMessage());
        }
    }
}
