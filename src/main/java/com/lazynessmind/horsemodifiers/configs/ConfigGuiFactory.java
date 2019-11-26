package com.lazynessmind.horsemodifiers.configs;

import com.lazynessmind.horsemodifiers.Const;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CategoryEntry;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConfigGuiFactory implements IModGuiFactory {

    @Override
    public void initialize(Minecraft minecraftInstance) {
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    public static class ConfigGui extends GuiConfig {

        public ConfigGui(GuiScreen parentScreen) {
            super(parentScreen, getConfigElements(), Const.MOD_ID, false, false, I18n.format("gui.config.main_title"));
        }

        private static List<IConfigElement> getConfigElements() {
            List<IConfigElement> list = new ArrayList<IConfigElement>();
            list.add(new DummyCategoryElement(I18n.format("gui.config.category.items"), "gui.config.category.items", CategoryEntryItems.class));
            list.add(new DummyCategoryElement(I18n.format("gui.config.category.features"), "gui.config.category.features", CategoryEntryFeatures.class));
            return list;
        }

        public static class CategoryEntryItems extends CategoryEntry {

            public CategoryEntryItems(GuiConfig owningScreen, GuiConfigEntries owningEntryList,
                                      IConfigElement configElement) {
                super(owningScreen, owningEntryList, configElement);
            }

            @Override
            protected GuiScreen buildChildScreen() {
                Configuration config = Configs.getConfig();
                ConfigElement categoryItems = new ConfigElement(config.getCategory(Configs.CATEGORY_NAME_ITEMS));
                List<IConfigElement> propertiesOnScreen = categoryItems.getChildElements();
                String windowTitle = I18n.format("gui.config.category.items");
                return new GuiConfig(owningScreen, propertiesOnScreen, owningScreen.modID, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart, this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart, windowTitle);
            }

        }

        public static class CategoryEntryFeatures extends CategoryEntry {

            public CategoryEntryFeatures(GuiConfig owningScreen, GuiConfigEntries owningEntryList,
                                         IConfigElement configElement) {
                super(owningScreen, owningEntryList, configElement);
            }

            @Override
            protected GuiScreen buildChildScreen() {
                Configuration config = Configs.getConfig();
                ConfigElement categoryFeatures = new ConfigElement(config.getCategory(Configs.CATEGORY_NAME_FEATURE));
                List<IConfigElement> propertiesOnScreen = categoryFeatures.getChildElements();
                String windowTitle = I18n.format("gui.config.category.features");
                return new GuiConfig(owningScreen, propertiesOnScreen, owningScreen.modID, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart, this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart, windowTitle);
            }

        }

    }

    @Override
    public boolean hasConfigGui() {
        return true;
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new ConfigGui(parentScreen);
    }

}