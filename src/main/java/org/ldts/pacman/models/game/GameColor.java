package org.ldts.pacman.models.game;

public enum GameColor {
    RED("#F52418"),
    PINK("#F575F5"),
    DARK_BLUE("#0000A6"),
    YELLOW("#FFED52"),
    ORANGE("ff8525"),
    LIGHT_BLUE("15f1f6"),
    WHITE("#FFFFFF");

    public String hexCode;

    private GameColor(String colorHexCode) {
        this.hexCode = colorHexCode;
    }
}
