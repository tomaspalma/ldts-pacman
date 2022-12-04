package org.ldts.pacman.viewers.menus;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.menus.MainMenu;
import org.ldts.pacman.models.menus.Menu;
import org.ldts.pacman.models.Position;
import org.ldts.pacman.viewers.Viewer;

public class MainMenuViewer extends Viewer<MainMenu> {
    public MainMenuViewer(MainMenu menu) {
        super(menu);
    }

    @Override
    public void drawEntities(GUI gui) {
        gui.writeToScreen(new Position(5, 5), "PACMAN", TextColor.ANSI.WHITE);

        for (int i = 0; i < getModel().getNumberOptions(); i++) {
            if (i == getModel().getCurrentOption())
                gui.writeToScreen(new Position(5, 7 + i), getModel().getOption(i), TextColor.ANSI.YELLOW);
            else
                gui.writeToScreen(new Position(5, 7 + i), getModel().getOption(i), TextColor.ANSI.WHITE);
        }
    }
}
