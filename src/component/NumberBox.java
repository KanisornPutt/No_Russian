package component;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NumberBox extends StackPane {
    private Rectangle rectangle;
    private Text text;
    public NumberBox(int number , int width, int height, Color color){
        text = new Text(Integer.toString(number+1));
        text.setFont(Font.font(50));
        rectangle = new Rectangle(width,height,color);
        getChildren().add(rectangle);
        getChildren().add(text);
    }
}
