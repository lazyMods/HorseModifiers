package com.lazynessmind.horsemodifier.items;

import com.lazynessmind.horsemodifier.configs.HMConfig;
import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class FlashCarrot extends Carrot{

    public FlashCarrot() {
        super("flashcarrot");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag tooltipFlag) {
        super.addInformation(stack, world, list, tooltipFlag);
        list.add(new StringTextComponent(TextFormatting.GOLD + "Changes the speed attribute!"));
        list.add(new StringTextComponent(TextFormatting.WHITE + "When the limit is reached you need to lock the horse in a cell!"));
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        return super.itemInteractionForEntity(stack, player, entity, hand);
    }

    @Override
    public boolean changeAttributes(LivingEntity entity, PlayerEntity playerEntity) {
        //Add 0.5D to the speed maybe?
        //The game generates a random number to the speed starting at 0.45D
        //Limit the speed to 1.0D because the server starts shaking in higher values
        //But for the love of goods a config will be added.
        double currentSpeed = entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue();
        if(currentSpeed < 1.0D) {
            entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(currentSpeed + HMConfig.CARROT_SPEED_ADD.get());
            entity.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundType.CROP.getVolume() * 0.6F, SoundType.CROP.getPitch());
            System.out.println("Current speed: " + currentSpeed);
            return true;
        } else {
            playerEntity.sendMessage(new StringTextComponent(TextFormatting.BLUE + "The limit has been reached!" + TextFormatting.RED + " Don't go to fast!"));
            return false;
        }
    }
}
