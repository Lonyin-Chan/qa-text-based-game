package org.lbg.c4.board;

import java.awt.*;
import java.util.ArrayList;

public class Grid {
    private final ArrayList<ArrayList<Tile>> grid;
    private final int size;

    public Grid(ArrayList<ArrayList<Tile>> grid, int size) {
        this.grid = grid;
        this.size = size;
    }

    public ArrayList<ArrayList<Tile>> getGrid() {
        return grid;
    }

    public int getSize() {
        return size;
    }

    public void printGrid() {
        // Print column indices
        System.out.print("   ");
        for (int y = 0; y < size; y++) {
            System.out.print(y + " ");
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
