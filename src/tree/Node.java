/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import tree.visitor.IVisitor;

/**
 *
 * @author Jakub
 */
public abstract class Node {
    private final int line;
    private final int column;
    private Node parent;
    
    public Node(int line, int column)
    {
        this.line = line;
        this.column = column;
    }
    
    public int getLine()
    {
        return this.line;
    }
    public int getColumn()
    {
        return this.column;
    }
    public Node getParent()
    {
        return this.parent;
    }
    public void setParent(Node parent)
    {
        this.parent = parent;
    }
    
    public abstract void accept(IVisitor visitor);
}
