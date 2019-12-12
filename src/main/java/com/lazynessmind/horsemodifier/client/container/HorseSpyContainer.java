package com.lazynessmind.horsemodifier.client.container;

import com.lazynessmind.horsemodifier.common.horsedata.HorsesData;
import com.lazynessmind.horsemodifier.common.registry.ModContainers;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;

public class HorseSpyContainer extends Container {

    private HorseEntity horseEntity;

    public HorseSpyContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
        this(ModContainers.HORSE_SPY, id, null);
    }

    public HorseSpyContainer(ContainerType<?> type, int id, HorseEntity horseEntity) {
        super(type, id);
        this.horseEntity = horseEntity;
    }

    public static HorseSpyContainer create(int windowId, HorseEntity horseEntity) {
        return new HorseSpyContainer(ModContainers.HORSE_SPY, windowId, horseEntity);
    }



    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
