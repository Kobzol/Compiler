/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.expression;

import tree.visitor.IVisitor;
import tree.SemanticException;
import tree.SymbolTable;

/**
 *
 * @author Jakub
 */
public class ConditionalExpression extends Expression {
    private final Expression expression;
    private final Expression firstAlternative;
    private final Expression secondAlternative;
    
    public ConditionalExpression(int line, int column, Expression expression, Expression firstAlternative, Expression secondAlternative)
    {
        super(line, column);
        
        this.expression = expression;
        this.firstAlternative = firstAlternative;
        this.secondAlternative = secondAlternative;
    }

    public Expression getExpression()
    {
        return expression;
    }

    public Expression getFirstAlternative()
    {
        return firstAlternative;
    }

    public Expression getSecondAlternative()
    {
        return secondAlternative;
    }

    @Override
    public void accept(IVisitor visitor)
    {       
        visitor.visit(this);
    }

    @Override
    public DataType getDataType(SymbolTable symbolTable) throws SemanticException
    {
        if (this.firstAlternative == null)
        {
            return this.expression.getDataType(symbolTable);
        }
        else return this.firstAlternative.getDataType(symbolTable);
    }

    @Override
    public boolean isConstant()
    {
        return this.expression.isConstant() && this.firstAlternative.isConstant() && this.secondAlternative.isConstant();
    }

    @Override
    public Object getValue(SymbolTable symbolTable) throws SemanticException
    {
        if (this.firstAlternative == null)
        {
            return this.expression.getValue(symbolTable);
        }
        else
        {
            if (this.isConstant())
            {
                if (Boolean.parseBoolean(this.expression.getValue(symbolTable).toString()))
                {
                    return this.firstAlternative.getValue(symbolTable);
                }
                else return this.secondAlternative.getValue(symbolTable);
            }
            else return null;
        }
    }

    @Override
    public boolean hasSideEffects()
    {
        if (this.firstAlternative == null)
        {
            return this.expression.hasSideEffects();
        }
        else    return this.expression.hasSideEffects() ||
                this.firstAlternative.hasSideEffects() ||
                this.secondAlternative.hasSideEffects();
    }
}
