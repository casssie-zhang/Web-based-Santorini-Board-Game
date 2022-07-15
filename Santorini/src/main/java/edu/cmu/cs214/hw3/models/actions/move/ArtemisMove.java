package edu.cmu.cs214.hw3.models.actions.move;

import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Grid;
import edu.cmu.cs214.hw3.models.Worker;

/**
 * Artemis may move one additional time but not back to
 * it's initial space
 */
public class ArtemisMove implements MoveRule {
    private static final int MOVE_ALLOWED = 2;
    private int x1;
    private int y1;
    private int moveLeft;

    public ArtemisMove() {
        reset();
    }

    private void reset() {
        assert (moveLeft == 0);
        x1 = -1;
        y1 = -1;
        moveLeft = MOVE_ALLOWED;
    }

    public int getMoveLeft() {
        return moveLeft;
    }


    @Override
    public boolean isValidMove(Worker worker, Board board, int x, int y) {
        if (board.checkBound(x, y)) {
            Grid dest = board.getGrid(x, y);
            Grid src = worker.getPosition();
            if (!dest.isOccupied() && board.withinAdjacent(src, dest) && board.withinLevel(src, dest)) {
                return !(x == x1 && y == y1); // cannot build on the initial position
            }
        }
        return false;

    }

    @Override
    public boolean move(Worker worker, Board board, int x, int y) {
        if (moveLeft == MOVE_ALLOWED) {
            if (isValidMove(worker, board, x, y)) { // first build
                Grid dest = board.getGrid(x, y);
                x1 = worker.getPosition().getX();
                y1 = worker.getPosition().getY();
                board.move(worker, dest);
                moveLeft--;

            }
        } else if (moveLeft == 1) {
            if (x == -1 && y == -1) { // skip second build
                moveLeft--;
                reset();
                return true;
            }

            if (isValidMove(worker, board, x, y)) { // second build
                Grid dest = board.getGrid(x, y);
                board.move(worker, dest);
                moveLeft--;
                reset();
                return true;
            }
        }
        return false;
    }
}
