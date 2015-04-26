package interpret;


import java.util.ArrayList;
import tree.expression.DataType;
import tree.Variable;
import java.util.List;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jakub
 */
public class Interpreter {
    private final List<Variable> programStack = new ArrayList<Variable>();
    
    private final List<Instruction> instructions;
    private int instructionIndex = 0;
    
    private final Stack<Integer> stackFrames = new Stack<Integer>();
    private final int maxStackSize;
    
    public Interpreter(List<Instruction> instructions, int maxStackSize)
    {
        this.instructions = instructions;
        this.stackFrames.add(0);
        this.maxStackSize = maxStackSize;
    }
    
    public void Interpret() throws InterpretException
    {
        while (true)
        {
            Instruction inst = this.getNextInstruction();
            
            if (inst == null)
            {
                break;
            }
            
            this.handleInstruction(inst);
        }
    }
    
    private Instruction getNextInstruction()
    {
        if (this.instructionIndex >= this.instructions.size())
        {
            return null;
        }
        else return this.instructions.get(this.instructionIndex++);
    }
    
    private Variable pop() throws InterpretException
    {
        if (this.programStack.isEmpty())
        {
            throw new InterpretException("An attempt was made to pop value from an empty stack");
        }
        
        Variable variable = this.programStack.get(this.programStack.size() - 1);
        this.programStack.remove(this.programStack.size() - 1);
        return variable;
    }
    private Variable peek()
    {
        return this.programStack.get(this.programStack.size() - 1);
    }
    private void push(Variable variable)
    {
        this.programStack.add(variable);
    }
    private Variable getVariable(int address)
    {
        return this.programStack.get(this.stackFrames.peek() + address);
    }
    
    private void handleInstruction(Instruction instruction) throws InterpretException
    {
        switch (instruction.type)
        {
            case ADD:
            case SUB:
            case MUL:
            case DIV:
                this.handleArithmetic(instruction); break;
            case REM:
                this.handleRemainder(instruction); break;
            case CONCAT:
                this.handleConcat(instruction); break;
            case UNARY_MINUS:
                this.handleUnaryMinus(instruction); break;
            case NEGATE:
                this.handleNegate(instruction); break;
            case AND:
            case OR:
                this.handleLogicalOperator(instruction); break;
            case LT:
            case LE:
            case GT:
            case GE:
            case EQ:
            case NE:
                this.handleComparison(instruction); break;
            case DECLARE:
                this.handleDeclare(instruction); break;
            case PUSH:
                this.handlePush(instruction); break;
            case LOAD:
                this.handleLoad(instruction); break;
            case SAVE:
                this.handleSave(instruction); break;
            case WRITE:
                this.handleWrite(instruction); break;
            case READ:
                this.handleRead(instruction); break;
            case JMP:
            case FJMP:
                this.handleJump(instruction); break;
            case CREATE_FRAME:
                this.handleCreateFrame(instruction); break;
            case DESTROY_FRAME:
                this.handleDestroyFrame(instruction); break;
            case RETURN:
                this.handleReturn(instruction); break;
        }
    }
    
