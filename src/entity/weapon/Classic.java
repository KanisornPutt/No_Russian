package entity.weapon;

import entity.base.Weapon;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Classic extends Weapon {
    public Classic() {
        super("Classic", 12,24,20,1400);
        upgradeName = "Vandal";
        post1ImageName = ClassLoader.getSystemResource("image/Classic/post1.png").toString();
        post1Image = new ImageView(new Image(post1ImageName));
        post1FlipImageName = ClassLoader.getSystemResource("image/Classic/post1flip.png").toString();
        post1FlipImage = new ImageView(new Image(post1FlipImageName));
        post2ImageName = ClassLoader.getSystemResource("image/Classic/post2.png").toString();
        post2Image = new ImageView(new Image(post2ImageName));
        post2FlipImageName = ClassLoader.getSystemResource("image/Classic/post2flip.png").toString();
        post2FlipImage = new ImageView(new Image(post2FlipImageName));
    }
}
