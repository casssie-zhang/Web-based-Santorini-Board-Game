package edu.cmu.cs214.hw3.models.gods;

import edu.cmu.cs214.hw3.models.actions.build.DefaultBuild;
import edu.cmu.cs214.hw3.models.actions.move.DefaultMove;
import edu.cmu.cs214.hw3.models.actions.win.DefaultWin;

public class NoGod extends God {
    public NoGod() {
        super(new DefaultMove(), new DefaultBuild(), new DefaultWin());
    }
}
