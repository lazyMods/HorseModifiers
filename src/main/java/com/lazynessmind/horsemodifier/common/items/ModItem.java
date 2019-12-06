package com.lazynessmind.horsemodifier.common.items;

import com.lazynessmind.horsemodifier.HorseModifiers;
import net.minecraft.item.Item;

public class ModItem extends Item {

    public ModItem(String name) {
        super(new Item.Properties().group(HorseModifiers.tabs.itemGroup));
        setRegistryName(name);
        ModItems.ITEMS.add(this);
    }
}
