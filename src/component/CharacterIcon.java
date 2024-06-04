package component;

import entity.base.Agent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.GameLogic;

public class CharacterIcon extends StackPane {
    private Rectangle rectangle;
    private ImageView imageView;
    private Agent agent;
    public CharacterIcon(Agent agent) {
        try{
        Rectangle newrectangle = new Rectangle(100,100, Color.GRAY);
        rectangle = newrectangle;
        //charactor icon
            this.agent = agent;
            imageView = agent.getIconImage();
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(80);
            getChildren().addAll(rectangle, imageView);
            setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    setActive(true);
                }
            });
            setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    setActive(false);
                }
            });
            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(GameLogic.getInstance().getAllAgents().size()<6){
                        GameLogic.getInstance().addAgent(agent);
                        if(GameLogic.getInstance().getAllAgents().size()>3){
                            GameLogic.getInstance().getVersusPane().addToIcons(new CharacterImageBox(80,agent,Color.DARKBLUE));
                        }else{
                            GameLogic.getInstance().getVersusPane().addToIcons(new CharacterImageBox(80,agent,Color.DARKRED));
                        }
                    }
                }
            });
        }catch (Exception e){
            System.out.println("error");
        }
    }
    public void setActive(boolean value){
        if(value){
            rectangle.setFill(Color.LIGHTGRAY);
        }else{
            rectangle.setFill(Color.GRAY);
        }

    }
}
