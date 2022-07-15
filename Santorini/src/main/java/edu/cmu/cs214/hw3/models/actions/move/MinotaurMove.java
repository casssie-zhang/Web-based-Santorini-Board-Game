package edu.cmu.cs214.hw3.models.actions.move;

import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Grid;
import edu.cmu.cs214.hw3.models.Player;
import edu.cmu.cs214.hw3.models.Worker;

public class MinotaurMove implements MoveRule {

    public Grid getForcePos(Board board, Grid workerPos, Grid opponentPos) {
        int dx = opponentPos.getX() - workerPos.getX();
        int dy = opponentPos.getY() - workerPos.getY();
        int x = opponentPos.getX() + dx;
        int y = opponentPos.getY() + dy;
        if (board.checkBound(x, y)) {
            Grid forcePos = board.getGrid(x, y);
            if (!forcePos.isOccupied()) return forcePos;
        }
        return null;

    }

    @Override
    public boolean isValidMove(Worker worker, Board board, int x, int y) {
        if (board.checkBound(x, y)) {
            Grid dest = board.getGrid(x, y);
            Grid src = worker.getPosition();
            Player player = worker.getPlayer();
            if (board.withinAdjacent(src, dest) && board.withinLevel(src, dest)) { // validMove
                if (!dest.isOccupied()) return true; // defaultMove
                else { // need to force opponent's worker
                    Player destPlayer = dest.getOccupiedBy().getPlayer();
                    if (!destPlayer.equals(player)) { // destination is occupied by opponent's worker
                        return getForcePos(board, worker.getPosition(), dest) != null;
                    }
                }

            }
        }
        return false;


    }

    @Override
    public boolean move(Worker worker, Board board, int x, int y) {
        if (isValidMove(worker, board, x, y)) {
            Grid dest = board.getGrid(x, y);
            if (dest.isOccupied()) {
                board.move(dest.getOccupiedBy(), getForcePos(board, worker.getPosition(), dest));
            }
            // move selected worker to destination
            board.move(worker, dest);
            return true;

        }
        return false;
    }
}
