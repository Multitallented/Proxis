package com.redcastlemedia.multitallented.spout.proxis.controllers;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.ProxisConfiguration;
import com.redcastlemedia.multitallented.spout.proxis.models.SkillClass;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.Skill;
import com.redcastlemedia.multitallented.spout.proxis.models.states.UserState;
import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import org.spout.api.util.config.yaml.YamlConfiguration;


/**
 *
 * @author Multitallented
 */
public class UserManager {
    private final Proxis proxis;
    private final HashMap<String, User> users = new HashMap<>();
    public UserManager(Proxis proxis) {
        this.proxis = proxis;
    }

    public User getUser(String username) {
        if (users.containsKey(username)) {
            return users.get(username);
        } else {
            return loadUser(username);
        }
    }
    
    private User loadUser(String name) {
        User user;
        if (ProxisConfiguration.USE_DB.getBoolean()) {
            user = loadDBUser(name);
        } else {
            user = loadYMLUser(name);
        }
        users.put(name, user);
        return user;
    }
    
    private User loadDBUser(String name) {
        String locale = "en";
        int hp = 1;
        int mana = 1;
        SkillClass skillClass = null;
        int kills = 0;
        int deaths = 0;
        int killStreak = 0;
        int highestKillStreak = 0;
        int points = 0;
        HashMap<String, Double> experience = new HashMap<>();
        HashMap<String, Skill> skills = new HashMap<>();
        HashMap<String, Long> cooldowns = new HashMap<>();
        HashMap<String, Integer> favoriteWeapons = new HashMap<>();
        HashMap<String, Integer> favoriteSkills = new HashMap<>();
        HashMap<String, Integer> favoriteVictims = new HashMap<>();
        HashMap<String, Integer> favoriteKillers = new HashMap<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://" + ProxisConfiguration.DATABASE_ADDRESS.getString() + 
                         ":" + ProxisConfiguration.DATABASE_PORT.getInt() + "/" + ProxisConfiguration.DATABASE_NAME.getString(),
                         ProxisConfiguration.DATABASE_USERNAME.getString(), ProxisConfiguration.DATABASE_PASSWORD.getString())) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT u.hp, u.locale, u.mana," +
                    " u.skillclass, u.highestkillstreak, u.killstreak," + 
                    " u.kills, u.deaths, u.points FROM " + 
                    "User AS u " +
                    "WHERE u.name=" + name);
            if (rs.next()) {
                locale = rs.getString("u.locale");
                hp = rs.getInt("u.hp");
                mana = rs.getInt("u.mana");
                skillClass = proxis.getClassManager().getSkillClass(rs.getString("u.skillclass"));
                kills = rs.getInt("u.kills");
                deaths = rs.getInt("u.deaths");
                killStreak = rs.getInt("u.killstreak");
                highestKillStreak = rs.getInt("u.highestkillstreak");
                points = rs.getInt("u.points");
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT fw.name, fw.amount FROM " + 
                    "FavoriteWeapons AS fw WHERE fw.username=" + name);
            while (rs.next()) {
                favoriteWeapons.put(rs.getString("fw.name"), rs.getInt("fw.amount"));
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT fs.name, fs.amount FROM " + 
                    "FavoriteSkills AS fs WHERE fs.username=" + name);
            while (rs.next()) {
                favoriteSkills.put(rs.getString("fs.name"), rs.getInt("fs.amount"));
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT fv.name, fv.amount FROM " + 
                    "FavoriteVictims AS fv WHERE fv.username=" + name);
            while (rs.next()) {
                favoriteVictims.put(rs.getString("fv.name"), rs.getInt("fv.amount"));
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT fk.name, fk.amount FROM " + 
                    "FavoriteKillers AS fk WHERE fk.username=" + name);
            while (rs.next()) {
                favoriteKillers.put(rs.getString("fk.name"), rs.getInt("fk.amount"));
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT cd.name, cd.amount FROM " + 
                    "Cooldowns AS cd WHERE cd.username=" + name);
            while (rs.next()) {
                cooldowns.put(rs.getString("cd.name"), rs.getLong("cd.amount"));
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT cd.name, cd.amount FROM " + 
                    "Experience AS cd WHERE cd.username=" + name);
            while (rs.next()) {
                experience.put(rs.getString("cd.name"), rs.getDouble("cd.amount"));
            }
            //TODO get skills
            return new User(proxis, name, experience, skills, locale, hp,
                mana, cooldowns, skillClass, kills, deaths, killStreak,
                highestKillStreak, favoriteWeapons, favoriteSkills, favoriteVictims,
                favoriteKillers, points);
        } catch (SQLException ex) {
            proxis.log(Level.SEVERE, " failed to connect to database.");
            proxis.log(Level.SEVERE, " using YAML as backup storage.");
            ProxisConfiguration.USE_DB.setValue(false);
            return loadYMLUser(name);
        }
    }
    
    private User loadYMLUser(String name) {
        String locale = "en";
        int hp = 1;
        int mana = 1;
        SkillClass skillClass = null;
        int kills = 0;
        int deaths = 0;
        int killStreak = 0;
        int highestKillStreak = 0;
        int points = 0;
        HashMap<String, Double> experience = new HashMap<>();
        HashMap<String, Skill> skills = new HashMap<>();
        HashMap<String, Long> cooldowns = new HashMap<>();
        HashMap<String, Integer> favoriteWeapons = new HashMap<>();
        HashMap<String, Integer> favoriteSkills = new HashMap<>();
        HashMap<String, Integer> favoriteVictims = new HashMap<>();
        HashMap<String, Integer> favoriteKillers = new HashMap<>();
        File userFolder = new File(proxis.getDataFolder(), "users");
        userFolder.mkdir();
        File userFile = new File(userFolder, name + ".yml");
        YamlConfiguration config;
        try {
            if (!userFile.exists()) {
                    userFile.createNewFile();
            }
            config = new YamlConfiguration(userFile);
            config.load();
            locale = config.getNode("locale").getString();
            hp = config.getNode("hp").getInt();
            mana = config.getNode("mana").getInt();
            skillClass = proxis.getClassManager().getSkillClass(config.getNode("class").getString());
            kills = config.getNode("kills").getInt();
            deaths = config.getNode("deaths").getInt();
            killStreak = config.getNode("killstreak").getInt();
            points = config.getNode("points").getInt();
            highestKillStreak = config.getNode("highestkillstreak").getInt();
            for (String s : config.getChild("favorite-victims").getKeys(false)) {
                favoriteVictims.put(s, config.getNode("favorite-victims." + s).getInt());
            }
            for (String s : config.getChild("favorite-killers").getKeys(false)) {
                favoriteKillers.put(s, config.getNode("favorite-killers." + s).getInt());
            }
            for (String s : config.getChild("favorite-skills").getKeys(false)) {
                favoriteSkills.put(s, config.getNode("favorite-skills." + s).getInt());
            }
            for (String s : config.getChild("favorite-weapons").getKeys(false)) {
                favoriteWeapons.put(s, config.getNode("favorite-weapons." + s).getInt());
            }
            for (String s : config.getChild("cooldowns").getKeys(false)) {
                cooldowns.put(s, config.getNode("cooldowns." + s).getLong());
            }
            for (String s : config.getChild("experience").getKeys(false)) {
                experience.put(s, config.getNode("experience." + s).getDouble());
            }
            //TODO get skills
        } catch (Exception ex) {
            proxis.log(Level.SEVERE, Proxis.NAME + " failed to load " + name + ".yml");
        }
        
        return new User(proxis, name, experience, skills, locale, hp,
                mana, cooldowns, skillClass, kills, deaths, killStreak,
                highestKillStreak, favoriteWeapons, favoriteSkills, favoriteVictims,
                favoriteKillers, points);
    }

}
