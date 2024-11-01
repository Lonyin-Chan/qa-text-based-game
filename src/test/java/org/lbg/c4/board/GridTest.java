package org.lbg.c4.board;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridTest {

    @Test
    public void getGrid_returns_a_2D_array() {
        Grid cut = new Grid(4);
        int expectedResult = 4;

        ArrayList<ArrayList<Tile>> actualResult = cut.getGrid();

        assertEquals(expectedResult, actualResult.size());
        assertEquals(expectedResult, actualResult.get(0).size());
    }
}
