/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import tree.expression.DataType;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import parser.Symbol;

/**
 *
 * @author Jakub
 */
public class SymbolTable {
    public static class VariableInfo
    {
        private final Variable variable;
        private final int address;
        
        public VariableInfo(Variable variable, int address)
        {
            this.variable = variable;
            this.address = address;
        }
        
        public Variable getVariable()
        {
            return this.variable;
        }
        
        public int getAddress()
        {
            return this.address;
        }
    }
    
    private final Stack<HashMap<String, VariableInfo>> symbols = new Stack<HashMap<String, VariableInfo>>();
    private final Map<String, Function> functions = new HashMap<String, Function>();
    
    public SymbolTable()
    {
        this.symbols.push(new HashMap<String, VariableInfo>());
    }
    
    private HashMap<String, VariableInfo> getVariables()
    {
        return this.symbols.peek();
    }
    
    public VariableInfo addVariable(String identifier, DataType dataType) throws SemanticException
    {
        if (this.hasIdentifier(identifier))
        {
            throw new SemanticException("Symbol with name " + identifier + " already exists");
        }
        
        VariableInfo variable = new VariableInfo(new Variable(dataType), this.getVariables().size());
        this.getVariables().put(identifier, variable);
        
        return variable;
    }
    
    public VariableInfo getVariable(String identifier) throws SemanticException
    {
        if (!this.hasIdentifier(identifier))
        {
            throw new SemanticException("Symbol with name " + identifier + " does not exist");
        }
        
        return this.getVariables().get(identifier);
    }
    
    public boolean hasIdentifier(String identifier)
    {
        return this.getVariables().containsKey(identifier);
    }
    
    public void addFunction(Function function) throws SemanticException
    {
        if (this.hasFunction(function.getName()))
        {
            throw new SemanticException("Function with name " + function.getName() + " already exists");
        }
        
        this.functions.put(function.getName(), function);
    }
    
    public Function getFunction(String identifier) throws SemanticException
    {
        if (!this.hasFunction(identifier))
        {
            throw new SemanticException("No function with name " + identifier + " exists");
        }
        
        return this.functions.get(identifier);
    }
    
    public boolean hasFunction(String name)
    {
        return this.functions.containsKey(name);
    }
    
    public void addFunctionParameters(Function function)
    {
        this.symbols.push(new HashMap<String, VariableInfo>());
        for (Symbol parameter : function.getParameters())
        {
            this.getVariables().put(parameter.identifier, new VariableInfo(new Variable(parameter.type), this.getVariables().size()));
        } 
    }
    public void removeFunctionParameters(Function function)
    {
        this.symbols.pop();
    }
}
