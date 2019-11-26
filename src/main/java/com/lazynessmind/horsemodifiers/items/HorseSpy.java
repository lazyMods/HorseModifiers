package com.lazynessmind.horsemodifiers.items;

import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class HorseSpy extends HMItem {

    public HorseSpy() {
        super("horsespy");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        ITextComponent tooltipOne = new TextComponentTranslation("translation.horsespy.tooltip.one");
        tooltipOne.getStyle().setColor(TextFormatting.GOLD);
        tooltip.add(tooltipOne.getFormattedText());
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity, EnumHand hand) {
        if (!player.world.isRemote) {
            if (entity instanceof AbstractHorse) {
                AbstractHorse horse = (AbstractHorse) entity;
                float speed = (float) horse.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();
                float jump = (float) horse.getAttributeMap().getAttributeInstanceByName("horse.jumpStrength").getBaseValue();
                float health = (float) horse.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue();
                player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE + "======================================="));
                player.sendMessage(new TextComponentString("Speed: " + TextFormatting.GREEN + speed));
                player.sendMessage(new TextComponentString("Jump: " + TextFormatting.GREEN + jump));
                player.sendMessage(new TextComponentString("Health: " + TextFormatting.GREEN + health));
                player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE + "======================================="));
                entity.playSound(SoundEvents.BLOCK_CHEST_CLOSE, SoundType.SLIME.getVolume() * 0.6F, SoundType.SLIME.getPitch());

            }
        }
        return true;
    }
}
