package com.lazynessmind.horsemodifier;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Configs {

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.DoubleValue CARROT_JUMP_ADD_VALUE;
    public static ForgeConfigSpec.DoubleValue CARROT_HEALTH_ADD_VALUE;
    public static ForgeConfigSpec.DoubleValue CARROT_SPEED_ADD_VALUE;
    public static ForgeConfigSpec.IntValue MAX_MODIFIERS_VALUE;

    static {
        COMMON_BUILDER.push("Horse Modifiers Configs");

        CARROT_JUMP_ADD_VALUE = COMMON_BUILDER.comment("Value that Jump Carrot gives").defineInRange("jumpCarrot", 0.1D, 0.1D, Integer.MAX_VALUE);
        CARROT_HEALTH_ADD_VALUE = COMMON_BUILDER.comment("Value that Healthy Carrot gives").defineInRange("healthyCarrot", 2.0D, 0.1D, Integer.MAX_VALUE);
        CARROT_SPEED_ADD_VALUE = COMMON_BUILDER.comment("Value that Flash Carrot gives").defineInRange("flashCarrot", 0.1D, 0.1D, Integer.MAX_VALUE);
        MAX_MODIFIERS_VALUE = COMMON_BUILDER.comment("How much modifiers can the horse hold for each modifiers (Normal is 10 each)").defineInRange("maxModifiers", 10, 1, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    public static void load(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
        spec.setConfig(configData);
    }
}
