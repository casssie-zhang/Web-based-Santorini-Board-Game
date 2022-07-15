package edu.cmu.cs214.hw3.models.gods;

import edu.cmu.cs214.hw3.models.GameState;
import edu.cmu.cs214.hw3.models.actions.build.DemeterBuild;
import edu.cmu.cs214.hw3.models.actions.move.DefaultMove;
import edu.cmu.cs214.hw3.models.actions.win.DefaultWin;

public class Demeter extends God {
    public Demeter() {
        super(new DefaultMove(), new DemeterBuild(), new DefaultWin());
    }

    @Override
    public boolean skip(GameState state) {
        if (state.equals(GameState.Build)) {
            DemeterBuild db = (DemeterBuild) getBuildRule();
            return db.getBuildLeft() == 1;
        }
        return false;
    }
}
