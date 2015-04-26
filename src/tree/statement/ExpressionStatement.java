/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.statement;

import tree.expression.Expression;
import tree.visitor.IVisitor;

/**
 *
 * @author Jakub
 */
public class ExpressionStatement extends Statement {
    private final Expression expression;
    
    public ExpressionStatement(int line, int column, Expression expression)
    {
        super(line, column);
        
        this.expression = expression;
    }

    public Expression getExpression()
    {
        return expression;
    }

    @Override
    public void accept(IVisitor visitor)
    {
        visitor.visit(this);
    } 
}
