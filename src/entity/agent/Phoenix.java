package entity.agent;

import entity.base.Agent;
import entity.skills.Flash;
import entity.skills.Molly;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.GameLogic;

public class Phoenix extends Agent {
    public Phoenix() {
        super("Phoenix");
        skill1 = new Flash("Curve Ball",250);
        skill2 = new Molly("Hot Hand",250);
        iconImageName = ClassLoader.getSystemResource("image/Phoenix/Icon.png").toString();
        iconImage = new ImageView(new Image(iconImageName));
        post1ImageName = ClassLoader.getSystemResource("image/Phoenix/post1.png").toString();
        post1Image = new ImageView(new Image(post1ImageName));
        post1FlipImageName = ClassLoader.getSystemResource("image/Phoenix/post1flip.png").toString();
        post1FlipImage = new ImageView(new Image(post1FlipImageName));
        post2ImageName = ClassLoader.getSystemResource("image/Phoenix/post2.png").toString();
        post2Image = new ImageView(new Image(post2ImageName));
        post2FlipImageName = ClassLoader.getSystemResource("image/Phoenix/post2flip.png").toString();
        post2FlipImage = new ImageView(new Image(post2FlipImageName));
    }
}
