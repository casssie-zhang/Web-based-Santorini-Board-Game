package edu.cmu.cs214.hw3.models;

import edu.cmu.cs214.hw3.models.GameController;
import edu.cmu.cs214.hw3.models.GameState;
import edu.cmu.cs214.hw3.models.Grid;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The test is mainly focused on testing {@link Grid}
 */

public class ControllerTest {
    GameController controller;

    @Before
    public void setup() {
        controller = new GameController();
    }

    @Test
    public void test_controllerSequence() {

        assertEquals(controller.getState(), GameState.InitWorker);

        assertEquals(controller.getNextState(), GameState.SelectWorker);
        controller.nextState();

        assertEquals(controller.getNextState(), GameState.Move);
        controller.nextState();

        assertEquals(controller.getNextState(), GameState.Build);
        controller.nextState();

        // go back to select worker
        assertEquals(controller.getNextState(), GameState.SelectWorker);
        controller.nextState();
    }

    @Test
    public void test_GameOver() {
        controller.gameOver();
        assertEquals(controller.getState(), GameState.GameOver);

        assertEquals(controller.getNextState(), GameState.GameOver);
    }


}
