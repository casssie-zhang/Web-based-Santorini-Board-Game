package edu.cmu.cs214.hw3.models.gods;

import edu.cmu.cs214.hw3.models.actions.build.DefaultBuild;
import edu.cmu.cs214.hw3.models.actions.move.MinotaurMove;
import edu.cmu.cs214.hw3.models.actions.win.DefaultWin;

public class Minotaur extends God {
    public Minotaur() {
        super(new MinotaurMove(), new DefaultBuild(), new DefaultWin());
    }

}
