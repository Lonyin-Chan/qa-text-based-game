package org.lbg.c4.inputoutput;

public class ErrorPrompt implements ICustomPrompt{
    @Override
    public void prompt( String msg )
    {
        if (msg == null)
            throw new IllegalArgumentException("Null value is not valid!!!");
        System.out.println("\uD83D\uDEAB \uD83D\uDEAB \uD83D\uDEAB \uD83D\uDEAB " + msg + " \uD83D\uDEAB \uD83D\uDEAB \uD83D\uDEAB \uD83D\uDEAB");
    }
}
