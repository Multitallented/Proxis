package com.redcastlemedia.multitallented.spout.proxis.models;

import java.util.HashMap;

/**
 * Handles all alterations to the player, as well as owned skills.
 * @author Multitallented
 */
public class SkillClass {
    public final int MAX_HP;
    public final int MAX_MANA;
    public final String NAME;
    private final HashMap<String, Integer> experience;
    public SkillClass(String name, int maxHP, int maxMana, HashMap<String, Integer> experience) {
        NAME = name;
        MAX_HP = maxHP;
        MAX_MANA = maxMana;
        this.experience = experience;
    }
}
