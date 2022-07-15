package edu.cmu.cs214.hw3.models.gods;

import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Player;
import edu.cmu.cs214.hw3.models.Worker;
import edu.cmu.cs214.hw3.models.actions.move.ArtemisMove;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A simple test of god cards rulesets
 */

public class GodsTest {
    Board board;

    Player select;
    Worker selectWorker;


    @Before
    public void setup() {
        board = new Board(5,5);
        select = new Player();
        selectWorker = new Worker(select);
    }

    @Test
    public void test_demeterGod(){
        Demeter demeter = new Demeter();
        board.initPosition(selectWorker, 2, 2);
        assertFalse(demeter.build(selectWorker, board, 3, 3));

        assertTrue(demeter.build(selectWorker, board, 1, 1));
        assertEquals(board.getGrid(3,3).getHeight(),1);
        assertEquals(board.getGrid(1,1).getHeight(),1);
    }

    @Test
    public void test_minotaurGod(){
        Minotaur minotaur = new Minotaur();
        board.initPosition(selectWorker, 2, 2);
        Worker opponentWorker = new Worker(new Player());
        board.initPosition(opponentWorker, 3, 3);

        assertTrue(minotaur.move(selectWorker, board, 3, 3));

        assertEquals(selectWorker.getPosition(), board.getGrid(3,3));
        assertEquals(opponentWorker.getPosition(), board.getGrid(4, 4));
        assertFalse(board.getGrid(2,2).isOccupied());
    }

    @Test
    public void test_PanWin(){
        Pan pan = new Pan();
        board.initPosition(selectWorker, 2, 2);

        assertTrue(pan.isWinner(selectWorker, 2));

        board.getGrid(2,2).build();
        board.getGrid(2,2).build();
        board.getGrid(2,2).build();
        assertTrue(pan.isWinner(selectWorker, 2));
    }

    @Test
    public void test_ApolloMove(){
        Apollo apollo = new Apollo();
        board.initPosition(selectWorker, 2,2);

        Worker opponentWorker = new Worker(new Player());
        board.initPosition(opponentWorker, 3, 3);

        assertTrue(apollo.move(selectWorker, board, 3,3));
        assertEquals(board.getGrid(3,3).getOccupiedBy(), selectWorker);
        assertEquals(board.getGrid(2,2).getOccupiedBy(), opponentWorker);
    }

    @Test
    public void  test_ArtemisMove(){
        Artemis artemis = new Artemis();
        board.initPosition(selectWorker, 2,2);

        // first move
        assertFalse(artemis.move(selectWorker, board, 3, 3));
        assertEquals(((ArtemisMove) artemis.getMoveRule()).getMoveLeft(), 1);
        assertEquals(selectWorker.getPosition(), board.getGrid(3, 3));
        assertTrue(board.getGrid(3,3).isOccupied());

        // second move
        assertFalse(artemis.move(selectWorker, board, 2, 2)); // cannot move back to original place
        assertEquals(selectWorker.getPosition(), board.getGrid(3, 3));
        assertTrue(board.getGrid(3,3).isOccupied());
        assertFalse(board.getGrid(2,2).isOccupied());

        assertTrue(artemis.move(selectWorker, board, 3, 4)); // cannot move back to original place
        assertEquals(selectWorker.getPosition(), board.getGrid(3, 4));
        assertTrue(board.getGrid(3,4).isOccupied());
        assertFalse(board.getGrid(3,3).isOccupied());

    }

    @Test
    public void  test_ArtemisMove_Skip(){
        Artemis artemis = new Artemis();
        board.initPosition(selectWorker, 2,2);

        // first move
        assertFalse(artemis.move(selectWorker, board, 3, 3));
        assertEquals(((ArtemisMove) artemis.getMoveRule()).getMoveLeft(), 1);
        assertEquals(selectWorker.getPosition(), board.getGrid(3, 3));
        assertTrue(board.getGrid(3,3).isOccupied());

        // second move
        assertTrue(artemis.move(selectWorker, board, -1, -1)); // cannot move back to original place
        assertEquals(selectWorker.getPosition(), board.getGrid(3, 3));
        assertTrue(board.getGrid(3,3).isOccupied());

    }

    @Test
    public void test_AtlasBuild(){
        Atlas atlas = new Atlas();
        board.initPosition(selectWorker, 2, 2);
        assertFalse(atlas.build(selectWorker, board, 3, 3));
        assertEquals(board.getGrid(3,3).getHeight(), 1);
        assertFalse(board.getGrid(3,3).hasDome());

        // reject invalid request
        atlas.build(selectWorker, board, 2, 3);
        assertFalse(atlas.getBuildRule().isValidBuild(selectWorker, board, 2,3));
        assertEquals(board.getGrid(2,3).getHeight(), 0);

        // build with dome
        atlas.build(selectWorker, board, 3, 3);
        assertEquals(board.getGrid(3,3).getHeight(), 1);
        assertTrue(board.getGrid(3,3).hasDome());
    }

    @Test
    public void test_AtlasBuild_Skip(){
        Atlas atlas = new Atlas();
        board.initPosition(selectWorker, 2, 2);
        assertFalse(atlas.build(selectWorker, board, 3, 3));
        assertEquals(board.getGrid(3,3).getHeight(), 1);
        assertFalse(board.getGrid(3,3).hasDome());

        assertTrue(atlas.build(selectWorker, board, -1, -1));
        assertEquals(board.getGrid(3,3).getHeight(), 1);
        assertFalse(board.getGrid(3,3).hasDome());

    }
}
