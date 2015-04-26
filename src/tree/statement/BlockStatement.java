/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.statement;

import java.util.ArrayList;
import java.util.List;
import tree.visitor.IVisitor;

/**
 *
 * @author Jakub
 */
public class BlockStatement extends Statement {
    private final List<Statement> statements = new ArrayList<Statement>();
    
    public BlockStatement(int line, int column)
    {
        super(line, column);
    }
    
    public List<Statement> getStatements()
    {
        return this.statements;
    }
    public void addStatement(Statement statement)
    {
        this.statements.add(statement);
    }

    @Override
    public void accept(IVisitor visitor)
    {
        for (Statement st : this.statements)
        {
            st.accept(visitor);
        }
        
        visitor.visit(this);
    }

    @Override
    public boolean containsFlowBreak()
    {
        for (Statement statement : this.statements)
        {
            if (statement.containsFlowBreak())
            {
                return true;
            }
        }
        
        return false;
    }
}
