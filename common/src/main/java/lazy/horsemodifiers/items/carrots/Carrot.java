package lazy.horsemodifiers.items.carrots;

import lazy.horsemodifiers.Ref;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class Carrot extends Item {

    public Carrot() {
        super(new Item.Properties().tab(Ref.MOD_GROUP));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        if (!player.level.isClientSide) {
            if (livingEntity instanceof Horse) {
                Horse horse = (Horse) livingEntity;
                if (horse.isTamed()) {
                    if (this.changeAttributes(horse, player) && !player.isCreative()) {
                        itemStack.shrink(1);
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    public abstract boolean changeAttributes(Horse entity, Player playerEntity);
}
