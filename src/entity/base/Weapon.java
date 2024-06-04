package entity.base;

import javafx.scene.image.ImageView;

public abstract class Weapon {
    protected String name;
    protected String upgradeName;
    protected int damage;
    protected int criticalRate;
    protected int criticalDamage;
    protected int upgradePrice;
    protected ImageView post1Image;
    protected String post1ImageName;
    protected ImageView post1FlipImage;
    protected String post1FlipImageName;
    protected ImageView post2Image;
    protected String post2ImageName;
    protected ImageView post2FlipImage;
    protected String post2FlipImageName;
    protected Weapon(String name, int damage, int criticalDamage, int criticalRate, int upgradePrice ) {
        this.name = name;
        this.damage = damage;
        this.criticalDamage = criticalDamage;
        this.criticalRate = criticalRate;
        this.upgradePrice = upgradePrice;
    }

    public String toString() {
        return name;
    }
    public int getDamage() {
        return damage;
    }

    public int getUpgradePrice() {
        return upgradePrice;
    }

    public String getUpgradeName() {
        return upgradeName;
    }

    public int getCriticalDamage() {
        return criticalDamage;
    }

    public ImageView getPost1Image() {
        return post1Image;
    }

    public String getPost1ImageName() {
        return post1ImageName;
    }

    public ImageView getPost1FlipImage() {
        return post1FlipImage;
    }

    public String getPost1FlipImageName() {
        return post1FlipImageName;
    }

    public ImageView getPost2Image() {
        return post2Image;
    }

    public ImageView getPost2FlipImage() {
        return post2FlipImage;
    }
}
