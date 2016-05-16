package thexnator.furnituremod;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thexnator.furnituremod.config.properties.ConfigurationChange;

public class EntityEventHandler {

	public static final String FURNITUREMOD_PERSIST_TAG = "FurnitureMod";
	public static final String GIVEN_MANUAL_TAG = "givenManual";
	public static final String LATEST_CHANGELOG_TAG = "latestChangelog";

	private Set<Class<? extends Entity>> entityBlacklist;

	private Set<Class<? extends Entity>> getBlacklist() {
		if (entityBlacklist == null) {
			entityBlacklist = Sets.newIdentityHashSet();

			Set<String> unknownNames = Sets.newHashSet();
			for (String name : Config.disableMobNames) {

				Class<? extends Entity> cls = EntityList.stringToClassMapping.get(name);
				if (cls != null) entityBlacklist.add(cls);
				else unknownNames.add(name);
			}

			for (Class<? extends Entity> cls : EntityList.classToStringMapping.keySet()) {
				if (unknownNames.isEmpty()) break;
				if (unknownNames.remove(cls.getName())) entityBlacklist.add(cls);
			}

			if (!unknownNames.isEmpty()) Log.warn("Can't identify mobs for blacklist: %s", unknownNames);
		}

		return entityBlacklist;
	}

	@SubscribeEvent
	public void onReconfigure(ConfigurationChange.Post evt) {
		if (evt.check("additional", "disableMobNames")) entityBlacklist = null;
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {

		final Entity entity = event.entity;
		if (entity != null) {
			Set<Class<? extends Entity>> blacklist = getBlacklist();
			if (blacklist.contains(entity.getClass())) {
				entity.setDead();
				event.setCanceled(true);
				return;
			}
		}
	}
}