    private void handleArithmetic(Instruction instruction) throws InterpretException
    {
        Variable op1 = this.pop();
        Variable op2 = this.pop();
        
        if (VariableHelper.areArithmeticallyCompatible(op1, op2))
        {
            double result = 0.0;
            double val1 = op1.asNumber();
            double val2 = op2.asNumber();
            
            switch (instruction.type)
            {
                case ADD:
                    result = val1 + val2; break;
                case SUB:
                    result = val2 - val1; break;
                case MUL:
                    result = val1 * val2; break;
                case DIV:
                {
                    if (val1 == 0)
                    {
                        throw new InterpretException("Division by zero (" + op2.toString() + " / " + op1.toString() + ")");
                    }
                    result = val2 / val1; break;
                }
            }
            
            this.push(new Variable(op1.getDataType(), result));
        }
        else throw new InterpretException("Variables " + op1.toString() + " and " + op2.toString() + " cannot be arithmetically manipulated together");
    }
    private void handleRemainder(Instruction instruction) throws InterpretException
    {
        Variable op1 = this.pop();
        Variable op2 = this.pop();
        
        if (op1.isInteger() && op2.isInteger())
        {            
            int val1 = op1.asInteger();
            int val2 = op2.asInteger();
            
            this.push(new Variable(op1.getDataType(), val2 % val1));
        }
        else throw new InterpretException("Trying to apply modulo to " + op1.toString() + " and " + op2.toString());
    }
    private void handlePush(Instruction instruction) throws InterpretException
    {
        Variable variable = (Variable) instruction.arguments.get(0);
        this.push(variable);
    }
    private void handleWrite(Instruction instruction) throws InterpretException
    {
        int writeCount = Integer.parseInt(instruction.arguments.get(0).toString());
        
        for (int i = 0; i < writeCount; i++)
        {
            System.out.println("WRITE: " + this.pop().toString());
        }
    }
    private void handleDeclare(Instruction instruction) throws InterpretException
    {
        DataType type = (DataType) instruction.arguments.get(0);
        
        this.push(new Variable(type));
    }
    private void handleLoad(Instruction instruction) throws InterpretException
    {
        int address = Integer.parseInt(instruction.arguments.get(0).toString());
        this.push(this.getVariable(address));
    }
    private void handleSave(Instruction instruction) throws InterpretException
    {
        int address = Integer.parseInt(instruction.arguments.get(0).toString());
        Variable var = this.pop();
        Variable target = this.getVariable(address);
        target.setValue(var.getValue());
        this.push(target);
    }
    private void handleUnaryMinus(Instruction instruction) throws InterpretException
    {
        Variable var = this.pop();
        
        if (var.isNumeric())
        {
            var.setValue(-var.asNumber());
            this.push(var);
        }
        else throw new InterpretException("Trying to apply unary minus to " + var.toString());
    }
    private void handleNegate(Instruction instruction) throws InterpretException
    {
        Variable var = this.pop();
        
        if (var.isBoolean())
        {
            var.setValue(!var.asBoolean());
            this.push(var);
        }
        else throw new InterpretException("Trying to apply negation to " + var.toString());
    }
    private void handleConcat(Instruction instruction) throws InterpretException
    {
        Variable var1 = this.pop();
        Variable var2 = this.pop();
        
        if (var1.isString() && var2.isString())
        {
            this.push(new Variable(var1.getDataType(), var2.asString() + var1.asString()));
        }
        else throw new InterpretException("Trying to concatenate " + var1.toString() + " with " + var2.toString());
    }
    private void handleLogicalOperator(Instruction instruction) throws InterpretException
    {
        Variable var1 = this.pop();
        Variable var2 = this.pop();
        
        if (var1.isBoolean() && var2.isBoolean())
        {
            boolean bool1 = var1.asBoolean();
            boolean bool2 = var2.asBoolean();
            boolean result = bool1 && bool2;
            
            if (instruction.type.equals(InstructionType.OR))
            {
                result = bool1 || bool2;
            }
            
            this.push(new Variable(var1.getDataType(), result));
        }
        else throw new InterpretException("Trying to apply logical operation " + instruction.type.name() + " to " + var1.toString() + " and " + var2.toString());
    }
    private void handleComparison(Instruction instruction) throws InterpretException
    {
        Variable var1 = this.pop();
        Variable var2 = this.pop();
        
        if (VariableHelper.haveSameType(var1, var2))
        {
            boolean result = VariableHelper.performComparison(instruction.type, var2, var1);
            
            this.push(new Variable(DataType.BOOLEAN, result));
        }
        else throw new InterpretException("Trying to apply logical comparison " + instruction.type.name() + " to " + var1.toString() + " and " + var2.toString());
    }
    private void handleRead(Instruction instruction) throws InterpretException
    {
        int address = Integer.parseInt(instruction.arguments.get(0).toString());
        
        Variable current = this.getVariable(address);

        System.out.print("ENTER VALUE (" + current.getDataType().name() + "):");
        Variable input = VariableHelper.readVariable(System.in, current.getDataType());

        if (input != null)
        {
            current.setValue(input.getValue());
        }
        else throw new InterpretException("Bad input");
    }
    private void handleJump(Instruction instruction) throws InterpretException
    {
        if (instruction.type == InstructionType.FJMP)
        {
            Variable var = this.pop();
            
            if (var.isBoolean())
            {
                if (var.asBoolean())
                {
                    return;
                }
            }
            else throw new InterpretException("Condition for conditional jump must have boolean type");
        }
        
        int targetLabel = Integer.parseInt(instruction.arguments.get(0).toString());
        this.instructionIndex = targetLabel;
    }
    private void handleCreateFrame(Instruction instruction) throws InterpretException
    {
        if (this.stackFrames.size() == this.maxStackSize)
        {
            throw new InterpretException("Stack overflow");
        }
        
        int parameterCount = Integer.parseInt(instruction.arguments.get(0).toString());
        this.stackFrames.push(this.programStack.size() - parameterCount);
    }
    private void handleDestroyFrame(Instruction instruction) throws InterpretException
    {
        this.stackFrames.pop();
    }
    private void handleReturn(Instruction instruction) throws InterpretException
    {
        int targetAddress = this.getVariable(0).asInteger();
        this.instructionIndex = targetAddress;
    }
}
