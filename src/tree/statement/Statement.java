/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.statement;

import tree.Node;

/**
 *
 * @author Jakub
 */
public abstract class Statement extends Node {
    public Statement(int line, int column)
    {
        super(line, column);
    }
    
    public boolean containsFlowBreak()
    {
        return false;
    }
}
