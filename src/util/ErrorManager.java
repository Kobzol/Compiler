/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jakub
 */
public class ErrorManager {
    private final List<Error> errors = new ArrayList<Error>();

    public List<Error> getErrors()
    {
        return errors;
    }
    
    public void addError(Error error)
    {
        this.errors.add(error);
    }
    public void addError(int line, int column, String message)
    {
        this.addError(new Error(line, column, message));
    }
    
    public void clearErrors()
    {
        this.errors.clear();
    }
}
