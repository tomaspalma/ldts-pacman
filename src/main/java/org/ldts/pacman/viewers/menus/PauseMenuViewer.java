package org.ldts.pacman.viewers.menus;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.menus.PauseMenu;
import org.ldts.pacman.viewers.Viewer;

public class PauseMenuViewer extends Viewer<PauseMenu> {
    public PauseMenuViewer(PauseMenu model) {
        super(model);
    }

    @Override
    public void drawEntities(GUI gui) {
        gui.writeToScreen(new Position(6, 4), getModel().getTitle(), getModel().getColor());

        for (int i = 0; i < getModel().getNumberOptions(); i++) {
            String message = getModel().getOption(i).getMessage();
            if (i == getModel().getCurrentNumber())
                gui.writeToScreen(new Position(10 - message.length() / 2, 9 + i), message, TextColor.ANSI.GREEN_BRIGHT);
            else
                gui.writeToScreen(new Position(10 - message.length() / 2, 9 + i), message, TextColor.ANSI.WHITE);
        }
    }
}
