package multitallented.redcastlemedia.spout.proxis.models.skill;

import java.util.ArrayList;
import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.models.SourceType;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.util.config.ConfigurationHolderConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;
import org.spout.vanilla.event.entity.EntityDamageEvent;
import org.spout.vanilla.source.DamageCause;

/**
 *
 * @author Multitallented
 */
public abstract class SkillSource extends ConfigurationHolderConfiguration {
	public final SourceType TYPE;
	private String name;
	private Proxis plugin;
	public final ArrayList<SkillComponentType> TYPES = new ArrayList<>();

	public SkillSource(SourceType type) {
		super(new YamlConfiguration());
		TYPE = type;
	}

	public void init(Proxis plugin, String sourceName) {
		this.plugin = plugin;
		this.name = sourceName;
	}

	public Proxis getPlugin() {
		return plugin;
	}
	public String getName() {
		return name;
	}

	public boolean damageCheck(Player damager, Entity damagee) {
		return plugin.getEngine().getEventManager().callEvent(new EntityDamageEvent(damagee, 0, DamageCause.UNKNOWN, false, (Entity) damager)).isCancelled();
	}
}
