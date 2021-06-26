package lazy.horsemodifiers.platform.data;

import lazy.horsemodifiers.util.Ref;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LangGenerator extends LanguageProvider {

    public LangGenerator(DataGenerator gen) {
        super(gen, Ref.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add("item.horsemodifiers.healthy_carrot", "Healthy Carrot");
        this.add("item.horsemodifiers.flash_carrot", "Flash Carrot");
        this.add("item.horsemodifiers.jump_carrot", "Jump Carrot");
        this.add("item.horsemodifiers.horse_spy", "Horse Spy");

        this.add("itemGroup.horsemodifiers.general", "Horse Modifiers");

        this.add("translation.flashcarrot.tooltip.one", "Changes the speed attribute!");
        this.add("translation.flashcarrot.tooltip.two", "When the limit is reached you need to lock the horse in a cell!");
        this.add("translation.healthycarrot.tooltip.one", "Changes the health attribute!");
        this.add("translation.healthycarrot.tooltip.two", "When the limit is reached the horse becomes the HULK!");
        this.add("translation.jumpcarrot.tooltip.one", "Changes the jump attribute!");
        this.add("translation.jumpcarrot.tooltip.two", "When the limit is reached the affected horse can jump 7 blocks height!!");
        this.add("translation.horsespy.tooltip.one", "Sneak Left Click to change the current mode!");
        this.add("translation.horsespy.tooltip.two", "Left Click on a tamed horse to use the modes!");
        this.add("translation.horsespy.message.failmodifier", "No modifiers to remove!");
        this.add("translation.horsespy.message.speed", "Remove one speed modifier. Left ");
        this.add("translation.horsespy.message.jump", "Remove one jump modifier. Left ");
        this.add("translation.horsespy.message.health", "Remove one health modifier. Left ");
        this.add("translation.horsespy.message.mode.one", "§l§cMode: §rShow current status.");
        this.add("translation.horsespy.message.mode.two", "§l§cMode: §rRemove Speed Modifier.");
        this.add("translation.horsespy.message.mode.three", "§l§cMode: §rRemove Jump Modifier.");
        this.add("translation.horsespy.message.mode.four", "§l§cMode: §rRemove Health Modifier.");
        this.add("translation.horsespy.message.status.speed", "§l§cSpeed: §r");
        this.add("translation.horsespy.message.status.jump", "§l§cJump: §r");
        this.add("translation.horsespy.message.status.health", "§l§cHealth: §r");
        this.add("translation.horsespy.message.status.speedcount", "§l§cSpeed Modifier Count: §r");
        this.add("translation.horsespy.message.status.jumpcount", "§l§cJump Modifier Count: §r");
        this.add("translation.horsespy.message.status.healthcount", "§l§cHealth Modifier Count: §r");
        this.add("translation.horsespy.message.tame", "You should tame the horse first, just saying...");

        this.add("config.title", "Horse Modifiers Configuration Screen");
        this.add("config.general", "General Configs:");
        this.add("config.maxModifierCount", "Max number of each modifier.");
        this.add("config.healthyCarrot", "The increase value for each Health Carrot");
        this.add("config.jumpCarrot", "The increase value for each Jump Carrot");
        this.add("config.flashCarrot", "The increase value for each Flash Carrot");
    }
}
