package org.lemonade.gui.values;

import javafx.scene.control.Label;
import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiLabelValue extends GuiValue<String> {

    private String value;
    private Label label;

    public GuiLabelValue(String labelText) {
        this.value = labelText;
        this.label = new Label(labelText);
        label.setMinWidth(200);
    }

    @Override public void update() {

    }

    @Override String getValue() {
        return label.getText();
    }

    @Override void setValue(final String value) {
    }

    @Override
    public Label getWidget() {
        return label;
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
