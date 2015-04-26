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
    private UnaryOperator preOperator = UnaryOperator.NOOP;
    private UnaryOperator postOperator = UnaryOperator.NOOP;
    
    public UnaryExpression(int line, int column, Expression expression) {
        super(line, column);
        
        this.expression = expression;
    }

    public Expression getExpression()
    {
        return expression;
    }

    public UnaryOperator getPreOperator()
    {
        return preOperator;
    }
    
    public UnaryOperator getPostOperator()
    {
        return postOperator;
    }
    
    public void setPreOperator(UnaryOperator preOperator)
    {
        this.preOperator = preOperator;
    }
    public void setPostOperator(UnaryOperator postOperator)
    {
        this.postOperator = postOperator;
    }
    
    public boolean hasTwoOperators()
    {
        return this.preOperator != UnaryOperator.NOOP && this.postOperator != UnaryOperator.NOOP;
    }
    public UnaryOperator getUnaryOperator()
    {
        if (this.preOperator != UnaryOperator.NOOP)
        {
            return this.preOperator;
        }
        else return this.postOperator;
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
            return VariableHelper.performUnaryOperation(new Variable(this.expression.getDataType(symbolTable), this.expression.getValue(symbolTable)), this.getUnaryOperator());
        }
        else return null;
    }

    @Override
    public boolean hasSideEffects()
    {
        return this.expression.hasSideEffects() || this.postOperator != UnaryOperator.NOOP;
    }
}
