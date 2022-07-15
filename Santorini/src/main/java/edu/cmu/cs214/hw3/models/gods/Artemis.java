package edu.cmu.cs214.hw3.models.gods;

import edu.cmu.cs214.hw3.models.GameState;
import edu.cmu.cs214.hw3.models.actions.build.DefaultBuild;
import edu.cmu.cs214.hw3.models.actions.move.ArtemisMove;
import edu.cmu.cs214.hw3.models.actions.win.DefaultWin;

public class Artemis extends God {
    public Artemis() {
        super(new ArtemisMove(), new DefaultBuild(), new DefaultWin());
    }

    @Override
    public boolean skip(GameState state) {
        if (state.equals(GameState.Move)) {
            // second move is optional
            ArtemisMove am = (ArtemisMove) getMoveRule();
            return am.getMoveLeft() == 1;
        }
        return false;
    }
}
