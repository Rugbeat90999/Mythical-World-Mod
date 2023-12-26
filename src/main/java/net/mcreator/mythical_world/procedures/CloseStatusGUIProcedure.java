package net.mcreator.mythical_world.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.mythical_world.MythicalWorldModVariables;
import net.mcreator.mythical_world.MythicalWorldMod;

import java.util.Map;

public class CloseStatusGUIProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MythicalWorldMod.LOGGER.warn("Failed to load dependency entity for procedure CloseStatusGUI!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			double _setval = 0;
			entity.getCapability(MythicalWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.status_open = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}