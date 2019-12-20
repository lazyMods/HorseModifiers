package com.lazynessmind.horsemodifier.common.horsedata;

import com.lazynessmind.horsemodifier.common.configs.ModConfigs;
import com.lazynessmind.horsemodifier.common.items.ModItems;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;

public class HorsesData {

    public static final String TAG = "HM";
    public static final String TAG_SPEED = "Speed";
    public static final String TAG_JUMP = "Jump";
    public static final String TAG_HEALTH = "Health";

    public static void saveNewDataToHorse(HorseEntity entity) {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt(TAG_SPEED, 0);
        compoundNBT.putInt(TAG_JUMP, 0);
        compoundNBT.putInt(TAG_HEALTH, 0);
        entity.getPersistentData().put(TAG, compoundNBT);
    }

    public static void saveDataToHorse(HorseEntity entity, int speed, int jump, int health) {
        CompoundNBT compoundNBT = entity.getPersistentData().getCompound(TAG);
        compoundNBT.putInt(TAG_SPEED, speed);
        compoundNBT.putInt(TAG_JUMP, jump);
        compoundNBT.putInt(TAG_HEALTH, health);
        entity.getPersistentData().put(TAG, compoundNBT);
    }

    public static CompoundNBT getData(HorseEntity entity) {
        return entity.getPersistentData().getCompound(TAG);
    }

    //! *************************** Getters and setters ******************************

    public static int getSpeed(HorseEntity entity) {
        return getData(entity).getInt(TAG_SPEED);
    }

    public static int getJump(HorseEntity entity) {
        return getData(entity).getInt(TAG_JUMP);
    }

    public static int getHealth(HorseEntity entity) {
        return getData(entity).getInt(TAG_HEALTH);
    }

    public static void setSpeed(HorseEntity horseEntity, int newValue) {
        saveDataToHorse(horseEntity, newValue, getJump(horseEntity), getHealth(horseEntity));
    }

    public static void setJump(HorseEntity horseEntity, int newValue) {
        saveDataToHorse(horseEntity, getSpeed(horseEntity), newValue, getHealth(horseEntity));
    }

    public static void setHealth(HorseEntity horseEntity, int newValue) {
        saveDataToHorse(horseEntity, getSpeed(horseEntity), getJump(horseEntity), newValue);
    }

    //! Get the current attributes on the horse
    //* 0 - Speed
    //? 1 - Jump
    //+ 2 - Health
    public static double getAttrValue(HorseEntity entity, int index) {
        switch (index) {
            case 0:
                return entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue();
            case 1:
                return entity.getAttributes().getAttributeInstanceByName("horse.jumpStrength").getValue();
            case 2:
                return entity.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getValue();
        }
        return 6.9D;
    }

    //! Increases the attribute value
    //* 0 - Speed
    //? 1 - Jump
    //+ 2 - Health
    public static void increaseAttrValue(HorseEntity entity, int index) {
        switch (index) {
            case 0:
                entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getAttrValue(entity, 0) + ModConfigs.CARROT_SPEED_ADD_VALUE.get());
                break;
            case 1:
                entity.getAttributes().getAttributeInstanceByName("horse.jumpStrength").setBaseValue(getAttrValue(entity, 1) + ModConfigs.CARROT_JUMP_ADD_VALUE.get());
                break;
            case 2:
                entity.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getAttrValue(entity, 2) + ModConfigs.CARROT_HEALTH_ADD_VALUE.get());
                break;
        }
    }

    //! Used on the RemovedModifierUpgrade to change the value.
    public static void setValueFromItem(Item item, HorseEntity entity) {
        if (item == ModItems.FLASH_CARROT) {
            entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getAttrValue(entity, 0) - ModConfigs.CARROT_SPEED_ADD_VALUE.get());
        } else if (item == ModItems.JUMP_CARROT) {
            entity.getAttributes().getAttributeInstanceByName("horse.jumpStrength").setBaseValue(getAttrValue(entity, 1) - ModConfigs.CARROT_JUMP_ADD_VALUE.get());
        } else if (item == ModItems.HEALTH_CARROT) {
            entity.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getAttrValue(entity, 2) - ModConfigs.CARROT_HEALTH_ADD_VALUE.get());
        }
    }

    public static int getValueById(int id, HorseEntity entity) {
        switch (id) {
            case 0:
                return getSpeed(entity);
            case 1:
                return getJump(entity);
            case 2:
                return getHealth(entity);
        }
        return 0;
    }

    public static void setValueById(int id, HorseEntity entity, int value) {
        switch (id) {
            case 0:
                setSpeed(entity, value);
                break;
            case 1:
                setJump(entity, value);
                break;
            case 2:
                setHealth(entity, value);
                break;
        }
    }

    public static void setAttrValueById(int id, HorseEntity entity) {
        switch (id) {
            case 0:
                setValueFromItem(ModItems.FLASH_CARROT, entity);
                break;
            case 1:
                setValueFromItem(ModItems.JUMP_CARROT, entity);
                break;
            case 2:
                setValueFromItem(ModItems.HEALTH_CARROT, entity);
                break;
        }
    }

    public static Item getItemById(int id, HorseEntity entity) {
        switch (id) {
            case 0:
                return ModItems.FLASH_CARROT;
            case 1:
                return ModItems.JUMP_CARROT;
            case 2:
                return ModItems.HEALTH_CARROT;
        }
        return Items.AIR;
    }
}

