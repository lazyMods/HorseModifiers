package com.lazynessmind.horsemodifier.common.tab;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class Tabs {

    public ItemGroup itemGroup = new ItemGroup("horseTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.GOLDEN_CARROT);
        }
    };

    public void init() {
    }
}
