package entity.skills;

import entity.base.Skill;
import logic.GameLogic;
import pane.AgentInGame;

import java.util.Collections;

import static java.lang.Math.min;

public class Heal extends Skill {
    int healed = 50;
    public Heal(String name, int price) {
        super(name, price, 7);
        effect = 50;
    }

    @Override
    public void perform(AgentInGame target) {
        if (target.getAgent().isAlive()) {
            healed = min(100 - target.getAgent().getHp(),50);
            target.getAgent().setHp(target.getAgent().getHp() + effect);
            GameLogic.getInstance().spendCredits(price);
        }
        else healed = 0;
    }

    public String toString() {
        return super.toString() + "Heal";
    }

    @Override
    public int getEffect() {return healed;}
}
