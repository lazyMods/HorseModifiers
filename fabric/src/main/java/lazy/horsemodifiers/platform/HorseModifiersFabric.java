package lazy.horsemodifiers.platform;

import lazy.horsemodifiers.HorseModifiers;
import net.fabricmc.api.ModInitializer;

public class HorseModifiersFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        HorseModifiers.init();
    }
}
