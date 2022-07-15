package edu.cmu.cs214.hw3.models;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * The test is mainly focused on testing {@link Worker}
 * {@link Worker} are integrated with {@link Grid} to test
 */
public class WorkerTest {

    Player player;
    Worker worker;

    @Before
    public void setup() {
        player = new Player();
        worker = new Worker(player);
    }

    @Test
    public void test_position() {
        player = new Player();
        worker = new Worker(player);
        Grid grid = new Grid(2,2);
        worker.setPosition(grid);

        assertEquals(worker.getPosition(), grid);
        assertEquals(worker.getHeight(), 0);

        grid.build();
        assertEquals(worker.getHeight(), 1);
    }

    @Test
    public void test_getters() {
        player = new Player();
        worker = new Worker(player);
        Grid grid = new Grid(2,2);
        worker.setPosition(grid);

        assertEquals(worker.getPlayer(), player);
    }
}
