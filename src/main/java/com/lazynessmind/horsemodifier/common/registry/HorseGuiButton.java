package com.lazynessmind.horsemodifier.common.registry;


import com.lazynessmind.horsemodifier.HorseModifiers;
import com.lazynessmind.horsemodifier.common.network.PacketHandler;
import com.lazynessmind.horsemodifier.common.network.packet.OpenHorseSpyScreenPacket;
import com.lazynessmind.horsemodifier.common.proxy.ClientProxy;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.HorseInventoryScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ForgeInternalHandler;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class HorseGuiButton {

    //!Add button to access the current modifiers that the horse holds
    @SubscribeEvent
    public static void onGuiPost(GuiScreenEvent.InitGuiEvent.Post event) {
        Screen screen = event.getGui();
        if (screen instanceof HorseInventoryScreen) {
            HorseInventoryScreen horseInventoryScreen = (HorseInventoryScreen) screen;
            if (event.getWidgetList() != null) {
                final ResourceLocation HORSE_SPY = new ResourceLocation(HorseModifiers.MOD_ID + ":textures/gui/guibuttons.png");
                event.addWidget(new ImageButton(horseInventoryScreen.getGuiLeft(), horseInventoryScreen.getGuiTop() - 18, 20, 18, 0, 0, 19, HORSE_SPY, button -> {
                    if (HorseModifiers.proxy.getClientEntity().getRidingEntity() != null) {
                        try {
                            AbstractHorseEntity horseEntity = (AbstractHorseEntity) FieldUtils.readField(horseInventoryScreen, "horseEntity", true);
                            PacketHandler.sendToServer(new OpenHorseSpyScreenPacket(horseEntity.getEntityId()));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("raht");
                    }
                }));
            }
        }
    }
}
