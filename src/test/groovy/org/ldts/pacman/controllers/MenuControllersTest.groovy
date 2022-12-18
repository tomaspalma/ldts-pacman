package org.ldts.pacman.controllers

import org.ldts.pacman.models.menus.Menu
import spock.lang.Specification

class MenuControllersTest extends Specification {
    private def menu

    def setup() {
        menu = Mock(Menu.class)
    }
}
