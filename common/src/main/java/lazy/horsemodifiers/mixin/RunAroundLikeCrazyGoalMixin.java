package lazy.horsemodifiers.mixin;

import lazy.horsemodifiers.util.HorseData;
import net.minecraft.world.entity.ai.goal.RunAroundLikeCrazyGoal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Horse;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RunAroundLikeCrazyGoal.class)
public class RunAroundLikeCrazyGoalMixin {

    @Shadow @Final private AbstractHorse horse;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/horse/AbstractHorse;tameWithName(Lnet/minecraft/world/entity/player/Player;)Z"))
    public void tick(CallbackInfo ci){
        if(horse instanceof Horse){
            HorseData.saveNewDataToHorse((Horse)this.horse);
            System.out.println("Add data to horse");
        }
    }
}
