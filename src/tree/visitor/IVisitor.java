/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.visitor;

import tree.expression.Literal;
import tree.statement.ExpressionStatement;
import tree.statement.ReadStatement;
import tree.statement.EmptyStatement;
import tree.statement.BlockStatement;
import tree.statement.DeclarationStatement;
import tree.statement.WriteStatement;
import tree.expression.AssignmentExpression;
import tree.expression.ConditionalExpression;
import tree.expression.IdentifierExpression;
import tree.expression.BinaryExpression;
import tree.expression.FunctionCallExpression;
import tree.expression.UnaryExpression;
import tree.statement.ForStatement;
import tree.statement.FunctionStatement;
import tree.statement.IfStatement;
import tree.statement.ReturnStatement;
import tree.statement.WhileStatement;

/**
 *
 * @author Jakub
 */
public interface IVisitor {
    void visit(BlockStatement blockStatement);
    void visit(ExpressionStatement expressionStatement);
    void visit(DeclarationStatement declarationStatement);
    void visit(ReadStatement readStatement);
    void visit(WriteStatement writeStatement);
    void visit(EmptyStatement emptyStatement);
    void visit(IfStatement ifStatement);
    void visit(ForStatement forStatement);
    void visit(FunctionStatement functionStatement);
    void visit(ReturnStatement returnStatement);
    void visit(WhileStatement whileStatement);
    
    void visit(AssignmentExpression expression);
    void visit(BinaryExpression binaryExpression);
    void visit(UnaryExpression unaryExpression);
    void visit(Literal literal);
    void visit(ConditionalExpression conditionalExpression);
    void visit(IdentifierExpression identifierExpression);
    void visit(FunctionCallExpression functionCallExpression);
}
