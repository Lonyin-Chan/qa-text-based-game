package org.lbg.c4.inputoutput;

public class MonsterPrompt implements ICustomPrompt{
    @Override
    public void prompt( String msg )
    {
        if (msg == null)
            throw new IllegalArgumentException("Null value is not valid!!!");
        System.out.println("\uD83D\uDD25 \uD83D\uDD25 \uD83D\uDD25 \uD83D\uDD25 " + msg + " \uD83D\uDD25 \uD83D\uDD25 \uD83D\uDD25 \uD83D\uDD25");
    }
}
