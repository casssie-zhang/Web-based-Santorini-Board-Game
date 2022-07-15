package edu.cmu.cs214.hw3.models.actions.move;

import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Grid;
import edu.cmu.cs214.hw3.models.Worker;

public class DefaultMove implements MoveRule {

    @Override
    public boolean isValidMove(Worker worker, Board board, int x, int y) {
        if (board.checkBound(x, y)) {
            Grid dest = board.getGrid(x, y);
            Grid src = worker.getPosition();
            return !dest.isOccupied() && board.withinAdjacent(src, dest) && board.withinLevel(src, dest);
        }
        return false;
    }

    @Override
    public boolean move(Worker worker, Board board, int x, int y) {
        if (isValidMove(worker, board, x, y)) {
            Grid dest = board.getGrid(x, y);
            board.move(worker, dest);
            return true;
        }
        return false;
    }
}
