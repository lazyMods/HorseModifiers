package lazy.horsemodifiers.horsedata;

import lazy.horsemodifiers.register.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class HorsesData {

    public static final String TAG = "HM";
    public static final String TAG_SPEED = "Speed";
    public static final String TAG_JUMP = "Jump";
    public static final String TAG_HEALTH = "Health";

    public static void saveNewDataToHorse(Horse entity) {
        CompoundTag tag = new CompoundTag();
        tag.putInt(TAG_SPEED, 0);
        tag.putInt(TAG_JUMP, 0);
        tag.putInt(TAG_HEALTH, 0);
        //entity.getPersistentData().put(TAG, tag);
    }

    public static void saveDataToHorse(Horse entity, int speed, int jump, int health) {
        CompoundTag tag = new CompoundTag()/*entity.getPersistentData().getCompound(TAG)*/;
        tag.putInt(TAG_SPEED, speed);
        tag.putInt(TAG_JUMP, jump);
        tag.putInt(TAG_HEALTH, health);
        //entity.get.put(TAG, tag);
    }

    public static CompoundTag getData(Horse entity) {
        return new CompoundTag()/*entity.getPersistentData().getCompound(TAG)*/;
    }

    //! *************************** Getters and setters ******************************

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

    //! Get the current attributes on the horse
    //* 0 - Speed
    //? 1 - Jump
    //+ 2 - Health
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

    //! Increases the attribute value
    //* 0 - Speed
    //? 1 - Jump
    //+ 2 - Health
    public static void increaseAttrValue(Horse entity, int index) {
        switch (index) {
            case 0:
                entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(getAttrValue(entity, 0) /*+ Configs.CARROT_SPEED_ADD_VALUE.get()*/);
                break;
            case 1:
                entity.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(getAttrValue(entity, 1) /*+ Configs.CARROT_JUMP_ADD_VALUE.get()*/);
                break;
            case 2:
                entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(getAttrValue(entity, 2) /*+ Configs.CARROT_HEALTH_ADD_VALUE.get()*/);
                break;
        }
    }

    //! Used on the RemovedModifierUpgrade to change the value.
    public static void setValueFromItem(Item item, Horse entity) {
        if (item == ModItems.FLASH_CARROT.get()) {
            entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(getAttrValue(entity, 0) /*- Configs.CARROT_SPEED_ADD_VALUE.get()*/);
        } else if (item == ModItems.JUMP_CARROT.get()) {
            entity.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(getAttrValue(entity, 1) /*- Configs.CARROT_JUMP_ADD_VALUE.get()*/);
        } else if (item == ModItems.HEALTH_CARROT.get()) {
            entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(getAttrValue(entity, 2) /*- Configs.CARROT_HEALTH_ADD_VALUE.get()*/);
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

