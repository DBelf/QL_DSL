package org.lemonade.visitors.interfaces;

import org.lemonade.nodes.expressions.binary.AndBinary;
import org.lemonade.nodes.expressions.binary.DivideBinary;
import org.lemonade.nodes.expressions.binary.EqBinary;
import org.lemonade.nodes.expressions.binary.GTBinary;
import org.lemonade.nodes.expressions.binary.GTEBinary;
import org.lemonade.nodes.expressions.binary.LTBinary;
import org.lemonade.nodes.expressions.binary.LTEBinary;
import org.lemonade.nodes.expressions.binary.MinusBinary;
import org.lemonade.nodes.expressions.binary.NEqBinary;
import org.lemonade.nodes.expressions.binary.OrBinary;
import org.lemonade.nodes.expressions.binary.PlusBinary;
import org.lemonade.nodes.expressions.binary.ProductBinary;
import org.lemonade.nodes.expressions.literal.BooleanLiteral;
import org.lemonade.nodes.expressions.literal.DateLiteral;
import org.lemonade.nodes.expressions.literal.DecimalLiteral;
import org.lemonade.nodes.expressions.literal.IdentifierLiteral;
import org.lemonade.nodes.expressions.literal.IntegerLiteral;
import org.lemonade.nodes.expressions.literal.MoneyLiteral;
import org.lemonade.nodes.expressions.literal.StringLiteral;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;

public interface ExpressionVisitor<T> {

    T visit(AndBinary andBinary);

    T visit(OrBinary orBinary);

    T visit(PlusBinary plusBinary);

    T visit(ProductBinary productBinary);

    T visit(MinusBinary minusBinary);

    T visit(DivideBinary divideBinary);

    T visit(EqBinary eqBinary);

    T visit(NEqBinary nEqBinary);

    T visit(GTBinary gtBinary);

    T visit(GTEBinary gteBinary);

    T visit(LTBinary ltBinary);

    T visit(LTEBinary lteBinary);

    T visit(BangUnary bangUnary);

    T visit(NegUnary negUnary);

    T visit(BooleanLiteral booleanValue);

    T visit(DecimalLiteral decimalValue);

    T visit(DateLiteral dateValue);

    T visit(MoneyLiteral moneyValue);

    T visit(IntegerLiteral integerValue);

    T visit(StringLiteral stringValue);

    T visit(IdentifierLiteral identifierValue);
}
