package com.helliongames.snifferplus.client.gui.screens.inventory;

import com.helliongames.snifferplus.Constants;
import com.helliongames.snifferplus.access.SnifferAccess;
import com.helliongames.snifferplus.world.inventory.SnifferInventoryMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.player.Inventory;

public class SnifferInventoryScreen extends AbstractContainerScreen<SnifferInventoryMenu> {
    private static final ResourceLocation SNIFFER_INVENTORY_LOCATION = new ResourceLocation(Constants.MOD_ID, "textures/gui/container/sniffer.png");
    private final Sniffer sniffer;
    private float xMouse;
    private float yMouse;

    public SnifferInventoryScreen(SnifferInventoryMenu menu, Inventory inventory, Sniffer sniffer) {
        super(menu, inventory, sniffer.getDisplayName());
        this.sniffer = sniffer;
    }

    @Override
    protected void renderBg(PoseStack stack, float $$1, int $$2, int $$3) {
        RenderSystem.setShaderTexture(0, SNIFFER_INVENTORY_LOCATION);
        int width = (this.width - this.imageWidth) / 2;
        int height = (this.height - this.imageHeight) / 2;

        // Render inventory screen
        blit(stack, width, height, 0, 0, this.imageWidth, this.imageHeight);

        // Render saddle space
        blit(stack, width + 7, height + 16, 18, this.imageHeight + 72, 18, 18);

        // Render sniffing space
        blit(stack, width + 7, height + 34, 0, this.imageHeight + 72, 18, 18);

        if (((SnifferAccess) sniffer).hasChest()) {
            // Render chest inventory spaces
            blit(stack, width + 79, height + 7, 0, this.imageHeight, ((SnifferAccess) sniffer).getInventoryColumns() * 18, 72);
        }

        InventoryScreen.renderEntityInInventoryFollowsMouse(stack, width + 51, height + 55, 11, (float)(width + 51) - this.xMouse, (float)(height + 75 - 50) - this.yMouse, this.sniffer);
    }

    public void render(PoseStack stack, int $$1, int $$2, float $$3) {
        this.renderBackground(stack);
        this.xMouse = (float)$$1;
        this.yMouse = (float)$$2;
        super.render(stack, $$1, $$2, $$3);
        this.renderTooltip(stack, $$1, $$2);
    }
}
