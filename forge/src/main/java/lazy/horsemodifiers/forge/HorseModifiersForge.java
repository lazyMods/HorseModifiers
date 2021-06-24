package lazy.horsemodifiers.forge;

import lazy.horsemodifiers.HorseModifiers;
import me.shedaniel.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HorseModifiers.MOD_ID)
public class HorseModifiersForge {
    public HorseModifiersForge() {
        EventBuses.registerModEventBus(HorseModifiers.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        HorseModifiers.init();
    }
}
