package com.lazynessmind.horsemodifier.common.proxy;

import com.lazynessmind.horsemodifier.client.gui.HorseSpyScreen;
import com.lazynessmind.horsemodifier.common.registry.ModContainers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
        ScreenManager.registerFactory(ModContainers.HORSE_SPY, HorseSpyScreen::new);
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientEntity() {
        return Minecraft.getInstance().player;
    }
}
