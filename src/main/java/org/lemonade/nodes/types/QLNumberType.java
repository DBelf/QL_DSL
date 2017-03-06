package org.lemonade.nodes.types;

/**
 *
 */
public abstract class QLNumberType extends QLType {
    public int precedenceStrength;

    public QLNumberType(int precedenceStrength) {
        this.precedenceStrength = precedenceStrength;
    }

    @Override
    public boolean isOf(Class<?> other) {
        return this.getClass().equals(other);
    }

    @Override
    public boolean isNumeric() {
        return true;
    }

    @Override
    public boolean isComparable() {
        return true;
    }

    public static QLNumberType precedence(QLNumberType left, QLNumberType right) {

        return left.precedenceStrength >= right.precedenceStrength ? left : right;
    }
}
