package tree;

import tree.expression.DataType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jakub
 */
public final class Variable {
    private static Object getDefaultValue(DataType dataType)
    {
        switch (dataType)
        {
            case BOOLEAN:
                return false;
            case INTEGER:
            case FLOAT:
                return 0;
            case STRING:
                return "";
        }
        
        return null;
    }
    
    private final DataType dataType;
    private Object value;
    
    public Variable(DataType dataType)
    {
        this.dataType = dataType;
        this.setValue(Variable.getDefaultValue(dataType));
    }
    public Variable(DataType dataType, Object value) {
        this.dataType = dataType;
        this.setValue(value);
    }
    
    public DataType getDataType()
    {
        return this.dataType;
    }
    public Object getValue()
    {
        return this.value;
    }
    public void setValue(Object value)
    {
        switch (this.getDataType())
        {
            case INTEGER:
            {
                double num = Double.parseDouble(value.toString());
                value = (int) num;
            }
            break;
            case FLOAT:
            {
                double num = Double.parseDouble(value.toString());
                value = (float) num;
            }
            break;   
            case BOOLEAN:
                value = Boolean.parseBoolean(value.toString()); break;
        }
        
        this.value = value;
    }
    
    public double asNumber()
    {
        return Double.parseDouble(this.value.toString());
    }
    public int asInteger()
    {
        return Integer.parseInt(this.value.toString());
    }
    public float asFloat()
    {
        return Float.parseFloat(this.value.toString());
    }
    public String asString()
    {
        return this.value.toString();
    }
    public boolean asBoolean()
    {
        return Boolean.parseBoolean(this.value.toString());
    }
    
    public boolean isNumeric()
    {
        return this.dataType == DataType.INTEGER || this.dataType == DataType.FLOAT;
    }
    public boolean isInteger()
    {
        return this.dataType == DataType.INTEGER;
    }
    public boolean isBoolean()
    {
        return this.dataType == DataType.BOOLEAN;
    }
    public boolean isString()
    {
        return this.dataType == DataType.STRING;
    }
    
    @Override
    public String toString()
    {
        String val = this.getValue().toString();
        
        if (this.getDataType().equals(DataType.STRING))
        {
            val = "\"" + val + "\"";
        }
        
        return this.getDataType().name() + ": " + val;
    }
}
