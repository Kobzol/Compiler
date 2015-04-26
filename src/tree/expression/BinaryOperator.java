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
public enum BinaryOperator {
    PLUS,
    MINUS,
    DIVIDE,
    MULTIPLY,
    REMAINDER,
    CONCAT,
    AND,
    OR,
    EQ,
    NE,
    LT,
    LE,
    GT,
    GE;
    
    public static BinaryOperator getFromString(String data)
    {
        switch (data)
        {
            case "+": return BinaryOperator.PLUS;
            case "-": return BinaryOperator.MINUS;
            case "/": return BinaryOperator.DIVIDE;
            case "*": return BinaryOperator.MULTIPLY;
            case "%": return BinaryOperator.REMAINDER;
            case ".": return BinaryOperator.CONCAT;
            case "&&": return BinaryOperator.AND;
            case "||": return BinaryOperator.OR;
            case "==": return BinaryOperator.EQ;
            case "!=": return BinaryOperator.NE;
            case "<": return BinaryOperator.LT;
            case "<=": return BinaryOperator.LE;
            case ">": return BinaryOperator.GT;
            case ">=": return BinaryOperator.GE;
        }
        
        return null;
    }
    
    public boolean isComparison()
    {
        return  this == AND ||
                this == OR ||
                this == EQ ||
                this == NE ||
                this == LT ||
                this == LE ||
                this == GT ||
                this == GE;
    }
}
