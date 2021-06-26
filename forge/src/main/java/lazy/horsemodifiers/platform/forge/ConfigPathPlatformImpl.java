package lazy.horsemodifiers.platform.forge;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ConfigPathPlatformImpl {

    public static Path get() {
        return FMLPaths.CONFIGDIR.get();
    }
}
