package com.lazynessmind.horsemodifier.common.network.packet;

import com.lazynessmind.horsemodifier.client.gui.HorseSpyScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenHorseSpyScreenPacket {

    private int id;

    public OpenHorseSpyScreenPacket(int id) {
        this.id = id;
    }

    public static void encode(OpenHorseSpyScreenPacket msg, PacketBuffer buf) {
        buf.writeInt(msg.id);
    }

    public static OpenHorseSpyScreenPacket decode(PacketBuffer buf) {
        return new OpenHorseSpyScreenPacket(buf.readInt());
    }

    @OnlyIn(Dist.CLIENT)
    public static void handle(OpenHorseSpyScreenPacket msg, Supplier<NetworkEvent.Context> ctx) {
        System.out.println("Direction: " + ctx.get().getDirection());
        ctx.get().enqueueWork(() -> {
            HorseSpyScreen.open((HorseEntity) Minecraft.getInstance().world.getEntityByID(msg.id));
        });
        ctx.get().setPacketHandled(true);
    }
}
