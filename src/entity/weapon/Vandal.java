package entity.weapon;

import entity.base.Weapon;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Vandal extends Weapon {
    public Vandal() {
        super("Vandal", 40, 80,30,2000);
        upgradeName = "Operator";
        post1ImageName = ClassLoader.getSystemResource("image/Vandal/post1.png").toString();
        post1Image = new ImageView(new Image(post1ImageName));
        post1FlipImageName = ClassLoader.getSystemResource("image/Vandal/post1flip.png").toString();
        post1FlipImage = new ImageView(new Image(post1FlipImageName));
        post2ImageName = ClassLoader.getSystemResource("image/Vandal/post2.png").toString();
        post2Image = new ImageView(new Image(post2ImageName));
        post2FlipImageName = ClassLoader.getSystemResource("image/Vandal/post2flip.png").toString();
        post2FlipImage = new ImageView(new Image(post2FlipImageName));
    }
}