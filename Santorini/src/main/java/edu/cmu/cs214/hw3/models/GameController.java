package edu.cmu.cs214.hw3.models;

public class GameController {

    private GameState state;

    GameController() {
        // When start game we first need to initialize worker positions
        state = GameState.InitWorker;
    }

    /**
     * Move the game to the next state
     */
    public void nextState() {
        state = getNextState();
    }

    public void gameOver() {
        state = GameState.GameOver;
    }

    /**
     * change game state to the next state based on current state
     *
     * @return next state of the game
     */
    public GameState getNextState() {
        switch (state) {
            case SelectWorker:
                return GameState.Move;
            case Move:
                return GameState.Build;
            case Build:
            case InitWorker:
                return GameState.SelectWorker;
            case GameOver:
                return GameState.GameOver; // always game over
            default:
                return state;
        }

    }

    /**
     * Get current state
     * @return current state
     */
    public GameState getState() {
        return state;
    }


}
