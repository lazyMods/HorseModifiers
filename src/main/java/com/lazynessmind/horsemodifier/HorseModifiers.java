package com.lazynessmind.horsemodifier;

import com.lazynessmind.horsemodifier.common.configs.ModConfigs;
import com.lazynessmind.horsemodifier.common.network.PacketHandler;
import com.lazynessmind.horsemodifier.common.proxy.ClientProxy;
import com.lazynessmind.horsemodifier.common.proxy.IProxy;
import com.lazynessmind.horsemodifier.common.proxy.ServerProxy;
import com.lazynessmind.horsemodifier.common.tab.Tabs;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(HorseModifiers.MOD_ID)
public class HorseModifiers {

    public static final String MOD_ID = "horsemodifiers";

    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    public static Tabs tabs = new Tabs();

    public HorseModifiers() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModConfigs.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.COMMON_CONFIG);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        ModConfigs.load(ModConfigs.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("horsemodifiers-client.toml"));
        ModConfigs.load(ModConfigs.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("horsemodifiers-common.toml"));
    }

    private void setup(final FMLCommonSetupEvent event) {
        tabs.init();
        proxy.init();
        PacketHandler.init();
    }
}
