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
public class ForStatement extends Statement {
    private final Expression initializer;
    private final Expression condition;
    private final Expression increment;
    private final BlockStatement statements;

    public ForStatement(int line, int column, Expression initializer, Expression condition, Expression increment, BlockStatement statements)
    {
        super(line, column);
        
        this.initializer = initializer;
        this.condition = condition;
        this.increment = increment;
        this.statements = statements;
    }

    public Expression getInitializer()
    {
        return initializer;
    }

    public Expression getCondition()
    {
        return condition;
    }

    public Expression getIncrement()
    {
        return increment;
    }

    public BlockStatement getStatements()
    {
        return statements;
    }

    @Override
    public void accept(IVisitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    public boolean containsFlowBreak()
    {
        return this.statements.containsFlowBreak();
    }
}
