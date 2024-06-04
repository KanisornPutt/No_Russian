package component;

import entity.base.Agent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CharacterImageBox extends StackPane {
    private ImageView imageView;
    private int size;
    private Rectangle rectangle;
    public CharacterImageBox(int size, Agent agent, Color color){
        rectangle = new Rectangle(size,size,color);
        this.size = size;
        imageView = new ImageView(agent.getIconImageName());
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(size);
        getChildren().addAll(rectangle,imageView);
    }

    public void setImageView(ImageView imageView) {
        getChildren().remove(this.imageView);
        this.imageView = imageView;
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(size);
        getChildren().add(this.imageView);
    }
    public void setRectangle(Color color){
        getChildren().remove(rectangle);
        rectangle.setFill(color);
        getChildren().add(0,rectangle);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
