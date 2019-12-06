package com.lazynessmind.horsemodifier.common.registry;


import com.lazynessmind.horsemodifier.HorseModifiers;
import com.lazynessmind.horsemodifier.common.configs.ModConfigs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeRegistryEvents {

    @SubscribeEvent
    public static void onWorldLoaded(EntityJoinWorldEvent event) {
        if (!event.getWorld().isRemote) {
            if (ModConfigs.UPDATE_MSG.get()) {
                if (event.getEntity() instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) event.getEntity();
                    VersionChecker.CheckResult result = VersionChecker.getResult(ModList.get().getModContainerById(HorseModifiers.MOD_ID).get().getModInfo());
                    if (result.status == VersionChecker.Status.OUTDATED) {
                        player.sendMessage(new StringTextComponent(TextFormatting.BOLD + "[Horse Modifiers]" + TextFormatting.RED + " Current version is outdated! " + TextFormatting.WHITE + "Check the mod page to update. :)"));
                    }
                }
            }
        }
    }
}
