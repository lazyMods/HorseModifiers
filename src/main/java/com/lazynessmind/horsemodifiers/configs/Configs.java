package com.lazynessmind.horsemodifiers.configs;

import com.lazynessmind.horsemodifiers.Const;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Configs {

    private static Configuration config = null;

    public static final String CATEGORY_NAME_ITEMS = "items";
    public static final String CATEGORY_NAME_FEATURE = "feature";

    public static double jumpAddValue;
    public static double jumpCarrotLimit;
    public static double healthyAddValue;
    public static double flashAddValue;
    public static double flashCarrotLimit;

    public static boolean showUpdateMessage;

    public static void preInit() {
        File configFile = new File(Loader.instance().getConfigDir(), "horsemodifiers.cfg");
        config = new Configuration(configFile);
        syncFromFiles();
    }

    public static Configuration getConfig() {
        return config;
    }


    public static void clientPreInit() {
        MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
    }

    public static void syncFromFiles() {
        syncConfig(true, true);
    }

    public static void syncFromGui() {
        syncConfig(false, true);
    }

    public static void syncFromFields() {
        syncConfig(false, false);
    }

    private static void syncConfig(boolean loadFromConfigFile, boolean readFieldsFromConfig) {
        if (loadFromConfigFile)
            config.load();

        setItemsConfigs(readFieldsFromConfig);
        setFeatureConfigs(readFieldsFromConfig);

        if (config.hasChanged())
            config.save();
    }

    private static void setItemsConfigs(boolean readFieldsFromConfig) {
        Property flashCarrot = config.get(CATEGORY_NAME_ITEMS, "flashCarrotValue", 0.1D);
        flashCarrot.setLanguageKey("gui.config.items.flashCarrotValue.name");
        flashCarrot.setComment(I18n.format("gui.config.items.flashCarrotValue.comment"));
        flashCarrot.setMinValue(0.1D);
        flashCarrot.setMaxValue(1.0D);

        Property flashLimit = config.get(CATEGORY_NAME_ITEMS, "flashCarrotLimit", 1.0D);
        flashLimit.setLanguageKey("gui.config.items.flashCarrotLimit.name");
        flashLimit.setComment(I18n.format("gui.config.items.flashCarrotLimit.comment"));
        flashLimit.setMinValue(1.0D);
        flashLimit.setMaxValue(3.0D);

        Property healthyCarrot = config.get(CATEGORY_NAME_ITEMS, "healthyCarrotValue", 2.0D);
        healthyCarrot.setLanguageKey("gui.config.items.healthyCarrotValue.name");
        healthyCarrot.setComment(I18n.format("gui.config.items.healthyCarrotValue.comment"));
        healthyCarrot.setMinValue(0.5D);
        healthyCarrot.setMaxValue(20.0D);

        Property jumpCarrot = config.get(CATEGORY_NAME_ITEMS, "jumpCarrotValue", 0.1D);
        jumpCarrot.setLanguageKey("gui.config.items.jumpCarrotValue.name");
        jumpCarrot.setComment(I18n.format("gui.config.items.jumpCarrotValue.comment"));
        jumpCarrot.setMinValue(0.1D);
        jumpCarrot.setMaxValue(1.0D);

        Property jumpLimit = config.get(CATEGORY_NAME_ITEMS, "jumpCarrotLimit", 1.0D);
        jumpLimit.setLanguageKey("gui.config.items.jumpCarrotLimit.name");
        jumpLimit.setComment(I18n.format("gui.config.items.jumpCarrotLimit.comment"));
        jumpLimit.setMinValue(1.0D);
        jumpLimit.setMaxValue(2.2D);

        List<String> propertyOrderItems = new ArrayList<String>();
        propertyOrderItems.add(flashCarrot.getName());
        propertyOrderItems.add(flashLimit.getName());
        propertyOrderItems.add(healthyCarrot.getName());
        propertyOrderItems.add(jumpCarrot.getName());
        propertyOrderItems.add(jumpLimit.getName());
        config.setCategoryPropertyOrder(CATEGORY_NAME_ITEMS, propertyOrderItems);

        if (readFieldsFromConfig) {
            flashAddValue = flashCarrot.getDouble();
            flashCarrotLimit = flashLimit.getDouble();
            healthyAddValue = healthyCarrot.getDouble();
            jumpAddValue = jumpCarrot.getDouble();
            jumpCarrotLimit = jumpLimit.getDouble();
        }

        flashCarrot.set(flashAddValue);
        flashLimit.set(flashCarrotLimit);
        healthyCarrot.set(healthyAddValue);
        jumpCarrot.set(jumpAddValue);
        jumpLimit.set(jumpCarrotLimit);
    }

    private static void setFeatureConfigs(boolean readFieldsFromConfig) {
        Property showUpdateMessageProp = config.get(CATEGORY_NAME_FEATURE, "show_update_message", true);
        showUpdateMessageProp.setLanguageKey("gui.config.features.showUpdateMessage.name");
        showUpdateMessageProp.setComment(I18n.format("gui.config.features.showUpdateMessage.comment"));

        List<String> propertyOrderFeatures = new ArrayList<String>();
        propertyOrderFeatures.add(showUpdateMessageProp.getName());
        config.setCategoryPropertyOrder(CATEGORY_NAME_ITEMS, propertyOrderFeatures);

        if (readFieldsFromConfig) {
            showUpdateMessage = showUpdateMessageProp.getBoolean();
        }

        showUpdateMessageProp.set(showUpdateMessage);
    }

    public static class ConfigEventHandler {

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void onEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Const.MOD_ID)) {
                syncFromGui();
            }
        }

    }

}