package org.lbg.c4.entities;

import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class MonsterSelector {
    private static final List<Class<? extends Monster>> MONSTER_TYPES = Arrays.asList(MonsterA.class, MonsterB.class);
    private static final Random RANDOM = new Random();

    public static Monster getRandomMonster() {
        int index = RANDOM.nextInt(MONSTER_TYPES.size());
        try {
            return MONSTER_TYPES.get(index).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create monster instance");
        }
    }
}
