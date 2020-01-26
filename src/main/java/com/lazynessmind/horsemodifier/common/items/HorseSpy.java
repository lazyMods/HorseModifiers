package com.lazynessmind.horsemodifier.common.items;

import com.lazynessmind.horsemodifier.common.horsedata.HorsesData;
import com.lazynessmind.horsemodifier.common.util.ItemHelper;
import com.sun.javafx.geom.Vec3f;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.annotation.Nullable;
import java.util.List;

public class HorseSpy extends ModItem {

    private static final String TAG_CURRENT_MODE = "CurrentMode";
    public int currentMode = 0;

    public HorseSpy() {
        super("horsespy");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag tooltipFlag) {
        super.addInformation(stack, world, list, tooltipFlag);
        list.add(new TranslationTextComponent("translation.horsespy.tooltip.one").applyTextStyle(TextFormatting.GOLD));
        list.add(new TranslationTextComponent("translation.horsespy.tooltip.two").applyTextStyle(TextFormatting.RED));
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (!player.world.isRemote) {
            if (entity instanceof HorseEntity) {
                HorseEntity horseEntity = (HorseEntity) entity;

                if (horseEntity.isTame()) {
                    this.createTag(stack);

                    if (stack.getTag() != null) {
                        if (stack.getTag().getInt(TAG_CURRENT_MODE) == 0) {
                            this.displayHorseStats(horseEntity, player);
                        } else if (stack.getTag().getInt(TAG_CURRENT_MODE) == 1) {
                            this.removeUpgrade(0, "translation.horsespy.message.speed", player, horseEntity);
                        } else if (stack.getTag().getInt(TAG_CURRENT_MODE) == 2) {
                            this.removeUpgrade(1, "translation.horsespy.message.jump", player, horseEntity);
                        } else if (stack.getTag().getInt(TAG_CURRENT_MODE) == 3) {
                            this.removeUpgrade(2, "translation.horsespy.message.health", player, horseEntity);
                        }
                    }
                } else {
                    player.sendMessage(new TranslationTextComponent("translation.horsespy.message.tame").applyTextStyle(TextFormatting.RED));
                }
            }
        }
        return true;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        if (!context.getWorld().isRemote) {
            //Is Sneaking 1.14.4 - Is Shift Key Down ??? 😒
            if (context.getPlayer().isShiftKeyDown()) {
                if (this.currentMode < 3) this.currentMode++;
                else this.currentMode = 0;
                this.createTag(context.getItem());
                if (context.getItem().getTag() != null)
                    context.getItem().getTag().putInt(TAG_CURRENT_MODE, this.currentMode);
                this.sendStatusFromMode(this.currentMode, context.getPlayer());
                return ActionResultType.SUCCESS;
            }
        }

        return ActionResultType.PASS;
    }

    private void createTag(ItemStack stack) {
        if (!stack.hasTag()) {
            CompoundNBT data = new CompoundNBT();
            data.putInt(TAG_CURRENT_MODE, currentMode);
            stack.setTag(data);
        }
    }

    private void displayHorseStats(HorseEntity horseEntity, PlayerEntity player) {
        float speed = (float) horseEntity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue();
        float jump = 0;
        try {
            jump = (float) horseEntity.getAttribute((IAttribute) FieldUtils.readField(horseEntity, "JUMP_STRENGTH", true)).getValue();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        float health = (float) horseEntity.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getValue();

        player.sendMessage(new StringTextComponent(TextFormatting.DARK_PURPLE + "===================="));
        player.sendMessage(new TranslationTextComponent("translation.horsespy.message.status.speed").appendText(String.valueOf(speed)));
        player.sendMessage(new TranslationTextComponent("translation.horsespy.message.status.jump").appendText(String.valueOf(jump)));
        player.sendMessage(new TranslationTextComponent("translation.horsespy.message.status.health").appendText(String.valueOf(health)));
        player.sendMessage(new TranslationTextComponent("translation.horsespy.message.status.speedcount").appendText(String.valueOf(HorsesData.getSpeed(horseEntity))));
        player.sendMessage(new TranslationTextComponent("translation.horsespy.message.status.jumpcount").appendText(String.valueOf(HorsesData.getJump(horseEntity))));
        player.sendMessage(new TranslationTextComponent("translation.horsespy.message.status.healthcount").appendText(String.valueOf(HorsesData.getHealth(horseEntity))));
        player.sendMessage(new StringTextComponent(TextFormatting.DARK_PURPLE + "===================="));
    }

    private void sendStatusFromMode(int id, PlayerEntity playerEntity) {
        switch (id) {
            case 0:
                playerEntity.sendStatusMessage(new TranslationTextComponent("translation.horsespy.message.mode.one"), true);
                break;
            case 1:
                playerEntity.sendStatusMessage(new TranslationTextComponent("translation.horsespy.message.mode.two"), true);
                break;
            case 2:
                playerEntity.sendStatusMessage(new TranslationTextComponent("translation.horsespy.message.mode.three"), true);
                break;
            case 3:
                playerEntity.sendStatusMessage(new TranslationTextComponent("translation.horsespy.message.mode.four"), true);
                break;
        }
    }

    private void removeUpgrade(int id, String succMessage, PlayerEntity playerEntity, HorseEntity horseEntity) {
        if ((HorsesData.getValueById(id, horseEntity) - 1) >= 0) {
            HorsesData.setValueById(id, horseEntity, HorsesData.getValueById(id, horseEntity) - 1);
            HorsesData.setAttrValueById(id, horseEntity);
            Vec3d playerPos = playerEntity.getPositionVec();
            ItemEntity itementity = new ItemEntity(playerEntity.world, playerPos.x, playerPos.y + 1, playerPos.z, ItemHelper.getStackFromItem(HorsesData.getItemById(id, horseEntity)));
            itementity.setDefaultPickupDelay();
            playerEntity.world.addEntity(itementity);
            playerEntity.sendMessage(new TranslationTextComponent(succMessage).appendText(String.valueOf(HorsesData.getValueById(id, horseEntity))));
        } else {
            playerEntity.sendMessage(new TranslationTextComponent("translation.horsespy.message.failmodifier").applyTextStyle(TextFormatting.RED));
        }
    }
}
