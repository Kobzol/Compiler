/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Jakub
 */
public class Error {
    private final int line;
    private final int column;
    private final String message;
    
    public Error(int line, int column, String message)
    {
        this.line = line;
        this.column = column;
        this.message = message;
    }

    public int getLine()
    {
        return line;
    }

    public int getColumn()
    {
        return column;
    }

    public String getMessage()
    {
        return message;
    }
    
    @Override
    public String toString()
    {
        return "Error on line " + this.line + ", column " + this.column + ": " + this.message;
    }
}
