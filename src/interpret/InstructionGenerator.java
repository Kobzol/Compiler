package interpret;


import tree.expression.DataType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import tree.Variable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jakub
 */
public class InstructionGenerator {
    private final List<Instruction> instructions = new ArrayList<Instruction>();
    private final Map<UUID, List<Integer>> labelIndeces = new HashMap<UUID, List<Integer>>();
    
    public int getInstructionIndex()
    {
        return this.instructions.size();
    }
    
    private void addInstruction(Instruction instruction)
    {
        this.instructions.add(instruction);
    }
    
    private List<Object> createArguments(Object... args)
    {
        return Arrays.asList(args);
    }
    
    public List<Instruction> getInstructions()
    {
        return this.instructions;
    }
    public Instruction getInstruction(int instructionIndex)
    {
        return this.instructions.get(instructionIndex);
    }
    
    public void declareVariable(DataType type)
    {
        this.addInstruction(new Instruction(InstructionType.DECLARE, this.createArguments(type)));
    }
    
    public void readVariable(int address)
    {
        this.addInstruction(new Instruction(InstructionType.READ, this.createArguments(address)));
    }
    public void writeVariable(int writeCount)
    {
        this.addInstruction(new Instruction(InstructionType.WRITE, this.createArguments(writeCount)));
    }
     
    public void saveVariable(int address)
    {
        this.addInstruction(new Instruction(InstructionType.SAVE, this.createArguments(address)));
    }
    public void loadVariable(int address)
    {
        this.addInstruction(new Instruction(InstructionType.LOAD, this.createArguments(address)));
    }
    public void pushLiteral(DataType type, Object value)
    {
        if (type.equals(DataType.STRING))
        {
            value = value.toString().replace("\"", "");
        }
        
        this.addInstruction(new Instruction(InstructionType.PUSH, this.createArguments(new Variable(type, value))));
    }
    
    public void addOperator(InstructionType type)
    {
        this.addInstruction(new Instruction(type));
    }
    
    public void negate()
    {
        this.addInstruction(new Instruction(InstructionType.NEGATE));
    }
    public void unaryMinus()
    {
        this.addInstruction(new Instruction(InstructionType.UNARY_MINUS));
    }
    
    public void jump(UUID uuid, boolean conditional)
    {
        int instructionIndex = this.instructions.size();
        this.instructions.add(new Instruction(conditional ? InstructionType.FJMP : InstructionType.JMP));
        
        this.addInstructionLabel(uuid, instructionIndex);
    }
    public void jump(int instructionIndex, boolean conditional)
    {
        this.instructions.add(new Instruction(conditional ? InstructionType.FJMP : InstructionType.JMP, this.createArguments(instructionIndex)));
    }
    
    public void label(UUID uuid)
    {
        int currentInstruction = this.instructions.size();
        
        if (this.labelIndeces.containsKey(uuid))
        {
            for (Integer index : this.labelIndeces.get(uuid))
            {
                this.instructions.get(index).arguments.add(currentInstruction);
            }
        }
    }
    
    private void addInstructionLabel(UUID uuid, int instructionIndex)
    {
        if (!this.labelIndeces.containsKey(uuid))
        {
            this.labelIndeces.put(uuid, new ArrayList<Integer>());
        }
        
        this.labelIndeces.get(uuid).add(instructionIndex);
    }
    
    public void generateReturn()
    {
        this.instructions.add(new Instruction(InstructionType.RETURN));
    }
    
    public void createStackFrame(int parameterCount)
    {
        this.instructions.add(new Instruction(InstructionType.CREATE_FRAME, this.createArguments(parameterCount)));
    }
    public void destroyStackFrame()
    {
        this.instructions.add(new Instruction(InstructionType.DESTROY_FRAME));
    }
    
    public int callerAddress()
    {
        this.instructions.add(new Instruction(InstructionType.PUSH, this.createArguments(new Variable(DataType.INTEGER, -1))));
        return this.getInstructionIndex() - 1;
    }
}
