
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

import net.mcreator.mythical_world.MythicalWorldMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class RaceGUIGuiWindow extends ContainerScreen<RaceGUIGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = RaceGUIGui.guistate;

	public RaceGUIGuiWindow(RaceGUIGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 111;
		this.ySize = 190;
	}

	@Override
	public boolean isPauseScreen() {
		return true;
	}

	private static final ResourceLocation texture = new ResourceLocation("mythical_world:textures/race_gui.png");

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
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
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
		this.font.drawString(ms, "Select a Race", 21, 6, -13421773);
		this.font.drawString(ms, "", 118, 14, -13421773);
		this.font.drawString(ms, "", 37, 77, -13421773);
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
		this.addButton(new Button(this.guiLeft + 29, this.guiTop + 18, 50, 20, new StringTextComponent("Human"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new RaceGUIGui.ButtonPressedMessage(0, x, y, z));
				RaceGUIGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 39, this.guiTop + 43, 30, 20, new StringTextComponent("Elf"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new RaceGUIGui.ButtonPressedMessage(1, x, y, z));
				RaceGUIGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 29, this.guiTop + 67, 50, 20, new StringTextComponent("Dwarf"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new RaceGUIGui.ButtonPressedMessage(2, x, y, z));
				RaceGUIGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 19, this.guiTop + 91, 70, 20, new StringTextComponent("Merfolk"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new RaceGUIGui.ButtonPressedMessage(3, x, y, z));
				RaceGUIGui.handleButtonAction(entity, 3, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 14, this.guiTop + 115, 80, 20, new StringTextComponent("Halfling"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new RaceGUIGui.ButtonPressedMessage(4, x, y, z));
				RaceGUIGui.handleButtonAction(entity, 4, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 9, this.guiTop + 139, 90, 20, new StringTextComponent("Dragonoid"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new RaceGUIGui.ButtonPressedMessage(5, x, y, z));
				RaceGUIGui.handleButtonAction(entity, 5, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 30, this.guiTop + 163, 50, 20, new StringTextComponent("Demon"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new RaceGUIGui.ButtonPressedMessage(6, x, y, z));
				RaceGUIGui.handleButtonAction(entity, 6, x, y, z);
			}
		}));
	}
}
