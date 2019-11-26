package com.lazynessmind.horsemodifiers.registry;

import com.lazynessmind.horsemodifiers.configs.Configs;
import com.lazynessmind.horsemodifiers.interfaces.IHasModel;
import com.lazynessmind.horsemodifiers.items.HMItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryEvents {

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(HMItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : HMItems.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModels();
                ((IHasModel) item).registerModels(item.getMetadata(item.getDefaultInstance()));
            }
        }
    }

    @SubscribeEvent
    public static void onWorldLoaded(EntityJoinWorldEvent joinWorldEvent) {
        World world = joinWorldEvent.getWorld();
        Entity entity = joinWorldEvent.getEntity();

        if (Configs.showUpdateMessage) {
            if (!world.isRemote) {
                if (entity instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) entity;
                    if (ForgeVersion.getResult(Loader.instance().activeModContainer()).status == ForgeVersion.Status.OUTDATED) {
                        player.sendMessage(new TextComponentString(TextFormatting.BOLD + "[Horse Modifiers]" + TextFormatting.RED + " Current version is outdated! " + TextFormatting.WHITE + "Check the mod page to update. :)"));
                    }
                }
            }
        }
    }
}
