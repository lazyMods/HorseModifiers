package com.lazynessmind.horsemodifiers.items;

import com.lazynessmind.horsemodifiers.configs.Configs;
import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class FlashCarrot extends Carrot {

    public FlashCarrot() {
        super("flashcarrot");
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> list, ITooltipFlag tooltipFlag) {
        super.addInformation(stack, world, list, tooltipFlag);
        ITextComponent tooltipOne = new TextComponentTranslation("translation.flashcarrot.tooltip.one");
        tooltipOne.getStyle().setColor(TextFormatting.GOLD);
        list.add(tooltipOne.getFormattedText());
        list.add(new TextComponentTranslation("translation.flashcarrot.tooltip.two").getFormattedText());
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity, EnumHand hand) {
        return super.itemInteractionForEntity(stack, player, entity, hand);
    }

    @Override
    public boolean changeAttributes(EntityLivingBase entity, EntityPlayer playerEntity) {
        //Add 0.5D to the speed maybe?
        //The game generates a random number to the speed starting at 0.45D
        //Limit the speed to 1.0D because the server starts shaking in higher values
        //But for the love of goods a config will be added.
        double currentSpeed = entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
        if (currentSpeed < Configs.flashCarrotLimit) {
            entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(currentSpeed + Configs.flashAddValue);
            entity.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundType.ANVIL.getVolume() * 0.6F, SoundType.ANVIL.getPitch());
            return true;
        } else {
            ITextComponent tooltipOne = new TextComponentTranslation("translation.flashcarrot.message");
            tooltipOne.getStyle().setColor(TextFormatting.BLUE);
            playerEntity.sendMessage(tooltipOne);
            return false;
        }
    }
}
