/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.statement;

import java.util.ArrayList;
import java.util.List;
import tree.expression.Expression;
import tree.visitor.IVisitor;

/**
 *
 * @author Jakub
 */
public class WriteStatement extends Statement {
    private final List<Expression> expressions = new ArrayList<Expression>();
    
    public WriteStatement(int line, int column)
    {
        super(line, column);
    }

    public List<Expression> getExpressions()
    {
        return expressions;
    }
    
    public void addExpression(Expression expression)
    {
        this.expressions.add(expression);
    }

    @Override
    public void accept(IVisitor visitor)
    {
        for (int i = this.expressions.size() - 1; i >= 0; i--)
        {
            this.expressions.get(i).accept(visitor);
        }
        
        visitor.visit(this);
    }
}
