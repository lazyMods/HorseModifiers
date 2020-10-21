package com.lazynessmind.horsemodifier.items.carrots;

import com.lazynessmind.horsemodifier.Configs;
import com.lazynessmind.horsemodifier.horsedata.HorsesData;
import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class FlashCarrot extends Carrot {

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag tooltipFlag) {
        super.addInformation(stack, world, list, tooltipFlag);
        list.add(new TranslationTextComponent("translation.flashcarrot.tooltip.one").mergeStyle(TextFormatting.GOLD));
        list.add(new TranslationTextComponent("translation.flashcarrot.tooltip.two"));
    }

    @Override
    public boolean changeAttributes(HorseEntity entity, PlayerEntity playerEntity) {
        if (HorsesData.getSpeed(entity) < Configs.MAX_MODIFIERS_VALUE.get()) {
            entity.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundType.CROP.getVolume() * 0.6F, SoundType.CROP.getPitch());
            HorsesData.setSpeed(entity, HorsesData.getSpeed(entity) + 1);
            HorsesData.increaseAttrValue(entity, 0);
            return true;
        } else {
            return false;
        }
    }
}
