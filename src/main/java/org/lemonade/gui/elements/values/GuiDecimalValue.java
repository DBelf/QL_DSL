package org.lemonade.gui.elements.values;

import javafx.scene.control.TextField;

public class GuiDecimalValue extends GuiValue<Double> {
    private Double value;

    public GuiDecimalValue(Double value) {
        this.value = value;
    }

    @Override
    Double getValue() {
        return value;
    }

    @Override
    public void update(Double newValue) {
        this.value = newValue;
    }
}
