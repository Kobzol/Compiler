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
    INCREMENT,
    DECREMENT,
    NOOP;
    
    public static UnaryOperator getFromString(String data)
    {
        if (data == null || data.equals(""))
        {
            return UnaryOperator.NOOP;
        }
        
        switch (data)
        {
            case "-": return MINUS; 
            case "!": return NEGATE;
            case "++": return INCREMENT;
            case "--": return DECREMENT;
        }
        
        return null;
    }
}
