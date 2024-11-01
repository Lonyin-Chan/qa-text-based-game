package org.lbg.c4.inputs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntReaderTest {
    private IntReader cut;

    @BeforeEach
    public void setUp() {
        cut = new IntReader();
    }

    @Test
    public void readFromKeyboard_valid_input() {
        String expectedResult = "22";
        System.setIn(new ByteArrayInputStream("22".getBytes()));

        String actualResult = cut.readFromKeyboard(System.in);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void readFromKeyboard_invalid_then_valid_input() {
        String expectedResult = "22";
        System.setIn(new ByteArrayInputStream("cheese".getBytes()));
        System.setIn(new ByteArrayInputStream("22".getBytes()));

        String actualResult = cut.readFromKeyboard(System.in);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void readFromKeyboard_quit_input() {
        String expectedResult = "QUIT";
        System.setIn(new ByteArrayInputStream("QUIT".getBytes()));

        String actualResult = cut.readFromKeyboard(System.in);

        assertEquals(expectedResult, actualResult);
    }

}
