package lazy.horsemodifiers.platform.data;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerator {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        if (event.includeServer()) {
            event.getGenerator().addProvider(new RecipeGenerator(event.getGenerator()));
        }
        if(event.includeClient()){
            event.getGenerator().addProvider(new LangGenerator(event.getGenerator()));
        }
    }
}
