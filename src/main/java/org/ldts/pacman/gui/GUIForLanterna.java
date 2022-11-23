package org.ldts.pacman.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.Position;

public class GUIForLanterna implements GUI {
    private final Screen screen;
    private final Terminal terminal;
    private final TextGraphics graphics;
    private final AWTTerminalFontConfiguration font;

    /*
    public GUIForLanterna(Screen screen) {
        this.screen = screen;
    }*/

    // O código de criar terminal só deve ser corrido uma vez por objeto de GUI
    public GUIForLanterna(int width, int height) throws IOException, URISyntaxException, FontFormatException {
        this.font = setFont("fonts/square.ttf");
        this.terminal = this.createTerminalScreen(width, height, font);

        this.screen = createScreen(terminal);
        this.screen.startScreen();

        hideCursor();

        this.graphics = this.screen.newTextGraphics();
    }

    public TerminalSize getTerminalSize() throws IOException {
        return this.screen.getTerminalSize();
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

    public void close(Screen screen) throws IOException {
        screen.close();
    }

    @Override
    public void hideCursor() {
        this.screen.setCursorPosition(null);
    }

    @Override
    public void showCursor() {

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

    @Override
    public void writeToScreen(Position position, String text, TextColor.ANSI color) {
        // Temos de criar um TextGraphics para cada  customização de texto que queiramos escrever
        TextGraphics currentGraphics = this.screen.newTextGraphics();
        currentGraphics.setForegroundColor(color);
        currentGraphics.putString(new TerminalPosition(position.getX(), position.getY()), text);
    }

    private Terminal createTerminalScreen(int width, int height, AWTTerminalFontConfiguration font) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(font);

        return terminalFactory.createTerminal();
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        Screen resultScreen = new TerminalScreen(terminal);
        resultScreen.startScreen();
        
        return resultScreen;
    }

    private AWTTerminalFontConfiguration setFont(String fontNameWithExtension) throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource(fontNameWithExtension);
        
        // Criar identificador da localização do recurso
        File fontFile = new File(resource.toURI());
        Font newFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsEnvironment.registerFont(newFont);

        Font loadedFont = newFont.deriveFont(Font.PLAIN, 25);

        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    private void drawElement(Position position, TextColor.ANSI color, String drawSymbol) {
        this.graphics.setForegroundColor(color);
        this.graphics.putString(position.getX(), position.getY(), drawSymbol);
    }


}
