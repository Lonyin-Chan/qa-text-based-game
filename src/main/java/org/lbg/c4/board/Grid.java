package org.lbg.c4.board;

import java.util.ArrayList;

public class Grid {
    private final ArrayList<ArrayList<Tile>> grid;

    public Grid(int size) {
        this.grid = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ArrayList<Tile> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                Tile tile = new Tile();
                row.add(tile);
            }
            this.grid.add(row);
        }
    }

    public ArrayList<ArrayList<Tile>> getGrid() {
        return grid;
    }

}
