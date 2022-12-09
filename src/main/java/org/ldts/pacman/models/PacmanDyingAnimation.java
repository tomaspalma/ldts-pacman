package org.ldts.pacman.models;

public class PacmanDyingAnimation extends PacmanAnimation {

    public PacmanDyingAnimation(Pacman pacman) {
        super(pacman);
    }

    @Override
    public void start() {
        try {
            Thread.sleep(1000);
            this.pacman.setCurrentDirectionTo(new PacmanDirectionRight(pacman));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void preparation() {
       //this.pacman.changeDrawSymbolTo("");
    }
}
