package org.lbg.c4;

import org.lbg.c4.board.Grid;
import org.lbg.c4.board.Tile;
import org.lbg.c4.entities.*;
import org.lbg.c4.inputs.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GameManager {
    boolean gameFinished = false;
    boolean gameStarted = false;
    Grid grid = null;
    Point playerLocation;
    Point treasureLocation;

    ICustomPrompt titlePrompt;
    ICustomPrompt gamePrompt;

    public GameManager() {
        titlePrompt = new TitlePrompt();
        gamePrompt = new GamePrompt();
    }

    public void startGame() {
        ICustomPrompt customPrompt = new CustomPrompt();
        IntReader ir = new IntReader(customPrompt);
        while(!gameStarted) {
            setUpGame(ir);
        }
        while(!gameFinished) {
            gameLogic();
        }
        titlePrompt.prompt("\uD83C\uDF89 \uD83C\uDF89 End of game \uD83C\uDF89 \uD83C\uDF89");
    }

    private void setUpGame(IntReader ir) {
        titlePrompt.prompt("Welcome to my Game!");
        String lineRead = ir.readFromKeyboard(System.in);
        int size = Integer.parseInt(lineRead);
        ArrayList<ArrayList<Tile>> newGrid = createGrid(size);
        grid = new Grid(newGrid, size);
        placeEntities(newGrid, size);

        gameStarted = true;
        titlePrompt.prompt("Game has started!");
        grid.printGrid();
        treasureHint();
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

        playerLocation = placeSingleEntity(new Player(), random, totalTiles);
        treasureLocation = placeSingleEntity(new Treasure(), random, totalTiles);

        int numMonsters = totalTiles / 10;
        for (int i = 0; i < numMonsters; i++) {

            Monster randomMonster = MonsterSelector.getRandomMonster();
            placeSingleEntity(randomMonster, random, totalTiles);
        }
    }

    private Point placeSingleEntity( IEntity entity, Random random, int totalTiles) {
        int x, y;
        Point location;
        do {
            x = random.nextInt(grid.getSize() - 1);
            y = random.nextInt(grid.getSize() - 1);
            location = new Point(x, y);
        } while (grid.getTile(location).getEntity() != null);

        grid.getTile(location).setEntity(entity);
        return location;
    }

    private void gameLogic() {

       String result = processInput();
       if (result.equals( "Treasure")) {
           gamePrompt.prompt("Treasure Found!!!! Well Done!");
           gameFinished = true;
       }
       if (result.equals("Monster")) {
           gamePrompt.prompt("Monster Encountered!");
       }
       grid.printGrid();
       treasureHint();
    }

    private void treasureHint() {
        int xDiff = Math.abs(playerLocation.x - treasureLocation.x);
        int yDiff = Math.abs(playerLocation.y - treasureLocation.y);
        gamePrompt.prompt("Treasure is " + xDiff + " rows away and " + yDiff + " columns away...");
    }

    private String processInput() {
        ICustomPrompt customPrompt = new CustomPrompt();
        ArrowReader ar = new ArrowReader(customPrompt);
        String lineread = "";
        String result = "";
        lineread = ar.readFromKeyboard(System.in);
        if (lineread.equalsIgnoreCase("W")) {
            Point newPlayerLocation = new Point(playerLocation.x - 1, playerLocation.y);
            result = grid.setNewPlayerTile(playerLocation, newPlayerLocation);
            playerLocation = newPlayerLocation;
        }
        if (lineread.equalsIgnoreCase("S")) {
            Point newPlayerLocation = new Point(playerLocation.x + 1, playerLocation.y);
            result =  grid.setNewPlayerTile(playerLocation, newPlayerLocation);
            playerLocation = newPlayerLocation;
        }
        if (lineread.equalsIgnoreCase("A")) {
            Point newPlayerLocation = new Point(playerLocation.x, playerLocation.y - 1);
            result = grid.setNewPlayerTile(playerLocation, newPlayerLocation);
            playerLocation = newPlayerLocation;
        }
        if (lineread.equalsIgnoreCase("D")) {
            Point newPlayerLocation = new Point(playerLocation.x, playerLocation.y + 1);
            result = grid.setNewPlayerTile(playerLocation, newPlayerLocation);
            playerLocation = newPlayerLocation;
        }
        return result;
    }


}
