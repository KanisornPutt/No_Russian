package entity.agent;

import entity.base.Agent;
import entity.skills.Molly;
import entity.skills.Smoke;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.GameLogic;

public class Brimstone extends Agent {
    public Brimstone() {
        super("Brimstone");
        skill1 = new Smoke("Sky Smoke",100);
        skill2 = new Molly("Incendiary",250);
        iconImageName = ClassLoader.getSystemResource("image/Brimstone/Icon.png").toString();
        iconImage = new ImageView(new Image(iconImageName));
        post1ImageName = ClassLoader.getSystemResource("image/Brimstone/post1.png").toString();
        post1Image = new ImageView(new Image(post1ImageName));
        post1FlipImageName = ClassLoader.getSystemResource("image/Brimstone/post1flip.png").toString();
        post1FlipImage = new ImageView(new Image(post1FlipImageName));
        post2ImageName = ClassLoader.getSystemResource("image/Brimstone/post2.png").toString();
        post2Image = new ImageView(new Image(post2ImageName));
        post2FlipImageName = ClassLoader.getSystemResource("image/Brimstone/post2flip.png").toString();
        post2FlipImage = new ImageView(new Image(post2FlipImageName));
    }
}
