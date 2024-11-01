package org.lbg.c4.inputs;

public abstract class Reader {
    protected ICustomPrompt itsPrompt;

    public  Reader( ICustomPrompt cp )
    {
        itsPrompt = cp;
    }
}
