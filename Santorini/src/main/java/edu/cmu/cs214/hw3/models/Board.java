package edu.cmu.cs214.hw3.models;

import java.util.HashSet;

/**
 * Implements a Board which stores grids
 */
public class Board {
    private static final int[][] DIRECTIONS = new int[][]{
            {-1, -1}, {-1, 0}, {-1, 1}, {1, 0}, {1, 1}, {1, -1}, {0, -1}, {0, 1},
    };
    private final Grid[][] grids;
    private final int row;
    private final int col;


    public Board(int row, int col) {
        this.row = row;
        this.col = col;
        // initialize grids with height = 0
        grids = new Grid[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grids[i][j] = new Grid(i, j);
            }
        }
    }

    /**
     * Get grid object based on coordinates
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return grid object with given coordinates
     */
    public Grid getGrid(int x, int y) {
        return grids[x][y];
    }

    /**
     * Get all grids
     * @return all grids on this Board
     */
    public Grid[][] getAllGrids() {
        return grids;
    }


    /**
     * Get adjacent grids of a position
     * @param grid the grid in the center
     * @return adjacent grids (within bound)
     */
    public HashSet<Grid> getAdjacentGrids(Grid grid) {
        HashSet<Grid> grids = new HashSet<>();

        int x = grid.getX();
        int y = grid.getY();

        for (int[] direction : DIRECTIONS) {
            int xx = x + direction[0];
            int yy = y + direction[1];
            if (checkBound(xx, yy)) grids.add(getGrid(xx, yy));
        }
        return grids;
    }

    public boolean isValidMove(Grid src, Grid dest) {
        return withinAdjacent(src, dest) && withinLevel(src, dest);
    }

    /**
     * Check whether a worker at {@link Grid} src can build at the {@link Grid} dest.
     * The destination cannot be occupied and must be adjacent with current position
     * This only applies to default rules.
     *
     * @param src src to build from (worker's position)
     * @param dest target to build at
     * @return Whether this is a valid build
     */
    public boolean isValidBuild(Grid src, Grid dest) {
        return withinAdjacent(src, dest) && !dest.isOccupied();
    }


    /**
     * Initialize a worker's position
     * @param worker worker to be initialized position
     * @param x target x
     * @param y target y
     * @return whether initialization is successful
     */
    public boolean initPosition(Worker worker, int x, int y) {
        if (checkBound(x, y)) {
            Grid dest = getGrid(x, y);
            if (!dest.isOccupied() && worker.getPosition() == null) {
                worker.setPosition(dest);
                dest.setOccupied(worker);
                return true;
            }

        }
        return false;
    }

    /**
     * Move the {@link Worker} to the {@link Grid} destination
     * @param worker worker to be moved
     * @param dest destination of move
     */
    public void move(Worker worker, Grid dest) {
        dest.setOccupied(worker);
        worker.getPosition().clearOccupied();
        worker.setPosition(dest);
    }

    /**
     * A helper function to check whether src and target is adjacent to each other
     * @param src source Grid
     * @param target target Grid
     * @return the two grids are adjacent to each other or not
     */
    public boolean withinAdjacent(Grid src, Grid target) {
        return Math.abs(target.getX() - src.getX()) <= 1 && Math.abs(target.getY() - src.getY()) <= 1;
    }

    /**
     * A helper function to check whether src and target is within the movable level
     * (their height difference < 1)
     * @param src source Grid
     * @param target target Grid
     * @return the two grids are in the movable level to each other or not
     */
    public boolean withinLevel(Grid src, Grid target) {
        return Math.abs(target.getHeight() - src.getHeight()) <= 1;
    }

    /**
     * Helper function to check whether request coordinates are within boundary
     *
     * @param x x
     * @param y y
     * @return (x, y) is in Boundary
     */
    public boolean checkBound(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }


    /**
     * print current board
     * This method is mainly for debugging purpose
     * Height and occupation will be displayed
     */
    public void printBoard() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grids[i][j].isOccupied()) {
                    System.out.printf("%15s(%s)", grids[i][j].getOccupiedBy().getName(), grids[i][j].getHeight());
                } else {
                    System.out.printf("%18s", grids[i][j].getHeight());
                }
                System.out.print(" ");

            }
            System.out.print("\n");
        }
        System.out.println("\n");
    }
}
