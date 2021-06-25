package lazy.horsemodifiers.util;

import me.shedaniel.architectury.registry.CreativeTabs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Ref {

    public static final String MOD_ID = "horsemodifiers";
    public static final CreativeModeTab MOD_GROUP = CreativeTabs.create(new ResourceLocation(Ref.MOD_ID, "general"), () -> new ItemStack(Items.GOLDEN_CARROT));
}
