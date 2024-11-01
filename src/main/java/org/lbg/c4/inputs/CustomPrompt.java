package org.lbg.c4.inputs;

public class CustomPrompt implements ICustomPrompt{
    @Override
    public void prompt( String msg )
    {
        if (msg == null)
            throw new IllegalArgumentException("Null value is not valid!!!");
        System.out.print(msg + ": ");
    }
}
