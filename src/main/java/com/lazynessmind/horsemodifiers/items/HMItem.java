package com.lazynessmind.horsemodifiers.items;

import com.lazynessmind.horsemodifiers.HorseModifiers;
import com.lazynessmind.horsemodifiers.interfaces.IHasModel;
import net.minecraft.item.Item;

public class HMItem extends Item implements IHasModel {

    public HMItem(String name) {
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(HorseModifiers.tabs);
        HMItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        HorseModifiers.common.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public void registerModels(int metadata) {
    }
}
