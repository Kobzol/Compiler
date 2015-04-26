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
public class ReadStatement extends Statement {
    private final List<String> identifiers = new ArrayList<String>();
    
    public ReadStatement(int line, int column)
    {
        super(line, column);
    }

    public List<String> getIdentifiers()
    {
        return identifiers;
    }
    
    public void addIdentifier(String identifier)
    {
        this.identifiers.add(identifier);
    }

    @Override
    public void accept(IVisitor visitor)
    {
        visitor.visit(this);
    }
}
