package edu.cmu.cs214.hw3.models;

import edu.cmu.cs214.hw3.models.gods.NoGod;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class GameTest {
    Player player1;
    Player player2;


    Game game;

    @Before
    public void setup() {
        player1 = new Player(new NoGod(), "Player1");
        player2 = new Player(new NoGod(), "Player2");
        game = new Game(player1, player2);
    }


    @Test
    public void test_newGame_rejectWrongSequence() {
        assertEquals(game.getGameState(), GameState.InitWorker);
        assertFalse(game.move(3, 4));
        assertFalse(game.build(3, 4));
        assertFalse(game.selectWorker(2, 3));

    }

    @Test
    public void test_rejectOutBoundGrid() {
        // initWorker
        assertFalse(game.initWorker(5, 5));
        assertFalse(game.initWorker(5, 0));
        assertFalse(game.initWorker(0, 5));
        assertFalse(game.initWorker(-1, -1));
    }

    @Test
    public void test_startGameMustInitAllWorkers() {
        // initWorker
        assertTrue(game.initWorker(1, 2));
        assertEquals(game.getGameState(), GameState.InitWorker);

        game.initWorker(2, 3);
        assertEquals(game.getGameState(), GameState.InitWorker);

        game.initWorker(2, 1);
        assertEquals(game.getGameState(), GameState.InitWorker);

        game.initWorker(3, 2);
        assertEquals(game.getGameState(), GameState.SelectWorker);

        assertFalse(game.initWorker(1, 2));
    }
}
