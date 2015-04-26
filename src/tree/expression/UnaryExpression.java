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
public class UnaryExpression extends Expression {
    private final Expression expression;
    private final UnaryOperator unaryOperator;
    
    public UnaryExpression(int line, int column, Expression expression, UnaryOperator unaryOperator) {
        super(line, column);
        
        this.expression = expression;
        this.unaryOperator = unaryOperator;
    }

    public Expression getExpression()
    {
        return expression;
    }

    public UnaryOperator getUnaryOperator()
    {
        return unaryOperator;
    }
    
    @Override
    public void accept(IVisitor visitor)
    {
        visitor.visit(this);
    }  

    @Override
    public DataType getDataType(SymbolTable symbolTable) throws SemanticException
    {
        return this.expression.getDataType(symbolTable);
    }

    @Override
    public boolean isConstant()
    {
        return this.expression.isConstant();
    }

    @Override
    public Object getValue(SymbolTable symbolTable) throws SemanticException
    {
        if (this.expression.isConstant())
        {
            return VariableHelper.performUnaryOperation(new Variable(this.expression.getDataType(symbolTable), this.expression.getValue(symbolTable)), this.unaryOperator);
        }
        else return null;
    }

    @Override
    public boolean hasSideEffects()
    {
        return this.expression.hasSideEffects();
    }
}
