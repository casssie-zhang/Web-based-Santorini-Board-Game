package edu.cmu.cs214.hw3.models.gods;

import edu.cmu.cs214.hw3.models.actions.build.DefaultBuild;
import edu.cmu.cs214.hw3.models.actions.move.ApolloMove;
import edu.cmu.cs214.hw3.models.actions.win.DefaultWin;

public class Apollo extends God {
    public Apollo() {

        super(new ApolloMove(), new DefaultBuild(), new DefaultWin());
    }
}
