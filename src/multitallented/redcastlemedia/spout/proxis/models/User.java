package multitallented.redcastlemedia.spout.proxis.models;

/**
 *
 * @author Multitallented
 */
public class User {
    private String locale = "en";
    private final String name;
    
    public User(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setLocale(String locale) {
        this.locale = locale;
    }
    public String getLocale() {
        return locale;
    }
}
