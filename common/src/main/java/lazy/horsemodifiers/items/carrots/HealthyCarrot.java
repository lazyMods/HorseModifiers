package lazy.horsemodifiers.items.carrots;

import lazy.horsemodifiers.ModConfigs;
import lazy.horsemodifiers.util.HorseData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HealthyCarrot extends Carrot {

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);
        list.add(new TranslatableComponent("translation.healthycarrot.tooltip.one").withStyle(ChatFormatting.GOLD));
        list.add(new TranslatableComponent("translation.healthycarrot.tooltip.two"));
    }

    @Override
    public boolean changeAttributes(Horse entity, Player playerEntity) {
        if (HorseData.getHealth(entity) < ModConfigs.maxModifierCount.get()) {
            entity.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, SoundType.CROP.getVolume() * 0.6F, SoundType.CROP.getPitch());
            HorseData.setHealth(entity, HorseData.getHealth(entity) + 1);
            HorseData.increaseAttrValue(entity, 2);
            return true;
        } else {
            return false;
        }
    }
}
