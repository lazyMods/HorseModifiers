package com.lazynessmind.horsemodifier;

import com.lazynessmind.horsemodifier.proxy.ClientProxy;
import com.lazynessmind.horsemodifier.proxy.CommonProxy;
import com.lazynessmind.horsemodifier.util.Ref;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(Ref.MOD_ID)
public class HorseModifiers {

    public HorseModifiers() {
        System.out.println("h1111111111111111111111111");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configs.COMMON_CONFIG);
        DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new).init();
        Configs.load(Configs.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("horsemodifiers-common.toml"));
    }
}
