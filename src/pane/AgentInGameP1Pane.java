package pane;

import entity.base.Agent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.GameLogic;

public class AgentInGameP1Pane extends AgentInGame {
    public AgentInGameP1Pane(Agent agent){
        super(agent);
        setDefaultView(new ImageView(agent.getPost1ImageName()));
        defaultView.setPreserveRatio(true);
        defaultView.setFitWidth(100);
        setWeaponView(new ImageView(agent.getWeapon().getPost1ImageName()));
        weaponView.setPreserveRatio(true);
        weaponView.setFitWidth(100);
        weaponView.setVisible(true);
        status = new StatusPane();
        status.setStatus(agent);
        rectangle = new Rectangle(100,100, Color.RED);
        rectangle.setOpacity(0.3);
        stackPane = new StackPane(rectangle,currentRectangle,molliedView,defaultView,weaponView,flashView,smokedView);
        stackPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setHovering(true);
            }
        });
        stackPane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setHovering(false);
            }
        });
        stackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GameLogic.getInstance().action(getSelf());
            }
        });
        getChildren().addAll(status,stackPane);
        setHovering(false);
    }
}