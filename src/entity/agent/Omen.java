package entity.agent;

import entity.base.Agent;
import entity.skills.Flash;
import entity.skills.Smoke;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.GameLogic;

public class Omen extends Agent {
    public Omen() {
        super("Omen");
        skill1 = new Smoke("Dark Cover",150);
        skill2 = new Flash("Paranoia",250);
        iconImageName = ClassLoader.getSystemResource("image/Omen/Icon.png").toString();
        iconImage = new ImageView(new Image(iconImageName));
        post1ImageName = ClassLoader.getSystemResource("image/Omen/post1.png").toString();
        post1Image = new ImageView(new Image(post1ImageName));
        post1FlipImageName = ClassLoader.getSystemResource("image/Omen/post1flip.png").toString();
        post1FlipImage = new ImageView(new Image(post1FlipImageName));
        post2ImageName = ClassLoader.getSystemResource("image/Omen/post2.png").toString();
        post2Image = new ImageView(new Image(post2ImageName));
        post2FlipImageName = ClassLoader.getSystemResource("image/Omen/post2flip.png").toString();
        post2FlipImage = new ImageView(new Image(post2FlipImageName));
    }
}
