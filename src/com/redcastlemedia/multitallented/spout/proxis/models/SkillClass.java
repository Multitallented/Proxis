package com.redcastlemedia.multitallented.spout.proxis.models;

/**
 *
 * @author Multitallented
 */
public class SkillClass {
    public final int MAX_HP;
    public final int MAX_MANA;
    public final String NAME;
    public SkillClass(String name, int maxHP, int maxMana) {
        NAME = name;
        MAX_HP = maxHP;
        MAX_MANA = maxMana;
    }
}
