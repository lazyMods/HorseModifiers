package lazy.horsemodifiers.items;

import lazy.horsemodifiers.Ref;
import lazy.horsemodifiers.horsedata.HorsesData;
import lazy.horsemodifiers.util.ItemHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HorseSpy extends Item {

    private static final String TAG_CURRENT_MODE = "CurrentMode";
    public int currentMode = 0;

    public HorseSpy() {
        super(new Properties().tab(Ref.MOD_GROUP));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);
        list.add(new TranslatableComponent("translation.horsespy.tooltip.one").withStyle(ChatFormatting.GOLD));
        list.add(new TranslatableComponent("translation.horsespy.tooltip.two").withStyle(ChatFormatting.RED));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        if (!player.level.isClientSide) {
            if (livingEntity instanceof Horse) {
                Horse horse = (Horse) livingEntity;

                if (horse.isTamed()) {
                    this.createTag(itemStack);

                    if (itemStack.getTag() != null) {
                        if (itemStack.getTag().getInt(TAG_CURRENT_MODE) == 0) {
                            this.displayHorseStats(horse, player);
                        } else if (itemStack.getTag().getInt(TAG_CURRENT_MODE) == 1) {
                            this.removeUpgrade(0, "translation.horsespy.message.speed", player, horse);
                        } else if (itemStack.getTag().getInt(TAG_CURRENT_MODE) == 2) {
                            this.removeUpgrade(1, "translation.horsespy.message.jump", player, horse);
                        } else if (itemStack.getTag().getInt(TAG_CURRENT_MODE) == 3) {
                            this.removeUpgrade(2, "translation.horsespy.message.health", player, horse);
                        }
                    }
                } else {
                    player.sendMessage(new TranslatableComponent("translation.horsespy.message.tame").withStyle(ChatFormatting.RED), player.getUUID());
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getLevel().isClientSide && context.getPlayer() != null) {
            if (context.getPlayer().isCrouching()) {
                if (this.currentMode < 3) this.currentMode++;
                else this.currentMode = 0;
                this.createTag(context.getItemInHand());
                if (context.getItemInHand().getTag() != null)
                    context.getItemInHand().getTag().putInt(TAG_CURRENT_MODE, this.currentMode);
                this.sendStatusFromMode(this.currentMode, context.getPlayer());
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    private void createTag(ItemStack stack) {
        if (!stack.hasTag()) {
            CompoundTag data = new CompoundTag();
            data.putInt(TAG_CURRENT_MODE, currentMode);
            stack.setTag(data);
        }
    }

    private void displayHorseStats(Horse horseEntity, Player player) {
        float speed = (float) horseEntity.getAttribute(Attributes.MOVEMENT_SPEED).getValue();
        float jump = (float) horseEntity.getAttribute(Attributes.JUMP_STRENGTH).getValue();
        float health = (float) horseEntity.getAttribute(Attributes.MAX_HEALTH).getValue();

        player.sendMessage(new TextComponent(ChatFormatting.DARK_PURPLE + "===================="), player.getUUID());
        player.sendMessage(new TranslatableComponent("translation.horsespy.message.status.speed").append(String.valueOf(speed)), player.getUUID());
        player.sendMessage(new TranslatableComponent("translation.horsespy.message.status.jump").append(String.valueOf(jump)), player.getUUID());
        player.sendMessage(new TranslatableComponent("translation.horsespy.message.status.health").append(String.valueOf(health)), player.getUUID());
        player.sendMessage(new TranslatableComponent("translation.horsespy.message.status.speedcount").append(String.valueOf(HorsesData.getSpeed(horseEntity))), player.getUUID());
        player.sendMessage(new TranslatableComponent("translation.horsespy.message.status.jumpcount").append(String.valueOf(HorsesData.getJump(horseEntity))), player.getUUID());
        player.sendMessage(new TranslatableComponent("translation.horsespy.message.status.healthcount").append(String.valueOf(HorsesData.getHealth(horseEntity))), player.getUUID());
        player.sendMessage(new TextComponent(ChatFormatting.DARK_PURPLE + "===================="), player.getUUID());
    }

    private void sendStatusFromMode(int id, Player playerEntity) {
        switch (id) {
            case 0:
                playerEntity.displayClientMessage(new TranslatableComponent("translation.horsespy.message.mode.one"), true);
                break;
            case 1:
                playerEntity.displayClientMessage(new TranslatableComponent("translation.horsespy.message.mode.two"), true);
                break;
            case 2:
                playerEntity.displayClientMessage(new TranslatableComponent("translation.horsespy.message.mode.three"), true);
                break;
            case 3:
                playerEntity.displayClientMessage(new TranslatableComponent("translation.horsespy.message.mode.four"), true);
                break;
        }
    }

    private void removeUpgrade(int id, String succMessage, Player playerEntity, Horse horseEntity) {
        if ((HorsesData.getValueById(id, horseEntity) - 1) >= 0) {
            HorsesData.setValueById(id, horseEntity, HorsesData.getValueById(id, horseEntity) - 1);
            HorsesData.setAttrValueById(id, horseEntity);
            Vec3 playerPos = playerEntity.position();
            ItemEntity itementity = new ItemEntity(playerEntity.level, playerPos.x, playerPos.y + 1, playerPos.z, ItemHelper.getStackFromItem(HorsesData.getItemById(id)));
            itementity.setDefaultPickUpDelay();
            playerEntity.level.addFreshEntity(itementity);
            playerEntity.sendMessage(new TranslatableComponent(succMessage).append(String.valueOf(HorsesData.getValueById(id, horseEntity))), playerEntity.getUUID());
        } else {
            playerEntity.sendMessage(new TranslatableComponent("translation.horsespy.message.failmodifier").withStyle(ChatFormatting.RED), playerEntity.getUUID());
        }
    }
}
