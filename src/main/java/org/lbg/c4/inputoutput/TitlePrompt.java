package org.lbg.c4.inputoutput;

public class TitlePrompt implements ICustomPrompt{
    @Override
    public void prompt( String msg )
    {
        if (msg == null)
            throw new IllegalArgumentException("Null value is not valid!!!");
        System.out.println("\uD83D\uDCD5 \uD83D\uDCD5 \uD83D\uDCD5 \uD83D\uDCD5 " + msg + " \uD83D\uDCD5 \uD83D\uDCD5 \uD83D\uDCD5 \uD83D\uDCD5");
    }
}
