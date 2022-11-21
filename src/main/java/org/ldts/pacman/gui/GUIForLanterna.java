package org.ldts.pacman.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.Position;

import java.io.IOException;

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

        this.screen = createScreen(terminal);
        this.screen.startScreen();
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
    public void hideCursor() {

    }

    @Override
    public void showCursor() {

    }

    @Override
    public void setFont(String fontNameWithExtension) {

    }

    @Override
    public GameActions.ControlActions getNextUserInput() throws IOException {
        KeyStroke pressedKey;

        if((pressedKey = this.terminal.pollInput()) == null) return GameActions.ControlActions.NONE;
        
        switch(pressedKey.getKeyType()) {
            case Character:
                if(Character.toLowerCase(pressedKey.getCharacter().charValue()) == 'q') return GameActions.ControlActions.EXIT;
                return GameActions.ControlActions.NONE;
            case ArrowUp:
                return GameActions.ControlActions.MOVE_UP;
            case ArrowDown:
                return GameActions.ControlActions.MOVE_UP;
            case ArrowLeft:
                return GameActions.ControlActions.MOVE_LEFT;
            case ArrowRight:
                return GameActions.ControlActions.MOVE_RIGHT;
            case Escape:
                break;
        }

        // Se chegar aqui é por que não leu nenhuma tecla interessante
        return GameActions.ControlActions.MOVE_DOWN;
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
