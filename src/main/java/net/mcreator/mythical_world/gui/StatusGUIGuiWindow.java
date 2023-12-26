
package net.mcreator.mythical_world.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.mythical_world.MythicalWorldModVariables;
import net.mcreator.mythical_world.MythicalWorldMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class StatusGUIGuiWindow extends ContainerScreen<StatusGUIGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = StatusGUIGui.guistate;

	public StatusGUIGuiWindow(StatusGUIGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mythical_world:textures/1.0.png"));
		this.blit(ms, this.guiLeft + 0, this.guiTop + 1, 0, 0, 176, 166, 176, 166);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "" + (int) ((entity.getCapability(MythicalWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MythicalWorldModVariables.PlayerVariables())).strength) + "", 41, 40, -12895429);
		this.font.drawString(ms, "" + (int) ((entity.getCapability(MythicalWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MythicalWorldModVariables.PlayerVariables())).dextarity) + "", 41, 74, -12895429);
		this.font.drawString(ms, "" + (int) ((entity.getCapability(MythicalWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MythicalWorldModVariables.PlayerVariables())).edurance) + "", 41, 109, -12895429);
		this.font.drawString(ms, "" + (int) ((entity.getCapability(MythicalWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MythicalWorldModVariables.PlayerVariables())).wisdom) + "", 124, 40, -12895429);
		this.font.drawString(ms, "" + (int) ((entity.getCapability(MythicalWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MythicalWorldModVariables.PlayerVariables())).inteligence) + "", 124, 74, -12895429);
		this.font.drawString(ms, "" + (int) ((entity.getCapability(MythicalWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MythicalWorldModVariables.PlayerVariables())).charisma) + "", 124, 109, -12895429);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		this.addButton(new Button(this.guiLeft + 55, this.guiTop + 33, 30, 20, new StringTextComponent("+"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new StatusGUIGui.ButtonPressedMessage(0, x, y, z));
				StatusGUIGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 55, this.guiTop + 67, 30, 20, new StringTextComponent("+"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new StatusGUIGui.ButtonPressedMessage(1, x, y, z));
				StatusGUIGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 55, this.guiTop + 102, 30, 20, new StringTextComponent("+"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new StatusGUIGui.ButtonPressedMessage(2, x, y, z));
				StatusGUIGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 55, this.guiTop + 101, 30, 20, new StringTextComponent("+"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new StatusGUIGui.ButtonPressedMessage(3, x, y, z));
				StatusGUIGui.handleButtonAction(entity, 3, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 138, this.guiTop + 33, 30, 20, new StringTextComponent("+"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new StatusGUIGui.ButtonPressedMessage(4, x, y, z));
				StatusGUIGui.handleButtonAction(entity, 4, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 138, this.guiTop + 67, 30, 20, new StringTextComponent("+"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new StatusGUIGui.ButtonPressedMessage(5, x, y, z));
				StatusGUIGui.handleButtonAction(entity, 5, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 138, this.guiTop + 102, 30, 20, new StringTextComponent("+"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new StatusGUIGui.ButtonPressedMessage(6, x, y, z));
				StatusGUIGui.handleButtonAction(entity, 6, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 6, this.guiTop + 33, 30, 20, new StringTextComponent("-"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new StatusGUIGui.ButtonPressedMessage(7, x, y, z));
				StatusGUIGui.handleButtonAction(entity, 7, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 6, this.guiTop + 67, 30, 20, new StringTextComponent("-"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new StatusGUIGui.ButtonPressedMessage(8, x, y, z));
				StatusGUIGui.handleButtonAction(entity, 8, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 6, this.guiTop + 102, 30, 20, new StringTextComponent("-"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new StatusGUIGui.ButtonPressedMessage(9, x, y, z));
				StatusGUIGui.handleButtonAction(entity, 9, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 89, this.guiTop + 33, 30, 20, new StringTextComponent("-"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new StatusGUIGui.ButtonPressedMessage(10, x, y, z));
				StatusGUIGui.handleButtonAction(entity, 10, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 89, this.guiTop + 67, 30, 20, new StringTextComponent("-"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new StatusGUIGui.ButtonPressedMessage(11, x, y, z));
				StatusGUIGui.handleButtonAction(entity, 11, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 89, this.guiTop + 102, 30, 20, new StringTextComponent("-"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new StatusGUIGui.ButtonPressedMessage(12, x, y, z));
				StatusGUIGui.handleButtonAction(entity, 12, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 64, this.guiTop + 140, 46, 20, new StringTextComponent("Next"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new StatusGUIGui.ButtonPressedMessage(13, x, y, z));
				StatusGUIGui.handleButtonAction(entity, 13, x, y, z);
			}
		}));
	}
}
