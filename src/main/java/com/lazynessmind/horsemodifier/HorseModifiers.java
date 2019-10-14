package com.lazynessmind.horsemodifier;

import com.lazynessmind.horsemodifier.configs.HMConfig;
import com.lazynessmind.horsemodifier.interfaces.IProxy;
import com.lazynessmind.horsemodifier.proxy.ClientProxy;
import com.lazynessmind.horsemodifier.proxy.ServerProxy;
import com.lazynessmind.horsemodifier.tab.Tabs;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Const.MOD_ID)
public class HorseModifiers {

    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    public static Tabs tabs = new Tabs();

    private static final Logger LOGGER = LogManager.getLogger();

    public HorseModifiers() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, HMConfig.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HMConfig.COMMON_CONFIG);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        HMConfig.load(HMConfig.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("horsemodifiers-client.toml"));
        HMConfig.load(HMConfig.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("horsemodifiers-common.toml"));
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

        tabs.init();
        proxy.init();
    }
}
