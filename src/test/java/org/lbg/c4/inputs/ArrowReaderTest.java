package org.lbg.c4.inputs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrowReaderTest {
    private ArrowReader cut;

    @BeforeEach
    public void setUp() {
        ICustomPrompt customPrompt = new CustomPrompt();
        cut = new ArrowReader(customPrompt);
    }

    @Test
    public void readFromKeyboard_valid_input_w() {
        String expectedResult = "w";
        System.setIn(new ByteArrayInputStream("w".getBytes()));

        String actualResult = cut.readFromKeyboard(System.in);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void readFromKeyboard_valid_input_a() {
        String expectedResult = "a";
        System.setIn(new ByteArrayInputStream("a".getBytes()));

        String actualResult = cut.readFromKeyboard(System.in);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void readFromKeyboard_valid_input_s() {
        String expectedResult = "s";
        System.setIn(new ByteArrayInputStream("s".getBytes()));

        String actualResult = cut.readFromKeyboard(System.in);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void readFromKeyboard_valid_input_d() {
        String expectedResult = "d";
        System.setIn(new ByteArrayInputStream("d".getBytes()));

        String actualResult = cut.readFromKeyboard(System.in);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void readFromKeyboard_invalid_then_valid_input() {
        String expectedResult = "a";
        System.setIn(new ByteArrayInputStream("cheese".getBytes()));
        System.setIn(new ByteArrayInputStream("a".getBytes()));

        String actualResult = cut.readFromKeyboard(System.in);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void readFromKeyboard_quit_input() {
        String expectedResult = "quit";
        System.setIn(new ByteArrayInputStream("quit".getBytes()));

        String actualResult = cut.readFromKeyboard(System.in);

        assertEquals(expectedResult, actualResult);
    }
}
