package lazy.horsemodifiers.forge;

import lazy.horsemodifiers.configs.ConfigsHolder;
import lazy.horsemodifiers.forge.configs.ModConfigs;

public class ConfigsExpectPlatformImpl {

    public static ConfigsHolder getConfigs() {
        return ModConfigs.createHolder();
    }
}
