package org.lemonade.gui.elements;

import javafx.scene.control.CheckBox;

public class GuiBooleanValue extends GuiValue<Boolean> {

    private Boolean value;
    private CheckBox checkBox;

    public GuiBooleanValue() {
        checkBox = new CheckBox();
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        this.value =  value;
    }

    @Override
    public void update() {
        setValue(checkBox.isSelected());
    }
}
