package org.ldts.pacman.viewers.menus;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.Position;
import org.ldts.pacman.models.menus.Menu;
import org.ldts.pacman.viewers.Viewer;

public class RegularMenuViewer extends Viewer<Menu> {
    public RegularMenuViewer(Menu model) {
        super(model);
    }

    @Override
    protected void drawEntities(GUI gui) {
        //gui.createRectangle(new TerminalPosition(7,9), new TerminalSize(6,3), '-');



        gui.writeToScreen(new Position(5, 4), getModel().getTitle(), getModel().getColor());

        for (int i = 0; i < getModel().getNumberOptions(); i++) {
            if (i == getModel().getCurrentNumber())
                gui.writeToScreen(new Position(8, 9+  i), getModel().getOption(i).getMessage(), TextColor.ANSI.GREEN_BRIGHT);
            else
                gui.writeToScreen(new Position(8, 9+  i), getModel().getOption(i).getMessage(), TextColor.ANSI.WHITE);
        }

    }


}
