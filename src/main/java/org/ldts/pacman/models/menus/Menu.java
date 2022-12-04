package org.ldts.pacman.models.menus;

import java.util.List;
import java.util.spi.ToolProvider;

import static java.lang.Math.abs;

public abstract class Menu {
    protected List<String> options;
    protected int currentOption;

    protected Menu() {
        currentOption = 0;
    }

    public void moveDown() {
        currentOption = abs((currentOption + 1) % getNumberOptions());
    }

    public void moveUp() {
        currentOption = abs((currentOption - 1) % getNumberOptions());
    }

    public int getNumberOptions() {
        return options.size();
    }

    public int getCurrentOption() {
        return currentOption;
    }

    public String getOption(int i) {
        return options.get(i);
    }
}
