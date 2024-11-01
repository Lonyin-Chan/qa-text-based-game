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

    public IEntity setNewPlayerTile(Point oldLocation, Point newLocation) {
        IEntity player = grid.get(oldLocation.x).get(oldLocation.y).getEntity();
        IEntity newTileEntity = grid.get(newLocation.x).get(newLocation.y).getEntity();
        grid.get(newLocation.x).get(newLocation.y).setEntity(player);
        grid.get(oldLocation.x).get(oldLocation.y).setEntity(null);
        return newTileEntity;
    }


    public void printGrid() {
        // print indices
        System.out.print("    ");
        for (int y = 0; y < size; y++) {
            System.out.print(y + "  ");
        }
        System.out.println();

        // print horizontal line at the top
        System.out.print("   ");
        for (int y = 0; y < size; y++) {
            System.out.print("---");
        }
        System.out.println();

        // print rows and horizontal lines
        for (int x = 0; x < size; x++) {
            System.out.print(x + " |"); // Print row index
            for (Tile tile : grid.get(x)) {
                System.out.print(tile + "|");
            }
            System.out.println();

            // print horizontal line after each row
            System.out.print("   ");
            for (int y = 0; y < size; y++) {
                System.out.print("---");
            }
            System.out.println();

        }

    }
}
