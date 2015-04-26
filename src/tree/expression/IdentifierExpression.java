/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.expression;

import tree.visitor.IVisitor;
import tree.SemanticException;
import tree.SymbolTable;

/**
 *
 * @author Jakub
 */
public class IdentifierExpression extends Expression {
    private final String identifier;
    
    public IdentifierExpression(int line, int column, String identifier)
    {
        super(line, column);
        
        this.identifier = identifier;
    }

    public String getIdentifier()
    {
        return identifier;
    }

    @Override
    public void accept(IVisitor visitor)
    {
        visitor.visit(this);
    }   

    @Override
    public DataType getDataType(SymbolTable symbolTable) throws SemanticException
    {
        if (!symbolTable.hasIdentifier(this.identifier))
        {
            throw new SemanticException("Symbol with name " + this.identifier + " does not exist");
        }
        
        return symbolTable.getVariable(this.identifier).getVariable().getDataType();
    }

    @Override
    public boolean isConstant()
    {
        return false;
    }

    @Override
    public Object getValue(SymbolTable symbolTable) throws SemanticException
    {
        return null;
    }

    @Override
    public boolean hasSideEffects()
    {
        return false;
    }
}
