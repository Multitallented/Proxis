package com.redcastlemedia.multitallented.spout.proxis.models.users;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.SkillClass;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.Skill;
import com.redcastlemedia.multitallented.spout.proxis.models.users.states.UserState;
import java.util.HashMap;
import java.util.HashSet;
import org.spout.vanilla.source.DamageCause;

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
    private int kills;
    private int deaths;
    private int killStreak;
    private int highestKillStreak;
    private final HashMap<String, Integer> favoriteWeapons;
    private final HashMap<String, Integer> favoriteSkills;
    private final HashMap<String, Integer> favoriteVictim;
    private final HashMap<String, Integer> favoriteKiller;
    private int points;
    private String lastSkillUsed = "";
    private long timeLastSkillUsed = 0L;
    private String lastDamager = "";
    private long lastDamageTime = 0L;
    private long lastDeath = 0L;
    private DamageCause lastDamageCause = DamageCause.UNKNOWN;

    
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
    
    public HashSet<UserState> getStates() {
        return states;
    }
    
    public void setSkillClass(SkillClass skillClass) {
        this.skillClass = skillClass;
    }
    public SkillClass getSkillClass() {
        return skillClass;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }
    public int getMana() {
        return mana;
    }
    public void setHP(int hp) {
        this.hp = hp;
    }
    public int getHP() {
        return hp;
    }
    public void setHighestKillStreak(int amount) {
        highestKillStreak = amount;
    }
    public int getHighestKillStreak() {
        return highestKillStreak;
    }
    public void setLocale(String locale) {
        this.locale = locale;
    }
    public String getLocale() {
        return locale;
    }
    public int getKillStreak() {
        return killStreak;
    }
    public void setKillStreak(int killStreak) {
        this.killStreak = killStreak;
    }
    public void addFavoriteWeapon(String name) {
        if (favoriteWeapons.containsKey(name)) {
            favoriteWeapons.put(name, favoriteWeapons.get(name) + 1);
        } else {
            favoriteWeapons.put(name, 1);
        }
    }
    public String getFavoriteWeapon() {
        String bestWeapon = "None: 0";
        int high = 0;
        for (String s : favoriteWeapons.keySet()) {
            if (high < favoriteWeapons.get(s)) {
                bestWeapon = s + ": " + favoriteWeapons.get(s);
            }
        }
        return bestWeapon;
    }
    public void addFavoriteSkills(String name) {
        if (favoriteSkills.containsKey(name)) {
            favoriteSkills.put(name, favoriteSkills.get(name) + 1);
        } else {
            favoriteSkills.put(name, 1);
        }
    }
    public String getFavoriteSkill() {
        String bestSkill = "None: 0";
        int high = 0;
        for (String s : favoriteSkills.keySet()) {
            if (high < favoriteSkills.get(s)) {
                bestSkill = s + ": " + favoriteSkills.get(s);
            }
        }
        return bestSkill;
    }
    public void addFavoriteVictim(String name) {
        if (favoriteVictim.containsKey(name)) {
            favoriteVictim.put(name, favoriteVictim.get(name) + 1);
        } else {
            favoriteVictim.put(name, 1);
        }
    }
    public String getFavoriteVictim() {
        String bestSkill = "None: 0";
        int high = 0;
        for (String s : favoriteVictim.keySet()) {
            if (high < favoriteVictim.get(s)) {
                bestSkill = s + ": " + favoriteVictim.get(s);
            }
        }
        return bestSkill;
    }
    public void addFavoriteKiller(String name) {
        if (favoriteKiller.containsKey(name)) {
            favoriteKiller.put(name, favoriteKiller.get(name) + 1);
        } else {
            favoriteKiller.put(name, 1);
        }
    }
    public String getFavoriteKiller() {
        String bestSkill = "None: 0";
        int high = 0;
        for (String s : favoriteKiller.keySet()) {
            if (high < favoriteKiller.get(s)) {
                bestSkill = s + ": " + favoriteKiller.get(s);
            }
        }
        return bestSkill;
    }
    public long getLastDeath() {
        return lastDeath;
    }
    public void setLastDeath(long time) {
        this.lastDeath = time;
    }
    public int getDeaths() {
        return deaths;
    }
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
    public DamageCause getLastDamageCause() {
        return lastDamageCause;
    }
    public void setLastDamageCause(DamageCause dc) {
        this.lastDamageCause = dc;
    }
    public long getLastDamageTime() {
        return lastDamageTime;
    }
    public void setLastDamageTime(long time) {
        this.lastDamageTime = time;
    }
    public long getLastSkillTime() {
        return timeLastSkillUsed;
    }
    public void setLastSkillTime(long time) {
        this.timeLastSkillUsed = time;
    }
    public String getLastSkill() {
        return lastSkillUsed;
    }
    public void setLastSkill(String name) {
        this.lastSkillUsed = name;
    }
    public String getLastDamager() {
        return lastDamager;
    }
    public void setLastDamager(String name) {
        this.lastDamager = name;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public int getKills() {
        return kills;
    }
    public void setKills(int kills) {
        this.kills = kills;
    }
}