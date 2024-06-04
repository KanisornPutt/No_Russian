package pane;

import component.Hoverable;
import entity.base.Agent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.GameLogic;

public class AgentInGame extends HBox implements Hoverable {
    protected StackPane stackPane;
    protected Agent agent;
    protected ImageView defaultView;
    protected ImageView weaponView;
    protected ImageView smokedView;
    protected ImageView flashView;
    protected ImageView molliedView;
    protected Rectangle rectangle;
    protected Rectangle currentRectangle;
    protected StatusPane status;
    protected Boolean isCurrent;
    protected ImageView skySmokeImage;
    protected String skySmokeImageName;
    protected ImageView darkCoverImage;
    protected String darkCoverImageName;
    protected ImageView deadImage;
    protected String deadImageName;
    protected ImageView omenFlashImage;
    protected String omenFlashImageName;
    protected ImageView phoenixFlashImage;
    protected String phoenixFlashImageName;
    protected ImageView skyeFlashImage;
    protected String skyeFlashImageName;
    protected ImageView molliedImage;
    protected String molliedImageName;

    public AgentInGame(Agent agent){
        this.agent = agent;
        skySmokeImageName = ClassLoader.getSystemResource("image/skysmoke.png").toString();
        skySmokeImage = new ImageView(new Image(skySmokeImageName));
        skySmokeImage.setFitHeight(100); skySmokeImage.setFitWidth(100);
        darkCoverImageName = ClassLoader.getSystemResource("image/darkcover.png").toString();
        darkCoverImage = new ImageView(new Image(darkCoverImageName));
        darkCoverImage.setFitHeight(100); darkCoverImage.setFitWidth(100);
        omenFlashImageName = ClassLoader.getSystemResource("image/omenflash.png").toString();
        omenFlashImage = new ImageView(new Image(omenFlashImageName));
        omenFlashImage.setFitHeight(100); omenFlashImage.setFitWidth(100);
        phoenixFlashImageName = ClassLoader.getSystemResource("image/phoenixflash.png").toString();
        phoenixFlashImage = new ImageView(new Image(phoenixFlashImageName));
        phoenixFlashImage.setFitHeight(100); phoenixFlashImage.setFitWidth(100);
        skyeFlashImageName = ClassLoader.getSystemResource("image/skyeflash.png").toString();
        skyeFlashImage = new ImageView(new Image(skyeFlashImageName));
        skyeFlashImage.setFitHeight(100); skyeFlashImage.setFitWidth(100);
        molliedImageName = ClassLoader.getSystemResource("image/mollied.png").toString();
        molliedImage = new ImageView(new Image(molliedImageName));
        molliedImage.setFitHeight(100); molliedImage.setFitWidth(100);
        currentRectangle = new Rectangle(100,100, Color.LIGHTGREEN);
        currentRectangle.setOpacity(0.4);
        smokedView = darkCoverImage;
        flashView =  omenFlashImage;
        molliedView = molliedImage;
        deadImageName = ClassLoader.getSystemResource("image/dead.png").toString();
        deadImage = new ImageView(new Image(deadImageName));
        deadImage.setFitHeight(100); deadImage.setFitWidth(100);
        setSmoked(false);
        setFlash(false);
        setCurrent(false);
        setMollied(false);
    }




    public void setHovering(boolean hovering) {
        if (hovering) rectangle.setVisible(true);
        else rectangle.setVisible(false);
    }


    public Agent getAgent() {
        return agent;
    }

    public void update() {
        boolean targetisAlive = agent.isAlive();
        agent.setSmoked(agent.getSmoked() - 1);
        if (!agent.isSmoked()) setSmoked(false);
        agent.setFlashed(agent.getFlashed() - 1);
        if (!agent.isFlashed()) setFlash(false);
        if (agent.isMollied() && agent.getMollied() < 5) agent.setHp(agent.getHp() - 15);
        agent.setMollied(agent.getMollied() - 1);
        if (!agent.isMollied()) setMollied(false);
        if (!agent.isAlive()) {
            setFlash(false);
            setSmoked(false);
            setMollied(false);
            setWeapon(false);
            stackPane.getChildren().remove(defaultView);
            defaultView = deadImage;
            stackPane.getChildren().add(2,defaultView);
        }
        if (targetisAlive && !agent.isAlive()) {
            GameLogic.getInstance().getStatusActionPane().addOutput("\n" + agent.getName() + " has died.");
            if (agent == GameLogic.getInstance().getP1().getAgent() || agent == GameLogic.getInstance().getP2().getAgent() || agent == GameLogic.getInstance().getP3().getAgent() ) GameLogic.getInstance().addCredit(1,300);
            if (agent == GameLogic.getInstance().getP4().getAgent() || agent == GameLogic.getInstance().getP5().getAgent() || agent == GameLogic.getInstance().getP6().getAgent() ) GameLogic.getInstance().addCredit(2,300);
        }
        status.setStatus(agent);
    }

    public void setCurrent(boolean current) {
        if (current) {
            currentRectangle.setVisible(true);
        }
        else currentRectangle.setVisible(false);
        isCurrent = current;
    }

    public void setSmoked(boolean smoked) {
        if (smoked) smokedView.setVisible(true);
        else smokedView.setVisible(false);
    }
    public void setFlash(boolean flash) {
        if (flash) flashView.setVisible(true);
        else flashView.setVisible(false);
    }
    public void setMollied(boolean mollied) {
        if (mollied) molliedView.setVisible(true);
        else molliedView.setVisible(false);
    }
    public void setWeapon(boolean weapon) {
        if (weapon) weaponView.setVisible(true);
        else weaponView.setVisible(false);
    }

    public AgentInGame getSelf() {
        return this;
    }

    public void setSmokedView (ImageView smokedView) {
        stackPane.getChildren().remove(this.smokedView);
        this.smokedView = smokedView;
        stackPane.getChildren().add(smokedView);
    }
    public void setFlashView (ImageView flashView) {
        stackPane.getChildren().remove(this.flashView);
        this.flashView = flashView;
        stackPane.getChildren().add(5,flashView);
    }
    public void changeDefaultView(ImageView defaultView) {
        stackPane.getChildren().remove(this.defaultView);
        this.defaultView = defaultView;
        defaultView.setPreserveRatio(true);
        defaultView.setFitWidth(100);
        stackPane.getChildren().add(3,defaultView);
    }
    public void setDefaultView(ImageView defaultView) {
        this.defaultView = defaultView;
    }
    public void changeWeaponView(ImageView weaponView) {
        stackPane.getChildren().remove(this.weaponView);
        this.weaponView = weaponView;
        weaponView.setPreserveRatio(true);
        weaponView.setFitWidth(100);
        stackPane.getChildren().add(4,weaponView);
    }
    public void setWeaponView(ImageView weaponView) {
        this.weaponView = weaponView;
    }

    public ImageView getSkySmokeImage() {
        return skySmokeImage;
    }

    public ImageView getDarkCoverImage() {
        return darkCoverImage;
    }

    public ImageView getOmenFlashImage() {
        return omenFlashImage;
    }

    public ImageView getPhoenixFlashImage() {
        return phoenixFlashImage;
    }

    public ImageView getSkyeFlashImage() {
        return skyeFlashImage;
    }

}