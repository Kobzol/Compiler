/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.visitor;

import tree.Node;
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
import util.ErrorManager;

/**
 *
 * @author Jakub
 */
public abstract class AbstractVisitor implements IVisitor {
    private final ErrorManager errorManager;
    
    public AbstractVisitor(ErrorManager errorManager)
    {
        this.errorManager = errorManager;
    }
    
    protected void reportError(int line, int column, String message)
    {
        this.errorManager.addError(line, column, message);
    }
    protected void reportError(Node node, String message)
    {
        this.errorManager.addError(node.getLine(), node.getColumn(), message);
    }

    @Override
    public void visit(BlockStatement blockStatement)
    {
        
    }

    @Override
    public void visit(ExpressionStatement expressionStatement)
    {
        
    }

    @Override
    public void visit(DeclarationStatement declarationStatement)
    {
        
    }

    @Override
    public void visit(ReadStatement readStatement)
    {
        
    }

    @Override
    public void visit(WriteStatement writeStatement)
    {
        
    }

    @Override
    public void visit(EmptyStatement emptyStatement)
    {
        
    }
    
    @Override
    public void visit(FunctionStatement functionStatement)
    {
        
    }
    
    @Override
    public void visit(ReturnStatement returnStatement)
    {
        
    }
    
    @Override
    public void visit(WhileStatement whileStatement)
    {
        
    }

    @Override
    public void visit(AssignmentExpression expression)
    {
        
    }

    @Override
    public void visit(BinaryExpression binaryExpression)
    {
        
    }

    @Override
    public void visit(UnaryExpression unaryExpression)
    {
        
    }

    @Override
    public void visit(Literal literal)
    {
        
    }

    @Override
    public void visit(ConditionalExpression conditionalExpression)
    {
        
    }

    @Override
    public void visit(IdentifierExpression identifierExpression)
    {
        
    }

    @Override
    public void visit(IfStatement ifStatement)
    {
        
    }

    @Override
    public void visit(ForStatement forStatement)
    {
        
    }
    
    @Override
    public void visit(FunctionCallExpression functionCallExpression)
    {
        
    }
}
