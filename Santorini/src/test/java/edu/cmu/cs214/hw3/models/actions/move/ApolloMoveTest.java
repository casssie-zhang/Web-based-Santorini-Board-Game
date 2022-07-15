package edu.cmu.cs214.hw3.models.actions.move;

import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Player;
import edu.cmu.cs214.hw3.models.Worker;
import edu.cmu.cs214.hw3.models.actions.move.ApolloMove;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApolloMoveTest {
    ApolloMove apolloMove = new ApolloMove();
    Board board;

    @Before
    public void setup() {
        board = new Board(5,5);

    }

    @Test
    public void test_apolloMove() {
        Player opponent = new Player();
        Player select = new Player();
        Worker opponentWorker = new Worker(opponent);
        Worker selectWorker = new Worker(select);

        board.initPosition(selectWorker, 2,2);
        board.initPosition(opponentWorker, 3, 3);

        assertTrue(apolloMove.move(selectWorker, board, 3,3));
        assertEquals(board.getGrid(3,3).getOccupiedBy(), selectWorker);
        assertEquals(board.getGrid(2,2).getOccupiedBy(), opponentWorker);
        assertEquals(selectWorker.getPosition(), board.getGrid(3,3));
        assertEquals(opponentWorker.getPosition(), board.getGrid(2, 2));
    }

    @Test
    public void test_apolloMove_OccupiedByAlly() {
        Player select = new Player();
        Worker allyWorker = new Worker(select);
        Worker selectWorker = new Worker(select);

        board.initPosition(selectWorker, 2,2);
        board.initPosition(allyWorker, 3, 3);

        assertFalse(apolloMove.move(selectWorker, board, 3,3));
        assertEquals(board.getGrid(3,3).getOccupiedBy(), allyWorker);
        assertEquals(board.getGrid(2,2).getOccupiedBy(), selectWorker);
        assertEquals(allyWorker.getPosition(), board.getGrid(3,3));
        assertEquals(selectWorker.getPosition(), board.getGrid(2, 2));
    }


}
