package edu.cmu.cs214.hw3.models.actions.build;

import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Worker;

public interface BuildRule {

    boolean isValidBuild(Worker worker, Board board, int x, int y);

    boolean build(Worker worker, Board board, int x, int y);

}
