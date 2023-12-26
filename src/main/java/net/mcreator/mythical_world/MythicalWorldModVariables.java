package net.mcreator.mythical_world;

import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.network.PacketBuffer;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

public class MythicalWorldModVariables {
	public MythicalWorldModVariables(MythicalWorldModElements elements) {
		elements.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new,
				PlayerVariablesSyncMessage::handler);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
	}

	private void init(FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(PlayerVariables.class, new PlayerVariablesStorage(), PlayerVariables::new);
	}

	@CapabilityInject(PlayerVariables.class)
	public static Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = null;

	@SubscribeEvent
	public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof PlayerEntity && !(event.getObject() instanceof FakePlayer))
			event.addCapability(new ResourceLocation("mythical_world", "player_variables"), new PlayerVariablesProvider());
	}

	private static class PlayerVariablesProvider implements ICapabilitySerializable<INBT> {
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(PLAYER_VARIABLES_CAPABILITY::getDefaultInstance);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public INBT serializeNBT() {
			return PLAYER_VARIABLES_CAPABILITY.getStorage().writeNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new),
					null);
		}

		@Override
		public void deserializeNBT(INBT nbt) {
			PLAYER_VARIABLES_CAPABILITY.getStorage().readNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new), null,
					nbt);
		}
	}

	private static class PlayerVariablesStorage implements Capability.IStorage<PlayerVariables> {
		@Override
		public INBT writeNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putBoolean("hasRace", instance.hasRace);
			nbt.putString("race", instance.race);
			nbt.putDouble("mana", instance.mana);
			nbt.putDouble("maxMana", instance.maxMana);
			nbt.putDouble("magicAffinity", instance.magicAffinity);
			nbt.putDouble("level", instance.level);
			nbt.putString("subrace", instance.subrace);
			nbt.putDouble("strength", instance.strength);
			nbt.putDouble("edurance", instance.edurance);
			nbt.putDouble("dextarity", instance.dextarity);
			nbt.putDouble("wisdom", instance.wisdom);
			nbt.putDouble("inteligence", instance.inteligence);
			nbt.putDouble("charisma", instance.charisma);
			nbt.putDouble("waterAffinity", instance.waterAffinity);
			nbt.putDouble("earthAffinity", instance.earthAffinity);
			nbt.putDouble("fireAffinity", instance.fireAffinity);
			nbt.putDouble("airAffinity", instance.airAffinity);
			nbt.putDouble("lightWeaponAffinity", instance.lightWeaponAffinity);
			nbt.putDouble("heavyWeaponAffinity", instance.heavyWeaponAffinity);
			nbt.putDouble("mediumWeaponAffinity", instance.mediumWeaponAffinity);
			nbt.putDouble("shieldAffinity", instance.shieldAffinity);
			nbt.putDouble("lightAffinity", instance.lightAffinity);
			nbt.putDouble("darkAffinity", instance.darkAffinity);
			nbt.putDouble("status_open", instance.status_open);
			return nbt;
		}

		@Override
		public void readNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side, INBT inbt) {
			CompoundNBT nbt = (CompoundNBT) inbt;
			instance.hasRace = nbt.getBoolean("hasRace");
			instance.race = nbt.getString("race");
			instance.mana = nbt.getDouble("mana");
			instance.maxMana = nbt.getDouble("maxMana");
			instance.magicAffinity = nbt.getDouble("magicAffinity");
			instance.level = nbt.getDouble("level");
			instance.subrace = nbt.getString("subrace");
			instance.strength = nbt.getDouble("strength");
			instance.edurance = nbt.getDouble("edurance");
			instance.dextarity = nbt.getDouble("dextarity");
			instance.wisdom = nbt.getDouble("wisdom");
			instance.inteligence = nbt.getDouble("inteligence");
			instance.charisma = nbt.getDouble("charisma");
			instance.waterAffinity = nbt.getDouble("waterAffinity");
			instance.earthAffinity = nbt.getDouble("earthAffinity");
			instance.fireAffinity = nbt.getDouble("fireAffinity");
			instance.airAffinity = nbt.getDouble("airAffinity");
			instance.lightWeaponAffinity = nbt.getDouble("lightWeaponAffinity");
			instance.heavyWeaponAffinity = nbt.getDouble("heavyWeaponAffinity");
			instance.mediumWeaponAffinity = nbt.getDouble("mediumWeaponAffinity");
			instance.shieldAffinity = nbt.getDouble("shieldAffinity");
			instance.lightAffinity = nbt.getDouble("lightAffinity");
			instance.darkAffinity = nbt.getDouble("darkAffinity");
			instance.status_open = nbt.getDouble("status_open");
		}
	}

	public static class PlayerVariables {
		public boolean hasRace = false;
		public String race = "\"\"";
		public double mana = 0.0;
		public double maxMana = 100.0;
		public double magicAffinity = 0.0;
		public double level = 0.0;
		public String subrace = "\"\"";
		public double strength = 1.0;
		public double edurance = 1.0;
		public double dextarity = 1.0;
		public double wisdom = 1.0;
		public double inteligence = 1.0;
		public double charisma = 1.0;
		public double waterAffinity = 1.0;
		public double earthAffinity = 1.0;
		public double fireAffinity = 1.0;
		public double airAffinity = 1.0;
		public double lightWeaponAffinity = 1.0;
		public double heavyWeaponAffinity = 1.0;
		public double mediumWeaponAffinity = 1.0;
		public double shieldAffinity = 1.0;
		public double lightAffinity = 1.0;
		public double darkAffinity = 1.0;
		public double status_open = 0;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayerEntity)
				MythicalWorldMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entity),
						new PlayerVariablesSyncMessage(this));
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event) {
		PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new PlayerVariables()));
		PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
		clone.hasRace = original.hasRace;
		clone.race = original.race;
		clone.maxMana = original.maxMana;
		clone.magicAffinity = original.magicAffinity;
		clone.level = original.level;
		clone.subrace = original.subrace;
		clone.strength = original.strength;
		clone.edurance = original.edurance;
		clone.dextarity = original.dextarity;
		clone.wisdom = original.wisdom;
		clone.inteligence = original.inteligence;
		clone.charisma = original.charisma;
		clone.waterAffinity = original.waterAffinity;
		clone.earthAffinity = original.earthAffinity;
		clone.fireAffinity = original.fireAffinity;
		clone.airAffinity = original.airAffinity;
		clone.lightWeaponAffinity = original.lightWeaponAffinity;
		clone.heavyWeaponAffinity = original.heavyWeaponAffinity;
		clone.mediumWeaponAffinity = original.mediumWeaponAffinity;
		clone.shieldAffinity = original.shieldAffinity;
		clone.lightAffinity = original.lightAffinity;
		clone.darkAffinity = original.darkAffinity;
		if (!event.isWasDeath()) {
			clone.mana = original.mana;
			clone.status_open = original.status_open;
		}
	}

	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;

		public PlayerVariablesSyncMessage(PacketBuffer buffer) {
			this.data = new PlayerVariables();
			new PlayerVariablesStorage().readNBT(null, this.data, null, buffer.readCompoundTag());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, PacketBuffer buffer) {
			buffer.writeCompoundTag((CompoundNBT) new PlayerVariablesStorage().writeNBT(null, message.data, null));
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new PlayerVariables()));
					variables.hasRace = message.data.hasRace;
					variables.race = message.data.race;
					variables.mana = message.data.mana;
					variables.maxMana = message.data.maxMana;
					variables.magicAffinity = message.data.magicAffinity;
					variables.level = message.data.level;
					variables.subrace = message.data.subrace;
					variables.strength = message.data.strength;
					variables.edurance = message.data.edurance;
					variables.dextarity = message.data.dextarity;
					variables.wisdom = message.data.wisdom;
					variables.inteligence = message.data.inteligence;
					variables.charisma = message.data.charisma;
					variables.waterAffinity = message.data.waterAffinity;
					variables.earthAffinity = message.data.earthAffinity;
					variables.fireAffinity = message.data.fireAffinity;
					variables.airAffinity = message.data.airAffinity;
					variables.lightWeaponAffinity = message.data.lightWeaponAffinity;
					variables.heavyWeaponAffinity = message.data.heavyWeaponAffinity;
					variables.mediumWeaponAffinity = message.data.mediumWeaponAffinity;
					variables.shieldAffinity = message.data.shieldAffinity;
					variables.lightAffinity = message.data.lightAffinity;
					variables.darkAffinity = message.data.darkAffinity;
					variables.status_open = message.data.status_open;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
