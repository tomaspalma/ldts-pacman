package org.ldts.pacman.gui;

import java.io.IOException;

// Abstração entre o resto dos elementos do programa e possíveis librarias de GUI que quisermos utilizar
// Contém métodos gerais abstraídos que vamos querer que uma determinada GUI implemente
public interface GUI {
    void clear();

    void refresh() throws IOException;

    void close() throws IOException;
}
