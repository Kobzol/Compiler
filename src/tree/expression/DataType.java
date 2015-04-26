package tree.expression;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jakub
 */
public enum DataType {
    BOOLEAN,
    INTEGER,
    FLOAT,
    STRING;

    public Object getDefaultValue() {
        if (this == BOOLEAN) return false;
        if (this == INTEGER) return 0;
        if (this == FLOAT) return 0.0f;
        if (this == STRING) return "";

        return null;
    }
    public boolean canContain(String value) {
        try
        {
            if (this == BOOLEAN)
            {
                Boolean.parseBoolean(value);
                return true;
            }
            else if (this == INTEGER)
            {
                Integer.parseInt(value);
                return true;
            }
            else if (this == FLOAT)
            {
                Float.parseFloat(value);
                return true;
            }
            else if (this == STRING)
            {
                return true;
            }
        }
        catch (Exception e)
        {
            return false;
        }
        
        return false;
    }

    public static DataType fromObject(Object value) {
        if (value instanceof Boolean) return BOOLEAN;
        if (value instanceof Integer) return INTEGER;
        if (value instanceof Float) return FLOAT;
        if (value instanceof String) return STRING;

        return INTEGER;
    }
    public static DataType fromString(String input) {
        if (input.equals("boolean")) return BOOLEAN;
        if (input.equals("int")) return INTEGER;
        if (input.equals("float")) return FLOAT;
        if (input.equals("String")) return STRING;
        
        return INTEGER;
    }
    
    public boolean isNumeric()
    {
        return this == INTEGER || this == FLOAT;
    }
}
