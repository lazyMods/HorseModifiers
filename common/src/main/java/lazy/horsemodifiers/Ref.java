package lazy.horsemodifiers;

import me.shedaniel.architectury.registry.CreativeTabs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Ref {

    public static final CreativeModeTab MOD_GROUP = CreativeTabs.create(new ResourceLocation(HorseModifiers.MOD_ID, "horsemodifiers.tab"), () -> new ItemStack(Items.GOLDEN_CARROT));
}
