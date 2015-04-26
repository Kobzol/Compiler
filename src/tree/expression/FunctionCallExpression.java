/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.expression;

import java.util.ArrayList;
import java.util.List;
import tree.SemanticException;
import tree.SymbolTable;
import tree.visitor.IVisitor;

/**
 *
 * @author Jakub
 */
public class FunctionCallExpression extends Expression {
    private final List<Expression> parameters = new ArrayList<Expression>();
    private final String functionName;
    
    public FunctionCallExpression(int line, int column, String functionName)
    {
        super(line, column);
        
        this.functionName = functionName;
    }
    
    public List<Expression> getParameters()
    {
        return parameters;
    }

    public String getFunctionName()
    {
        return functionName;
    }
    
    public void addParameter(Expression parameter)
    {
        this.parameters.add(parameter);
    }

    @Override
    public DataType getDataType(SymbolTable symbolTable) throws SemanticException
    {
        return symbolTable.getFunction(this.functionName).getReturnType();
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
        return true;
    }

    @Override
    public void accept(IVisitor visitor)
    {
        visitor.visit(this);
    }    
}
