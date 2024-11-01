package org.lbg.c4.inputs;

import java.io.InputStream;
import java.util.Scanner;
import java.util.Set;

public class ArrowReader extends Reader implements IReader{

    public ArrowReader(ICustomPrompt prompt) {
        super(prompt);
    }

    @Override
    public  String readFromKeyboard( InputStream is )
    {
        Scanner theScanner = new Scanner( is );
        String quantity = "";
        do {
            itsPrompt.prompt("Enter movement input (W,A,S,D)");
            quantity = theScanner.nextLine();
        } while (!isValid(quantity));
        return quantity;
    }

    private boolean isValid(String str) {
        Set<String> directions = Set.of("w", "a", "s", "d", "W", "A", "S", "D", "quit");
        return directions.contains(str);
    }
}
