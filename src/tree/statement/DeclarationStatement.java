/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.statement;

import tree.expression.DataType;
import java.util.ArrayList;
import java.util.List;
import tree.expression.Expression;
import tree.visitor.IVisitor;

/**
 *
 * @author Jakub
 */
public class DeclarationStatement extends Statement {
    private final List<String> identifiers = new ArrayList<String>();
    private final List<Expression> initializers = new ArrayList<Expression>();
    private final DataType dataType;
    
    public DeclarationStatement(int line, int column, DataType dataType)
    {
        super(line, column);
        
        this.dataType = dataType;
    }

    public List<String> getIdentifiers()
    {
        return identifiers;
    }
    
    public List<Expression> getInitializers()
    {
        return this.initializers;
    }

    public DataType getDataType()
    {
        return dataType;
    }
    
    public void addIdentifier(String identifier, Expression initializer)
    {
        this.identifiers.add(identifier);
        this.initializers.add(initializer);
    }

    @Override
    public void accept(IVisitor visitor)
    {
        visitor.visit(this);
    } 
}
