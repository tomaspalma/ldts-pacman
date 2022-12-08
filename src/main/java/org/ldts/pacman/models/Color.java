package org.ldts.pacman.models;

public enum Color {
    RED("#F52418"),
    PINK("#F575F5"),
    DARK_BLUE("#0000A6"),
    YELLOW("#FFED52"),
    ORANGE(""),
    LIGHT_BLUE("");

    public String colorHexCode;

    private Color(String colorHexCode) {
        this.colorHexCode = colorHexCode;
    }
}
