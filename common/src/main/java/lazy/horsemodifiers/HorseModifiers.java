package lazy.horsemodifiers;

import lazy.horsemodifiers.screen.ModConfigs;

public class HorseModifiers {

    public static void init() {
        ModConfigs.read();
        ModItems.init();
    }
}
