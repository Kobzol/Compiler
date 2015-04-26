package interpret;

import tree.expression.DataType;
import tree.Variable;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import tree.expression.BinaryOperator;
import tree.expression.UnaryOperator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jakub
 */
public class VariableHelper {
    public static boolean areArithmeticallyCompatible(Variable v1, Variable v2)
    {
        if (v1.isNumeric() && v2.isNumeric())
        {
            return v1.getDataType().equals(v2.getDataType());
        }
        else return false;
    }
    public static boolean haveSameType(Variable v1, Variable v2)
    {
        return v1.getDataType().equals(v2.getDataType());
    }
    
    public static Variable readVariable(InputStream is, DataType dataType)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            
            switch (dataType)
            {
                case INTEGER: return new Variable(dataType, Integer.parseInt(line));
                case FLOAT: return new Variable(dataType, Float.parseFloat(line));
                case BOOLEAN: return new Variable(dataType, Boolean.parseBoolean(line));
                case STRING: return new Variable(dataType, line);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static Object performBinaryOperation(Variable var1, Variable var2, BinaryOperator binaryOperator)
    {
        DataType type = var1.getDataType();
        
        switch (type)
        {
            case INTEGER:
            case FLOAT:
                return VariableHelper.performNumericBinaryOperation(var1, var2, binaryOperator);
            case BOOLEAN:
                return VariableHelper.performBooleanBinaryOperation(var1, var2, binaryOperator);
            case STRING:
                return VariableHelper.performStringBinaryOperation(var1, var2, binaryOperator);
        }
        
        return null;
    }
    
    private static InstructionType getComparisonTypeByOperator(BinaryOperator operator)
    {
        switch (operator)
        {
            case LT: return InstructionType.LT;
            case LE: return InstructionType.LE;
            case GT: return InstructionType.GT;
            case GE: return InstructionType.GE;
            case EQ: return InstructionType.EQ;
            case NE: return InstructionType.NE;
        }
        
        return null;
    }
    
    private static Object performNumericBinaryOperation(Variable var1, Variable var2, BinaryOperator binaryOperator)
    {
        float val1 = var1.asFloat();
        float val2 = var2.asFloat();
        DataType type = var1.getDataType();
        
        switch (binaryOperator)
        {
            case PLUS:
                return VariableHelper.normalize(val1 + val2, type);
            case MINUS:
                return VariableHelper.normalize(val1 - val2, type);
            case DIVIDE:
                return VariableHelper.normalize(val1 / val2, type);
            case MULTIPLY:
                return VariableHelper.normalize(val1 * val2, type);
            case REMAINDER:
                return (int) val1 % (int) val2;
            default:
                return VariableHelper.performComparison(VariableHelper.getComparisonTypeByOperator(binaryOperator), var1, var2);
        }
    }
    private static Object performBooleanBinaryOperation(Variable var1, Variable var2, BinaryOperator binaryOperator)
    {
        boolean val1 = var1.asBoolean();
        boolean val2 = var2.asBoolean();
        
        switch (binaryOperator)
        {
            case AND:
                return val1 && val2;
            case OR:
                return val1 || val2;
            default:
                return VariableHelper.performComparison(VariableHelper.getComparisonTypeByOperator(binaryOperator), var1, var2);
        }
    }
    private static Object performStringBinaryOperation(Variable var1, Variable var2, BinaryOperator binaryOperator)
    {
        String val1 = var1.asString();
        String val2 = var2.asString();
        
        if (binaryOperator == BinaryOperator.CONCAT)
        {
            return val1 + val2;
        }
        else return VariableHelper.performComparison(VariableHelper.getComparisonTypeByOperator(binaryOperator), var1, var2);
    }
    
    public static Object performUnaryOperation(Variable var1, UnaryOperator unaryOperator)
    {
        switch (unaryOperator)
        {
            case MINUS:
                return VariableHelper.normalize(-var1.asNumber(), var1.getDataType());
            case NEGATE:
                return !var1.asBoolean();
        }
        
        return var1.getValue();
    }
    
    public static Object normalize(Object value, DataType dataType)
    {
        switch (dataType)
        {
            case INTEGER:
            {
                double num = Double.parseDouble(value.toString());
                return (int) num;
            }
            case FLOAT:
            {
                double num = Double.parseDouble(value.toString());
                return (float) num;
            }
            case BOOLEAN:
                return Boolean.parseBoolean(value.toString());
        }
        
        return value;
    }
    
    public static boolean performComparison(InstructionType instructionType, Variable var1, Variable var2)
    {
        switch (var1.getDataType())
        {
            case INTEGER: return VariableHelper.performValueComparison(instructionType, var1.asInteger(), var2.asInteger());
            case FLOAT: return VariableHelper.performValueComparison(instructionType, var1.asFloat(), var2.asFloat());
            case BOOLEAN: return VariableHelper.performValueComparison(instructionType, var1.asBoolean(), var2.asBoolean());
            case STRING: return VariableHelper.performValueComparison(instructionType, var1.asString(), var2.asString());
        }
        
        return false;
    }
    private static <T extends Comparable> boolean performValueComparison(InstructionType instructionType, T val1, T val2)
    {
        int result = val1.compareTo(val2);
        
        switch (instructionType)
        {
            case EQ: return result == 0;
            case NE: return result != 0;
            case LT: return result < 0;
            case LE: return result <= 0;
            case GT: return result > 0;
            case GE: return result >= 0;
        }
        
        return false;
    }
}
