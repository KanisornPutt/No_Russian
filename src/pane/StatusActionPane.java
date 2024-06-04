package pane;

import component.CharacterImageBox;
import component.InGameButton;
import entity.base.Agent;
import entity.weapon.Operator;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;

import java.util.ArrayList;
import java.util.List;

public class StatusActionPane extends HBox {
    private CharacterImageBox image;
    private StatusPane status;
    private GridPane controlPanel;
    private InGameButton shoot;
    private InGameButton upgrade;
    private InGameButton skill1;
    private InGameButton skill2;
    private Text output;
    private Text playerTurn;
    private List<InGameButton> buttons = new ArrayList<>();
    public StatusActionPane(Agent agent){
        image = new CharacterImageBox(70,agent, Color.GRAY);
        setSpacing(20);
        status = new StatusPane();
        status.setStatus(agent);
        output = new Text();
        output.setFill(Color.WHITE);
        output.setFont(new Font(30));
        playerTurn = new Text();
        playerTurn.setFill(Color.WHITE);
        playerTurn.setFont(new Font(30));
        setPlayerTurn();
        output.setText("Choose your action");
        createControllPanel();
        getChildren().addAll(playerTurn,image,status, controlPanel, output);
    }
    public void createControllPanel(){
        controlPanel = new GridPane();
        shoot = new InGameButton("Shoot",0);
        upgrade = new InGameButton("upgrade",0);
        skill1 = new InGameButton("skill1",0);
        skill2 = new InGameButton("skill2",0);
        buttons.add(shoot);
        buttons.add(skill1);
        buttons.add(skill2);
        buttons.add(upgrade);
        setButton(GameLogic.getInstance().getCurrentAgent());
        controlPanel.add(shoot,0,0);
        controlPanel.add(upgrade,1,0);
        controlPanel.add(skill1,0,1);
        controlPanel.add(skill2,1,1);
        shoot.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                press(shoot);
                setOutput("Select your target.");
            }
        });
        upgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (GameLogic.getInstance().canBuy(upgrade.getPrice())) {
                    GameLogic.getInstance().getCurrentAgent().upgrade();
                    upgrade.setPrice(GameLogic.getInstance().getCurrentAgent().getUpgradePrice());
                    GameLogic.getInstance().getCurrentAgentsInGame().update();
                    upgrade.setPrice(GameLogic.getInstance().getCurrentAgent().getUpgradePrice());
                    status.setWeaponStatus(GameLogic.getInstance().getCurrentAgent().getWeapon());
                    upgrade.setText("     Upgrade\n" + GameLogic.getInstance().getCurrentAgent().getWeapon().getUpgradeName() + " (" + GameLogic.getInstance().getCurrentAgent().getUpgradePrice() + ")");
                    if (GameLogic.getInstance().getCurrentAgent().getWeapon() instanceof Operator) {
                        upgrade.setText("          Upgrade\n" + GameLogic.getInstance().getCurrentAgent().getWeapon().getUpgradeName());
                    }
                }
            }
        });
        skill1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (GameLogic.getInstance().canBuy(skill1.getPrice())) {
                    press(skill1);
                    setOutput("Select your target.");
                }
            }
        });
        skill2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (GameLogic.getInstance().canBuy(skill2.getPrice())) {
                    press(skill2);
                    setOutput("Select your target.");
                }
            }
        });
    }

    public CharacterImageBox getImage() {
        return image;
    }

    public void setImage(Agent agent) {
        System.out.println("set image to " + agent.getName());
        this.image.setImageView(agent.getIconImage());
    }

    public StatusPane getStatus() {
        return status;
    }

    public void resetButtonsExcept(InGameButton button) {
        for (InGameButton b : buttons) {
            if (b != button)
                b.setActive(false);
        }
    }

    public List<InGameButton> getButtons() {
        return buttons;
    }

    public InGameButton getShoot() {
        return shoot;
    }

    public InGameButton getSkill1() {
        return skill1;
    }

    public InGameButton getSkill2() {
        return skill2;
    }

    public void press(InGameButton button) {
        resetButtonsExcept(button);
        button.setActive(true);
    }

    public void setButton(Agent agent) {
        upgrade.setPrice(agent.getUpgradePrice());
        upgrade.setText("     Upgrade\n" + agent.getWeapon().getUpgradeName() + " (" + agent.getUpgradePrice() + ")");
        if (agent.getWeapon() instanceof Operator) {
            upgrade.setText("          Upgrade\n" + agent.getWeapon().getUpgradeName());
        }
        skill1.setPrice(agent.getSkill1().getPrice());
        skill1.setText(agent.getSkill1().toString());
        skill2.setPrice(agent.getSkill2().getPrice());
        skill2.setText(agent.getSkill2().toString());
    }

    public void setOutput(String string) {
        output.setText(string);
    }

    public void addOutput(String string) {
        output.setText(output.getText() + string);
    }

    public void setPlayerTurn() {
        this.playerTurn.setText("Player " + GameLogic.getInstance().getCurrentPlayer() + " turn.");
    }
}
