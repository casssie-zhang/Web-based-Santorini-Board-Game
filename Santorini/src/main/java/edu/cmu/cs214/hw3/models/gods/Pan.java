package edu.cmu.cs214.hw3.models.gods;

import edu.cmu.cs214.hw3.models.actions.build.DefaultBuild;
import edu.cmu.cs214.hw3.models.actions.move.PanMove;
import edu.cmu.cs214.hw3.models.actions.win.PanWin;

public class Pan extends God {
    public Pan() {
        super(new PanMove(), new DefaultBuild(), new PanWin());
    }
}
