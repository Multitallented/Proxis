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
        }
        return "";
    }
    
    private static String replaceVars(String input, String locale) {
        switch (locale) {
            default:
                return input;
            case "ma":
                return input.replace("%名稱", "%name").replace("%量", "%amount");
            case "es":
                return input.replace("%nombre", "%name").replace("%cantidad", "%amount");
            case "de":
                return input.replace("%menge", "%amount");
            case "fr":
                return input.replace("%nom", "%name").replace("%montant", "%amount");
            case "ru":
                return input.replace("%имя", "%name").replace("%количество", "%amount");
            case "it":
                return input.replace("%nome", "%name").replace("%importo", "%amount");
            case "hi":
                return input.replace("%नाम", "%name").replace("%राशि", "%amount");
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
