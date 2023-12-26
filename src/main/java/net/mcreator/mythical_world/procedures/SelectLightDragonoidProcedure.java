package net.mcreator.mythical_world.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.mythical_world.MythicalWorldModVariables;
import net.mcreator.mythical_world.MythicalWorldMod;

import java.util.Map;

public class SelectLightDragonoidProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MythicalWorldMod.LOGGER.warn("Failed to load dependency entity for procedure SelectLightDragonoid!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			String _setval = "light";
			entity.getCapability(MythicalWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.subrace = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
