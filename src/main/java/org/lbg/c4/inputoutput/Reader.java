package org.lbg.c4.inputoutput;

public abstract class Reader {
    protected ICustomPrompt itsPrompt;

    public  Reader( ICustomPrompt cp )
    {
        itsPrompt = cp;
    }
}
