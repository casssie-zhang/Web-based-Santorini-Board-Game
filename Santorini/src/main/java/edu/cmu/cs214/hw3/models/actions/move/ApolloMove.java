package edu.cmu.cs214.hw3.models.actions.move;

import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Grid;
import edu.cmu.cs214.hw3.models.Player;
import edu.cmu.cs214.hw3.models.Worker;

public class ApolloMove implements MoveRule {

    @Override
    public boolean isValidMove(Worker worker, Board board, int x, int y) {
        if (board.checkBound(x, y)) {
            Grid dest = board.getGrid(x, y);
            Grid src = worker.getPosition();
            Player player = worker.getPlayer();
            if (board.withinAdjacent(src, dest) && board.withinLevel(src, dest)) { // validMove
                if (!dest.isOccupied()) return true; // defaultMove
                else { // occupied by the worker
                    Player destPlayer = dest.getOccupiedBy().getPlayer();
                    // destination is occupied by opponent's worker
                    return !destPlayer.equals(player);
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
                Worker opponent = dest.getOccupiedBy();
                // move opponent's worker to the current worker's space
                Grid origPos = worker.getPosition();
                board.move(opponent, origPos);
                board.move(worker, dest);
                // worker's original position will be cleared. So set again.
                origPos.setOccupied(opponent);

            } else {
                // move selected worker to destination
                board.move(worker, dest);
            }

            return true;

        }
        return false;
    }
}
