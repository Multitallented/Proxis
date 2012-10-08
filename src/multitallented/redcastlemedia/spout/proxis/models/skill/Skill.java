package multitallented.redcastlemedia.spout.proxis.models.skill;

import java.io.File;
import java.util.HashSet;
import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.models.conditions.ConditionSource;
import multitallented.redcastlemedia.spout.proxis.models.effects.EffectSource;
import multitallented.redcastlemedia.spout.proxis.models.targets.TargetSource;
import org.spout.api.util.config.yaml.YamlConfiguration;

/**
 *
 * @author Multitallented
 */
public class Skill extends YamlConfiguration {
	public final String NAME;
	public final HashSet<SkillComponentType> TYPES = new HashSet<>();
	public final SkillConfiguration SKILL_CONFIG;
	private final Proxis plugin;
	private final HashSet<ConditionSource> conditions = new HashSet<>();
	private final HashSet<TargetSource> targets = new HashSet<>();
	private final HashSet<EffectSource> effects = new HashSet<>();

	public Skill(Proxis plugin, String name, SkillConfiguration skillConfig) {
		super(new File(new File(plugin.getDataFolder(), "skills"), name + ".yml"));
		NAME = name;
		SKILL_CONFIG = skillConfig;
		this.plugin = plugin;
	}
	
	public HashSet<EffectSource> getEffects() {
		return effects;
	}
}
