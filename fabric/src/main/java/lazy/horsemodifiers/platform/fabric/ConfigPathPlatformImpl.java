package lazy.horsemodifiers.platform.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class ConfigPathPlatformImpl {

    public static Path get() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
