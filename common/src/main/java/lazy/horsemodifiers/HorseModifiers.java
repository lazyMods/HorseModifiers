package lazy.horsemodifiers;

public class HorseModifiers {

    public static void init() {
        ModConfigs.read();
        ModItems.init();
    }
}
