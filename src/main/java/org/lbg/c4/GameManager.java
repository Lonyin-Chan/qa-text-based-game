package org.lbg.c4;

import org.lbg.c4.board.Grid;
import org.lbg.c4.board.Tile;
import org.lbg.c4.entities.IEntity;
import org.lbg.c4.entities.Monster;
import org.lbg.c4.entities.Player;
import org.lbg.c4.entities.Treasure;
import org.lbg.c4.inputs.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

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
        ArrayList<ArrayList<Tile>> newGrid = createGrid(size);
        placeEntities(newGrid, size);
        grid = new Grid(newGrid, size);

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

    private void placeEntities(ArrayList<ArrayList<Tile>> grid, int size) {
        Random random = new Random();
        int totalTiles = size * size;

        placeSingleEntity(grid, new Player(), random, totalTiles);
        placeSingleEntity(grid, new Treasure(), random, totalTiles);

        int numMonsters = totalTiles / 4;
        for (int i = 0; i < numMonsters; i++) {
//            placeSingleEntity(grid, new Monster(), random, totalTiles);
        }
    }

    private void placeSingleEntity(ArrayList<ArrayList<Tile>> grid, IEntity entity, Random random, int totalTiles) {
        int x, y;
        do {
            int position = random.nextInt(totalTiles);
            x = position / grid.size();
            y = position / grid.size();
        } while (grid.get(x).get(y).getEntity() != null);

        grid.get(x).get(y).setEntity(entity);
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
