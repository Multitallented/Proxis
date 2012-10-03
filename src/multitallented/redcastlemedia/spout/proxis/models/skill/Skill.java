package multitallented.redcastlemedia.spout.proxis.models.skill;

import java.io.File;
import java.util.HashSet;
import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.models.effects.Effect;
import org.spout.api.util.config.yaml.YamlConfiguration;

/**
 *
 * @author Multitallented
 */
public abstract class Skill extends YamlConfiguration {
	public final String NAME;
	public final HashSet<SkillComponentType> TYPES = new HashSet<>();
	public final SkillConfiguration SKILL_CONFIG;
	private final Proxis plugin;

	public Skill(Proxis plugin, String name, SkillConfiguration skillConfig) {
		super(new File(new File(plugin.getDataFolder(), "skills"), name + ".yml"));
		NAME = name;
		SKILL_CONFIG = skillConfig;
		this.plugin = plugin;
	}
	
	public Effect getEffect(String something) {
		//TODO fix this
		return null;
	}
}
