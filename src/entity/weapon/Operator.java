package entity.weapon;

import entity.base.Weapon;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Operator extends Weapon {
    public Operator() {
        super("Operator", 100,200,50,100000);
        upgradeName = "Cannot be upgraded";
        post1ImageName = ClassLoader.getSystemResource("image/Op/post1.png").toString();
        post1Image = new ImageView(new Image(post1ImageName));
        post1FlipImageName = ClassLoader.getSystemResource("image/Op/post1flip.png").toString();
        post1FlipImage = new ImageView(new Image(post1FlipImageName));
        post2ImageName = ClassLoader.getSystemResource("image/Op/post2.png").toString();
        post2Image = new ImageView(new Image(post2ImageName));
        post2FlipImageName = ClassLoader.getSystemResource("image/Op/post2flip.png").toString();
        post2FlipImage = new ImageView(new Image(post2FlipImageName));
    }

}