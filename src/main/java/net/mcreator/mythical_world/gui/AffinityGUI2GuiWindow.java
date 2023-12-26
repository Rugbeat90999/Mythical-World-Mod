
package net.mcreator.mythical_world.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.mythical_world.MythicalWorldModVariables;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class AffinityGUI2GuiWindow extends ContainerScreen<AffinityGUI2Gui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = AffinityGUI2Gui.guistate;

	public AffinityGUI2GuiWindow(AffinityGUI2Gui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
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

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mythical_world:textures/2.0.png"));
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
				.orElse(new MythicalWorldModVariables.PlayerVariables())).waterAffinity) + "", 41, 40, -12895429);
		this.font.drawString(ms, "" + (int) ((entity.getCapability(MythicalWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MythicalWorldModVariables.PlayerVariables())).earthAffinity) + "", 41, 74, -12895429);
		this.font.drawString(ms, "" + (int) ((entity.getCapability(MythicalWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MythicalWorldModVariables.PlayerVariables())).fireAffinity) + "", 41, 109, -12895429);
		this.font.drawString(ms, "" + (int) ((entity.getCapability(MythicalWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MythicalWorldModVariables.PlayerVariables())).airAffinity) + "", 124, 40, -12895429);
		this.font.drawString(ms, "" + (int) ((entity.getCapability(MythicalWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MythicalWorldModVariables.PlayerVariables())).lightAffinity) + "", 124, 74, -12895429);
		this.font.drawString(ms, "" + (int) ((entity.getCapability(MythicalWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MythicalWorldModVariables.PlayerVariables())).darkAffinity) + "", 124, 109, -12895429);
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
	}
}
