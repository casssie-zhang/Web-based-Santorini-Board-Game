package edu.cmu.cs214.hw3.server;

import edu.cmu.cs214.hw3.models.Game;
import edu.cmu.cs214.hw3.models.Player;
import edu.cmu.cs214.hw3.models.gods.*;
import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;
import java.util.Map;

public class App extends NanoHTTPD {

    private Game game;

    public App() throws IOException {
        super(8080);

        Player player1 = new Player(new NoGod(), "player1");
        Player player2 = new Player(new NoGod(), "player2");
        this.game = new Game(player1, player2);

        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning!\n");
    }

    public static void main(String[] args) {
        try {
            new App();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }

    /**
     * A helper function to map frontend god's name to object
     * @param godName god's name
     * @return God object
     */
    private God name2God(String godName) {
        switch (godName) {
            case "Demeter":
                return new Demeter();
            case "Apollo":
                return new Apollo();
            case "Minotaur":
                return new Minotaur();
            case "Pan":
                return new Pan();
            case "Hephaestus":
                return new Hephaestus();
            case "Artemis":
                return new Artemis();
            case "Atlas":
                return new Atlas();
            default:
                return new NoGod(); // default ruleset.
        }
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        Map<String, String> params = session.getParms();
        if (uri.equals("/newgame")) { // starting a new game
            System.out.println("--- New Game ---");
            String god1 = params.get("player1");
            String god2 = params.get("player2");
            System.out.println(god1 + god2);
            Player player1 = new Player(name2God(god1), "Player1");
            Player player2 = new Player(name2God(god2), "Player2");
            this.game = new Game(player1, player2);
        } else if (uri.equals("/init")) { // initialize the position of a worker
            System.out.println("--- Init Worker ---");
            int x = Integer.parseInt(params.get("x"));
            int y = Integer.parseInt(params.get("y"));
            System.out.println(x + " " + y);
            game.initWorker(x, y);

        } else if (uri.equals("/move")) { // move a worker
            System.out.println("--- Move ---");
            int x = Integer.parseInt(params.get("x"));
            int y = Integer.parseInt(params.get("y"));
            game.move(x, y);
        } else if (uri.equals("/build")) { // build a worker
            System.out.println("--- Build ---");
            int x = Integer.parseInt(params.get("x"));
            int y = Integer.parseInt(params.get("y"));
            game.build(x, y);
        } else if (uri.equals("/select")) {
            System.out.println("--- Select Worker ---");
            int x = Integer.parseInt(params.get("x"));
            int y = Integer.parseInt(params.get("y"));
            game.selectWorker(x, y);
        } else if (uri.equals("getboard")) {
            System.out.println("--- Get Board ---");
            // do nothing
        }

        GameParser gameParser = GameParser.forGame(this.game);
        return newFixedLengthResponse(gameParser.toString());
    }
}
