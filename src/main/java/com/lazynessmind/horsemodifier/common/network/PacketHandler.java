package com.lazynessmind.horsemodifier.common.network;

import com.lazynessmind.horsemodifier.HorseModifiers;
import com.lazynessmind.horsemodifier.common.network.packet.OpenHorseSpyScreenPacket;
import com.lazynessmind.horsemodifier.common.network.packet.RemoveModifierPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

    public static SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(HorseModifiers.MOD_ID, "netchannel"), () -> "1.0", s -> true, s -> true);;
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
        INSTANCE.registerMessage(nextID(), OpenHorseSpyScreenPacket.class, OpenHorseSpyScreenPacket::encode, OpenHorseSpyScreenPacket::decode, OpenHorseSpyScreenPacket::handle);
        INSTANCE.registerMessage(nextID(), RemoveModifierPacket.class, RemoveModifierPacket::encode, RemoveModifierPacket::decode, RemoveModifierPacket::handle);
    }

    public static void sendToServer(Object msg) {
        INSTANCE.sendToServer(msg);
    }

    public static void sendToPlayer(ServerPlayerEntity playerEntity, Object mess){
        INSTANCE.sendTo(mess, playerEntity.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }

    private PacketHandler() {
    }
}
