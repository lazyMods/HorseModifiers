package com.lazynessmind.horsemodifier.common.configs;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class ModConfigs {

    public static final String CATEGORY_GENERAL = "horse";
    public static final String CATEGORY_CARROTS = "carrots";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.DoubleValue CARROT_JUMP_ADD_VALUE;
    public static ForgeConfigSpec.DoubleValue CARROT_HEALTH_ADD_VALUE;
    public static ForgeConfigSpec.DoubleValue CARROT_SPEED_ADD_VALUE;
    public static ForgeConfigSpec.IntValue MAX_MODIFIERS_VALUE;

    public static ForgeConfigSpec.BooleanValue UPDATE_MSG;

    static {
        COMMON_BUILDER.comment("Horse Modifiers").push(CATEGORY_GENERAL);

        UPDATE_MSG = COMMON_BUILDER.comment("Show update message on world enter.").define("showUpdateMessage", true);

        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Carrots").push(CATEGORY_CARROTS);

        CARROT_JUMP_ADD_VALUE = COMMON_BUILDER.comment("Value that Jump Carrot gives").defineInRange("jumpCarrot", 0.1D, 0.1D, Integer.MAX_VALUE);
        CARROT_HEALTH_ADD_VALUE = COMMON_BUILDER.comment("Value that Healthy Carrot gives").defineInRange("healthyCarrot", 2.0D, 0.1D, Integer.MAX_VALUE);
        CARROT_SPEED_ADD_VALUE = COMMON_BUILDER.comment("Value that Flash Carrot gives").defineInRange("flashCarrot", 0.1D, 0.1D, Integer.MAX_VALUE);
        MAX_MODIFIERS_VALUE = COMMON_BUILDER.comment("How much modifiers can the horse hold for each modifiers (Normal is 10 each)").defineInRange("maxModifiers", 10, 1, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();

    }

    public static void load(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
    }


}
