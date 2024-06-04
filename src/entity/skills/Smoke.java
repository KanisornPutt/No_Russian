package entity.skills;

import entity.agent.Brimstone;
import entity.agent.Omen;
import entity.base.Skill;
import logic.GameLogic;
import pane.AgentInGame;

public class Smoke extends Skill {
    public Smoke(String name, int price) {
        super(name, price, 7);
    }

    @Override
    public void perform(AgentInGame target) {
        target.getAgent().setSmoked(effect);
        if (GameLogic.getInstance().getCurrentAgent() instanceof Brimstone)
            target.getSelf().setSmokedView(target.getSkySmokeImage());
        else if (GameLogic.getInstance().getCurrentAgent() instanceof Omen) {
            target.getSelf().setSmokedView(target.getDarkCoverImage());
        }
        target.getSelf().setSmoked(true);
        GameLogic.getInstance().spendCredits(price);
    }


    @Override
    public String toString() {
        return super.toString() + "Smoke";
    }
}
