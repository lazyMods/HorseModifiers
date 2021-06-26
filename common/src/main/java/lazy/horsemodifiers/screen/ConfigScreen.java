package lazy.horsemodifiers.screen;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.gui.entries.DoubleListEntry;
import me.shedaniel.clothconfig2.gui.entries.IntegerListEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;

public class ConfigScreen {

    public static Screen create(Screen parent) {

        ModConfigs.read();

        ConfigBuilder configBuilder = ConfigBuilder.create().setParentScreen(parent).setTitle(new TranslatableComponent("config.title"));

        ConfigCategory generalCategory = configBuilder.getOrCreateCategory(new TranslatableComponent("config.general"));

        IntegerListEntry maxModifierEntry = configBuilder.entryBuilder()
                .startIntField(new TranslatableComponent("config.maxModifierCount"), ModConfigs.maxModifierCount.get())
                .setDefaultValue(10)
                .setSaveConsumer(ModConfigs.maxModifierCount::save)
                .build();

        DoubleListEntry healthyCarrot = configBuilder.entryBuilder()
                .startDoubleField(new TranslatableComponent("config.healthyCarrot"), ModConfigs.healthyCarrotIncrease.get())
                .setDefaultValue(2.0D)
                .setSaveConsumer(ModConfigs.healthyCarrotIncrease::save)
                .build();

        DoubleListEntry jumpCarrot = configBuilder.entryBuilder()
                .startDoubleField(new TranslatableComponent("config.jumpCarrot"), ModConfigs.jumpCarrotIncrease.get())
                .setDefaultValue(0.1D)
                .setSaveConsumer(ModConfigs.jumpCarrotIncrease::save)
                .build();

        DoubleListEntry flashCarrot = configBuilder.entryBuilder()
                .startDoubleField(new TranslatableComponent("config.flashCarrot"), ModConfigs.flashCarrotIncrease.get())
                .setDefaultValue(0.1D)
                .setSaveConsumer(ModConfigs.flashCarrotIncrease::save)
                .build();

        generalCategory.addEntry(maxModifierEntry);
        generalCategory.addEntry(flashCarrot);
        generalCategory.addEntry(jumpCarrot);
        generalCategory.addEntry(healthyCarrot);

        return configBuilder.build();
    }
}