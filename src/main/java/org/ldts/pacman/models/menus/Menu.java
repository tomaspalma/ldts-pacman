package org.ldts.pacman.models.menus;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.menus.options.Option;

import java.util.List;

import static java.lang.Math.abs;

public abstract class Menu {
    protected TextColor.ANSI color;
    protected String title;
    protected List<Option> options;
    protected int currentOption;

    protected Menu() {
        currentOption = 0;
    }

    public String getTitle() {
        return title;
    }

    public TextColor.ANSI getColor() {
        return color;
    }

    public void moveDown() {
        currentOption = abs((currentOption + 1) % getNumberOptions());
    }

    public void moveUp() {
        if (currentOption == 0)
            currentOption = getNumberOptions() - 1;
        else
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
