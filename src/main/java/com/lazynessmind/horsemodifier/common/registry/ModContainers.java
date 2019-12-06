package com.lazynessmind.horsemodifier.common.registry;

import com.lazynessmind.horsemodifier.HorseModifiers;
import com.lazynessmind.horsemodifier.client.container.HorseSpyContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

public class ModContainers {

    public static List<ContainerType<?>> CONTAINERS = new ArrayList<>();

    @ObjectHolder("horsemodifiers:horse_spy")
    public static ContainerType<HorseSpyContainer> HORSE_SPY = createContainer("horse_spy", HorseSpyContainer::new);

    private static <T extends Container> ContainerType<T> createContainer(String name, IContainerFactory<T> factory) {
        ContainerType<T> containerType = IForgeContainerType.create(factory);
        containerType.setRegistryName(new ResourceLocation(HorseModifiers.MOD_ID, name));
        CONTAINERS.add(containerType);
        return containerType;
    }

    @Mod.EventBusSubscriber(modid = HorseModifiers.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    static class Registration {

        @SubscribeEvent
        public static void onContainerRegister(final RegistryEvent.Register<ContainerType<?>> event) {
            event.getRegistry().registerAll(CONTAINERS.toArray(new ContainerType[0]));
        }
    }
}
