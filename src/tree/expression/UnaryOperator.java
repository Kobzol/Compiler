/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.expression;

/**
 *
 * @author Jakub
 */
public enum UnaryOperator {
    MINUS,
    NEGATE,
    NOOP;
    
    public static UnaryOperator getFromString(String data)
    {
        if (data == null || data.equals(""))
        {
            return UnaryOperator.NOOP;
        }
        else if (data.equals("-"))
        {
            return UnaryOperator.MINUS;
        }
        else if (data.equals("!"))
        {
            return UnaryOperator.NEGATE;
        }
        
        return null;
    }
}
