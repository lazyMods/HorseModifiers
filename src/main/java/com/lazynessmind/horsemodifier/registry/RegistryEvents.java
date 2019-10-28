package com.lazynessmind.horsemodifier.registry;

import com.lazynessmind.horsemodifier.items.HMItems;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(HMItems.ITEMS.toArray(new Item[0]));
    }

    /*@SubscribeEvent
    public static void onWorldLoaded(PlayerEvent.PlayerLoggedInEvent event) {
        System.out.println("updating");
        if (HMConfig.UPDATE_MSG.get()) {
            if (event.getPlayer() instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
                VersionChecker.CheckResult result = VersionChecker.getResult(ModList.get().getModContainerById(Const.MOD_ID).get().getModInfo());
                if (result.status == VersionChecker.Status.OUTDATED) {
                    player.sendMessage(new StringTextComponent(TextFormatting.BOLD + "[Horse Modifiers]" + TextFormatting.RED + " Current version is outdated! " + TextFormatting.WHITE + "Check the mod page to update. :)"));
                }
            }
        }
    }*/
}
