package org.ldts.pacman.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.ldts.pacman.models.Position;

import java.io.IOException;
import java.nio.charset.Charset;

public class GUIForLanterna implements GUI {
    private final Screen screen;
    private final Terminal terminal;
    private final TextGraphics graphics;

    /*
    public GUIForLanterna(Screen screen) {
        this.screen = screen;
    }*/

    // O código de criar terminal só deve ser corrido uma vez por objeto de GUI
    public GUIForLanterna(int width, int height) throws IOException {
        this.terminal = this.createTerminalScreen(width, height);
        this.terminal.enterPrivateMode();

        this.screen = createScreen(terminal);
        this.graphics = this.screen.newTextGraphics();
    }

    public TerminalSize getTerminalSize() throws IOException {
        return terminal.getTerminalSize();
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public Screen getScreen() {
        return screen;
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        this.screen.close();
    }

    @Override
    public void gracefulExit() throws IOException {
        if(this.terminal != null) this.terminal.exitPrivateMode();
    }

    @Override
    public void hideCursor() {

    }

    @Override
    public void showCursor() {

    }


    @Override
    public void drawEntity(Position position, TextColor.ANSI color, String drawSymbol) {
        this.drawElement(position, color, drawSymbol);
    }

    private Terminal createTerminalScreen(int width, int height) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        return terminalFactory.createTerminal();
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        Screen resultScreen = new TerminalScreen(terminal);
        resultScreen.startScreen();
        return resultScreen;
    }

    private void drawElement(Position position, TextColor.ANSI color, String drawSymbol) {
        this.graphics.setForegroundColor(color);
        this.graphics.putString(position.getX(), position.getY(), drawSymbol);
    }

}
