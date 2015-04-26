package parser;


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
public class Symbol {
    public Object value;
    public String identifier;
    public DataType type;

    public Symbol(String identifier, DataType type) {
        this.identifier = identifier;
        this.type = type;
        this.value = type.getDefaultValue();
    }
}
