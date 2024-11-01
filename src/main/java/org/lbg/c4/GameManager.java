package org.lbg.c4;

import org.lbg.c4.board.Grid;
import org.lbg.c4.board.Tile;
import org.lbg.c4.inputs.*;

import java.awt.*;
import java.util.ArrayList;

public class GameManager {
    boolean gameFinished = false;
    boolean gameStarted = false;
    Grid grid = null;

    ICustomPrompt titlePrompt;

    public GameManager() {
        titlePrompt = new TitlePrompt();
    }

    public void processInputs() {
        ICustomPrompt customPrompt = new CustomPrompt();
        IntReader ir = new IntReader(customPrompt);
        while(!gameStarted) {
            setUpGame(ir);
        }
    }

    private void setUpGame(IntReader ir) {
        titlePrompt.prompt("Welcome to my Game!");
        String lineRead = ir.readFromKeyboard(System.in);
        int size = Integer.parseInt(lineRead);
        grid = new Grid(createGrid(size), size);
        gameStarted = true;
        titlePrompt.prompt("Game has started!");
        grid.printGrid();
    }

    private ArrayList<ArrayList<Tile>> createGrid(int size) {
        ArrayList<ArrayList<Tile>> newGrid = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            ArrayList<Tile> row = new ArrayList<>();
            for (int y = 0; y < size; y++) {
                Tile tile = new Tile(new Point(x, y), null);
                row.add(tile);
            }
            newGrid.add(row);
        }
        return newGrid;
    }

    private void gameLogic() {
        ICustomPrompt customPrompt = new CustomPrompt();
        ArrowReader ar = new ArrowReader(customPrompt);
        String lineread = "";
        while (!gameFinished) {
            lineread = ar.readFromKeyboard(System.in);
        }
    }

}
