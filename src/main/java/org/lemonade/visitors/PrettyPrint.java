package org.lemonade.visitors;

import org.lemonade.nodes.*;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.types.QLType;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.literal.*;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;

/**
 *
 */
public class PrettyPrint implements ASTVisitor<ASTNode> {
    private String tabLevel = "";

    public ASTNode visit(Form form) {
        String identifier = form.getIdentifier();
        String formStr = String.format("form %s {\n", identifier);
        System.err.print(formStr);
        for (Block block : form.getBlocks()) {
            block.accept(this);
            System.err.print(tabLevel.length() +"~~~\n");
        }
        System.err.print("\n}");
        return form;

    }

    @Override
    public ASTNode visit(Question question) {
        tabLevel += "\t";
        String identifier = question.getIdentifier();
        String label = question.getLabel();
        QLType type = question.getType();
        String questionStr = String.format("%s%s: \"%s\" %s",tabLevel, identifier, label, type);
        System.err.print(questionStr);
        tabLevel.replaceFirst("\t", "");
        return question;
    }

    @Override
    public ASTNode visit(Conditional conditional) {
        tabLevel += "\t";
        System.err.print(tabLevel + "if (");
        conditional.getCondition().accept(this );
        System.err.print(") {\n");
        for (Block block : conditional.getBlocks()) {
            block.accept(this);
            System.err.print("\n");
        }
        tabLevel.replaceFirst("\t", "");
        System.err.print(tabLevel + "}");
        return conditional;
    }

    @Override
    public ASTNode visit(Expression expression) {
        return null;
    }

    @Override
    public ASTNode visit(AndBinary andBinary) {
        return null;
    }

    @Override
    public ASTNode visit(OrBinary orBinary) {
        orBinary.getLeft().accept(this);
        System.err.print(" || ");
        orBinary.getRight().accept(this);
        return orBinary;
    }

    @Override
    public ASTNode visit(PlusBinary plusBinary) {
        plusBinary.getLeft().accept(this);
        System.err.print(" + ");
        plusBinary.getRight().accept(this);
        return plusBinary;
    }

    @Override
    public ASTNode visit(ProductBinary productBinary) {
        productBinary.getLeft().accept(this);
        System.err.print(" * ");
        productBinary.getRight().accept(this);
        return productBinary;
    }

    @Override
    public ASTNode visit(MinusBinary minusBinary) {
        minusBinary.getLeft().accept(this);
        System.err.print(" - ");
        minusBinary.getRight().accept(this);
        return minusBinary;
    }

    @Override
    public ASTNode visit(DivideBinary divideBinary) {
        divideBinary.getLeft().accept(this);
        System.err.print(" / ");
        divideBinary.getRight().accept(this);
        return divideBinary;
    }

    @Override
    public ASTNode visit(EqBinary eqBinary) {
        eqBinary.getLeft().accept(this);
        System.err.print(" == ");
        eqBinary.getRight().accept(this);
        return eqBinary;
    }

    @Override
    public ASTNode visit(NEqBinary nEqBinary) {
        nEqBinary.getLeft().accept(this);
        System.err.print(" != ");
        nEqBinary.getRight().accept(this);
        return nEqBinary;
    }

    @Override
    public ASTNode visit(GTBinary gtBinary) {
        gtBinary.getLeft().accept(this);
        System.err.print(" > ");
        gtBinary.getRight().accept(this);
        return gtBinary;
    }

    @Override
    public ASTNode visit(GTEBinary gteBinary) {
        gteBinary.getLeft().accept(this);
        System.err.print(" >= ");
        gteBinary.getRight().accept(this);
        return gteBinary;
    }

    @Override
    public ASTNode visit(LTBinary ltBinary) {
        ltBinary.getLeft().accept(this);
        System.err.print(" < ");
        ltBinary.getRight().accept(this);
        return ltBinary;
    }

    @Override
    public ASTNode visit(LTEBinary lteBinary) {
        lteBinary.getLeft().accept(this);
        System.err.print(" <= ");
        lteBinary.getRight().accept(this);
        return lteBinary;
    }

    @Override
    public ASTNode visit(BangUnary bangUnary) {
        System.err.print("!");
        bangUnary.getExpression().accept(this);
        return bangUnary;
    }

    @Override
    public ASTNode visit(NegUnary negUnary) {
        System.err.print("-");
        negUnary.getExpression().accept(this);
        return negUnary;
    }

    @Override
    public ASTNode visit(BooleanLit booleanLit) {
        System.err.print(booleanLit);
        return booleanLit;
    }

    @Override
    public ASTNode visit(DecimalLit decimalLit) {
        System.err.print(decimalLit);
        return decimalLit;
    }

    @Override
    public ASTNode visit(IntegerLit integerLit) {
        System.err.print(integerLit);
        return integerLit;
    }

    @Override
    public ASTNode visit(StringLit stringLit) {
        System.err.print(stringLit);
        return stringLit;
    }

    @Override
    public ASTNode visit(IdentifierLit identifierLit) {
        System.err.print(identifierLit);
        return identifierLit;
    }

    @Override
    public ASTNode visit(QLType type) {
        return null;
    }
}