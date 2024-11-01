package org.lbg.c4.inputoutput;

public class GamePrompt implements ICustomPrompt{
    @Override
    public void prompt( String msg )
    {
        if (msg == null)
            throw new IllegalArgumentException("Null value is not valid!!!");
        System.out.println("\uD83C\uDFAE \uD83C\uDFAE \uD83C\uDFAE \uD83C\uDFAE " + msg + " \uD83C\uDFAE \uD83C\uDFAE \uD83C\uDFAE \uD83C\uDFAE");
    }
}
