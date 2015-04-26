/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.statement;

import java.util.ArrayList;
import java.util.List;
import parser.Symbol;
import tree.expression.DataType;
import tree.expression.Expression;
import tree.visitor.IVisitor;

/**
 *
 * @author Jakub
 */
public class FunctionStatement extends Statement {
    private final List<Symbol> parameters = new ArrayList<Symbol>();
    private final DataType returnType;
    private final String functionName;
    private BlockStatement blockStatement;
    
    public FunctionStatement(int line, int column, DataType returnType, String functionName)
    {
        super(line, column);
        
        this.returnType = returnType;
        this.functionName = functionName;
    }

    public List<Symbol> getParameters()
    {
        return parameters;
    }

    public DataType getReturnType()
    {
        return returnType;
    }

    public String getFunctionName()
    {
        return functionName;
    }

    public BlockStatement getBlockStatement()
    {
        return blockStatement;
    }
    
    public void addParameter(Symbol symbol)
    {
        this.parameters.add(symbol);
    }
    public void setBlockStatement(BlockStatement blockStatement)
    {
        this.blockStatement = blockStatement;
    }

    @Override
    public void accept(IVisitor visitor)
    {
        visitor.visit(this);
    }
}
