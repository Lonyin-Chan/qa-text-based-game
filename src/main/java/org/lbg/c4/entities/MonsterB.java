package org.lbg.c4.entities;

public class MonsterB extends Monster{
    @Override
    public String toString() {
        return "\uD83D\uDC79";
    }

    @Override
    public String greeting() {
        return "Hello from MonsterB";
    }
}
