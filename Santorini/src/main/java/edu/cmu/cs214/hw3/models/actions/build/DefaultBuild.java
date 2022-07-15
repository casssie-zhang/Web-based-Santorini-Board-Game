package edu.cmu.cs214.hw3.models.actions.build;

import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Grid;
import edu.cmu.cs214.hw3.models.Worker;

public class DefaultBuild implements BuildRule {


    @Override
    public boolean isValidBuild(Worker worker, Board board, int x, int y) {
        if (board.checkBound(x, y)) {
            Grid dest = board.getGrid(x, y);
            return board.isValidBuild(worker.getPosition(), dest);
        }
        return false;
    }

    @Override
    public boolean build(Worker worker, Board board, int x, int y) {
        if (isValidBuild(worker, board, x, y)) {
            Grid dest = board.getGrid(x, y);
            dest.build();
            return true;
        }
        return false;
    }
}
