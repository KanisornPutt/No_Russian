package entity.skills;

import entity.base.Skill;
import logic.GameLogic;
import pane.AgentInGame;

public class Molly extends Skill {
    public Molly(String name, int price) {
        super(name, price,5);
    }

    @Override
    public void perform(AgentInGame target) {
        target.getAgent().setMollied(effect);
        target.getSelf().setMollied(true);
        GameLogic.getInstance().spendCredits(price);
    }

    @Override
    public String toString() {
        return super.toString() + "Molly";
    }
}
