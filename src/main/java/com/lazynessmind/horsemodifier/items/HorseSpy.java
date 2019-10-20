package com.lazynessmind.horsemodifier.items;

import net.minecraft.block.SoundType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class HorseSpy extends HMItem {

    public HorseSpy() {
        super("horsespy");
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if(!player.world.isRemote){
            if(entity instanceof HorseEntity){
                HorseEntity horse = (HorseEntity)entity;

                float speed = (float)horse.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();
                float jump = (float)horse.getAttributes().getAttributeInstanceByName("horse.jumpStrength").getBaseValue();
                float health = (float)horse.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue();
                player.sendMessage(new StringTextComponent(TextFormatting.DARK_PURPLE + "======================================="));
                player.sendMessage(new StringTextComponent("Speed: " + TextFormatting.GREEN + speed));
                player.sendMessage(new StringTextComponent("Jump: " + TextFormatting.GREEN + jump));
                player.sendMessage(new StringTextComponent("Health: " + TextFormatting.GREEN + health));
                player.sendMessage(new StringTextComponent(TextFormatting.DARK_PURPLE + "======================================="));
                entity.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, SoundType.CROP.getVolume() * 0.6F, SoundType.CROP.getPitch());

            }
        }

        return true;
    }
}
