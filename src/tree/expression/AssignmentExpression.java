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
public class AssignmentExpression extends Expression {
    private final String identifier;
    private final Expression expression;
    
    public AssignmentExpression(int line, int column, String identifier, Expression expression)
    {
        super(line, column);
        
        this.identifier = identifier;
        this.expression = expression;
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public Expression getExpression()
    {
        return expression;
    }
    
    @Override
    public void accept(IVisitor visitor)
    {
        this.expression.accept(visitor);
        visitor.visit(this);
    }

    @Override
    public DataType getDataType(SymbolTable symbolTable) throws SemanticException
    {
        return this.expression.getDataType(symbolTable);
    }

    @Override
    public boolean isConstant()
    {
        return this.expression.isConstant();
    }

    @Override
    public Object getValue(SymbolTable symbolTable) throws SemanticException
    {
        return this.expression.getValue(symbolTable);
    }

    @Override
    public boolean hasSideEffects()
    {
        return true;
    }
}
