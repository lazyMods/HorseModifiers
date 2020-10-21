package com.lazynessmind.horsemodifier.proxy;

import com.lazynessmind.horsemodifier.items.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CommonProxy {

    public void init() {
        this.addToBus(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public void addToBus(IEventBus bus) {
        ModItems.init(bus);

        bus.addListener(this::setupCommon);
    }

    public void setupCommon(FMLCommonSetupEvent e) {

    }
}
