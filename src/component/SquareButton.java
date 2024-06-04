package component;

import application.Main;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SquareButton extends StackPane implements Hoverable {
    private Rectangle inside;
    private Rectangle outside;

    public SquareButton(String name) {
        Rectangle backrectangle = new Rectangle(220, 95, Color.DARKGRAY);
        outside = backrectangle;
        Rectangle rectangle = new Rectangle(200, 75, Color.GRAY);
        inside = rectangle;
        Text text = new Text(name);
        text.setFont(Font.font(Main.myfont.getFamily(), 50));
        this.getChildren().addAll(backrectangle, rectangle, text);
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setHovering(true);
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setHovering(false);
            }
        });
    }

    public SquareButton(String name,int width, int height) {
        Rectangle backrectangle = new Rectangle(width + 20, height + 20, Color.DARKGRAY);
        outside = backrectangle;
        Rectangle rectangle = new Rectangle(width, height, Color.GRAY);
        inside = rectangle;
        Text text = new Text(name);
        text.setFont(Font.font(Main.myfont.getFamily(), 50));
        this.getChildren().addAll(backrectangle, rectangle, text);
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setHovering(true);
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setHovering(false);
            }
        });
    }

    public void setHovering(boolean value) {
        if (value) {
            inside.setFill(Color.LIGHTGRAY);
            outside.setFill(Color.GRAY);
        } else {
            inside.setFill(Color.GRAY);
            outside.setFill(Color.DARKGRAY);
        }
    }

    public void setText(String name) {
        getChildren().remove(2);
        Text text = new Text(name);
        text.setFont(Font.font(Main.myfont.getFamily(), 50));
        getChildren().add(text);
    }

    public void setText(String name, int size) {
        getChildren().remove(2);
        Text text = new Text(name);
        text.setFont(Font.font(size));
        getChildren().add(text);
    }

}