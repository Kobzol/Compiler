/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.expression;

import interpret.VariableHelper;
import tree.visitor.IVisitor;
import tree.SemanticException;
import tree.SymbolTable;
import tree.Variable;

/**
 *
 * @author Jakub
 */
public class BinaryExpression extends Expression {
    private final Expression leftOperand;
    private final Expression rightOperand;
    private final BinaryOperator binaryOperator;
    
    public BinaryExpression(int line, int column, Expression leftOperand, Expression rightOperand, BinaryOperator binaryOperator) {
        super(line, column);
        
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.binaryOperator = binaryOperator;
    }

    public Expression getLeftOperand()
    {
        return leftOperand;
    }

    public Expression getRightOperand()
    {
        return rightOperand;
    }

    public BinaryOperator getBinaryOperator()
    {
        return binaryOperator;
    }

    @Override
    public void accept(IVisitor visitor)
    {
        visitor.visit(this);
    } 

    @Override
    public DataType getDataType(SymbolTable symbolTable) throws SemanticException
    {
        if (this.binaryOperator.isComparison())
        {
            return DataType.BOOLEAN;
        }
        else return this.leftOperand.getDataType(symbolTable);
    }

    @Override
    public boolean isConstant()
    {
        return this.leftOperand.isConstant() && this.rightOperand.isConstant();
    }

    @Override
    public Object getValue(SymbolTable symbolTable) throws SemanticException
    {
        if (this.isConstant())
        {
            Object val1 = this.leftOperand.getValue(symbolTable);
            Object val2 = this.rightOperand.getValue(symbolTable);
            DataType type1 = this.leftOperand.getDataType(symbolTable);
            DataType type2 = this.rightOperand.getDataType(symbolTable);

            return VariableHelper.performBinaryOperation(new Variable(type1, val1), new Variable(type2, val2), this.binaryOperator);
        }
        else return null;
    }

    @Override
    public boolean hasSideEffects()
    {
        return this.leftOperand.hasSideEffects() || this.rightOperand.hasSideEffects();
    }
}
