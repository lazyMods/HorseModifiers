package com.lazynessmind.horsemodifier.items.carrots;

import com.lazynessmind.horsemodifier.util.Ref;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;

public abstract class Carrot extends Item {

    public Carrot() {
        super(new Item.Properties().group(Ref.MOD_GROUP));
    }

    @Override
    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (!player.world.isRemote) {
            if (entity instanceof HorseEntity) {
                HorseEntity horse = (HorseEntity) entity;
                if (horse.isTame()) {
                    if (this.changeAttributes(horse, player) && !player.isCreative()) {
                        stack.shrink(1);
                    }
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    public abstract boolean changeAttributes(HorseEntity entity, PlayerEntity playerEntity);
}
