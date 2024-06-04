package pane;

import entity.base.Agent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.GameLogic;

public class AgentInGameP2Pane extends AgentInGame {
    public AgentInGameP2Pane(Agent agent) {
        super(agent);
        // Scale the whole HBox to mirror both ImageView and StatusPane
        setDefaultView(new ImageView(agent.getPost1FlipImageName()));
        defaultView.setPreserveRatio(true);
        defaultView.setFitWidth(100);
        setWeaponView(new ImageView(agent.getWeapon().getPost1FlipImageName()));
        weaponView.setPreserveRatio(true);
        weaponView.setFitWidth(100);
        status = new StatusPane();
        status.setStatus(agent);
        rectangle = new Rectangle(100,100, Color.BLUE);
        rectangle.setOpacity(0.3);
        stackPane = new StackPane(rectangle, currentRectangle ,molliedView,defaultView,weaponView,flashView, smokedView);
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
        getChildren().addAll(stackPane, status);
        setHovering(false);
    }
}