package lazy.horsemodifiers;

import lazy.horsemodifiers.configs.ConfigsHolder;
import me.shedaniel.architectury.annotations.ExpectPlatform;

public class ConfigsExpectPlatform {

    @ExpectPlatform
    public static ConfigsHolder getConfigs() {
        throw new AssertionError();
    }
}
