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
public class WhileStatement extends Statement {
    private final Expression condition;
    private final BlockStatement statements;
    
    public WhileStatement(int line, int column, Expression condition, BlockStatement statements)
    {
        super(line, column);
        
        this.condition = condition;
        this.statements = statements;
    }

    public Expression getCondition()
    {
        return condition;
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
