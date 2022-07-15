package edu.cmu.cs214.hw3.models.actions.build;

import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Player;
import edu.cmu.cs214.hw3.models.Worker;
import edu.cmu.cs214.hw3.models.actions.build.DemeterBuild;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DemeterTest {
    DemeterBuild demeterBuild = new DemeterBuild();
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
    public void test_demeterBuild2Blocks() {
        board.initPosition(selectWorker, 2, 2);
        assertFalse(demeterBuild.build(selectWorker, board, 3,3));
        assertEquals(demeterBuild.getBuildLeft(), 1); // left
        assertTrue(demeterBuild.build(selectWorker, board, 1, 1));

        assertEquals(demeterBuild.getBuildLeft(), 2); // reset
        assertEquals(board.getGrid(3,3).getHeight(),1);
        assertEquals(board.getGrid(1,1).getHeight(),1);
    }


    @Test
    public void test_demeterBuildOnSame() {
        board.initPosition(selectWorker, 2, 2);
        // build1 and build2 on the same position
        assertFalse(demeterBuild.build(selectWorker, board, 3,3));
        assertEquals(demeterBuild.getBuildLeft(), 1);
        assertFalse(demeterBuild.build(selectWorker, board, 3, 3));
        assertEquals(demeterBuild.getBuildLeft(), 1);
        assertEquals(board.getGrid(3,3).getHeight(), 1);

    }

    @Test
    public void test_demeterRejectInvalidBuild(){
        board.initPosition(selectWorker, 2, 2);
        // destination2 (1,1) is occupied
        board.getGrid(1,1).setOccupied(new Worker(new Player()));

        assertFalse(demeterBuild.build(selectWorker, board, 3,3));
        assertEquals(board.getGrid(3,3).getHeight(),1);
        assertEquals(demeterBuild.getBuildLeft(), 1);

        assertFalse(demeterBuild.build(selectWorker, board, 1, 1));
        assertEquals(board.getGrid(1,1).getHeight(), 0);
        assertEquals(demeterBuild.getBuildLeft(), 1);

    }



}
