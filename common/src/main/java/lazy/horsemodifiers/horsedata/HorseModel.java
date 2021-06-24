package lazy.horsemodifiers.horsedata;

public class HorseModel {

    int speed;
    int jump;
    int health;

    public HorseModel() {
        this(0, 0, 0);
    }

    public HorseModel(int speed, int jump, int health) {
        this.speed = speed;
        this.jump = jump;
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getJump() {
        return jump;
    }

    public void setJump(int jump) {
        this.jump = jump;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Data: " + " {Speed: " + getSpeed() + "} | {Jump: " + getJump() + "} | {Health: " + getHealth() + "}";
    }
}
