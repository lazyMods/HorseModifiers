package lazy.horsemodifiers.mixin;

import lazy.horsemodifiers.extension.HorseExtension;
import lazy.horsemodifiers.util.HorseData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.animal.horse.Horse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Horse.class)
public abstract class HorseMixin implements HorseExtension {

    private final String TAG = "CustomData";
    public CompoundTag customData = new CompoundTag();

    @Override
    public CompoundTag getCustomData() {
        return customData;
    }

    @Override
    public void saveData(CompoundTag tag) {
        this.customData.put(HorseData.TAG, tag);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void addAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        tag.put(TAG, customData);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains(TAG, 10)) {
            customData = tag.getCompound(TAG);
        }
    }
}
