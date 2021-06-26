package lazy.horsemodifiers.platform;

import lazy.horsemodifiers.HorseModifiers;
import lazy.horsemodifiers.screen.ConfigScreen;
import lazy.horsemodifiers.util.Ref;
import me.shedaniel.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Ref.MOD_ID)
public class HorseModifiersForge {

    public HorseModifiersForge() {
        EventBuses.registerModEventBus(Ref.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        HorseModifiers.init();
        if(ModList.get().isLoaded("cloth-config"))
            ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (client, parent) -> ConfigScreen.create(parent));
    }
}
