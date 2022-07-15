package edu.cmu.cs214.hw3.models;

import edu.cmu.cs214.hw3.models.gods.God;
import edu.cmu.cs214.hw3.models.gods.NoGod;

import java.util.HashSet;

public class Player {
    private final HashSet<Worker> workerList = new HashSet<>();

    private Worker selectedWorker;
    private final God god;
    private static final int WORKER_NUM = 2;

    private String name;


    /**
     * Non-arg constructor, assigned {@link NoGod} to the player
     */
    public Player() {
        this.god = new NoGod();
    }

    public Player(String name){
        this.god =  new NoGod();
        this.name = name;
    }

    public Player(God god) {
        this.god = god;
    }

    public Player(God god, String name) {
        this.god = god;
        this.name = name;
    }


    public God getGod() {
        return god;
    }

    public String getName() {
        return name;
    }


    private int getNumWorker() {
        return workerList.size();
    }


    public HashSet<Worker> getWorkerList() {
        return workerList;
    }

    public Worker getSelectedWorker() {
        return selectedWorker;
    }

    /**
     * set a selected {@link Worker} for current player
     * This selected worker must belong to current player
     *
     * @param selectedWorker worker selected
     */
    public void setSelectedWorker(Worker selectedWorker) { //throws WorkerMisMatchException
        this.selectedWorker = selectedWorker;
    }


    /**
     * Check if a player's workers have all been initialized
     * A player must have 2 workers
     * This means that this player is ready to start the game
     *
     * @return All workers are initialized
     */
    public boolean workersAllInit() {
        if (workerList.size() == WORKER_NUM) {
            for (Worker w : workerList) {
                if (w.getPosition() == null) return false;
            }
            return true;
        }

        return false;
    }

    /**
     * Add a worker to player's workers list
     *
     * @param worker worker to be added
     */
    public void addWorker(Worker worker) {
        workerList.add(worker);

    }
}



