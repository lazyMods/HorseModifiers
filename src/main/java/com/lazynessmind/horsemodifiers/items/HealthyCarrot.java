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

public class HealthyCarrot extends Carrot {

    public HealthyCarrot() {
        super("healthycarrot");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> list, ITooltipFlag tooltipFlag) {
        super.addInformation(stack, world, list, tooltipFlag);
        ITextComponent tooltipOne = new TextComponentTranslation("translation.healthycarrot.tooltip.one");
        tooltipOne.getStyle().setColor(TextFormatting.GOLD);
        list.add(tooltipOne.getFormattedText());
        list.add(new TextComponentTranslation("translation.healthycarrot.tooltip.two").getFormattedText());
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity, EnumHand hand) {
        return super.itemInteractionForEntity(stack, player, entity, hand);
    }

    @Override
    public boolean changeAttributes(EntityLivingBase entity, EntityPlayer playerEntity) {
        //Each heart = 2.0D
        //Each horse can have 60.0D (30 hearts)
        //The only value that will be configurable is the amount of heart on carrot can add
        double currentMaxHealth = entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue();
        if (currentMaxHealth < 60.0D) {
            entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(currentMaxHealth + Configs.healthyAddValue);
            entity.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundType.ANVIL.getVolume() * 0.6F, SoundType.ANVIL.getPitch());
            return true;
        } else {
            ITextComponent tooltipOne = new TextComponentTranslation("translation.healthycarrot.message");
            tooltipOne.getStyle().setColor(TextFormatting.BLUE);
            playerEntity.sendMessage(tooltipOne);
            return false;
        }
    }
}
