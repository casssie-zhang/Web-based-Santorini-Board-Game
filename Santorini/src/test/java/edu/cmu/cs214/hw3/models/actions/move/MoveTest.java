package edu.cmu.cs214.hw3.models.actions.move;

import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Grid;

import edu.cmu.cs214.hw3.models.Player;
import edu.cmu.cs214.hw3.models.Worker;
import edu.cmu.cs214.hw3.models.actions.move.DefaultMove;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveTest {

    DefaultMove defaultMove;
    Board gameBoard;
    Worker worker;

    @Before
    public void setup(){
        defaultMove = new DefaultMove();
        gameBoard = new Board(6,6);
        worker = new Worker(new Player());
    }

    @Test
    public void test_move_adjacent(){
        DefaultMove defaultMove = new DefaultMove();

        // test isValidMove: adjacent grids
        Grid grid1 = gameBoard.getGrid(3, 4);
        Grid grid2 = gameBoard.getGrid(4, 5);
        Grid grid3 = gameBoard.getGrid(2, 3);

        // grid1 is adjacent to grid2
        worker.setPosition(grid1);
        assertTrue(defaultMove.isValidMove(worker, gameBoard, 4,5));
        assertTrue(defaultMove.isValidMove(worker, gameBoard, 2, 3));

        // grid2 is not adjacent to grid3
        worker.setPosition(grid2);
        assertTrue(defaultMove.isValidMove(worker, gameBoard, 3, 4));
        assertFalse(defaultMove.isValidMove(worker, gameBoard, 2, 3));

        worker.setPosition(grid3);
        assertFalse(defaultMove.isValidMove(worker, gameBoard, 4, 5));

    }

    @Test
    public void test_move_DifferentLevel() {
        // test isValidMove: level difference >= 1
        Board gameBoard = new Board(6, 6);
        Grid grid1 = gameBoard.getGrid(3, 4);
        Grid grid2 = gameBoard.getGrid(4, 5);
        for (int i = 0; i < 3; i++) grid1.build(); // height=3
        grid2.build(); // height=1

        worker.setPosition(grid1);
        assertFalse(defaultMove.isValidMove(worker, gameBoard, 4, 5));

        // level difference now <= 1
        grid2.build(); // grid2 height=2
        assertTrue(defaultMove.isValidMove(worker, gameBoard, 4, 5));

        //grid1 is a dome
        worker.setPosition(grid2);
        grid1.build();
        assertFalse(defaultMove.isValidMove(worker, gameBoard, 3, 4));

    }

    @Test
    public void test_move_occupiedByWorkerOrDome() {
        // test isValidBuild: level difference >= 1
        Board gameBoard = new Board(6, 6);

        Grid grid1 = gameBoard.getGrid(3, 4);
        Grid grid2 = gameBoard.getGrid(4, 5);
        for (int i = 0; i < 3; i++) grid1.build(); // height=3
        for (int i = 0; i < 2; i++) grid2.build(); // height=2
        // clear
        worker.setPosition(grid2);
        assertTrue(defaultMove.isValidMove(worker, gameBoard, 3, 4));

        // grid1 is built with dome.
        grid1.build();
        assertFalse(defaultMove.isValidMove(worker, gameBoard, 3, 4));

    }



}
