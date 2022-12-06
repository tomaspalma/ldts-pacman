package org.ldts.pacman.viewers.menus;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.menus.MainMenu;
import org.ldts.pacman.models.Position;
import org.ldts.pacman.viewers.Viewer;

public class MainMenuViewer extends Viewer<MainMenu> {
    public MainMenuViewer(MainMenu model) {
        super(model);
    }

    @Override
    public void drawEntities(GUI gui) {
        gui.writeToScreen(new Position(5, 5), "P A C M A N", TextColor.ANSI.YELLOW_BRIGHT);

        for (int i = 0; i < getModel().getNumberOptions(); i++) {
            if (i == getModel().getCurrentNumber())
                gui.writeToScreen(new Position(8, 9 + i), getModel().getOption(i).getMessage(), TextColor.ANSI.GREEN_BRIGHT);
            else
                gui.writeToScreen(new Position(8, 9 + i), getModel().getOption(i).getMessage(), TextColor.ANSI.WHITE);
        }
    }
}
