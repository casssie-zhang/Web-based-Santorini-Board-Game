package edu.cmu.cs214.hw3.models;

public class Worker {
    private final Player player;
    private Grid position;
    private final String name;

    /**
     * Constructors: create a worker object, must assign a player
     * to this worker
     * @param player player that this worker belongs to
     * @param name name of worker
     */
    public Worker(Player player, String name) {
        this.player = player;
        player.addWorker(this);
        this.name = name;
    }

    public Worker(Player player) {
        this.player = player;
        player.addWorker(this);
        this.name = player.getName() + "Worker" + player.getWorkerList().size();
    }

    public int getHeight() {
        return this.position.getHeight();
    }

    /**
     * Return the {@link Grid} position that this worker sits on
     * @return worker's position
     */
    public Grid getPosition() {
        return this.position;
    }

    /**
     *
     * @return Player that this worker belongs to
     */
    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }

    /**
     * Set the position of current worker
     * @param pos Target position
     */
    public void setPosition(Grid pos) {
        this.position = pos;
    }

}
