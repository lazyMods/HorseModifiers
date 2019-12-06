package com.lazynessmind.horsemodifier.client.gui;

import com.lazynessmind.horsemodifier.HorseModifiers;
import com.lazynessmind.horsemodifier.client.container.HorseSpyContainer;
import com.lazynessmind.horsemodifier.common.items.ModItems;
import com.lazynessmind.horsemodifier.common.network.PacketHandler;
import com.lazynessmind.horsemodifier.common.network.packet.RemoveUpgradePacket;
import com.lazynessmind.horsemodifier.common.util.ItemHelper;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class HorseSpyScreen extends ContainerScreen<HorseSpyContainer> {

    private static final ResourceLocation HORSE_SPY = new ResourceLocation(HorseModifiers.MOD_ID + ":textures/gui/horse_spy.png");
    private HorseSpyContainer horseSpyContainer;

    public HorseSpyScreen(HorseSpyContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.setSize(176, 84);
        this.horseSpyContainer = screenContainer;
    }

    @Override
    protected void init() {
        super.init();
        initButtons();
    }

    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        this.drawItemStack(new ItemStack(ModItems.FLASH_CARROT), this.guiLeft + 18, this.guiTop + 9, String.valueOf(this.horseSpyContainer.getModifierCount(0)));
        this.drawItemStack(new ItemStack(ModItems.JUMP_CARROT), this.guiLeft + 18, this.guiTop + 34, String.valueOf(this.horseSpyContainer.getModifierCount(1)));
        this.drawItemStack(new ItemStack(ModItems.HEALTH_CARROT), this.guiLeft + 18, this.guiTop + 59, String.valueOf(this.horseSpyContainer.getModifierCount(2)));
    }

    //TODO: Change from hardcoded strings to translations.
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.drawString(this.font, TextFormatting.GOLD + "Speed: " + TextFormatting.WHITE + this.horseSpyContainer.getAttributeValue(0), 40, 16 - 4, 0);
        this.drawString(this.font, TextFormatting.GOLD + "Jump: " + TextFormatting.WHITE + this.horseSpyContainer.getAttributeValue(1), 40, 41 - 4, 0);
        this.drawString(this.font, TextFormatting.GOLD + "Health: " + TextFormatting.WHITE + this.horseSpyContainer.getAttributeValue(2), 40, 67 - 4, 0);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(HORSE_SPY);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.blit(i, j, 0, 0, this.xSize, this.ySize);
    }

    private void initButtons() {
        this.addButton(new GuiButtonExt(this.guiLeft + 154, this.guiTop + 13, 18, 10, "-", onPress -> {
            if (this.horseSpyContainer.getModifierCount(0) != 0) {
                PacketHandler.sendToServer(new RemoveUpgradePacket(ItemHelper.getStackFromItem(ModItems.FLASH_CARROT), this.horseSpyContainer.getHorseID()));
                horseSpyContainer.removeModifier(0);
            }
        }));

        this.addButton(new GuiButtonExt(this.guiLeft + 154, this.guiTop + 38, 18, 10, "-", onPress -> {
            if (this.horseSpyContainer.getModifierCount(1) != 0) {
                PacketHandler.sendToServer(new RemoveUpgradePacket(ItemHelper.getStackFromItem(ModItems.JUMP_CARROT), this.horseSpyContainer.getHorseID()));
                horseSpyContainer.removeModifier(1);
            }
        }));

        this.addButton(new GuiButtonExt(this.guiLeft + 154, this.guiTop + 63, 18, 10, "-", onPress -> {
            if (this.horseSpyContainer.getModifierCount(2) != 0) {
                PacketHandler.sendToServer(new RemoveUpgradePacket(ItemHelper.getStackFromItem(ModItems.HEALTH_CARROT), this.horseSpyContainer.getHorseID()));
                horseSpyContainer.removeModifier(2);
            }
        }));
    }

    //TODO: Move method to lazylib
    private void drawItemStack(ItemStack stack, int x, int y, String altText) {
        GlStateManager.translatef(0.0F, 0.0F, 32.0F);
        this.blitOffset = 200;
        this.itemRenderer.zLevel = 200.0F;
        FontRenderer font = stack.getItem().getFontRenderer(stack);
        if (font == null) font = this.font;
        this.itemRenderer.renderItemAndEffectIntoGUI(stack, x, y);
        this.itemRenderer.renderItemOverlayIntoGUI(font, stack, x, y, altText);
        this.blitOffset = 0;
        this.itemRenderer.zLevel = 0.0F;
    }

}
