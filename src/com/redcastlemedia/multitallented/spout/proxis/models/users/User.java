package com.redcastlemedia.multitallented.spout.proxis.models.users;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.SkillClass;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.Skill;
import com.redcastlemedia.multitallented.spout.proxis.models.states.UserState;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Multitallented
 */
public class User {
    public String locale;
    public final String NAME;
    private final HashSet<UserState> states = new HashSet<>();
    private final HashMap<String, Double> experience;
    private final HashMap<String, Skill> skills;
    private int hp;
    private int mana;
    private final HashMap<String, Long> cooldowns;
    private SkillClass skillClass;
    private final int kills;
    private final int deaths;
    private final int killStreak;
    private final int highestKillStreak;
    private final HashMap<String, Integer> favoriteWeapons;
    private final HashMap<String, Integer> favoriteSkills;
    private final HashMap<String, Integer> favoriteVictim;
    private final HashMap<String, Integer> favoriteKiller;
    private final int points;

    
    public User(Proxis plugin, String name, HashMap<String, Double> experience,
            HashMap<String, Skill> skills, String locale, int hp, int mana, HashMap<String, Long> cooldowns,
            SkillClass skillClass, int kills, int deaths, int killStreak, int highestKillStreak,
            HashMap<String, Integer> favoriteWeapons, HashMap<String, Integer> favoriteSkills,
            HashMap<String, Integer> favoriteVictim, HashMap<String, Integer> favoriteKiller,
            int points) {
        NAME = name;
        this.experience = experience;
        this.skills = skills;
        this.locale = locale;
        this.hp = hp;
        this.mana = mana;
        this.cooldowns = cooldowns;
        this.skillClass = skillClass;
        this.kills = kills;
        this.deaths = deaths;
        this.killStreak = killStreak;
        this.highestKillStreak = highestKillStreak;
        this.favoriteWeapons = favoriteWeapons;
        this.favoriteSkills = favoriteSkills;
        this.favoriteVictim = favoriteVictim;
        this.favoriteKiller = favoriteKiller;
        this.points = points;
        //TODO finish this
    }
}