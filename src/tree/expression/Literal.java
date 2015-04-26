/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.expression;

import tree.SemanticException;
import tree.visitor.IVisitor;
import tree.SymbolTable;

/**
 *
 * @author Jakub
 */
public class Literal extends Expression {
    private final DataType dataType;
    private final Object value;
    
    public Literal(int line, int column, DataType dataType, Object value)
    {
        super(line, column);
        
        this.dataType = dataType;
        this.value = value;
    }

    public DataType getDataType()
    {
        return dataType;
    }

    public Object getValue()
    {
        return value;
    }

    @Override
    public void accept(IVisitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    public DataType getDataType(SymbolTable symbolTable)
    {
        return this.dataType;
    }

    @Override
    public boolean isConstant()
    {
        return true;
    }

    @Override
    public Object getValue(SymbolTable symbolTable) throws SemanticException
    {
        return this.value;
    }

    @Override
    public boolean hasSideEffects()
    {
        return false;
    }
}
