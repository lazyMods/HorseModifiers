package lazy.horsemodifiers.register;

import lazy.horsemodifiers.HorseModifiers;
import lazy.horsemodifiers.items.HorseSpy;
import lazy.horsemodifiers.items.carrots.FlashCarrot;
import lazy.horsemodifiers.items.carrots.HealthyCarrot;
import lazy.horsemodifiers.items.carrots.JumpCarrot;
import me.shedaniel.architectury.registry.DeferredRegister;
import me.shedaniel.architectury.registry.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;

@SuppressWarnings("unused")
public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(HorseModifiers.MOD_ID, Registry.ITEM_REGISTRY);

    public static final RegistrySupplier<Item> HEALTH_CARROT = ITEMS.register("healthy_carrot", HealthyCarrot::new);
    public static final RegistrySupplier<Item> FLASH_CARROT = ITEMS.register("flash_carrot", FlashCarrot::new);
    public static final RegistrySupplier<Item> JUMP_CARROT = ITEMS.register("jump_carrot", JumpCarrot::new);
    public static final RegistrySupplier<Item> HORSE_SPY = ITEMS.register("horse_spy", HorseSpy::new);

    public static void init() {
        ITEMS.register();
    }
}