package com.redcastlemedia.multitallented.spout.proxis.models;

import java.io.File;
import java.io.IOException;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.util.config.ConfigurationHolder;
import org.spout.api.util.config.yaml.YamlConfiguration;

/**
 *
 * @author Multitallented
 */
public class LanguageConfiguration extends YamlConfiguration {
    public static final ConfigurationHolder EN_KILLSTREAK = new ConfigurationHolder("%name is on a killstreak of %amount", "en.killstreak");
    public static final ConfigurationHolder DE_KILLSTREAK = new ConfigurationHolder("%name hat %menge menschen getötet", "de.killstreak");
    public static final ConfigurationHolder ES_KILLSTREAK = new ConfigurationHolder("%nombre está en una racha de matar %cantidad", "es.killstreak");
    public static final ConfigurationHolder FR_KILLSTREAK = new ConfigurationHolder("%nom a tué %montant personnes", "fr.killstreak");
    public static final ConfigurationHolder RU_KILLSTREAK = new ConfigurationHolder("%имя убил %количество человек", "ru.killstreak");
    public static final ConfigurationHolder IT_KILLSTREAK = new ConfigurationHolder("%nome ha ucciso %importo persone", "it.killstreak");
    public static final ConfigurationHolder MA_KILLSTREAK = new ConfigurationHolder("%名稱 已造成 %量 人死亡", "ma.killstreak");
    public static final ConfigurationHolder HI_KILLSTREAK = new ConfigurationHolder("%नाम %राशि लोग मारे गए हैं", "hi.killstreak");
    public static final ConfigurationHolder EN_KILLJOY = new ConfigurationHolder("%name ended %victim's killing spree of %amount", "en.killjoy");
    public static final ConfigurationHolder DE_KILLJOY = new ConfigurationHolder("%name endete %victims amoklauf von %menge", "de.killjoy");
    public static final ConfigurationHolder ES_KILLJOY = new ConfigurationHolder("%nombre terminó matanza de %víctima de %cantidad", "es.killjoy");
    public static final ConfigurationHolder FR_KILLJOY = new ConfigurationHolder("%nom terminée tuerie %victime du %montant", "fr.killjoy");
    public static final ConfigurationHolder RU_KILLJOY = new ConfigurationHolder("%имя закончилось резню %жертва из %количество", "ru.killjoy");
    public static final ConfigurationHolder IT_KILLJOY = new ConfigurationHolder("%nome concluso serie di omicidi %vittima su %importo", "it.killjoy");
    public static final ConfigurationHolder MA_KILLJOY = new ConfigurationHolder("%名稱 截至 %受害者 殺人狂魔 %量", "ma.killjoy");
    public static final ConfigurationHolder HI_KILLJOY = new ConfigurationHolder("%नाम %शिकार %राशि की हत्या होड़ समाप्त", "hi.killjoy");
    public static final ConfigurationHolder EN_DEATH = new ConfigurationHolder("Death: %amount pts", "en.death");
    public static final ConfigurationHolder DE_DEATH = new ConfigurationHolder("Tod %menge punkten", "de.death");
    public static final ConfigurationHolder ES_DEATH = new ConfigurationHolder("Muerte %cantidad puntos", "es.death");
    public static final ConfigurationHolder FR_DEATH = new ConfigurationHolder("Décès %montant degrés", "fr.death");
    public static final ConfigurationHolder RU_DEATH = new ConfigurationHolder("смерть %количество пунктов", "ru.death");
    public static final ConfigurationHolder IT_DEATH = new ConfigurationHolder("Morte %importo punti", "it.death");
    public static final ConfigurationHolder MA_DEATH = new ConfigurationHolder("死亡 %量 點", "ma.death");
    public static final ConfigurationHolder HI_DEATH = new ConfigurationHolder("मौत %राशि अंक", "hi.death");
    public static final ConfigurationHolder EN_KILL = new ConfigurationHolder("Kill: %amount pts", "en.kill");
    public static final ConfigurationHolder DE_KILL = new ConfigurationHolder("Töten %menge punkten", "de.kill");
    public static final ConfigurationHolder ES_KILL = new ConfigurationHolder("Matar %cantidad puntos", "es.kill");
    public static final ConfigurationHolder FR_KILL = new ConfigurationHolder("Tuer %montant degrés", "fr.kill");
    public static final ConfigurationHolder RU_KILL = new ConfigurationHolder("убивать %количество пунктов", "ru.kill");
    public static final ConfigurationHolder IT_KILL = new ConfigurationHolder("Uccidere %importo punti", "it.kill");
    public static final ConfigurationHolder MA_KILL = new ConfigurationHolder("殺 %量 點", "ma.kill");
    public static final ConfigurationHolder HI_KILL = new ConfigurationHolder("मारना %राशि अंक", "hi.kill");
    
