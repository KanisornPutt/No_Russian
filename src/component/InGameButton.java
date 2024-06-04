package component;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;


public class InGameButton extends StackPane implements Hoverable {
    private boolean active = false;
    private boolean hovering = false;
    private Rectangle rectangle;
    private Rectangle rectangle2;
    private Text text;
    private int price;
    public InGameButton(String string, int price){
        setAlignment(Pos.CENTER);
        rectangle = new Rectangle(140,50, Color.DARKGREEN);
        rectangle2 = new Rectangle(130,40, Color.GREEN);
        text = new Text(string);
        text.setFont(Font.font(30));
        setPrice(price);
        getChildren().addAll(rectangle,rectangle2,text);
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!isActive())
                    setHovering(true);
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!isActive())
                    setHovering(false);
            }
        });

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (canBuy())
                    setActive(true);
            }
        });
    }

    public void setHovering(boolean hover) {
        if(hover){
            if (canBuy()) {
                rectangle.setFill(Color.GREEN);
                rectangle2.setFill(Color.LIGHTGREEN);
            }
            else {
                rectangle.setFill(Color.RED);
                rectangle2.setFill(Color.LIGHTPINK);
            }
        }else{
            rectangle.setFill(Color.DARKGREEN);
            rectangle2.setFill(Color.GREEN);
        }
        this.hovering = hover;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        if(active){
            rectangle.setFill(Color.DARKRED);
            rectangle2.setFill(Color.RED);
        }else{
            rectangle.setFill(Color.DARKGREEN);
            rectangle2.setFill(Color.GREEN);
        }
        this.active = active;
    }

    public void setText(String name) {
        getChildren().remove(text);
        this.text = new Text(name);
        getChildren().add(text);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean canBuy() {
        return GameLogic.getInstance().canBuy(price);
    }
}
