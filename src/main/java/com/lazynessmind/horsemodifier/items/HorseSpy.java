package com.lazynessmind.horsemodifier.items;

import com.lazynessmind.horsemodifier.horsedata.HorsesData;
import com.lazynessmind.horsemodifier.util.ItemHelper;
import com.lazynessmind.horsemodifier.util.Ref;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class HorseSpy extends Item {

    private static final String TAG_CURRENT_MODE = "CurrentMode";
    public int currentMode = 0;

    public HorseSpy() {
        super(new Properties().group(Ref.MOD_GROUP));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag tooltipFlag) {
        super.addInformation(stack, world, list, tooltipFlag);
        list.add(new TranslationTextComponent("translation.horsespy.tooltip.one").mergeStyle(TextFormatting.GOLD));
        list.add(new TranslationTextComponent("translation.horsespy.tooltip.two").mergeStyle(TextFormatting.RED));
    }

    @Override
    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
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
                    player.sendMessage(new TranslationTextComponent("translation.horsespy.message.tame").mergeStyle(TextFormatting.RED), player.getUniqueID());
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (!context.getWorld().isRemote && context.getPlayer() != null) {
            if (context.getPlayer().isCrouching()) {
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
        float speed = (float) horseEntity.getAttribute(Attributes.MOVEMENT_SPEED).getValue();
        float jump = (float) horseEntity.getAttribute(Attributes.HORSE_JUMP_STRENGTH).getValue();
        float health = (float) horseEntity.getAttribute(Attributes.MAX_HEALTH).getValue();

        player.sendMessage(new StringTextComponent(TextFormatting.DARK_PURPLE + "===================="), player.getUniqueID());
        player.sendMessage(new TranslationTextComponent("translation.horsespy.message.status.speed").appendString(String.valueOf(speed)), player.getUniqueID());
        player.sendMessage(new TranslationTextComponent("translation.horsespy.message.status.jump").appendString(String.valueOf(jump)), player.getUniqueID());
        player.sendMessage(new TranslationTextComponent("translation.horsespy.message.status.health").appendString(String.valueOf(health)), player.getUniqueID());
        player.sendMessage(new TranslationTextComponent("translation.horsespy.message.status.speedcount").appendString(String.valueOf(HorsesData.getSpeed(horseEntity))), player.getUniqueID());
        player.sendMessage(new TranslationTextComponent("translation.horsespy.message.status.jumpcount").appendString(String.valueOf(HorsesData.getJump(horseEntity))), player.getUniqueID());
        player.sendMessage(new TranslationTextComponent("translation.horsespy.message.status.healthcount").appendString(String.valueOf(HorsesData.getHealth(horseEntity))), player.getUniqueID());
        player.sendMessage(new StringTextComponent(TextFormatting.DARK_PURPLE + "===================="), player.getUniqueID());
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
            Vector3d playerPos = playerEntity.getPositionVec();
            ItemEntity itementity = new ItemEntity(playerEntity.world, playerPos.x, playerPos.y + 1, playerPos.z, ItemHelper.getStackFromItem(HorsesData.getItemById(id)));
            itementity.setDefaultPickupDelay();
            playerEntity.world.addEntity(itementity);
            playerEntity.sendMessage(new TranslationTextComponent(succMessage).appendString(String.valueOf(HorsesData.getValueById(id, horseEntity))), playerEntity.getUniqueID());
        } else {
            playerEntity.sendMessage(new TranslationTextComponent("translation.horsespy.message.failmodifier").mergeStyle(TextFormatting.RED), playerEntity.getUniqueID());
        }
    }
}
