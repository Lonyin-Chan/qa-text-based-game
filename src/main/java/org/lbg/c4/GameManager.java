package org.lbg.c4;

import org.lbg.c4.board.Grid;
import org.lbg.c4.board.Tile;
import org.lbg.c4.entities.*;
import org.lbg.c4.inputoutput.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    boolean gameFinished = false;
    boolean gameStarted = false;
    boolean gameLost = false;
    Grid grid = null;
    Point playerLocation;
    Point treasureLocation;

    ICustomPrompt titlePrompt;
    ICustomPrompt gamePrompt;
    ICustomPrompt monsterPrompt;
    ICustomPrompt errorPrompt;

    public GameManager() {
        titlePrompt = new TitlePrompt();
        gamePrompt = new GamePrompt();
        monsterPrompt = new MonsterPrompt();
        errorPrompt = new ErrorPrompt();
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
        processResult();
        titlePrompt.prompt("\uD83D\uDE31 \uD83D\uDE31 End of game \uD83D\uDE31 \uD83D\uDE31");
    }

    public void processResult() {
        if (gameLost) {
            gamePrompt.prompt("Game Lost! Better Luck Next Time!");
        } else {
            gamePrompt.prompt("\uD83C\uDF89 \uD83C\uDF89 Treasure Found!!!! Well Done! \uD83C\uDF89 \uD83C\uDF89");
        }
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
       IEntity result = processInput();
       grid.printGrid();
       if (result instanceof Treasure) {
           gameFinished = true;
           return;
       }
       if (result instanceof Monster) {
           Monster monster = (Monster) result;
           monsterPrompt.prompt(monster.greeting());
           gameFinished = true;
           gameLost = true;
           return;
       }
       treasureHint();
    }

    private void treasureHint() {
        int xDiff = Math.abs(playerLocation.x - treasureLocation.x);
        int yDiff = Math.abs(playerLocation.y - treasureLocation.y);
        gamePrompt.prompt("Treasure is " + xDiff + " rows away and " + yDiff + " columns away...");
    }

    private IEntity processInput() {
        ICustomPrompt customPrompt = new CustomPrompt();
        ArrowReader ar = new ArrowReader(customPrompt);
        String lineread = "";
        Point newPlayerLocation = null;
        int newX = 0, newY = 0;

        boolean isValidMove = false;

        while (!isValidMove) {
            lineread = ar.readFromKeyboard(System.in);

            if (lineread.equalsIgnoreCase("W")) {
                newX = playerLocation.x - 1;
                newY = playerLocation.y;
            } else if (lineread.equalsIgnoreCase("S")) {
                newX = playerLocation.x + 1;
                newY = playerLocation.y;
            } else if (lineread.equalsIgnoreCase("A")) {
                newX = playerLocation.x;
                newY = playerLocation.y - 1;
            } else if (lineread.equalsIgnoreCase("D")) {
                newX = playerLocation.x;
                newY = playerLocation.y + 1;
            }

            if (newX >= 0 && newX < grid.getSize() && newY >= 0 && newY < grid.getSize()) {
                isValidMove = true;
                newPlayerLocation = new Point(newX, newY);
            } else {
                errorPrompt.prompt("Move out of bounds. Please try again.");
            }
        }
        IEntity result = grid.setNewPlayerTile(playerLocation, newPlayerLocation);
        playerLocation = newPlayerLocation;
        return result;
    }


}
