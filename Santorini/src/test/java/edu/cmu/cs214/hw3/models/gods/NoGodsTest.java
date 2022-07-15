package edu.cmu.cs214.hw3.models.gods;

import edu.cmu.cs214.hw3.models.Game;
import edu.cmu.cs214.hw3.models.GameState;
import edu.cmu.cs214.hw3.models.Player;
import edu.cmu.cs214.hw3.models.Worker;
import edu.cmu.cs214.hw3.models.gods.NoGod;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Structural Testing for the game process
 * No God!
 */

public class NoGodsTest {


    Player player1;
    Player player2;

//    Worker worker1p1;
//    Worker worker2p1;
//
//    Worker worker1p2;
//    Worker worker2p2;

    Game game;

    @Before
    public void setup() {
        player1 = new Player(new NoGod(), "Player1");
        player2 = new Player(new NoGod(), "Player2");

//        worker1p1 = new Worker(player1, "worker1p1");
//        worker2p1 = new Worker(player1, "worker2p1");
//
//        worker1p2 = new Worker(player2, "worker1p2");
//        worker2p2 = new Worker(player2, "worker2p2");

        game = new Game(player1, player2);
    }



    @Test
    public void test_newGame_rejectWrongSequence() {
        Assert.assertEquals(game.getGameState(), GameState.InitWorker);
        assertFalse(game.move(3, 4));
        assertFalse(game.build(3, 4));
        assertFalse(game.selectWorker(1,2));

    }

    @Test
    public void test_rejectOutBoundGrid() {
        // initWorker
//        assertFalse(game.initWorker(worker1p1, 5, 5));
        game.initWorker(5, 5);

    }

    @Test
    public void test_gameRejectIllegalAction() {
        // the winning process

        //init
        game.initWorker(1, 2); // worker1p1
        game.initWorker(2, 3); // worker2p1

        assertEquals(game.getCurrPlayer(), player2);
        assertTrue(game.initWorker(2, 1)); // worker1p2
        game.initWorker(3, 2); // worker2p2

        Worker worker1p1 = game.getGameBoard().getGrid(1,2).getOccupiedBy();

        // ROUND 1
        assertEquals(game.getGameState(), GameState.SelectWorker);
        assertEquals(game.getCurrPlayer(), player1);
        // worker1: move
        game.selectWorker(worker1p1);
        assertEquals(game.getGameState(), GameState.Move);

        assertTrue(game.move(0, 3));
        assertEquals(game.getGameState(), GameState.Build);
        game.build(1, 3);
        assertEquals(game.getGameState(), GameState.SelectWorker);
        assertEquals(game.getCurrPlayer(), player2);

        game.selectWorker(3,2);
        game.move(3, 3);
        game.build(2, 2);
        assertEquals(game.getGameBoard().getGrid(2, 2).getHeight(), 1);
        game.getGameBoard().printBoard();
    }

    @Test
    public void test_wholeGame() {
        // the winning process

        //init
//        game.initWorker(worker1p1, 1, 2);
//        game.initWorker(worker2p1, 2, 3);
        game.initWorker(1, 2); // worker1p1
        game.initWorker(2, 3); // worker2p1

        assertEquals(game.getCurrPlayer(), player2);
//        game.initWorker(worker1p2, 2, 1);
//        game.initWorker(worker2p2, 3, 2);
        game.initWorker(2, 1); // worker1p2
        game.initWorker(3, 2); // worker2p2
//        game.startGame();

        Worker worker1p1 = game.getGameBoard().getGrid(1,2).getOccupiedBy();
        Worker worker2p1 = game.getGameBoard().getGrid(2,3).getOccupiedBy();
        Worker worker1p2 = game.getGameBoard().getGrid(2,1).getOccupiedBy();
        Worker worker2p2 = game.getGameBoard().getGrid(3,2).getOccupiedBy();

        // ROUND 1
        // worker1: move
        System.out.println("--- ROUND 1 ---");
        assertTrue(game.selectWorker(worker1p1));
        assertTrue(game.move(0, 3));
        assertTrue(game.build(1, 3));


        assertTrue(game.selectWorker(worker2p2));
        assertTrue(game.move(3, 3));
        assertTrue(game.build(2, 2));
        game.getGameBoard().printBoard();
        assertFalse(game.isGameOver());

        System.out.println("--- ROUND 2 ---");
        game.selectWorker(worker1p1);
        assertTrue(game.move(0, 2));
        assertTrue(game.build(1, 2));

        game.selectWorker(worker2p2);
        assertTrue(game.move(2, 2));
        assertTrue(game.build(1, 3));
        game.getGameBoard().printBoard();
        assertFalse(game.isGameOver());

        System.out.println("--- ROUND 3 ---");
        game.selectWorker(worker1p1);
        assertTrue(game.move(1, 2));
        assertTrue(game.build(0, 3));

        game.selectWorker(worker2p2);
        assertTrue(game.move(1, 3));
        assertTrue(game.build(2, 2));
        game.getGameBoard().printBoard();
        assertFalse(game.isGameOver());

        System.out.println("--- ROUND 4 ---");
        game.selectWorker(worker1p1);
        assertTrue(game.move(1, 1));
        assertTrue(game.build(2, 2));

        assertTrue(game.selectWorker(worker2p2));
        assertTrue(game.move(2, 2));
        game.getGameBoard().printBoard();


        assertTrue(game.isGameOver());

        // reject further move
        assertFalse(game.selectWorker(worker1p1));
        assertEquals(game.getGameState(), GameState.GameOver);
    }


}
