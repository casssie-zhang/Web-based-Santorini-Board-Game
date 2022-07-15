package edu.cmu.cs214.hw3.models.actions.move;

import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Grid;
import edu.cmu.cs214.hw3.models.Worker;

public class PanMove implements MoveRule {
    @Override
    public boolean isValidMove(Worker worker, Board board, int x, int y) {
        if (board.checkBound(x, y)) {
            Grid dest = board.getGrid(x, y);
            Grid src = worker.getPosition();
            if (!dest.isOccupied() && board.withinAdjacent(src, dest)) {
                return board.withinLevel(src, dest) ||
                        (src.getHeight() - dest.getHeight() >= 2);
            }
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
