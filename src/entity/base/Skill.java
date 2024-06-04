package entity.base;

import entity.base.Agent;
import pane.AgentInGame;

public abstract class Skill {
    protected String name;
    protected int price;
    protected int effect;
    public Skill(String name, int price, int effect) {
        this.name = name;
        this.price = price;
        this.effect = effect;
    }
    public String getName() {
        return name;
    }

    public abstract void perform(AgentInGame target);

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return " (" + getName() + "\n" + getPrice() + ") ";
    }

    public int getEffect() {
        return effect - 1;
    }
}
