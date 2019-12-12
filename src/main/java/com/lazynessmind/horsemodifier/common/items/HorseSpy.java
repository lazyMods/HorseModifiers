package com.lazynessmind.horsemodifier.common.items;

import com.lazynessmind.horsemodifier.client.container.HorseSpyContainer;
import com.lazynessmind.horsemodifier.common.horsedata.HorsesData;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.annotation.Nullable;
import java.util.List;

public class HorseSpy extends ModItem implements INamedContainerProvider {

    private HorseEntity entity;

    public HorseSpy() {
        super("horsespy");
}

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag tooltipFlag) {
        super.addInformation(stack, world, list, tooltipFlag);
        list.add(new TranslationTextComponent("translation.horsespy.tooltip.one").applyTextStyle(TextFormatting.GOLD));
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (!player.world.isRemote) {
            if (entity instanceof HorseEntity) {
                HorseEntity horseEntity = (HorseEntity) entity;
                float speed = (float) horseEntity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue();
                float jump = 0;
                try {
                    jump = (float) horseEntity.getAttribute((IAttribute)FieldUtils.readField(horseEntity, "JUMP_STRENGTH", true)).getValue();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                float health = (float) horseEntity.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getValue();

                player.sendMessage(new StringTextComponent(TextFormatting.DARK_PURPLE + "======================================="));
                player.sendMessage(new StringTextComponent("Speed: " + TextFormatting.GREEN + speed));
                player.sendMessage(new StringTextComponent("Jump: " + TextFormatting.GREEN + jump));
                player.sendMessage(new StringTextComponent("Health: " + TextFormatting.GREEN + health));
                player.sendMessage(new StringTextComponent("SpeedCount: " + TextFormatting.GREEN + HorsesData.getSpeed(horseEntity)));
                player.sendMessage(new StringTextComponent("JumpCount: " + TextFormatting.GREEN + HorsesData.getJump(horseEntity)));
                player.sendMessage(new StringTextComponent("HealthCount: " + TextFormatting.GREEN + HorsesData.getHealth(horseEntity)));
                player.sendMessage(new StringTextComponent(TextFormatting.DARK_PURPLE + "======================================="));
            }
        }

        return true;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("");
    }

    @Nullable
    @Override
    public Container createMenu(int windowID, PlayerInventory playerInv, PlayerEntity p_createMenu_3_) {
        return HorseSpyContainer.create(windowID, this.entity);
    }
}
