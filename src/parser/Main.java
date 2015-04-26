package parser;


import interpret.Instruction;
import interpret.InterpretException;
import interpret.Interpreter;
import tree.statement.BlockStatement;
import tree.visitor.CodeGenVisitor;
import tree.visitor.SemanticCheckVisitor;
import util.ErrorManager;
import util.Error;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jakub
 */
public class Main {
    public static void main(String[] args) throws ParseException, InterpretException
    {
        Parser parser = null;

        if (args.length == 0)
        {
          parser = new Parser(System.in);
        }
        else if (args.length == 1)
        {
            try
            {
                parser = new Parser(new java.io.FileInputStream(args[0]));
            }
            catch (java.io.FileNotFoundException e)
            {
                return;
            }
        }

        BlockStatement bs = parser.Start();
        ErrorManager errorManager = new ErrorManager();
        SemanticCheckVisitor semanticChecker = new SemanticCheckVisitor(errorManager);
        semanticChecker.Start(bs);
        
        for (Error error : errorManager.getErrors())
        {
            System.out.println(error.toString());
        }
        
        if (errorManager.getErrors().isEmpty())
        {
            errorManager.clearErrors();
            
            CodeGenVisitor codeGen = new CodeGenVisitor(errorManager);
            bs.accept(codeGen);
            
            for (Error error : errorManager.getErrors())
            {
                System.out.println(error.toString());
            }
            
            int index = 0;
            for (Instruction instruction : codeGen.getInstructions())
            {
                System.out.println(index++ + ": " + instruction.toString());
            }
            
            Interpreter interpreter = new Interpreter(codeGen.getInstructions(), 100);
            interpreter.Interpret();
        }
    }
}
