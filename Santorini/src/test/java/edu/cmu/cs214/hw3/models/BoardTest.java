package edu.cmu.cs214.hw3.models;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class BoardTest {
    Board board;
    Worker worker;
    Player player;

    @Before
    public void setup() {
        board = new Board(5,5);
        player = new Player("somePlayer");
        worker = new Worker(player);
    }

    @Test
    public void test_checkBound() {
        assertTrue(board.checkBound(2,2));
        assertFalse(board.checkBound(5,5));
        assertTrue((board.checkBound(0,0)));
        assertFalse(board.checkBound(-1, 0));
        assertFalse(board.checkBound(-1, -1));
        assertFalse(board.checkBound(0, 5));
    }

    @Test
    public void test_withinAdjacent() {
        assertTrue(board.withinAdjacent(board.getGrid(0,1), board.getGrid(1,1)));
        assertTrue(board.withinAdjacent(board.getGrid(0,1), board.getGrid(1,2)));
        assertFalse(board.withinAdjacent(board.getGrid(0,1), board.getGrid(2,2)));
    }



    @Test
    public void test_getAdjacentGrids(){
        Board gameBoard = new Board(6, 6);
        HashSet<Grid> adjacent = gameBoard.getAdjacentGrids(gameBoard.getGrid(0,3));
        assertEquals(adjacent.size(), 5);
        System.out.println("Grid(0,3) adjacent grids");
        System.out.println(adjacent);

    }

    /**
     * Integration test with {@link Worker}
     */

    @Test
    public void test_workerInit(){
        board.initPosition(worker, 3,3);
        assertTrue(board.getGrid(3,3).isOccupied());
        assertEquals(worker.getPosition(), board.getGrid(3,3));
    }

    @Test
    public void test_workerMove() {
        board.initPosition(worker, 3,3);
        board.move(worker, board.getGrid(4,4));

        assertFalse(board.getGrid(3,3).isOccupied());
        assertTrue(board.getGrid(4,4).isOccupied());
        assertEquals(worker.getPosition(), board.getGrid(4,4));
    }


}
