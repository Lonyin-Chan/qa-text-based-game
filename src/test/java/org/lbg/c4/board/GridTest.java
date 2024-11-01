package org.lbg.c4.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GridTest {


    ArrayList<ArrayList<Tile>> grid;
    int size = 3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        grid = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ArrayList<Tile> row = new ArrayList<>(size);
            for (int j = 0; j < size; j++) {
                Tile mockTile = mock(Tile.class);
                when(mockTile.toString()).thenReturn(" ");
                row.add(mockTile);
            }
            grid.add(row);
        }
    }

    @Test
    public void getGrid_returns_a_2D_array() {
        Grid cut = new Grid(grid, size);
        int expectedResult = 3;

        ArrayList<ArrayList<Tile>> actualResult = cut.getGrid();

        assertEquals(expectedResult, actualResult.size());
        assertEquals(expectedResult, actualResult.get(0).size());
    }

    @Test
    public void getGrid_size() {
        Grid cut = new Grid(grid, size);
        int expectedResult = 3;

        int actualResult = cut.getSize();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void printGrid_correctly() {
        String expectedOutput = "   0 1 2 \n" +
                "0 | | | |\n" +
                "1 | | | |\n" +
                "2 | | | |\n";
        Grid cut = new Grid(grid, 3);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        cut.printGrid();

        assertEquals(expectedOutput, outContent.toString());
    }
}
