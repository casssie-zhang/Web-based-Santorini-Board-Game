package edu.cmu.cs214.hw3.models.actions.build;

import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Grid;
import edu.cmu.cs214.hw3.models.Worker;

public class HephaestusBuild implements BuildRule {
    private static final int BUILD_ALLOWED = 2;
    private int x1;
    private int y1;
    private int buildLeft;

    public HephaestusBuild() {
        reset();
    }

    public int getBuildLeft() {
        return buildLeft;
    }

    private void reset() {
        assert (buildLeft == 0);
        x1 = -1;
        y1 = -1;
        buildLeft = BUILD_ALLOWED;
    }


    @Override
    public boolean isValidBuild(Worker worker, Board board, int x, int y) {
        if (board.checkBound(x, y)) {
            Grid dest = board.getGrid(x, y);
            if (board.isValidBuild(worker.getPosition(), dest)) {
                if (buildLeft == BUILD_ALLOWED) return true;
                if (buildLeft == 1) {
                    return dest.getHeight() < 3 && x == x1 && y == y1;
                }

            }
        }
        return false;
    }

    @Override
    public boolean build(Worker worker, Board board, int x, int y) {
        if (buildLeft == BUILD_ALLOWED) { // first build, return false anyway
            if (isValidBuild(worker, board, x, y)) {
                x1 = x;
                y1 = y;
                Grid dest = board.getGrid(x, y);
                dest.build();
                buildLeft--; // build minus 1
            }
        } else if (buildLeft == 1) { // second build
            if (x == -1 && y == -1) { // skip
                buildLeft--;
                reset();
                return true;
            } else if (x == x1 && y == y1) { // same position
                if (isValidBuild(worker, board, x, y)) {
                    Grid dest = board.getGrid(x, y);
                    dest.build();
                    buildLeft--;
                    reset(); // reset
                    return true;
                }
            }
        }
        return false;
    }
}
