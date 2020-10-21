package com.lazynessmind.horsemodifier.event;

import com.lazynessmind.horsemodifier.horsedata.HorsesData;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AnimalTameEventListener {

    @SubscribeEvent
    public static void onTame(AnimalTameEvent event) {
        if (event.getAnimal() instanceof HorseEntity) {
            HorsesData.saveNewDataToHorse((HorseEntity) event.getEntity());
        }
    }
}
