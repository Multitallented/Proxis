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
    //<editor-fold defaultstate="collapsed" desc="KILLSTREAK">
    public static final ConfigurationHolder EN_KILLSTREAK = new ConfigurationHolder("%name is on a killstreak of %amount", "en.killstreak");
    public static final ConfigurationHolder DE_KILLSTREAK = new ConfigurationHolder("%name hat %menge menschen getötet", "de.killstreak");
    public static final ConfigurationHolder ES_KILLSTREAK = new ConfigurationHolder("%nombre está en una racha de matar %cantidad", "es.killstreak");
    public static final ConfigurationHolder FR_KILLSTREAK = new ConfigurationHolder("%nom a tué %montant personnes", "fr.killstreak");
    public static final ConfigurationHolder RU_KILLSTREAK = new ConfigurationHolder("%имя убил %количество человек", "ru.killstreak");
    public static final ConfigurationHolder IT_KILLSTREAK = new ConfigurationHolder("%nome ha ucciso %importo persone", "it.killstreak");
    public static final ConfigurationHolder MA_KILLSTREAK = new ConfigurationHolder("%名稱已造成%量人死亡", "ma.killstreak");
    public static final ConfigurationHolder HI_KILLSTREAK = new ConfigurationHolder("%नाम %राशि लोग मारे गए हैं", "hi.killstreak");
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="KILLJOY">
    public static final ConfigurationHolder EN_KILLJOY = new ConfigurationHolder("%name ended %victim's killing spree of %amount", "en.killjoy");
    public static final ConfigurationHolder DE_KILLJOY = new ConfigurationHolder("%name endete %victims amoklauf von %menge", "de.killjoy");
    public static final ConfigurationHolder ES_KILLJOY = new ConfigurationHolder("%nombre terminó matanza de %víctima de %cantidad", "es.killjoy");
    public static final ConfigurationHolder FR_KILLJOY = new ConfigurationHolder("%nom terminée tuerie %victime du %montant", "fr.killjoy");
    public static final ConfigurationHolder RU_KILLJOY = new ConfigurationHolder("%имя закончилось резню %жертва из %количество", "ru.killjoy");
    public static final ConfigurationHolder IT_KILLJOY = new ConfigurationHolder("%nome concluso serie di omicidi %vittima su %importo", "it.killjoy");
    public static final ConfigurationHolder MA_KILLJOY = new ConfigurationHolder("%名稱截至%受害者殺人狂魔%量", "ma.killjoy");
    public static final ConfigurationHolder HI_KILLJOY = new ConfigurationHolder("%नाम %शिकार %राशि की हत्या होड़ समाप्त", "hi.killjoy");
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="DEATH">
    public static final ConfigurationHolder EN_DEATH = new ConfigurationHolder("Death: %amount pts", "en.death");
    public static final ConfigurationHolder DE_DEATH = new ConfigurationHolder("Tod %menge punkten", "de.death");
    public static final ConfigurationHolder ES_DEATH = new ConfigurationHolder("Muerte %cantidad puntos", "es.death");
    public static final ConfigurationHolder FR_DEATH = new ConfigurationHolder("Décès %montant degrés", "fr.death");
    public static final ConfigurationHolder RU_DEATH = new ConfigurationHolder("смерть %количество пунктов", "ru.death");
    public static final ConfigurationHolder IT_DEATH = new ConfigurationHolder("Morte %importo punti", "it.death");
    public static final ConfigurationHolder MA_DEATH = new ConfigurationHolder("死亡%量點", "ma.death");
    public static final ConfigurationHolder HI_DEATH = new ConfigurationHolder("मौत %राशि अंक", "hi.death");
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="KILL">
    public static final ConfigurationHolder EN_KILL = new ConfigurationHolder("Kill: %amount pts", "en.kill");
    public static final ConfigurationHolder DE_KILL = new ConfigurationHolder("Töten %menge punkten", "de.kill");
    public static final ConfigurationHolder ES_KILL = new ConfigurationHolder("Matar %cantidad puntos", "es.kill");
    public static final ConfigurationHolder FR_KILL = new ConfigurationHolder("Tuer %montant degrés", "fr.kill");
    public static final ConfigurationHolder RU_KILL = new ConfigurationHolder("убивать %количество пунктов", "ru.kill");
    public static final ConfigurationHolder IT_KILL = new ConfigurationHolder("Uccidere %importo punti", "it.kill");
    public static final ConfigurationHolder MA_KILL = new ConfigurationHolder("殺%量點", "ma.kill");
    public static final ConfigurationHolder HI_KILL = new ConfigurationHolder("मारना %राशि अंक", "hi.kill");
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="LOW_HEALTH">
    public static final ConfigurationHolder EN_LOW_HEALTH = new ConfigurationHolder("Near Death: %amount pts", "en.low-health");
    public static final ConfigurationHolder DE_LOW_HEALTH = new ConfigurationHolder("Dem tode nahe %menge punkten", "de.low-health");
    public static final ConfigurationHolder ES_LOW_HEALTH = new ConfigurationHolder("Cerca de la muerte %cantidad puntos", "es.low-health");
    public static final ConfigurationHolder FR_LOW_HEALTH = new ConfigurationHolder("Proche de la mort %montant degrés", "fr.low-health");
    public static final ConfigurationHolder RU_LOW_HEALTH = new ConfigurationHolder("рядом смерти %количество пунктов", "ru.low-health");
    public static final ConfigurationHolder IT_LOW_HEALTH = new ConfigurationHolder("Vicino a morte %importo punti", "it.low-health");
    public static final ConfigurationHolder MA_LOW_HEALTH = new ConfigurationHolder("近死亡%量點", "ma.low-health");
    public static final ConfigurationHolder HI_LOW_HEALTH = new ConfigurationHolder("मौत के पास %राशि अंक", "hi.low-health");
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="KILLSTREAK_POINTS">
    public static final ConfigurationHolder EN_KILLSTREAK_POINTS = new ConfigurationHolder("Kill Streak: %amount pts", "en.killstreak-points");
    public static final ConfigurationHolder DE_KILLSTREAK_POINTS = new ConfigurationHolder("Konsekutiv tötet %menge punkten", "de.killstreak-points");
    public static final ConfigurationHolder ES_KILLSTREAK_POINTS = new ConfigurationHolder("Muertes consecutiva %cantidad puntos", "es.killstreak-points");
    public static final ConfigurationHolder FR_KILLSTREAK_POINTS = new ConfigurationHolder("Tue consécutives %montant degrés", "fr.killstreak-points");
    public static final ConfigurationHolder RU_KILLSTREAK_POINTS = new ConfigurationHolder("последовательный убивает %количество пунктов", "ru.killstreak-points");
    public static final ConfigurationHolder IT_KILLSTREAK_POINTS = new ConfigurationHolder("Consecutivo uccide %importo punti", "it.killstreak-points");
    public static final ConfigurationHolder MA_KILLSTREAK_POINTS = new ConfigurationHolder("連續殺死%量點", "ma.killstreak-points");
    public static final ConfigurationHolder HI_KILLSTREAK_POINTS = new ConfigurationHolder("लगातार मारता %राशि अंक", "hi.killstreak-points");
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="KILLJOY_POINTS">
    public static final ConfigurationHolder EN_KILLJOY_POINTS = new ConfigurationHolder("Kill Joy: %amount pts", "en.killjoy-points");
    public static final ConfigurationHolder DE_KILLJOY_POINTS = new ConfigurationHolder("Fortlaufende killer gestoppt %menge punkten", "de.killjoy-points");
    public static final ConfigurationHolder ES_KILLJOY_POINTS = new ConfigurationHolder("Asesino consecutivo detenido %cantidad puntos", "es.killjoy-points");
    public static final ConfigurationHolder FR_KILLJOY_POINTS = new ConfigurationHolder("Tueur consécutive arrêté %montant degrés", "fr.killjoy-points");
    public static final ConfigurationHolder RU_KILLJOY_POINTS = new ConfigurationHolder("Последовательный убийца остановился %количество пунктов", "ru.killjoy-points");
    public static final ConfigurationHolder IT_KILLJOY_POINTS = new ConfigurationHolder("Assassino consecutiva fermato %importo punti", "it.killjoy-points");
    public static final ConfigurationHolder MA_KILLJOY_POINTS = new ConfigurationHolder("連續殺手停止%量點", "ma.killjoy-points");
    public static final ConfigurationHolder HI_KILLJOY_POINTS = new ConfigurationHolder("लगातार हत्यारा बंद कर दिया %राशि अंक", "hi.killjoy-points");
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="TOTAL_POINTS">
    public static final ConfigurationHolder EN_TOTAL_POINTS = new ConfigurationHolder("Total: %amount pts", "en.total-points");
    public static final ConfigurationHolder DE_TOTAL_POINTS = new ConfigurationHolder("Gesamt %menge punkten", "de.total-points");
    public static final ConfigurationHolder ES_TOTAL_POINTS = new ConfigurationHolder("Total %cantidad puntos", "es.total-points");
    public static final ConfigurationHolder FR_TOTAL_POINTS = new ConfigurationHolder("Total %montant degrés", "fr.total-points");
    public static final ConfigurationHolder RU_TOTAL_POINTS = new ConfigurationHolder("общий %количество пунктов", "ru.total-points");
    public static final ConfigurationHolder IT_TOTAL_POINTS = new ConfigurationHolder("Totale %importo punti", "it.total-points");
    public static final ConfigurationHolder MA_TOTAL_POINTS = new ConfigurationHolder("總%量點", "ma.total-points");
    public static final ConfigurationHolder HI_TOTAL_POINTS = new ConfigurationHolder("संपूर्ण %राशि अंक", "hi.total-points");
    //</editor-fold>
    public static LanguageConfiguration instance;
    
    public LanguageConfiguration(File dataFile) {
        super(dataFile);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        instance = this;
    }
    
    public String getTranslation(String locale, String name) {
        return replaceVars(this.getNode(locale + "." + name).getString(""), locale);
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
