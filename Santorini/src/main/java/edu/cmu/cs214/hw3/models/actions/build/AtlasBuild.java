package edu.cmu.cs214.hw3.models.actions.build;

import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Grid;
import edu.cmu.cs214.hw3.models.Worker;

public class AtlasBuild implements BuildRule {

    private int x1;
    private int y1;
    private boolean firstBuild;

    public AtlasBuild() {
        reset();
    }

    private void reset() {
        x1 = -1;
        y1 = -1;
        firstBuild = false;
    }

    public boolean getFirstBuild() {
        return firstBuild;
    }


    @Override
    public boolean isValidBuild(Worker worker, Board board, int x, int y) {
        if (board.checkBound(x, y)) {
            Grid dest = board.getGrid(x, y);
            if (board.isValidBuild(worker.getPosition(), dest)) {
                if (!firstBuild) return true;
                else return x == x1 && y == y1;
            }
        }
        return false;
    }

    @Override
    public boolean build(Worker worker, Board board, int x, int y) {
        if (!firstBuild) { // first build, return false anyway
            if (isValidBuild(worker, board, x, y)) {
                x1 = x;
                y1 = y;
                Grid dest = board.getGrid(x, y);
                dest.build();
                if (dest.hasDome()) { // already build with dome
                    reset();
                    return true;
                } else {
                    firstBuild = true;
                    return false;
                }

            }
        } else { // second build
            if (x == -1 && y == -1) { // skip dome
                reset();
                return true;
            } else if (x == x1 && y == y1) { // same position build dome!
                if (isValidBuild(worker, board, x, y)) {
                    Grid dest = board.getGrid(x, y);
                    dest.buildDome();
                    reset(); // reset
                    return true;
                }
            }
        }
        return false;

    }
}
