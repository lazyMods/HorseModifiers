package com.lazynessmind.horsemodifier.registry;

import com.lazynessmind.horsemodifier.HorseModifiers;
import com.lazynessmind.horsemodifier.items.HMItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.versions.forge.ForgeVersion;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        Item.Properties properties = new Item.Properties().group(HorseModifiers.tabs.itemGroup);
        for (Item i : HMItems.ITEMS) {
            event.getRegistry().register(i);
        }

    }

    //Maybe still broken at the moment
    @SubscribeEvent
    public static void onWorldLoaded(EntityJoinWorldEvent joinWorldEvent) {
        System.out.println("is working");
        if (!joinWorldEvent.getWorld().isRemote) {
            if (joinWorldEvent.getEntity() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) joinWorldEvent.getEntity();
                if (ForgeVersion.getStatus() == VersionChecker.Status.BETA) {
                    player.sendMessage(new StringTextComponent(TextFormatting.BOLD + "[Farming Tools]" + TextFormatting.RED + " Current version is outdated! " + TextFormatting.WHITE + "Check the mod page to update. :)"));
                }
            }
        }
    }
}
