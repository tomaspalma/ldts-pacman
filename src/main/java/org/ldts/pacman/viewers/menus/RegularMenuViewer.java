package org.ldts.pacman.viewers.menus;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.menus.Menu;
import org.ldts.pacman.viewers.Viewer;

public class RegularMenuViewer extends Viewer<Menu> {
    public RegularMenuViewer(Menu model) {
        super(model);
    }

    @Override
    protected void drawEntities(GUI gui) {
        String title = getModel().getTitle();
        gui.writeToScreen(new Position(10 - title.length() / 2, 4), title, getModel().getColor());

        for (int i = 0; i < getModel().getNumberOptions(); i++) {
            String message = getModel().getOption(i).getMessage();
            if (i == getModel().getCurrentNumber())
                gui.writeToScreen(new Position(10 - message.length() / 2, 9 + i), message, TextColor.ANSI.GREEN_BRIGHT);
            else
                gui.writeToScreen(new Position(10 - message.length() / 2, 9 + i), message, TextColor.ANSI.WHITE);
        }
    }
}
