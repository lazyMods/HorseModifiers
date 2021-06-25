package lazy.horsemodifiers.extension;

import net.minecraft.nbt.CompoundTag;

public interface HorseExtension {

    void saveData(CompoundTag tag);

    CompoundTag getCustomData();
}
