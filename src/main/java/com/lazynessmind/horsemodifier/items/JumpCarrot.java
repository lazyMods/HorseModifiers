package com.lazynessmind.horsemodifier.items;

import com.lazynessmind.horsemodifier.configs.HMConfig;
import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class JumpCarrot  extends Carrot{

    public JumpCarrot() {
        super("jumpcarrot");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag tooltipFlag) {
        super.addInformation(stack, world, list, tooltipFlag);
        list.add(new StringTextComponent(TextFormatting.GOLD + "Changes the jump attribute!"));
        list.add(new StringTextComponent(TextFormatting.WHITE + "When the limit is reached the affected horse can jump 7 blocks height!"));
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        return super.itemInteractionForEntity(stack, player, entity, hand);
    }

    @Override
    public boolean changeAttributes(LivingEntity entity, PlayerEntity playerEntity) {
        //One carrot gives 0.1D
        //Max could be 2.0D but, after 1.0D the horse starts to losing health
        //1.0D = 7 blocks
        //The only value that will be configurable is the amount that the carrot modifies the attribute
        double currentJump = entity.getAttributes().getAttributeInstanceByName("horse.jumpStrength").getValue();
        if (currentJump != 0 && currentJump < 1.0D){
            entity.getAttributes().getAttributeInstanceByName("horse.jumpStrength").setBaseValue(currentJump + HMConfig.CARROT_JUMP_ADD.get());
            entity.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundType.CROP.getVolume() * 0.6F, SoundType.CROP.getPitch());
            return true;
        } else {
            playerEntity.sendMessage(new StringTextComponent(TextFormatting.BLUE + "The limit has been reached!" + TextFormatting.GOLD + " The moon can't be reachable!"));
            return false;
        }

    }
}
