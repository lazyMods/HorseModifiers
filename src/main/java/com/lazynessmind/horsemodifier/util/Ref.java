package com.lazynessmind.horsemodifier.util;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class Ref {

    public static final String MOD_ID = "horsemodifiers";
    public static final ItemGroup MOD_GROUP = new ItemGroup("horseTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.GOLDEN_CARROT);
        }
    };

}
