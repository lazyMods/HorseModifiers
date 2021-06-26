package lazy.horsemodifiers.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemHelper {
    public static ItemStack getStackFromItem(Item item) {
        return new ItemStack(item);
    }
}
