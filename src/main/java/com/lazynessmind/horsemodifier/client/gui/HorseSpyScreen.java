package com.lazynessmind.horsemodifier.client.gui;

import com.lazynessmind.horsemodifier.HorseModifiers;
import com.lazynessmind.horsemodifier.common.horsedata.HorsesData;
import com.lazynessmind.horsemodifier.common.items.ModItems;
import com.lazynessmind.horsemodifier.common.network.PacketHandler;
import com.lazynessmind.horsemodifier.common.network.packet.RemoveModifierPacket;
import com.lazynessmind.horsemodifier.common.util.ItemHelper;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class HorseSpyScreen extends Screen {

    private static final ResourceLocation HORSE_SPY = new ResourceLocation(HorseModifiers.MOD_ID + ":textures/gui/horse_spy.png");
    private static HorseEntity horseEntity;
    private int guiLeft;
    private int guiTop;
    private final int xSize = 176;
    private final int ySize = 84;

    public HorseSpyScreen(HorseEntity entity) {
        super(new StringTextComponent(""));
        this.setSize(176, 84);
        horseEntity = entity;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        super.init();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        initButtons();
    }

    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        super.render(mouseX, mouseY, partialTicks);
        this.drawGuiContainerForegroundLayer(mouseX, mouseY);
        this.drawItemStack(new ItemStack(ModItems.FLASH_CARROT), this.guiLeft + 18, this.guiTop + 9, String.valueOf(getModifierCount(0)));
        this.drawItemStack(new ItemStack(ModItems.JUMP_CARROT), this.guiLeft + 18, this.guiTop + 34, String.valueOf(getModifierCount(1)));
        this.drawItemStack(new ItemStack(ModItems.HEALTH_CARROT), this.guiLeft + 18, this.guiTop + 59, String.valueOf(getModifierCount(2)));
    }

    //TODO: Change from hardcoded strings to translations.
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.drawString(this.font, TextFormatting.GOLD + "Speed: " + " " + TextFormatting.WHITE + getAttributeValue(0), this.guiLeft + 40, this.guiTop + 16 - 4, 0);
        this.drawString(this.font, TextFormatting.GOLD + "Jump: " + TextFormatting.WHITE + getAttributeValue(1), this.guiLeft + 40, this.guiTop + 41 - 4, 0);
        this.drawString(this.font, TextFormatting.GOLD + "Health: " + TextFormatting.WHITE + getAttributeValue(2), this.guiLeft + 40, this.guiTop + 67 - 4, 0);
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
            if (getModifierCount(0) != 0) {
                PacketHandler.sendToServer(new RemoveModifierPacket(ItemHelper.getStackFromItem(ModItems.FLASH_CARROT), getHorseID()));
                removeModifier(0);
            }
        }));

        this.addButton(new GuiButtonExt(this.guiLeft + 154, this.guiTop + 38, 18, 10, "-", onPress -> {
            if (getModifierCount(1) != 0) {
                PacketHandler.sendToServer(new RemoveModifierPacket(ItemHelper.getStackFromItem(ModItems.JUMP_CARROT), getHorseID()));
                removeModifier(1);
            }
        }));

        this.addButton(new GuiButtonExt(this.guiLeft + 154, this.guiTop + 63, 18, 10, "-", onPress -> {
            if (getModifierCount(2) != 0) {
                PacketHandler.sendToServer(new RemoveModifierPacket(ItemHelper.getStackFromItem(ModItems.HEALTH_CARROT), getHorseID()));
                removeModifier(2);
            }
        }));
    }

    public int getHorseID() {
        return horseEntity.getEntityId();
    }


    public float getAttributeValue(int index) {
        switch (index) {
            case 0:
                return (float) horseEntity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue();
            case 1:
                return (float) horseEntity.getAttributes().getAttributeInstanceByName("horse.jumpStrength").getValue();
            case 2:
                return (float) horseEntity.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getValue();
        }
        return 6.9F;
    }

    public int getModifierCount(int index) {
        if (horseEntity != null) {
            switch (index) {
                case 0:

                    return HorsesData.getSpeed(horseEntity);
                case 1:
                    return HorsesData.getJump(horseEntity);
                case 2:
                    return HorsesData.getHealth(horseEntity);
            }
        }
        return 69;
    }

    public void removeModifier(int index) {
        switch (index) {
            case 0:
                HorsesData.setSpeed(horseEntity, getModifierCount(0) - 1);
                break;
            case 1:
                HorsesData.setJump(horseEntity, getModifierCount(1) - 1);
                break;
            case 2:
                HorsesData.setHealth(horseEntity, getModifierCount(2) - 1);
                break;
        }
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

    public static void open(HorseEntity entity) {
        Minecraft.getInstance().displayGuiScreen(new HorseSpyScreen(entity));
    }
}
