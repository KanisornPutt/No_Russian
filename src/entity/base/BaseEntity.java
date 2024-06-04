package entity.base;

import logic.GameLogic;

import java.util.Random;

public abstract class BaseEntity {
    protected Random random = new Random();
    protected String name;
    protected int hp;
    protected int visibility;
    protected boolean isAlive;

    public BaseEntity(String name,int hp) {
        this.name = name;
        setHp(hp);
        visibility = 100;
        isAlive = true;
    }

    public void setHp(int hp) {
        if (hp <= 0) {
            hp = 0;
            this.isAlive = false;
        }
        this.hp = hp;
    }
    public int getHp() {
        return this.hp;
    }

    public boolean isAlive() {
        return isAlive;
    }
    public String getName() {
        return this.name;
    }

    public abstract String toString();

    public int getVisibility() {
        return visibility;
    }


}
