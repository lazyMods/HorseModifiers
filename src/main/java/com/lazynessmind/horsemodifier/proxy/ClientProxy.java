package com.lazynessmind.horsemodifier.proxy;

import com.lazynessmind.horsemodifier.interfaces.IProxy;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

    @Override
    public void init() {

    }

    @Override
    public World getClientWorld() {
        return null;
    }

    @Override
    public PlayerEntity getClientEntity() {
        return null;
    }
}
