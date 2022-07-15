package edu.cmu.cs214.hw3.models.gods;

import edu.cmu.cs214.hw3.models.Game;
import edu.cmu.cs214.hw3.models.Player;
import edu.cmu.cs214.hw3.models.Worker;
import edu.cmu.cs214.hw3.models.gods.Demeter;
import edu.cmu.cs214.hw3.models.gods.Pan;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PanLoseDemeterWin {
    Player player1;
    Player player2;

    Game game;

    @Before
    public void setup() {
        player1 = new Player(new Demeter(), "Player1");
        player2 = new Player(new Pan(), "Player2");

        game = new Game(player1, player2);
    }

    @Test
    public void test_wholeGame() {
        // the winning process

        //init
        game.initWorker(1, 2);
        game.initWorker(2, 3);

        assertEquals(game.getCurrPlayer(), player2);
        game.initWorker(2, 1);
        game.initWorker(3, 2);

        Worker worker1p1 = game.getGameBoard().getGrid(1,2).getOccupiedBy();
        Worker worker2p1 = game.getGameBoard().getGrid(2,3).getOccupiedBy();
        Worker worker1p2 = game.getGameBoard().getGrid(2,1).getOccupiedBy();
        Worker worker2p2 = game.getGameBoard().getGrid(3,2).getOccupiedBy();


        System.out.println("--- Game Start ---");
        game.getGameBoard().printBoard();


        // ROUND 1
        // worker1: move
        System.out.println("--- ROUND 1 ---");
        assertTrue(game.selectWorker(worker1p1));
        assertTrue(game.move(0, 3));
        assertFalse(game.build(1, 3));
        assertTrue(game.build(0,2));


        assertTrue(game.selectWorker(worker2p2));
        assertTrue(game.move(3, 3));
        assertTrue(game.build(2, 2));
        game.getGameBoard().printBoard();
        assertFalse(game.isGameOver());

        System.out.println("--- ROUND 2 ---");
        game.selectWorker(worker1p1);
        assertTrue(game.move(0, 2));
        assertFalse(game.build(1, 2));
        assertTrue(game.build(-1, -1)); // skip

        game.selectWorker(worker2p2);
        assertTrue(game.move(2, 2));
        assertTrue(game.build(1, 3));
        game.getGameBoard().printBoard();
        assertFalse(game.isGameOver());

        System.out.println("--- ROUND 3 ---");
        game.selectWorker(worker1p1);
        assertTrue(game.move(1, 3));
        assertFalse(game.build(1, 2));
        assertTrue(game.build(0, 3));

        game.selectWorker(worker1p2);
        assertTrue(game.move(1,1));
        assertTrue(game.build(1, 2));

        game.getGameBoard().printBoard();
        assertFalse(game.isGameOver());

        System.out.println("--- ROUND 4 ---");
        game.selectWorker(worker1p1);
        assertTrue(game.move(1, 2));
        assertTrue(game.isGameOver());
        game.getGameBoard().printBoard();
//
//        // reject further move
//        assertFalse(game.selectWorker(worker1p1));
//        Assert.assertEquals(game.getGameState(), GameState.GameOver);
    }
}
