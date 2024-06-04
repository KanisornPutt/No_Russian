package entity.base;

import entity.weapon.Classic;
import entity.weapon.Operator;
import entity.weapon.Vandal;
import javafx.scene.image.ImageView;
import logic.GameLogic;

public abstract class Agent extends BaseEntity{
    protected Weapon weapon;
    protected int flashed;
    protected int smoked;
    protected int mollied;
    protected int accuracy = 80;
    protected Skill skill1;
    protected Skill skill2;
    protected ImageView iconImage;
    protected String iconImageName;
    protected ImageView post1Image;
    protected String post1ImageName;
    protected ImageView post1FlipImage;
    protected String post1FlipImageName;
    protected ImageView post2Image;
    protected String post2ImageName;
    protected ImageView post2FlipImage;
    protected String post2FlipImageName;

    protected Agent(String name) {
        super(name, 100);
        flashed = 0;
        smoked = 0;
        mollied = 0;
        weapon = new Classic();
    }

    public void setFlashed(int flashed) {
        if (flashed < 0) flashed = 0;
        this.flashed = flashed;
    }

    public void setSmoked(int smoked) {
        if (smoked < 0) smoked = 0;
        this.smoked = smoked;
    }

    public void setMollied(int mollied) {
        if (mollied < 0) mollied = 0;
        this.mollied = mollied;
    }

    public boolean isFlashed() {
        return flashed > 0;
    }
    public boolean isSmoked() {
        return smoked > 0;
    }
    public boolean isMollied() {
        return mollied > 0;
    }

    public int attack(Agent target) {
        int chance = random.nextInt(100);
        int criticalChance = random.nextInt(100);
        if (!target.isAlive()) return  4;
        if (chance < getAccuracy() && chance < target.getVisibility()) {
            if (criticalChance < weapon.criticalRate) {
                System.out.println("Critical Hit !!!");
                System.out.println("Hit for " + weapon.criticalDamage + " damage.");
                target.setHp(target.getHp() - weapon.criticalDamage);
                return 2;
            }
            else {
                System.out.println("Hit for " + weapon.damage + " damage.");
                target.setHp(target.getHp() - weapon.damage);
                return 1;
            }

        }
        else {
            System.out.println("MISS");
            return 0;
        }
    }

    public void setHp(int hp) {
        if (hp > 100)
            hp = 100;
        super.setHp(hp);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getAccuracy() {
        int tmp = accuracy;
        if (isFlashed()) tmp = tmp/4;
        if (isSmoked()) tmp = tmp/4;
        return tmp;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getVisibility(){
        if (isSmoked()) {
            return 40;
        }
        return 100;
    }
    public Skill getSkill1() {
        return skill1;
    }

    public Skill getSkill2() {
        return skill2;
    }

    public ImageView getIconImage() {
        return iconImage;
    }

    public String getIconImageName() {
        return iconImageName;
    }

    public ImageView getPost1Image() {
        return post1Image;
    }

    public String getPost1ImageName() {
        return post1ImageName;
    }

    public int getFlashed() {
        return flashed;
    }

    public int getSmoked() {
        return smoked;
    }

    public int getMollied() {
        return mollied;
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


    public int getUpgradePrice() {
        return getWeapon().getUpgradePrice();
    }

    public void upgrade() {
        GameLogic.getInstance().spendCredits(getUpgradePrice());
        if (weapon instanceof Classic) {
            weapon = new Vandal();
        }
        else if (weapon instanceof Vandal) {
            weapon = new Operator();
        }
    }



}
