package com.lazynessmind.horsemodifier.items;

import com.lazynessmind.horsemodifier.configs.HMConfig;
import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
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

public class HealthyCarrot extends Carrot {

    public HealthyCarrot() {
        super("healthycarrot");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag tooltipFlag) {
        super.addInformation(stack, world, list, tooltipFlag);
        list.add(new TranslationTextComponent("translation.healthycarrot.tooltip.one").applyTextStyle(TextFormatting.GOLD));
        list.add(new TranslationTextComponent("translation.healthycarrot.tooltip.two"));
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        return super.itemInteractionForEntity(stack, player, entity, hand);
    }

    @Override
    public boolean changeAttributes(LivingEntity entity, PlayerEntity playerEntity) {
        //Each heart = 2.0D
        //Each horse can have 60.0D (30 hearts)
        //The only value that will be configurable is the amount of heart on carrot can add
        double currentMaxHealth = entity.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getValue();
        if (currentMaxHealth < 60.0D) {
            entity.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(currentMaxHealth + HMConfig.CARROT_HEALTH_ADD.get());
            entity.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundType.CROP.getVolume() * 0.6F, SoundType.CROP.getPitch());
            return true;
        } else {
            playerEntity.sendMessage(new TranslationTextComponent("translation.healthycarrot.message").applyTextStyle(TextFormatting.BLUE));
            return false;
        }
    }
}
