/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.expression;

import tree.Node;
import tree.SemanticException;
import tree.SymbolTable;

/**
 *
 * @author Jakub
 */
public abstract class Expression extends Node {
    public Expression(int line, int column)
    {
        super(line, column);
    }
    
    public abstract DataType getDataType(SymbolTable symbolTable) throws SemanticException;
    public abstract boolean isConstant();
    public abstract Object getValue(SymbolTable symbolTable) throws SemanticException;
    public abstract boolean hasSideEffects();
}
