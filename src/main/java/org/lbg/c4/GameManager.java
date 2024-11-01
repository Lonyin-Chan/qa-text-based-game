package org.lbg.c4;

import org.lbg.c4.board.Grid;
import org.lbg.c4.inputs.CustomPrompt;
import org.lbg.c4.inputs.ICustomPrompt;
import org.lbg.c4.inputs.IntReader;

public class GameManager {
    boolean winStatus = false;
    boolean gameStarted = false;
    Grid grid = null;


    private void setUpGame() {
        ICustomPrompt customPrompt = new CustomPrompt();
        IntReader ir = new IntReader(customPrompt);
        String lineread = ir.readFromKeyboard(System.in);
        int size = Integer.parseInt(lineread);
        grid = new Grid(size);
        gameStarted = true;
        grid.printGrid();
    }

    public void processInputs() {
        setUpGame();
    }
}
