package com.redcastlemedia.multitallented.spout.proxis.controllers;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.ProxisConfiguration;
import com.redcastlemedia.multitallented.spout.proxis.models.SkillClass;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.Skill;
import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import org.spout.api.chat.style.ColorChatStyle;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.util.config.yaml.YamlConfiguration;
import org.spout.vanilla.component.inventory.PlayerInventory;
import org.spout.vanilla.component.living.VanillaEntity;
import org.spout.vanilla.source.DamageCause;


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
    
    public void handlePlayerDeath(String name) {
        User user = getUser(name);
        
        Player player = proxis.getEngine().getPlayer(user.getLastDamager(), true);
        if (player == null || !player.hasPermission("proxis.scorepoints")) {
            return;
        }
        if (user.getLastDeath() + (ProxisConfiguration.DEATH_GRACE_PERIOD_SECONDS.getLong()* 1000) > System.currentTimeMillis()) {
            player.sendMessage(ColorChatStyle.GRAY + Proxis.NAME + " " + user.NAME + " was killed too recently. No points awarded.");
            return;
        }
        User dUser = getUser(user.getLastDamager());
        //TODO level range invalid?
        user.setDeaths(user.getDeaths() + 1);
        dUser.setKills(dUser.getKills() + 1);
        
        double econBonus = 0.0;
        //TODO award money?
        /*
        if (econ != null) {
            double balance = econ.bankBalance(player.getName()).balance;
            if (balance < psm.getEconBaseStolen()) {
                if (balance > 0) {
                    econBonus += balance;
                    econPay = econBonus;
                }
                if (balance - econPay > 0) {
                    econBonus += (balance - econPay) * psm.getEconPercentStolen();
                    econPay = econBonus;
                }
                if (balance - econPay - psm.getEconBaseDrop() >0) {
                    econPay += psm.getEconBaseDrop();
                } else if (balance > 0) {
                    econPay = balance;
                }
                balance = econ.bankWithdraw(player.getName(), econPay).balance;
                if (balance >0) {
                    econPay = balance * psm.getEconPercentDrop();
                    econ.bankWithdraw(player.getName(), econPay);
                }
            }
            econBonus += psm.getEconBase();
        }
         */
        dUser.addFavoriteWeapon(player.get(PlayerInventory.class).getItemInHand().name());
        
        user.addFavoriteKiller(dUser.NAME);
        
        double killStreakBonus = ProxisConfiguration.POINTS_PER_KILLSTREAK.getInt() * dUser.getKillStreak();
        dUser.setKillStreak(dUser.getKillStreak() + 1);
        
        econBonus += dUser.getKillStreak() * ProxisConfiguration.POINTS_PER_KILLSTREAK.getInt() * ProxisConfiguration.MONEY_PER_POINT.getInt();
        if (dUser.getKillStreak() >= 3) {
            for (String s : proxis.getEngine().getAllPlayers()) {
                Player p = proxis.getEngine().getPlayer(s, true);
                if (p == null) {
                    return;
                }
                p.sendMessage(ColorChatStyle.GRAY + Proxis.NAME + player.getDisplayName() + " is on a killstreak of " + ColorChatStyle.RED + dUser.getKillStreak());
            }
        }
        
        double killJoyBonus = psm.getPointBonusKillJoy() * psv.getKillstreak();
        econBonus += psv.getKillstreak() * psm.getEconBonusKillJoy();
        if (psv.getKillstreak() >= 3) {
            plugin.getServer().broadcastMessage(ChatColor.GRAY + "[HeroScoreboard] " + dPlayer.getDisplayName() + " just ended "
                    + player.getDisplayName() + "'s killstreak of " + ChatColor.RED + psv.getKillstreak());
        }
        if (ps.getHighestKillstreak() < ps.getKillstreak()) {
            ps.setHighestKillstreak(ps.getKillstreak());
        }
        
        double points = psm.getPointBase();
        double preTotalValuables = 0;
        points += killStreakBonus + killJoyBonus;
        EnumMap<Material, Double> pointValuables = psm.getPointValuables();
        EnumMap<Material, Double> econValuables = psm.getEconValuables();
        for (ItemStack is : player.getInventory().getContents()) {
            if (is != null && pointValuables.containsKey(is.getType()))
                preTotalValuables += pointValuables.get(is.getType());
            if (is != null && econValuables.containsKey(is.getType()))
                econBonus += econValuables.get(is.getType());
        }
        points += preTotalValuables;
        
        double healthBonus = 0;
        if ((dHero == null && dPlayer.getHealth() <= 5)
                || (dHero != null && dHero.getHealth() <= dHero.getMaxHealth() / 4)) {
            healthBonus += psm.getPointQuarterHealth() + psm.getPointHalfHealth();
            econBonus += psm.getEconHalfHealth() + psm.getEconQuarterHealth();
        } else if ((dHero == null && dPlayer.getHealth() <= 10)
                || (dHero != null && dHero.getHealth() <= dHero.getMaxHealth() / 2)) {
            healthBonus += psm.getPointHalfHealth();
            econBonus += psm.getEconHalfHealth();
        }
        points += healthBonus;
        
        double pointLevelBonus = 0;
        if (hero != null) {
            int levelDifference = hero.getLevel() - dHero.getLevel();
            if (levelDifference > 0) {
                pointLevelBonus = levelDifference * psm.getPointBonusLevel();
                points += pointLevelBonus;
                econBonus += levelDifference * psm.getEconBonusLevel();
            }
        }
        //pay econ bonus
        if (econ != null)
            econ.bankDeposit(dPlayer.getName(), econBonus); 
        //save points
        ps.setPoints(ps.getPoints() + points);
        
        psm.setPlayerStats(dPlayer.getName(), ps);
        psm.addPlayerStatsDeath(player.getName());
        
        player.sendMessage(ChatColor.GRAY + "[HeroScoreboard] Death: -" + ChatColor.RED + psm.getPointLoss() + "pts");
        
        //display points
        long interval = 10L;
        if (points > 0) {
            final double pointBase = points;
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                dPlayer.sendMessage(ChatColor.GRAY + "[HeroScoreboard] Kill: +" + ChatColor.RED + pointBase + "pts");

            }
            }, interval);
            interval += 10L;
        }
        if (preTotalValuables > 0) {
            final double pointValuable = preTotalValuables;
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                dPlayer.sendMessage(ChatColor.GRAY + "[HeroScoreboard] Enemy Items: +" + ChatColor.RED + pointValuable + "pts");
            }
            }, 10L);
            interval += 10L;
        }
        if (pointLevelBonus > 0) {
            final double pts = pointLevelBonus;
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                dPlayer.sendMessage(ChatColor.GRAY + "[HeroScoreboard] Level Difference: +" + ChatColor.RED + pts + "pts");
            }
            }, interval);
            interval += 10L;
        }
        if (healthBonus > 0) {
            final double ptsHealth = healthBonus;
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                dPlayer.sendMessage(ChatColor.GRAY + "[HeroScoreboard] Low Health: +" + ChatColor.RED + ptsHealth + "pts");
            }
            }, interval);
            interval += 10L;
        }
        if (killStreakBonus > 0) {
            final double pts = killStreakBonus;
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                dPlayer.sendMessage(ChatColor.GRAY + "[HeroScoreboard] KillStreak: +" + ChatColor.RED + pts + "pts");
            }
            }, interval);
            interval += 10L;
        }
        if (killJoyBonus > 0) {
            final double pts = killJoyBonus;
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                dPlayer.sendMessage(ChatColor.GRAY + "[HeroScoreboard] KillJoy: +" + ChatColor.RED + pts + "pts");
            }
            }, interval);
            interval += 10L;
        }
        final double pts = points;
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
        @Override
        public void run() {
            dPlayer.sendMessage(ChatColor.GRAY + "[HeroScoreboard] Total: +" + ChatColor.RED + pts + "pts");
        }
        }, interval);
    }
    
    public void putDamage(User user, Entity damager, DamageCause cause) {
        user.setLastDamageTime(System.currentTimeMillis());
        if (cause != null) {
            user.setLastDamageCause(cause);
        } else {
            user.setLastDamageCause(DamageCause.UNKNOWN);
        }
        if (damager == null) {
            user.setLastDamager("null");
        } else if (damager.getClass().equals(Player.class)) {
            User uDamager = proxis.getUserManager().getUser(((Player) damager).getName());
            user.setLastDamager(uDamager.NAME);
        } else if (damager instanceof VanillaEntity) {
            VanillaEntity ve = (VanillaEntity) damager;
            //TODO store name of entity in lastDamager
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
            for (String s : config.getChild("skills").getKeys(false)) {
                
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
