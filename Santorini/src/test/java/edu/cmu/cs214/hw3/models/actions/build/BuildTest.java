package edu.cmu.cs214.hw3.models.actions.build;

import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Grid;
import edu.cmu.cs214.hw3.models.Player;
import edu.cmu.cs214.hw3.models.Worker;
import edu.cmu.cs214.hw3.models.actions.build.DefaultBuild;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BuildTest {
    DefaultBuild defaultBuild;
    Board gameBoard;
    Worker worker;

    @Before
    public void setup(){
        defaultBuild = new DefaultBuild();
        gameBoard = new Board(6,6);
        worker = new Worker(new Player());
    }

    @Test
    public void test_build_adjacent() {
        // test isValidBuild: adjacent grids
        Board gameBoard = new Board(6, 6);
        Grid grid1 = gameBoard.getGrid(3, 4);
        Grid grid2 = gameBoard.getGrid(4, 5);
//        Grid grid3 = gameBoard.getGrid(2, 3);

        // build can not happen on the current position
        grid1.setOccupied(worker);
        worker.setPosition(grid1);
        assertFalse(defaultBuild.isValidBuild(worker, gameBoard, 3, 4));

        // grid1 is adjacent to grid2
        assertTrue(defaultBuild.isValidBuild(worker, gameBoard, 4, 5));
        assertTrue(defaultBuild.isValidBuild(worker, gameBoard, 2, 3));

        worker.setPosition(grid2);
        // grid2 is not adjacent to grid3
        assertFalse(defaultBuild.isValidBuild(worker, gameBoard, 2, 3));

    }

    @Test
    public void test_build_occupiedByWorkerOrDome() {
        // test isValidBuild: level difference >= 1
        Board gameBoard = new Board(6, 6);

        Grid grid1 = gameBoard.getGrid(3, 4);
        Grid grid2 = gameBoard.getGrid(4, 5);

        for (int i = 0; i < 3; i++) grid1.build(); // height=3
        worker.setPosition(grid2);
        assertTrue(defaultBuild.isValidBuild(worker, gameBoard, 3, 4));

        // grid1 is occupied by a worker. so we can not build on grid1
        Worker anotherWorker = new Worker(new Player());
        grid1.setOccupied(anotherWorker);
        assertFalse(defaultBuild.isValidBuild(worker, gameBoard, 3, 4));

        // clear
        grid1.clearOccupied();
        assertTrue(defaultBuild.isValidBuild(worker, gameBoard,3, 4));

        // grid is built with dome.
        grid1.build();
        assertFalse(defaultBuild.isValidBuild(worker, gameBoard, 3, 4));

    }

    @Test
    public void test_normalBuild(){
        DefaultBuild defaultBuild = new DefaultBuild();
        gameBoard.initPosition(worker, 2, 2);
        assertTrue(defaultBuild.build(worker, gameBoard, 3, 3));
        // only the first build succeeds
        assertEquals(gameBoard.getGrid(3,3).getHeight(), 1);

//        assertFalse(defaultBuild.build(worker, gameBoard, 1, 1));
//        assertEquals(gameBoard.getGrid(1,1).getHeight(), 0);
    }
}
