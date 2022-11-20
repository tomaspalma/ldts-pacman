package org.ldts.pacman.gui;

import java.io.IOException;

// Abstração entre o resto dos elementos do programa e possíveis librarias de GUI que quisermos utilizar
// Contém métodos gerais abstraídos que vamos querer que uma determinada GUI implemente
// Aqui só estarão os métodos que pretendemos que possam ser executados de forma pública em outras partes do código
public interface GUI {
    // Funções para o screen
    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    void gracefulExit() throws IOException;

    void hideCursor();
    void showCursor();

}
