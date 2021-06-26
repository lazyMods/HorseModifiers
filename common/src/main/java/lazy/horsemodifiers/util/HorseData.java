package lazy.horsemodifiers.util;

import lazy.horsemodifiers.screen.ModConfigs;
import lazy.horsemodifiers.ModItems;
import lazy.horsemodifiers.extension.HorseExtension;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class HorseData {

    public static final String TAG = "HorseModifiers";
    public static final String TAG_SPEED = "Speed";
    public static final String TAG_JUMP = "Jump";
    public static final String TAG_HEALTH = "Health";

    public static void saveNewDataToHorse(Horse horse) {
        CompoundTag tag = new CompoundTag();
        tag.putInt(TAG_SPEED, 0);
        tag.putInt(TAG_JUMP, 0);
        tag.putInt(TAG_HEALTH, 0);
        ((HorseExtension) horse).saveData(tag);
    }

    public static void saveDataToHorse(Horse horse, int speed, int jump, int health) {
        CompoundTag tag = new CompoundTag();
        tag.putInt(TAG_SPEED, speed);
        tag.putInt(TAG_JUMP, jump);
        tag.putInt(TAG_HEALTH, health);
        ((HorseExtension) horse).saveData(tag);
    }

    public static CompoundTag getData(Horse horse) {
        return ((HorseExtension) horse).getCustomData().getCompound(TAG);
    }

    public static int getSpeed(Horse entity) {
        return getData(entity).getInt(TAG_SPEED);
    }

    public static int getJump(Horse entity) {
        return getData(entity).getInt(TAG_JUMP);
    }

    public static int getHealth(Horse entity) {
        return getData(entity).getInt(TAG_HEALTH);
    }

    public static void setSpeed(Horse Horse, int newValue) {
        saveDataToHorse(Horse, newValue, getJump(Horse), getHealth(Horse));
    }

    public static void setJump(Horse Horse, int newValue) {
        saveDataToHorse(Horse, getSpeed(Horse), newValue, getHealth(Horse));
    }

    public static void setHealth(Horse Horse, int newValue) {
        saveDataToHorse(Horse, getSpeed(Horse), getJump(Horse), newValue);
    }

    public static double getAttrValue(Horse entity, int index) {
        switch (index) {
            case 0:
                return entity.getAttribute(Attributes.MOVEMENT_SPEED).getValue();
            case 1:
                return entity.getAttribute(Attributes.JUMP_STRENGTH).getValue();
            case 2:
                return entity.getAttribute(Attributes.MAX_HEALTH).getValue();
        }
        return 6.9D;
    }

    public static void increaseAttrValue(Horse entity, int index) {
        switch (index) {
            case 0:
                entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(getAttrValue(entity, 0) + ModConfigs.flashCarrotIncrease.get());
                break;
            case 1:
                entity.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(getAttrValue(entity, 1) + ModConfigs.jumpCarrotIncrease.get());
                break;
            case 2:
                entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(getAttrValue(entity, 2) + ModConfigs.healthyCarrotIncrease.get());
                break;
        }
    }

    public static void setValueFromItem(Item item, Horse entity) {
        if (item == ModItems.FLASH_CARROT.get()) {
            entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(getAttrValue(entity, 0) - ModConfigs.flashCarrotIncrease.get());
        } else if (item == ModItems.JUMP_CARROT.get()) {
            entity.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(getAttrValue(entity, 1) - ModConfigs.jumpCarrotIncrease.get());
        } else if (item == ModItems.HEALTH_CARROT.get()) {
            entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(getAttrValue(entity, 2) - ModConfigs.healthyCarrotIncrease.get());
        }
    }

    public static int getValueById(int id, Horse entity) {
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

    public static void setValueById(int id, Horse entity, int value) {
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

    public static void setAttrValueById(int id, Horse entity) {
        switch (id) {
            case 0:
                setValueFromItem(ModItems.FLASH_CARROT.get(), entity);
                break;
            case 1:
                setValueFromItem(ModItems.JUMP_CARROT.get(), entity);
                break;
            case 2:
                setValueFromItem(ModItems.HEALTH_CARROT.get(), entity);
                break;
        }
    }

    public static Item getItemById(int id) {
        switch (id) {
            case 0:
                return ModItems.FLASH_CARROT.get();
            case 1:
                return ModItems.JUMP_CARROT.get();
            case 2:
                return ModItems.HEALTH_CARROT.get();
        }
        return Items.AIR;
    }
}

