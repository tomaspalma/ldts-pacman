package org.ldts.pacman.viewers.menus;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.Position;
import org.ldts.pacman.models.menus.GameOverMenu;
import org.ldts.pacman.viewers.Viewer;

public class GameOverMenuViewer extends Viewer<GameOverMenu> {
    public GameOverMenuViewer(GameOverMenu model) {
        super(model);
    }

    @Override
    public void drawEntities(GUI gui) {
        gui.writeToScreen(new Position(5, 5), getModel().getMessage(), TextColor.ANSI.WHITE);

        for (int i = 0; i < getModel().getNumberOptions(); i++) {
            if (i == getModel().getCurrentNumber())
                gui.writeToScreen(new Position(5, 9 + i), getModel().getOption(i).getMessage(), TextColor.ANSI.YELLOW);
            else
                gui.writeToScreen(new Position(5, 9 + i), getModel().getOption(i).getMessage(), TextColor.ANSI.WHITE);
        }
    }
}
