package com.example.exampleitemcontainer;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ExampleItemContainerScreen extends ContainerScreen<ExampleItemContainer> implements IHasContainer<ExampleItemContainer> {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(ExampleItemContainerMod.MOD_ID, "textures/gui/container/example_item_container.png");

    public ExampleItemContainerScreen(ExampleItemContainer screenContainer, PlayerInventory playerInventory, ITextComponent title) {
        super(screenContainer, playerInventory, title);
        passEvents = false;
        ySize = 132;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        font.drawString(this.title.getFormattedText(), 8.0F, 6.0F, 4210752);
        font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float) (this.ySize - 94), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.blit(x, y, 0, 0, this.xSize, this.ySize);
    }
}
