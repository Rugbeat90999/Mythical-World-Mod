
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
public class MerfolkGUIGuiWindow extends ContainerScreen<MerfolkGUIGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = MerfolkGUIGui.guistate;

	public MerfolkGUIGuiWindow(MerfolkGUIGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("mythical_world:textures/merfolk_gui.png");

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
		this.font.drawString(ms, "Merfolk", 6, 7, -13421773);
		this.font.drawString(ms, "Merfolk can breath underwater,", 6, 22, -12829636);
		this.font.drawString(ms, "but not on land. All their", 6, 32, -12829636);
		this.font.drawString(ms, "stats are increased while in", 6, 42, -12829636);
		this.font.drawString(ms, "water and decreased while", 6, 52, -12829636);
		this.font.drawString(ms, "Land and water benifits and", 6, 94, -12829636);
		this.font.drawString(ms, "penaltys are both decreased,", 6, 104, -12829636);
		this.font.drawString(ms, "", 6, 113, -12829636);
		this.font.drawString(ms, "outside of water", 6, 61, -12829636);
		this.font.drawString(ms, "breath time is increased and", 6, 116, -12829636);
		this.font.drawString(ms, "can stop oponites from moving", 7, 126, -12829636);
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
		this.addButton(new Button(this.guiLeft + 112, this.guiTop + 139, 56, 20, new StringTextComponent("Select"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new MerfolkGUIGui.ButtonPressedMessage(0, x, y, z));
				MerfolkGUIGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 6, this.guiTop + 74, 66, 20, new StringTextComponent("Siren"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new MerfolkGUIGui.ButtonPressedMessage(1, x, y, z));
				MerfolkGUIGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 6, this.guiTop + 139, 46, 20, new StringTextComponent("Back"), e -> {
			if (true) {
				MythicalWorldMod.PACKET_HANDLER.sendToServer(new MerfolkGUIGui.ButtonPressedMessage(2, x, y, z));
				MerfolkGUIGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
	}
}
