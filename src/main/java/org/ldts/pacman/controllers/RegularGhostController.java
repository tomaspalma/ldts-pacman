package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.Ghost;
import org.ldts.pacman.models.RegularGhost;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegularGhostController extends Controller<Arena> {
    private List<RegularGhost> regularGhostsToControl;
    public RegularGhostController(Arena model) {
        super(model);

        this.regularGhostsToControl = getModel().getRegularGhosts();
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException {
        for(RegularGhost regularGhost: regularGhostsToControl) {
            executeRegularGhostAction(regularGhost);
        }
    }

    private void executeRegularGhostAction(RegularGhost regularGhost) {
        switch(regularGhost.getState()) {
            case FRIGHTENED_PHASE -> regularGhost.getFrightenedStrategy().execute();
            case SCATTERING_PHASE -> regularGhost.getScatterStrategy().execute();
            case CHASING_PHASE -> regularGhost.getChaseStrategy().execute(regularGhost);
        }
    }
}
