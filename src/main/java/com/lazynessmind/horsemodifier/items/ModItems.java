package com.lazynessmind.horsemodifier.items;

import com.lazynessmind.horsemodifier.items.carrots.FlashCarrot;
import com.lazynessmind.horsemodifier.items.carrots.HealthyCarrot;
import com.lazynessmind.horsemodifier.items.carrots.JumpCarrot;
import com.lazynessmind.horsemodifier.util.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.logging.Logger;

public class ModItems {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ref.MOD_ID);

    public static RegistryObject<HealthyCarrot> HEALTH_CARROT;
    public static RegistryObject<FlashCarrot> FLASH_CARROT;
    public static RegistryObject<JumpCarrot> JUMP_CARROT;
    public static RegistryObject<HorseSpy> HORSE_SPY;

    public static void init(IEventBus bus){
        HEALTH_CARROT = ITEMS.register("healthy_carrot", HealthyCarrot::new);
        FLASH_CARROT = ITEMS.register("flash_carrot", FlashCarrot::new);
        JUMP_CARROT = ITEMS.register("jump_carrot", JumpCarrot::new);
        HORSE_SPY = ITEMS.register("horse_spy", HorseSpy::new);

        ITEMS.register(bus);
    }
}
