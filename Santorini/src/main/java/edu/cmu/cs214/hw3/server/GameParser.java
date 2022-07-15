package edu.cmu.cs214.hw3.server;


import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Game;
import edu.cmu.cs214.hw3.models.GameState;
import edu.cmu.cs214.hw3.models.Player;
import edu.cmu.cs214.hw3.models.Grid;

import java.util.Arrays;
import java.util.HashSet;

/**
 * A helper class that transforms the {@link Game} to json string for frontend
 */
public final class GameParser {
    private final Cell[] cells;
    private final Player winner;
    private final String turn;
    private final GameState state;
    private final int canSkip;

    private GameParser(Cell[] cells, Player winner, String turn, GameState state, int canSkip) {
        this.cells = cells;
        this.turn = turn;
        this.winner = winner;
        this.state = state;
        this.canSkip = canSkip;
    }

    /**
     * Parse the game information
     * @param game game to be parsed
     * @return a new GameParser contains the latest information of game
     */
    public static GameParser forGame(Game game) {
        Cell[] cells = getCells(game);
        Player winner = getWinner(game);
        String turn = getTurn(game);
        GameState state = getState(game);
        int canSkip = getSkip(game);

        //debug info
        System.out.println("turn: " + turn);
        System.out.println("action: " + getAction(state));
        return new GameParser(cells, winner, turn, state, canSkip);
    }

    private static Cell[] getCells(Game game) {
        Cell[] cells = new Cell[25];
        Board board = game.getGameBoard();

        String linkPrefix = getURL(game.getGameState());
        HashSet<Grid> playable = game.getAvailableGrids();

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                String link = "";
                String clazz = "cell";
                Grid grid = board.getGrid(x, y);
                String text = grid.toString();
                if (playable.contains(grid)) clazz = "playable"; // grid is valid

                if (!game.getGameState().equals(GameState.SelectWorker)) {
                    if (grid.getOccupiedBy() != null &&
                            grid.getOccupiedBy().equals(game.getCurrPlayer().getSelectedWorker())) {
                        clazz = "selected";
                    }
                }

                link = linkPrefix + "x=" + x + "&y=" + y;
                cells[5 * x + y] = new Cell(text, clazz, link);
            }
        }
        return cells;
    }

    private static Player getWinner(Game game) {
        return game.getWinner();
    }

    private static String getTurn(Game game) {
        return game.getCurrPlayer().getName();
    }

    private static GameState getState(Game game) {
        return game.getGameState();
    }

    private static int getSkip(Game game) {
        return game.nextCanSkip() ? 1 : 0;
    }

    private static String getAction(GameState state) {
        // instructions
        switch (state) {
            case InitWorker:
                return "Initialize Worker";
            case Move:
                return "Move";
            case Build:
                return "Build";
            case SelectWorker:
                return "Select Worker";
            case GameOver:
                return "Game Over";
            default:
                return "Error";
        }
    }

    private static String getURL(GameState state) {
        // instructions
        switch (state) {
            case InitWorker:
                return "/init?";
            case Move:
                return "/move?";
            case Build:
                return "/build?";
            case SelectWorker:
                return "/select?";
            case GameOver:
                return "/over?";
            default:
                return "/";
        }
    }

    @Override
    public String toString() {
        String skipLink = getURL(this.state) + "x=-1&y=-1";

        if (this.winner == null) {
            return "{ \"cells\": " + Arrays.toString(this.cells) + "," +
                    "\"action\": \"" + getAction(this.state) + "\"," +
                    "\"skip\": \"" + this.canSkip + "\"," +
                    "\"skipLink\": \"" + skipLink + "\"," +
                    "\"turn\": \"" + this.turn + "\"}";
        }
        return "{ \"cells\": " + Arrays.toString(this.cells) + "," +
                "\"winner\": \"" + this.winner.getName() + "\"}";
    }
}

/**
 * A helper class that transforms the {@link Grid} to json string for frontend
 */
class Cell {
    private final String text;
    private final String clazz;
    private final String link;

    Cell(String text, String clazz, String link) {
        this.text = text;
        this.clazz = clazz;
        this.link = link;
    }

    public String getText() {
        return this.text;
    }

    public String getClazz() {
        return this.clazz;
    }

    public String getLink() {
        return this.link;
    }

    @Override
    public String toString() {
        return "{ \"text\": \"" + this.text + "\"," +
                " \"clazz\": \"" + this.clazz + "\"," +
                " \"link\": \"" + this.link + "\"}";
    }
}
