package org.ldts.pacman.models.menus;

import org.ldts.pacman.models.menus.options.Option;

import java.util.List;

import static java.lang.Math.abs;

public abstract class Menu {
    protected List<Option> options;
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

    public int getCurrentNumber() {
        return currentOption;
    }

    public Option getCurrentOption() {
        return options.get(currentOption);
    }

    public Option getOption(int i) {
        return options.get(i);
    }
}
