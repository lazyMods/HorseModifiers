package lazy.horsemodifiers.platform.data;

import lazy.horsemodifiers.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class RecipeGenerator extends RecipeProvider {

    public RecipeGenerator(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<FinishedRecipe> consumer) {
        createCarrotRecipe(ModItems.FLASH_CARROT.get(), Items.SUGAR, consumer);
        createCarrotRecipe(ModItems.HEALTH_CARROT.get(), Items.APPLE, consumer);
        createCarrotRecipe(ModItems.JUMP_CARROT.get(), Items.PISTON, consumer);

        ShapedRecipeBuilder.shaped(ModItems.HORSE_SPY.get())
                .pattern("  b")
                .pattern(" a ")
                .pattern("a  ")
                .define('a', Items.IRON_INGOT)
                .define('b', Items.GLASS)
                .unlockedBy("canCraft", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT, Items.GLASS))
                .save(consumer);
    }

    private void createCarrotRecipe(Item result, Item ingredient, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("iii")
                .pattern("ici")
                .pattern("iii")
                .define('i', ingredient)
                .define('c', Items.GOLDEN_CARROT)
                .unlockedBy("canCraft", InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(consumer);
    }
}
