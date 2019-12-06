package com.lazynessmind.horsemodifier.common.network.packet;

import com.lazynessmind.horsemodifier.client.container.HorseSpyContainer;
import com.lazynessmind.horsemodifier.client.gui.HorseSpyScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenHorseSpyScreenPacket {

    private  int id;

    public OpenHorseSpyScreenPacket(int id) {
        this.id = id;
    }

    public static void encode(OpenHorseSpyScreenPacket msg, PacketBuffer buf) {
        buf.writeInt(msg.id);
    }

    public static OpenHorseSpyScreenPacket decode(PacketBuffer buf) {
        return new OpenHorseSpyScreenPacket(buf.readInt());
    }

    public static void handle(OpenHorseSpyScreenPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity playerEntity = ctx.get().getSender();
            if (playerEntity != null) {
                Entity entity = playerEntity.world.getEntityByID(msg.id);
                if (entity instanceof HorseEntity) {
                    HorseEntity horseEntity = (HorseEntity) entity;
                    HorseSpyContainer container = HorseSpyContainer.create(125, horseEntity);
                    playerEntity.openContainer = container;
                    Minecraft.getInstance().displayGuiScreen(new HorseSpyScreen(container, playerEntity.inventory, new StringTextComponent("")));
                } else {
                    System.out.println("is not horse ? what? " + entity + " " + msg.id);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
