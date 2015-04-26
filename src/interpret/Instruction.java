package interpret;


import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jakub
 */
public class Instruction {
    public InstructionType type;
    public List<Object> arguments;
    
    public Instruction(InstructionType type)
    {
        this(type, new ArrayList<Object>());
    }
    public Instruction(InstructionType type, List<Object> arguments)
    {
        this.type = type;
        this.arguments = arguments;
    }
    
    @Override
    public String toString()
    {
        StringBuilder name = new StringBuilder();
        name.append(this.type.name());
        name.append(": ");
        
        for (Object argument : this.arguments)
        {
            name.append(argument);
            name.append(", ");
        }
        
        name.delete(name.length() - 2, name.length());
        
        return name.toString();
    }
}
