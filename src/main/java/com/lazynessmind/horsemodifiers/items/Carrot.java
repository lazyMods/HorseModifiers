package com.lazynessmind.horsemodifiers.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class Carrot extends HMItem {

    public Carrot(String name) {
        super(name);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity, EnumHand hand) {
        if (!player.world.isRemote) {
            if (entity instanceof EntityHorse) {
                EntityHorse horse = (EntityHorse) entity;
                if (changeAttributes(horse, player) && !player.isCreative()) {
                    stack.shrink(1);
                }
            }
        }
        return true;
    }

    public boolean changeAttributes(EntityLivingBase entity, EntityPlayer playerEntity) {
        return true;
    }
}