    public LanguageConfiguration(File dataFile) {
        super(dataFile);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static String getTranslation(String locale, String name) {
        switch(name) {
            case "killstreak":
                switch (locale) {
                    default:
                        return EN_KILLSTREAK.getString();
                    case "ma":
                        return replaceVars(MA_KILLSTREAK.getString(), locale);
                    case "es":
                        return replaceVars(ES_KILLSTREAK.getString(), locale);
                    case "de":
                        return replaceVars(DE_KILLSTREAK.getString(), locale);
                    case "fr":
                        return replaceVars(FR_KILLSTREAK.getString(), locale);
                    case "ru":
                        return replaceVars(RU_KILLSTREAK.getString(), locale);
                    case "it":
                        return replaceVars(IT_KILLSTREAK.getString(), locale);
                    case "hi":
                        return replaceVars(HI_KILLSTREAK.getString(), locale);
                }
            case "killjoy":
                switch (locale) {
                    default:
                        return EN_KILLJOY.getString();
                    case "ma":
                        return replaceVars(MA_KILLJOY.getString(), locale);
                    case "es":
                        return replaceVars(ES_KILLJOY.getString(), locale);
                    case "de":
                        return replaceVars(DE_KILLJOY.getString(), locale);
                    case "fr":
                        return replaceVars(FR_KILLJOY.getString(), locale);
                    case "ru":
                        return replaceVars(RU_KILLJOY.getString(), locale);
                    case "it":
                        return replaceVars(IT_KILLJOY.getString(), locale);
                    case "hi":
                        return replaceVars(HI_KILLJOY.getString(), locale);
                }
            case "death":
                switch (locale) {
                    default:
                        return EN_DEATH.getString();
                    case "ma":
                        return replaceVars(MA_DEATH.getString(), locale);
                    case "es":
                        return replaceVars(ES_DEATH.getString(), locale);
                    case "de":
                        return replaceVars(DE_DEATH.getString(), locale);
                    case "fr":
                        return replaceVars(FR_DEATH.getString(), locale);
                    case "ru":
                        return replaceVars(RU_DEATH.getString(), locale);
                    case "it":
                        return replaceVars(IT_DEATH.getString(), locale);
                    case "hi":
                        return replaceVars(HI_DEATH.getString(), locale);
                }
            case "kill":
                switch (locale) {
                    default:
                        return EN_DEATH.getString();
                    case "ma":
                        return replaceVars(MA_KILL.getString(), locale);
                    case "es":
                        return replaceVars(ES_KILL.getString(), locale);
                    case "de":
                        return replaceVars(DE_KILL.getString(), locale);
                    case "fr":
                        return replaceVars(FR_KILL.getString(), locale);
                    case "ru":
                        return replaceVars(RU_KILL.getString(), locale);
                    case "it":
                        return replaceVars(IT_KILL.getString(), locale);
                    case "hi":
                        return replaceVars(HI_KILL.getString(), locale);
                }
        }
        return "";
    }
    
    private static String replaceVars(String input, String locale) {
        switch (locale) {
            default:
                return input;
            case "ma":
                return input.replace("%名稱", "%name").replace("%量", "%amount").replace("%受害者", "%victim");
            case "es":
                return input.replace("%nombre", "%name").replace("%cantidad", "%amount").replace("%víctima", "%victim");
            case "de":
                return input.replace("%menge", "%amount").replace("%opfer", "%victim");
            case "fr":
                return input.replace("%nom", "%name").replace("%montant", "%amount").replace("%victime", "%victim");
            case "ru":
                return input.replace("%имя", "%name").replace("%количество", "%amount").replace("%жертва","%victim");
            case "it":
                return input.replace("%nome", "%name").replace("%importo", "%amount").replace("%vittima", "%victim");
            case "hi":
                return input.replace("%नाम", "%name").replace("%राशि", "%amount").replace("%शिकार", "%victim");
        }
    }
    
    @Override
    public void load() {
        try {
            super.load();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void save() {
        try {
            super.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
