package com.lazynessmind.horsemodifier.common.items;

import com.lazynessmind.horsemodifier.common.configs.ModConfigs;
import com.lazynessmind.horsemodifier.common.horsedata.HorsesData;
import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
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

public class JumpCarrot extends Carrot {

    public JumpCarrot() {
        super("jumpcarrot");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag tooltipFlag) {
        super.addInformation(stack, world, list, tooltipFlag);
        list.add(new TranslationTextComponent("translation.jumpcarrot.tooltip.one").applyTextStyle(TextFormatting.GOLD));
        list.add(new TranslationTextComponent("translation.jumpcarrot.tooltip.two"));
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        return super.itemInteractionForEntity(stack, player, entity, hand);
    }

    @Override
    public boolean changeAttributes(HorseEntity entity, PlayerEntity playerEntity) {
        if (HorsesData.getJump(entity) < ModConfigs.MAX_MODIFIERS_VALUE.get()) {
            entity.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundType.CROP.getVolume() * 0.6F, SoundType.CROP.getPitch());
            HorsesData.setJump(entity, HorsesData.getJump(entity) + 1);
            HorsesData.increaseAttrValue(entity, 1);
            return true;
        } else {
            return false;
        }

    }
}
