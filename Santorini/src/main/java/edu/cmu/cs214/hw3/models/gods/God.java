package edu.cmu.cs214.hw3.models.gods;

import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.GameState;
import edu.cmu.cs214.hw3.models.Grid;
import edu.cmu.cs214.hw3.models.Worker;
import edu.cmu.cs214.hw3.models.actions.build.BuildRule;
import edu.cmu.cs214.hw3.models.actions.move.MoveRule;
import edu.cmu.cs214.hw3.models.actions.win.WinRule;

import java.util.HashSet;

/**
 * Serve as a context for rules
 */
public abstract class God {

    private final BuildRule buildRule;
    private final MoveRule moveRule;
    private final WinRule winRule;

    public BuildRule getBuildRule() {
        return buildRule;
    }

    public MoveRule getMoveRule() {
        return moveRule;
    }

    public WinRule getWinRule() {
        return winRule;
    }



    protected God(MoveRule moveRule, BuildRule buildRule, WinRule winRule) {
        this.buildRule = buildRule;
        this.moveRule = moveRule;
        this.winRule = winRule;
    }

    /**
     * Get movable grids on the {@param board} for the {@param worker}
     * @param worker worker
     * @param board board that
     * @return a set of grids that this worker can move to on this board.
     */
    public HashSet<Grid> getMovableGrids(Worker worker, Board board) {
        HashSet<Grid> grids = new HashSet<>();
        for (Grid adjacent : board.getAdjacentGrids(worker.getPosition())) {
            if (moveRule.isValidMove(worker, board, adjacent.getX(), adjacent.getY())) {
                grids.add(adjacent);
            }
        }
        return grids;
    }

    /**
     * Get buildable grids on the {@param board} for the {@param worker}
     * @param worker worker
     * @param board board
     * @return a set of grids that this worker can build to on this board.
     */
    public HashSet<Grid> getBuildableGrids(Worker worker, Board board) {
        HashSet<Grid> grids = new HashSet<>();
        for (Grid adjacent : board.getAdjacentGrids(worker.getPosition())) {
            if (buildRule.isValidBuild(worker, board, adjacent.getX(), adjacent.getY())) {
                grids.add(adjacent);
            }
        }
        return grids;
    }

    /**
     * Check this worker is winner or not
     *
     * @param worker worker
     * @param oldHeight worker's previous height before most recent move
     * @return the worker is winner or not
     */
    public boolean isWinner(Worker worker, int oldHeight) {
        return winRule.isWinner(worker, oldHeight);
    }

    /**
     * Move the {@param worker} to the {@link Grid} at (x, y) on the board
     * @param worker worker
     * @param board board
     * @param x target x
     * @param y target y
     * @return move is successfully performed or not
     */
    public boolean move(Worker worker, Board board, int x, int y) {
        return moveRule.move(worker, board, x, y);
    }


    /**
     * The {@param worker} performs build on the {@link Grid} at (x, y) on the board
     * @param worker worker
     * @param board board
     * @param x target x
     * @param y target y
     * @return build is successfully performed or not
     */
    public boolean build(Worker worker, Board board, int x, int y) {
        return buildRule.build(worker, board, x, y);
    }

    /**
     * Whether the current state can be skipped
     * By default (No god card chosen), none of the state can be skipped
     * @param state state
     * @return  Whether the current state can be skipped
     */
    public boolean skip(GameState state) {
        return false;
    }

}
