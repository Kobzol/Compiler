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
public class IfStatement extends Statement {
    private final List<Expression> conditions = new ArrayList<Expression>();
    private final List<BlockStatement> statementBlocks = new ArrayList<BlockStatement>();
    private BlockStatement elseBlock;
    
    public IfStatement(int line, int column)
    {
        super(line, column);
    }

    public List<Expression> getConditions()
    {
        return conditions;
    }

    public List<BlockStatement> getStatementBlocks()
    {
        return statementBlocks;
    }

    public BlockStatement getElseBlock()
    {
        return elseBlock;
    }
    
    public void addBranch(Expression condition, BlockStatement blockStatement)
    {
        this.conditions.add(condition);
        this.statementBlocks.add(blockStatement);
    }
    public void setElseBlock(BlockStatement elseBlock)
    {
        this.elseBlock = elseBlock;
    }

    @Override
    public void accept(IVisitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    public boolean containsFlowBreak()
    {
        for (Statement st : this.statementBlocks)
        {
            if (st.containsFlowBreak())
            {
                return true;
            }
        }
        
        if (this.elseBlock != null)
        {
            return this.elseBlock.containsFlowBreak();
        }
        else return false;
    }  
}
