package com.lazynessmind.horsemodifier.items;

import com.lazynessmind.horsemodifier.HorseModifiers;
import net.minecraft.item.Item;

public class HMItem extends Item {

    public HMItem(String name) {
        super(new Item.Properties().group(HorseModifiers.tabs.itemGroup));
        setRegistryName(name);
        HMItems.ITEMS.add(this);
    }
}
