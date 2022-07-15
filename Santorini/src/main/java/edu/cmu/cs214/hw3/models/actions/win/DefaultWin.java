package edu.cmu.cs214.hw3.models.actions.win;

import edu.cmu.cs214.hw3.models.Worker;

public class DefaultWin implements WinRule {


    @Override
    public boolean isWinner(Worker worker, int oldHeight) {
        return worker.getHeight() == 3 && oldHeight == 2;

    }
}
