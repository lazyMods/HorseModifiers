package com.lazynessmind.horsemodifier.common.horsedata;

import com.lazynessmind.horsemodifier.HorseModifiers;
import com.lazynessmind.horsemodifier.common.network.PacketHandler;
import com.lazynessmind.horsemodifier.common.network.packet.OpenHorseSpyScreenPacket;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.HorseInventoryScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.IOException;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HorseEvents {

    //! When the horse is tamed add it to the save folder.
    @SubscribeEvent
    public static void onTame(AnimalTameEvent event) {
        if (event.getAnimal() instanceof HorseEntity) {
            HorsesData.saveNewHorseToFile((HorseEntity) event.getEntity());
        }
    }

    //! Delete save file on death
    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event){
        if(event.getEntity() instanceof HorseEntity){
            HorseEntity entity = (HorseEntity) event.getEntity();
            if(entity.isTame()){
                try {
                    if(HorsesData.getHorseFile(entity.getUniqueID()).delete()) System.out.println("File from horse with id " + entity.getUniqueID() + " deleted");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //!Add button to access the current modifiers that the horse holds.
    @OnlyIn(value = Dist.CLIENT)
    @SubscribeEvent
    public static void onGuiPost(GuiScreenEvent.InitGuiEvent.Post event) {
        Screen screen = event.getGui();
        if (screen instanceof HorseInventoryScreen) {
            HorseInventoryScreen horseInventoryScreen = (HorseInventoryScreen) screen;
            if (event.getWidgetList() != null) {
                final ResourceLocation HORSE_SPY = new ResourceLocation(HorseModifiers.MOD_ID + ":textures/gui/guibuttons.png");
                event.addWidget(new ImageButton(horseInventoryScreen.getGuiLeft(), horseInventoryScreen.getGuiTop() - 18, 20, 18, 0, 0, 19, HORSE_SPY, button -> {
                    try {
                        AbstractHorseEntity horseEntity = (AbstractHorseEntity) FieldUtils.readField(horseInventoryScreen, "horseEntity", true);
                        PacketHandler.INSTANCE.sendToServer(new OpenHorseSpyScreenPacket(horseEntity.getEntityId()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }));
            }
        }
    }
}
