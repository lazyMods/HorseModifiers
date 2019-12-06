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

    public int getHorseID() {
        return this.horseEntity.getEntityId();
    }



    public float getAttributeValue(int index) {
        switch (index) {
            case 0:
                return (float)this.horseEntity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue();
            case 1:
                return (float)this.horseEntity.getAttributes().getAttributeInstanceByName("horse.jumpStrength").getValue();
            case 2:
                return (float)this.horseEntity.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getValue();
        }
        return 6.9F;
    }

    public int getModifierCount(int index) {
        if (this.horseEntity != null) {
            switch (index) {
                case 0:
                    return HorsesData.getSpeed(this.horseEntity);
                case 1:
                    return HorsesData.getJump(this.horseEntity);
                case 2:
                    return HorsesData.getHealth(this.horseEntity);
            }
        }
        return 69;
    }

    public void removeModifier(int index) {
        switch (index) {
            case 0:
                HorsesData.setSpeed(this.horseEntity, getModifierCount(0) - 1);
                break;
            case 1:
                HorsesData.setJump(this.horseEntity, getModifierCount(1) - 1);
                break;
            case 2:
                HorsesData.setHealth(this.horseEntity, getModifierCount(2) - 1);
                break;
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
