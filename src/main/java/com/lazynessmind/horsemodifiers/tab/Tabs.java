package com.lazynessmind.horsemodifiers.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Tabs extends CreativeTabs {

    public Tabs() {
        super("horseTab");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Items.GOLDEN_CARROT);
    }
}
