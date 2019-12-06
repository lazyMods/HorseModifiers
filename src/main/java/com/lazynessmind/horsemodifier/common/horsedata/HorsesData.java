package com.lazynessmind.horsemodifier.common.horsedata;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lazynessmind.horsemodifier.common.configs.ModConfigs;
import com.lazynessmind.horsemodifier.common.items.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.item.Item;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class HorsesData {

    //! Saves a blank file when the horse is tamed.
    public static void saveNewHorseToFile(HorseEntity entity) {
        try {
            int speed = 0;
            int jump = 0;
            int health = 0;

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            HorseModel horseModel = new HorseModel(speed, jump, health);

            FileWriter writer = new FileWriter(getHorseFile(entity.getUniqueID()), false);
            gson.toJson(horseModel, writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //! Saves the new info to the existing horse file.
    private static void saveHorseToFile(HorseEntity entity, int speed, int jump, int health) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            HorseModel horseModel = new HorseModel(speed, jump, health);

            FileWriter writer = new FileWriter(getHorseFile(entity.getUniqueID()), false);
            gson.toJson(horseModel, writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //! Return an HorseModel Object with the info on the horse file.
    public static HorseModel getHorseData(UUID id) {
        try {
            FileReader reader = new FileReader(getHorseFile(id));
            Gson jsonParser = new Gson();
            return jsonParser.fromJson(reader, HorseModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HorseModel();
    }

    //! Get the file that corresponds to the horse with the @param id
    public static File getHorseFile(UUID id) throws IOException {
        Path path = Paths.get(FMLPaths.GAMEDIR.get() + "/saves/" + Minecraft.getInstance().getIntegratedServer().getFolderName() + "/hm");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        return new File(path.toFile(), id.toString() + ".json");
    }

    //! *************************** Getters and setters ******************************
    public static int getSpeed(HorseEntity entity) {
        return getHorseData(entity.getUniqueID()).getSpeed();
    }

    public static int getJump(HorseEntity entity) {
        return getHorseData(entity.getUniqueID()).getJump();
    }

    public static int getHealth(HorseEntity entity) {
        return getHorseData(entity.getUniqueID()).getHealth();
    }

    public static void setSpeed(HorseEntity horseEntity, int newValue) {
        saveHorseToFile(horseEntity, newValue, getJump(horseEntity), getHealth(horseEntity));
    }

    public static void setJump(HorseEntity horseEntity, int newValue) {
        saveHorseToFile(horseEntity, getSpeed(horseEntity), newValue, getHealth(horseEntity));
    }

    public static void setHealth(HorseEntity horseEntity, int newValue) {
        saveHorseToFile(horseEntity, getSpeed(horseEntity), getJump(horseEntity), newValue);
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
}

