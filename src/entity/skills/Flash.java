package entity.skills;

import entity.agent.Omen;
import entity.agent.Phoenix;
import entity.agent.Skye;
import entity.base.Skill;
import logic.GameLogic;
import pane.AgentInGame;

public class Flash extends Skill {

    public Flash(String name, int price) {
        super(name, price, 7);
    }
    @Override
    public void perform(AgentInGame target) {
        target.getAgent().setFlashed(effect);
        if (GameLogic.getInstance().getCurrentAgent() instanceof Omen)
            target.getSelf().setFlashView(target.getOmenFlashImage());
        else if (GameLogic.getInstance().getCurrentAgent() instanceof Phoenix) {
            target.getSelf().setFlashView(target.getPhoenixFlashImage());
        } else if (GameLogic.getInstance().getCurrentAgent() instanceof Skye) {
            target.getSelf().setFlashView(target.getSkyeFlashImage());
        }
        GameLogic.getInstance().spendCredits(price);
        target.getSelf().setFlash(true);
    }

    @Override
    public String toString() {
        return super.toString() + "Flash";
    }
}
