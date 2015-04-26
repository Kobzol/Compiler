/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.statement;

import tree.visitor.IVisitor;

/**
 *
 * @author Jakub
 */
public class EmptyStatement extends Statement {
    public EmptyStatement(int line, int column)
    {
        super(line, column);
    }

    @Override
    public void accept(IVisitor visitor)
    {
        visitor.visit(this);
    }
}
