package com.lazynessmind.horsemodifier.items;

import com.lazynessmind.horsemodifier.HorseModifiers;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class HMItem extends Item {

    public HMItem(String name){
        super(new Item.Properties().maxStackSize(1).group(HorseModifiers.tabs.itemGroup));
        setRegistryName(name);
        HMItems.ITEMS.add(this);
    }
}
