package com.lazynessmind.horsemodifier.common.items;

import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<>();

    @ObjectHolder("horsemodifiers:healthycarrot")
    public static HealthyCarrot HEALTH_CARROT = new HealthyCarrot();

    @ObjectHolder("horsemodifiers:flashcarrot")
    public static FlashCarrot FLASH_CARROT = new FlashCarrot();

    @ObjectHolder("horsemodifiers:jumpcarrot")
    public static JumpCarrot JUMP_CARROT = new JumpCarrot();

//    @ObjectHolder("horsemodifiers:horsespy")
//    public static HorseSpy HORSE_SPY = new HorseSpy();
}
