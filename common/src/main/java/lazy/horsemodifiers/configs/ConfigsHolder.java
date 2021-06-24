package lazy.horsemodifiers.configs;

public class ConfigsHolder {

    public double healthyCarrotIncreaseValue;
    public double jumpCarrotIncreaseValue;
    public double flashCarrotIncreaseValue;
    public int maxModifierCount;

    public ConfigsHolder(double healthyCarrotIncreaseValue, double jumpCarrotIncreaseValue, double flashCarrotIncreaseValue, int maxModifierCount) {
        this.healthyCarrotIncreaseValue = healthyCarrotIncreaseValue;
        this.jumpCarrotIncreaseValue = jumpCarrotIncreaseValue;
        this.flashCarrotIncreaseValue = flashCarrotIncreaseValue;
        this.maxModifierCount = maxModifierCount;
    }
}
