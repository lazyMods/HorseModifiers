package com.lazynessmind.horsemodifier.registry;

import com.lazynessmind.horsemodifier.Const;
import com.lazynessmind.horsemodifier.HorseModifiers;
import com.lazynessmind.horsemodifier.configs.HMConfig;
import com.lazynessmind.horsemodifier.items.HMItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        Item.Properties properties = new Item.Properties().group(HorseModifiers.tabs.itemGroup);
        for (Item i : HMItems.ITEMS) {
            event.getRegistry().register(i);
        }

    }

    @SubscribeEvent
    public static void onWorldLoaded(EntityJoinWorldEvent joinWorldEvent) {
        World world = joinWorldEvent.getWorld();
        Entity entity = joinWorldEvent.getEntity();

        if (HMConfig.UPDATE_MSG.get()) {
            if (!world.isRemote) {
                if (entity instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) entity;
                    if (VersionChecker.getResult(ModList.get().getModContainerById(Const.MOD_ID).get().getModInfo()).status == VersionChecker.Status.OUTDATED) {
                        player.sendMessage(new StringTextComponent(TextFormatting.BOLD + "[Horse Modifiers]" + TextFormatting.RED + " Current version is outdated! " + TextFormatting.WHITE + "Check the mod page to update. :)"));
                    }
                }
            }
        }
    }
}
