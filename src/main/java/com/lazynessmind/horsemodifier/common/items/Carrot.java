package com.lazynessmind.horsemodifier.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class Carrot extends ModItem {

    public Carrot(String name) {
        super(name);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (!player.world.isRemote) {
            if (entity instanceof HorseEntity) {
                HorseEntity horse = (HorseEntity) entity;
                if (horse.isTame()) {
                    if (changeAttributes(horse, player) && !player.isCreative()) {
                        stack.shrink(1);
                    }
                }
            }
        }
        return true;
    }

    public boolean changeAttributes(HorseEntity entity, PlayerEntity playerEntity) {
        return true;
    }
}
