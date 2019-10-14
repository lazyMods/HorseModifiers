package com.lazynessmind.horsemodifier.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class Carrot extends HMItem{

    public Carrot(String name) {
        super(name);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if(!player.world.isRemote) {
            if (entity instanceof HorseEntity) {
                HorseEntity horse = (HorseEntity)entity;
                if(changeAttributes(horse, player) && !player.isCreative()){
                    stack.shrink(1);
                }
            }
        }
        return true;
    }

    public boolean changeAttributes(LivingEntity entity, PlayerEntity playerEntity){
        return true;
    }
}
