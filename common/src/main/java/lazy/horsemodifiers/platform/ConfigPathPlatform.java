package lazy.horsemodifiers.platform;

import me.shedaniel.architectury.annotations.ExpectPlatform;
import me.shedaniel.architectury.platform.Platform;

import java.nio.file.Path;

public class ConfigPathPlatform {

    @ExpectPlatform
    public static Path get() {
        throw new AssertionError();
    }
}
