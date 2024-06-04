package pane;

import component.CharacterIcon;
import entity.agent.Brimstone;
import entity.agent.Omen;
import entity.agent.Phoenix;
import entity.agent.Skye;
import javafx.geometry.Insets;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class AgentPane extends TilePane {
    private ArrayList<CharacterIcon> agentSelectPanel;
    private int currentBox;
    public AgentPane(){
        setPadding(new Insets(100,70,70,70));
        setHgap(20);
        agentSelectPanel = new ArrayList<>();
        agentSelectPanel.add(new CharacterIcon(new Brimstone()));
        agentSelectPanel.add(new CharacterIcon(new Omen()));
        agentSelectPanel.add(new CharacterIcon(new Phoenix()));
        agentSelectPanel.add(new CharacterIcon(new Skye()));
        for(CharacterIcon x : agentSelectPanel){
            getChildren().add(x);
        }
        setCurrentBox(0);
    }

    public void setCurrentBox(int currentBox) {
        this.currentBox = currentBox;
    }

}
