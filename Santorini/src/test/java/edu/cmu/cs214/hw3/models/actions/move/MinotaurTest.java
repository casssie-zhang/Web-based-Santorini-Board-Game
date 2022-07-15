package edu.cmu.cs214.hw3.models.actions.move;
import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Grid;
import edu.cmu.cs214.hw3.models.Player;
import edu.cmu.cs214.hw3.models.Worker;
import edu.cmu.cs214.hw3.models.actions.move.MinotaurMove;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class MinotaurTest {
    MinotaurMove minotaurMove = new MinotaurMove();
    Board board;

    @Before
    public void setup() {
        board = new Board(5,5);

    }

    @Test
    public void test_forceMovePosition_outBound(){

        Grid workerPos;
        Grid opponentPos;
        workerPos = board.getGrid(3,3);
        opponentPos = board.getGrid(4,4);
        assertNull(minotaurMove.getForcePos(board, workerPos, opponentPos));

    }

    @Test
    public void test_forceMovePosition(){
        Grid workerPos = board.getGrid(2,2);
        Grid opponentPos = board.getGrid(3,3);
        assertEquals(minotaurMove.getForcePos(board, workerPos, opponentPos),
                board.getGrid(4,4));

    }


    @Test
    public void test_forceMove_occupied(){
        Player opponent = new Player();
        Player select = new Player();
        Worker opponentWorker = new Worker(opponent);
        Worker selectWorker = new Worker(select);

        board.initPosition(selectWorker, 2, 2);
        board.initPosition(opponentWorker, 3, 3);

        Grid dest = board.getGrid(4,4);
        assertTrue(minotaurMove.isValidMove(selectWorker, board, 3, 3));
        assertTrue(minotaurMove.move(selectWorker, board, 3, 3));

        assertEquals(dest.getOccupiedBy(), opponentWorker);
        assertEquals(board.getGrid(3,3).getOccupiedBy(), selectWorker);
        assertFalse(board.getGrid(2,2).isOccupied());

    }


}
