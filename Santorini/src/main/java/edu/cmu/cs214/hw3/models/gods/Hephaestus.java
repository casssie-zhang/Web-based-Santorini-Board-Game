package edu.cmu.cs214.hw3.models.gods;

import edu.cmu.cs214.hw3.models.GameState;
import edu.cmu.cs214.hw3.models.actions.build.HephaestusBuild;
import edu.cmu.cs214.hw3.models.actions.move.DefaultMove;
import edu.cmu.cs214.hw3.models.actions.win.DefaultWin;

public class Hephaestus extends God {
    public Hephaestus() {
        super(new DefaultMove(), new HephaestusBuild(), new DefaultWin());
    }

    @Override
    public boolean skip(GameState state) {
        if (state.equals(GameState.Build)) {
            // second build is optional
            HephaestusBuild hb = (HephaestusBuild) getBuildRule();
            return hb.getBuildLeft() == 1;
        }
        return false;
    }
}
