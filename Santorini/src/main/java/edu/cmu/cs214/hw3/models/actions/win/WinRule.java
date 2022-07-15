package edu.cmu.cs214.hw3.models.actions.win;

import edu.cmu.cs214.hw3.models.Worker;

public interface WinRule {
    boolean isWinner(Worker worker, int oldHeight);
}
