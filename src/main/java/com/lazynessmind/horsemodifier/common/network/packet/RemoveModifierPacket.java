package com.lazynessmind.horsemodifier.common.network.packet;

import com.lazynessmind.horsemodifier.common.horsedata.HorsesData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class RemoveModifierPacket {

    private ItemStack stack;
    private int entityID;

    public RemoveModifierPacket(ItemStack stack, int entityID) {
        this.stack = stack;
        this.entityID = entityID;
    }

    public static void encode(RemoveModifierPacket msg, PacketBuffer buf) {
        buf.writeItemStack(msg.stack);
        buf.writeInt(msg.entityID);
    }

    public static RemoveModifierPacket decode(PacketBuffer buf) {
        return new RemoveModifierPacket(buf.readItemStack(), buf.readInt());
    }

    public static void handle(RemoveModifierPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity playerEntity = ctx.get().getSender();
            if (playerEntity != null) {
                Entity entityByID = playerEntity.world.getEntityByID(msg.entityID);
                if(entityByID instanceof HorseEntity){
                    //Change the attribute
                    HorseEntity horseEntity = (HorseEntity) entityByID;
                    HorsesData.setValueFromItem(msg.stack.getItem(), horseEntity);

                    //Drop correspondent item
                    ItemEntity itementity = new ItemEntity(playerEntity.world, playerEntity.posX, playerEntity.posY + 1, playerEntity.posZ, msg.stack);
                    itementity.setDefaultPickupDelay();
                    playerEntity.world.addEntity(itementity);
                }
            } else {
                System.out.println("Player is null");
            }
        });
        ctx.get().setPacketHandled(true);
    }
}