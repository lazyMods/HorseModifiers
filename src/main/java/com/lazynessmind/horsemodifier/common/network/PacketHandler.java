package com.lazynessmind.horsemodifier.common.network;

import com.lazynessmind.horsemodifier.HorseModifiers;
import com.lazynessmind.horsemodifier.common.network.packet.OpenHorseSpyScreenPacket;
import com.lazynessmind.horsemodifier.common.network.packet.RemoveModifierPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

    private static final String PROTOCOL = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(HorseModifiers.MOD_ID, "mod_channel"), () -> PROTOCOL, PROTOCOL::equals, PROTOCOL::equals);

    private static int id = 0;

    public static void init() {
        INSTANCE.registerMessage(id++, RemoveModifierPacket.class, RemoveModifierPacket::encode, RemoveModifierPacket::decode, RemoveModifierPacket::handle);
        INSTANCE.registerMessage(id++, OpenHorseSpyScreenPacket.class, OpenHorseSpyScreenPacket::encode, OpenHorseSpyScreenPacket::decode, OpenHorseSpyScreenPacket::handle);
    }

    public static void sendToServer(Object msg) {
        INSTANCE.sendToServer(msg);
        System.out.println("sended: " + msg.toString());
    }

    private PacketHandler() {}
}
