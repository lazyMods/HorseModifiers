package com.lazynessmind.horsemodifier.common.horsedata;

import com.lazynessmind.horsemodifier.HorseModifiers;
import com.lazynessmind.horsemodifier.common.network.PacketHandler;
import com.lazynessmind.horsemodifier.common.network.packet.OpenHorseSpyScreenPacket;
import com.lazynessmind.horsemodifier.common.proxy.ClientProxy;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HorseEvents {

    //! When the horse is tamed add it to the save folder.
    @SubscribeEvent
    public static void onTame(AnimalTameEvent event) {
        if (event.getAnimal() instanceof HorseEntity) {
            HorsesData.saveNewDataToHorse((HorseEntity) event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onInteract(PlayerInteractEvent.EntityInteract entityInteract){
        if(entityInteract.getPlayer().isSneaking()) {
            if (entityInteract.getPlayer() instanceof ServerPlayerEntity) {
                if (entityInteract.getTarget() instanceof HorseEntity) {
                    entityInteract.setCanceled(true);
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) entityInteract.getPlayer();
                    PacketHandler.INSTANCE.sendTo(new OpenHorseSpyScreenPacket(entityInteract.getTarget().getEntityId()), serverPlayerEntity.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
                }
            }
        }
    }
}
