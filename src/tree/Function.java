/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.util.List;
import parser.Symbol;
import tree.expression.DataType;

/**
 *
 * @author Jakub
 */
public class Function {
    private final String name;
    private final DataType returnType;
    private final List<Symbol> parameters;
    
    public Function(String name, DataType returnType, List<Symbol> parameters)
    {
        this.name = name;
        this.returnType = returnType;
        this.parameters = parameters;
    }

    public String getName()
    {
        return name;
    }

    public DataType getReturnType()
    {
        return returnType;
    }

    public List<Symbol> getParameters()
    {
        return parameters;
    }
    
    public void addParameter(Symbol symbol)
    {
        this.parameters.add(symbol);
    }
}
