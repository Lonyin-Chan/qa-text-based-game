package org.lbg.c4.board;

import org.lbg.c4.entities.IEntity;
import org.lbg.c4.entities.Monster;
import org.lbg.c4.entities.Player;
import org.lbg.c4.entities.Treasure;

import java.awt.*;
import java.util.ArrayList;

public class Grid {
    private final ArrayList<ArrayList<Tile>> grid;
    private final int size;

    private Tile playerTile;
    private Tile treasureTile;

    public Grid(ArrayList<ArrayList<Tile>> grid, int size) {
        this.grid = grid;
        this.size = size;
    }

    public ArrayList<ArrayList<Tile>> getGrid() {
        return grid;
    }

    public Tile getTile(Point location) {
        return grid.get(location.x).get(location.y);
    }

    public int getSize() {
        return size;
    }

    public String setNewPlayerTile(Point oldLocation, Point newLocation) {
        IEntity player = grid.get(oldLocation.x).get(oldLocation.y).getEntity();
        IEntity newTile = grid.get(newLocation.x).get(newLocation.y).getEntity();
        grid.get(newLocation.x).get(newLocation.y).setEntity(player);
        grid.get(oldLocation.x).get(oldLocation.y).setEntity(null);
        if(newTile instanceof Treasure) {
            return "Treasure";
        }
        if(newTile instanceof Monster) {
            return "Monster";
        }

        return "nothing";
    }


    public void printGrid() {
        // Print column indices
        System.out.print("    ");
        for (int y = 0; y < size; y++) {
            System.out.print(y + "  ");
        }
        System.out.println();

        // Print rows with x-coordinates
        for (int x = 0; x < size; x++) {
            System.out.print(x + " |"); // Print row index
            for (Tile tile : grid.get(x)) {
                System.out.print(tile + "|");
            }
            System.out.println();

        }
    }


}
