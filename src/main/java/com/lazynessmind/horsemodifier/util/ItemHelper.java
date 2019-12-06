package com.lazynessmind.horsemodifier.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHelper {
    public static ItemStack getStackFromItem(Item item){
        return new ItemStack(item);
    }
}
