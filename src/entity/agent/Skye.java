package entity.agent;

import entity.base.Agent;
import entity.skills.Flash;
import entity.skills.Heal;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.GameLogic;

public class Skye extends Agent {

    public Skye() {
        super("Skye");
        skill1 = new Flash("Guiding Light", 100);
        skill2 = new Heal("Regrowth", 200);
        iconImageName = ClassLoader.getSystemResource("image/Skye/Icon.png").toString();
        iconImage = new ImageView(new Image(iconImageName));
        post1ImageName = ClassLoader.getSystemResource("image/Skye/post1.png").toString();
        post1Image = new ImageView(new Image(post1ImageName));
        post1FlipImageName = ClassLoader.getSystemResource("image/Skye/post1flip.png").toString();
        post1FlipImage = new ImageView(new Image(post1FlipImageName));
        post2ImageName = ClassLoader.getSystemResource("image/Skye/post2.png").toString();
        post2Image = new ImageView(new Image(post2ImageName));
        post2FlipImageName = ClassLoader.getSystemResource("image/Skye/post2flip.png").toString();
        post2FlipImage = new ImageView(new Image(post2FlipImageName));
    }

}
