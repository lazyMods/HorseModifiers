package lazy.horsemodifiers;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.electronwill.nightconfig.toml.TomlWriter;
import lazy.horsemodifiers.platform.ConfigPathPlatform;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ModConfigs {

    public static ConfigEntry<Integer> maxModifierCount;
    public static ConfigEntry<Double> healthyCarrotIncrease;
    public static ConfigEntry<Double> jumpCarrotIncrease;
    public static ConfigEntry<Double> flashCarrotIncrease;

    private static final Path CONFIG_PATH = Paths.get(ConfigPathPlatform.get() + "/horsemodifiers.toml");

    public static void read() {
        validateExistence();

        CommentedFileConfig config = CommentedFileConfig.builder(CONFIG_PATH).autosave().build();
        config.load();

        maxModifierCount = new ConfigEntry<>("maxModifierCount", 10, config)
                .comment("The number of modifiers that you can add to the horse.");
        healthyCarrotIncrease = new ConfigEntry<>("healthyCarrotIncreaseValue", 2.0D, config)
                .comment("The value that each Health should add to the horse.");
        jumpCarrotIncrease = new ConfigEntry<>("jumpCarrotIncreaseValue", 0.1D, config)
                .comment("The value that each Health should add to the horse.");
        flashCarrotIncrease = new ConfigEntry<>("flashCarrotIncreaseValue", 0.1D, config)
                .comment("The value that each Health should add to the horse.");

        config.close();
    }

    private static void validateExistence() {
        if (!Files.exists(CONFIG_PATH)) {
            try {
                Files.createFile(CONFIG_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
            CommentedConfig config = CommentedConfig.inMemory();
            TomlWriter writer = new TomlWriter();
            writer.write(config, CONFIG_PATH.toFile(), WritingMode.REPLACE);

        }
    }

    public static class ConfigEntry<T> {

        private final String path;
        private final T defaultValue;
        private final CommentedConfig config;

        private ConfigEntry(String path, T defaultValue, CommentedConfig config) {
            this.path = path;
            this.defaultValue = defaultValue;
            this.config = config;
            if(this.config.get(path) == null) this.config.set(path, defaultValue);
        }

        public ConfigEntry<T> comment(String comment) {
            this.config.setComment(this.path, comment);
            return this;
        }

        public T get() {
            return this.config.getOrElse(this.path, this.defaultValue);
        }
    }
}
