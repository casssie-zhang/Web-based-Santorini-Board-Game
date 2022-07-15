package edu.cmu.cs214.hw3.models.actions.move;

import edu.cmu.cs214.hw3.models.Board;
import edu.cmu.cs214.hw3.models.Worker;

public interface MoveRule {

    boolean isValidMove(Worker worker, Board board, int x, int y);

    boolean move(Worker worker, Board board, int x, int y);
}
