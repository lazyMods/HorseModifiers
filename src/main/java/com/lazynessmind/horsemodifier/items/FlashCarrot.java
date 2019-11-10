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
import net.minecraft.util.text.*;
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
    public boolean changeAttributes(LivingEntity entity, PlayerEntity playerEntity) {
        //Add 0.5D to the speed maybe?
        //The game generates a random number to the speed starting at 0.45D
        //Limit the speed to 1.0D because the server starts shaking in higher values
        //But for the love of goods a config will be added.
        double currentSpeed = entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue();
        if (currentSpeed < HMConfig.CARROT_SPEED_LIMIT.get()) {
            entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(currentSpeed + HMConfig.CARROT_SPEED_ADD.get());
            entity.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundType.CROP.getVolume() * 0.6F, SoundType.CROP.getPitch());
            return true;
        } else {
            playerEntity.sendMessage(new TranslationTextComponent("translation.flashcarrot.message").applyTextStyle(TextFormatting.BLUE));
            return false;
        }
    }
}
