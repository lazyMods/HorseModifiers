package com.lazynessmind.horsemodifier.common.items;

import com.lazynessmind.horsemodifier.common.configs.ModConfigs;
import com.lazynessmind.horsemodifier.common.horsedata.HorsesData;
import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class FlashCarrot extends Carrot {

    public FlashCarrot() {
        super("flashcarrot");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag tooltipFlag) {
        super.addInformation(stack, world, list, tooltipFlag);
        list.add(new TranslationTextComponent("translation.flashcarrot.tooltip.one").applyTextStyle(TextFormatting.GOLD));
        list.add(new TranslationTextComponent("translation.flashcarrot.tooltip.two"));
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        return super.itemInteractionForEntity(stack, player, entity, hand);
    }

    @Override
    public boolean changeAttributes(HorseEntity entity, PlayerEntity playerEntity) {
        if(HorsesData.getSpeed(entity) < ModConfigs.MAX_MODIFIERS_VALUE.get()){
            entity.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundType.CROP.getVolume() * 0.6F, SoundType.CROP.getPitch());
            HorsesData.setSpeed(entity, HorsesData.getSpeed(entity)+1);
            entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(HorsesData.getDefaultSpeed(entity) + (ModConfigs.CARROT_SPEED_ADD_VALUE.get() * HorsesData.getSpeed(entity)));
            return true;
        } else {
            return false;
        }
    }
}
