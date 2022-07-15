package edu.cmu.cs214.hw3.models.gods;

import edu.cmu.cs214.hw3.models.GameState;
import edu.cmu.cs214.hw3.models.actions.build.AtlasBuild;
import edu.cmu.cs214.hw3.models.actions.move.DefaultMove;
import edu.cmu.cs214.hw3.models.actions.win.DefaultWin;

public class Atlas extends God {

    public Atlas() {
        super(new DefaultMove(), new AtlasBuild(), new DefaultWin());
    }

    @Override
    public boolean skip(GameState state) {
        if (state.equals(GameState.Build)) {
            // second move is optional
            AtlasBuild ab = (AtlasBuild) getBuildRule();
            return ab.getFirstBuild();
        }
        return false;
    }
}
