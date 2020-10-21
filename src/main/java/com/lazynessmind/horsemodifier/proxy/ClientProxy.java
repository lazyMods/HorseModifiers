package com.lazynessmind.horsemodifier.proxy;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void addToBus(IEventBus bus) {
        super.addToBus(bus);
        bus.addListener(this::setupClient);
    }

    public void setupClient(FMLClientSetupEvent e) {
    }
}
