package org.lemonade.gui;

import java.util.List;

import org.lemonade.visitors.interfaces.GuiBaseElementsVisitor;

public class GuiConditional extends GuiBody {

    private GuiExpression condition;
    private List<GuiBody> bodies;

    public GuiConditional(List<GuiBody> bodies, GuiExpression expression) {
        this.bodies = bodies;
        this.condition = expression;
    }

    public GuiExpression getCondition() {
        return condition;
    }

    public List<GuiBody> getBodies() {
        return bodies;
    }

    @Override
    public boolean isConditional() {
        return true;
    }

    public void accept(GuiBaseElementsVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void isVisible(final boolean flag) {
        bodies.forEach(body -> {
            if (body.isQuestion() && flag)
                body.isVisible(flag);
            else if (!flag)
                body.isVisible(flag);
        });
    }
}
