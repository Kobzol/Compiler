package interpret;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jakub
 */
public enum InstructionType {
    ADD,
    SUB,
    MUL,
    DIV,
    REM,
    UNARY_MINUS,
    NEGATE,
    CONCAT,
    AND,
    OR,
    GT,
    GE,
    LT,
    LE,
    EQ,
    NE,
    NOT,
    PUSH,
    LOAD,
    SAVE,
    LABEL,
    JMP,
    FJMP,
    WRITE,
    READ,
    DECLARE,
    CREATE_FRAME,
    DESTROY_FRAME,
    RETURN
}
