package edu.cmu.cs214.hw3.models;

import edu.cmu.cs214.hw3.models.Grid;
import edu.cmu.cs214.hw3.models.Player;
import edu.cmu.cs214.hw3.models.Worker;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The test is mainly focused on testing {@link Grid}
 */
public class GridTest {

    @Test
    public void test_getter() {
        // test getter
        Grid grid = new Grid(3, 4);
        assertEquals(grid.getX(), 3);
        assertEquals(grid.getY(), 4);
    }

    @Test
    public void test_newGridNotOccupied() {
        // test initialization: grid is not occupied, height = 0, doesn't have dome
        Grid grid = new Grid(3, 4);
        assertFalse(grid.isOccupied());
        assertEquals(grid.getHeight(), 0);
        assertFalse(grid.hasDome());
    }


    @Test
    public void test_build_ToDome() {
        // after building on top of level 3, a dome should be placed
        // the grid should be occupied
        Grid grid = new Grid(3, 4);
        grid.build();
        assertFalse(grid.isOccupied());
        assertFalse(grid.hasDome());
        assertEquals(grid.getHeight(), 1);

        grid.build();
        assertFalse(grid.isOccupied());
        assertFalse(grid.hasDome());
        assertEquals(grid.getHeight(), 2);

        grid.build();
        assertFalse(grid.isOccupied());
        assertFalse(grid.hasDome());
        assertEquals(grid.getHeight(), 3);

        // on the top of third level, there should be a dome
        grid.build();
        assertTrue(grid.isOccupied());
        assertTrue(grid.hasDome());

        // after a dome is built, we can not build anymore
        assertFalse(grid.build());
    }

    @Test
    public void test_buildDomeAnyLevel(){
        Grid grid = new Grid(3, 4);
        grid.buildDome();
        assertTrue(grid.hasDome());
        assertEquals(grid.getHeight(), 0);
        assertTrue(grid.isOccupied());

    }

    @Test
    public void test_gridOccupiedByWorker() {
        // test setOccupied
        Player player = new Player();
        Worker worker = new Worker(player, "worker");
        Grid grid = new Grid(3, 4);

        grid.setOccupied(worker);

        assertTrue(grid.isOccupied());
        assertFalse(grid.hasDome());

    }


}
