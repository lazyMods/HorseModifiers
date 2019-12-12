package com.lazynessmind.horsemodifier.common.proxy;

import com.lazynessmind.horsemodifier.client.gui.HorseSpyScreen;
import com.lazynessmind.horsemodifier.common.registry.ModContainers;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import net.java.games.input.Controller;
import net.java.games.input.Keyboard;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.SearchTreeManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy implements IProxy {

    public static final KeyBinding openGUI = new KeyBinding("Open HM Gui", 80, "Horse Modifiers");

    @Override
    public void init() {
        ClientRegistry.registerKeyBinding(openGUI);
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
