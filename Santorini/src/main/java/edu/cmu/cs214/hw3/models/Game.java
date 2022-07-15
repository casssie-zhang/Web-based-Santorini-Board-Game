package edu.cmu.cs214.hw3.models;


import edu.cmu.cs214.hw3.models.gods.God;

import java.util.HashSet;

/**
 * Santorini's main entrance for user interactions
 */
public class Game {

    private final Player player1;
    private final Player player2;
    private final GameController controller;
    private final Board gameBoard;
    private Player currPlayer;
    private Player winner;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        gameBoard = new Board(5, 5);
        winner = null;

        // the game always start with player1
        currPlayer = player1;
        controller = new GameController();
    }

    public Player getCurrPlayer() {
        return currPlayer;
    }

    public GameState getGameState() {
        return controller.getState();
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public Player getWinner() {
        return winner;
    }

    /**
     * End of Getters
     */

    /**
     * Check game over
     *
     * @return If either player 1 or player 2 is winner, return true
     */
    public boolean isGameOver() {
        return winner != null;
    }

    /**
     * Switch currentPlayer to another player
     */
    private void switchPlayer() {
        currPlayer = currPlayer.equals(player1) ? player2 : player1;
    }


    /**
     * Initialize {@link Worker}'s position to {@link Grid} at (x, y)
     * Change the turn of player if all of his workers are initialized
     * Start the game if both players' workers are initialized
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return initialization is successful or not
     */
    public boolean initWorker(int x, int y) {
        if (controller.getState().equals(GameState.InitWorker)) {
            Worker worker = new Worker(currPlayer);
            if (gameBoard.initPosition(worker, x, y)) {
                if (currPlayer.workersAllInit()) // switch turn
                    switchPlayer();
                startGame(); // start game if possible
                return true;
            }
        }
        return false;
    }


    /**
     * Check if all players' workers have been initialized
     * If yes, change the {@link GameState} to next state (SelectWorker)
     * If no, we still need to change state
     */
    private void startGame() {
        if (player1.workersAllInit() && player2.workersAllInit()) {
            controller.nextState();
        }
    }


    /**
     * Let current player's god decide whether next action can be skipped
     * This can be second move, second build, etc.
     * @return whether next action can be skipped
     */
    public boolean nextCanSkip() {
        return currPlayer.getGod().skip(getGameState());
    }

    /**
     * current player select worker
     * preconditions:
     * Current state = Select Worker
     *
     * @param x target x
     * @param y target y
     * @return select worker is successful or not
     */
    public boolean selectWorker(int x, int y) {
        if (controller.getState().equals(GameState.SelectWorker)) {
            if (gameBoard.checkBound(x, y)) {
                Worker selected = gameBoard.getGrid(x, y).getOccupiedBy();
                if (selected != null) return selectWorker(selected);

            }
        }
        return false;
    }

    /**
     * current player select worker
     * preconditions:
     * The selected worker must belong to current player
     * Current state = Select Worker
     *
     * @param selected {@link Worker}
     * @return select worker is successful or not
     */
    public boolean selectWorker(Worker selected) {
        if (controller.getState().equals(GameState.SelectWorker)) {
            if (selected.getPlayer().equals(currPlayer)) {
                currPlayer.setSelectedWorker(selected);
                controller.nextState();
                return true;
            }
        }
        return false;
    }


    /**
     * Move a {@link Worker} to a {@link Grid} at (x,y)
     * preconditions:
     * The player is selected and the worker belongs to the current player
     * The worker matches the selected worker
     * x,y within the boundary of the board
     * x,y must be adjacent to the current worker’s location
     * Destination’s level must be within one level of the current worker’s level
     * Destination (x,y) must not be occupied by any of the workers or a dome
     *
     * post-conditions:
     * update worker’s location
     * mark the destination (x,y) as occupied
     * mark the previous location as non-occupied
     * If destination’s height is 3, mark the current player as winner
     * <p>
     *
     * @param x x coordinates of destination
     * @param y y coordinates of destination
     * @return move is successful or not
     */
    public boolean move(int x, int y) {
        if (controller.getState().equals(GameState.Move)) {
            Worker worker = currPlayer.getSelectedWorker();

            God thisGod = currPlayer.getGod();
            int oldHeight = worker.getHeight();

            boolean res = false;

            if (thisGod.move(worker, gameBoard, x, y)) {
                controller.nextState();
                res = true;
            }

            if (thisGod.isWinner(worker, oldHeight)) {
                winner = currPlayer;
                controller.gameOver();
                res = true;
            }

            return res;
        }

        return false;
    }

    /**
     * Given current game's state, return the grids that is legal for this action
     * If Move: movable cell, If Build: buildable cells
     *
     * @return a set of grids that is valid for current state
     */
    public HashSet<Grid> getAvailableGrids() {
        GameState state = getGameState();
        Worker worker = currPlayer.getSelectedWorker();
        God thisGod = currPlayer.getGod();

        switch (state) {
            case Move:
                return thisGod.getMovableGrids(worker, gameBoard);
            case Build:
                return thisGod.getBuildableGrids(worker, gameBoard);
            case SelectWorker: // workers' positions that belong to current player
                HashSet<Grid> workersPos = new HashSet<>();
                for (Worker w : currPlayer.getWorkerList()) {
                    workersPos.add(w.getPosition());
                }
                return workersPos;
            case InitWorker: // any position that is not occupied
                HashSet<Grid> available = new HashSet<>();
                for (Grid[] row : gameBoard.getAllGrids()) {
                    for (Grid grid : row) {
                        if (!grid.isOccupied()) available.add(grid);
                    }
                }
                return available;
            default:
                return new HashSet<>();
        }

    }

    /**
     * Build a tower to a {@link Grid} at (x,y)
     * Let current player's {@link God} perform the move.
     * preconditions:
     * x,y within the boundary of the board
     * x,y must be adjacent to the current worker’s location
     * Destination (x,y) must not be occupied by any of the workers or a dome
     *
     * post conditions:
     * if build is successful, change game state and switch player
     *
     * @param x x coordinates of destination
     * @param y y coordinates of destination
     * @return build is successful or not
     */

    public boolean build(int x, int y) {

        if (controller.getState().equals(GameState.Build)) {
            Worker worker = currPlayer.getSelectedWorker();
            God thisGod = currPlayer.getGod();
            if (thisGod.build(worker, gameBoard, x, y)) {
                controller.nextState();
                switchPlayer();
                return true;
            }
        }
        return false;
    }

}
