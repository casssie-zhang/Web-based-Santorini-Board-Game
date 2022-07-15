package edu.cmu.cs214.hw3.models;

public class Grid {
    private final int x;
    private final int y;
    private Worker occupiedBy;
    private boolean hasDome;
    private int height;

    Grid(int x, int y) {
        this.x = x;
        this.y = y;
        occupiedBy = null;
        hasDome = false;
        height = 0;
    }


    public int getHeight() {
        return height;
    }

    /**
     * Check whether this Grid has a dome
     *
     * @return Grid has a dome or not
     */
    public boolean hasDome() {
        return hasDome;
    }


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Worker getOccupiedBy() {
        return this.occupiedBy;
    }


    /**
     * Check if this Grid is occupied.
     * A grid is occupied either by a {@link Worker} or a dome
     *
     * @return this grid is occupied or not
     */
    public boolean isOccupied() {
        return hasDome || (occupiedBy != null);
    }


    /**
     * Mark the grid is occupied the worker
     *
     * @param worker worker to be moved on this grid
     */
    public void setOccupied(Worker worker) {
        occupiedBy = worker;
    }

    public void setDome(Worker worker) {
        if (height == 3) hasDome = true;
    }

    /**
     * clear occupied worker
     */
    public void clearOccupied() {
        occupiedBy = null;
    }

    /**
     * build a base block or a dome depending on the height of current position
     * height < 3: build a tower
     * height = 3: build a dome
     * occupied: occupied, reject
     *
     * @return whether the build is successful or not
     */
    public boolean build() {
        if (isOccupied()) return false;

        if (height == 3) hasDome = true;
        else {
            height += 1;
        }
        return true;
    }

    public boolean buildDome() {
        if (isOccupied()) return false;
        hasDome = true;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder gridStr = new StringBuilder();
        for (int i = 0; i < height; i++) {
            gridStr.append("[");
        }
        if (hasDome) gridStr.append("Dome");
        else if (isOccupied()) {
            gridStr.append(occupiedBy.getName());
        }
        for (int i = 0; i < height; i++) {
            gridStr.append("]");
        }
        return gridStr.toString();
    }
}
