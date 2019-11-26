package com.lazynessmind.horsemodifiers.items;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class HMItems {

    public static final List<Item> ITEMS = new ArrayList<>();

    public static Item HEALTH_CARROT = new HealthyCarrot();
    public static Item FLASH_CARROT = new FlashCarrot();
    public static Item Jump_CARROT = new JumpCarrot();
    public static Item HORSE_SPY = new HorseSpy();
}
