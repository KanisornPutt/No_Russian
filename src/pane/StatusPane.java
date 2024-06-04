package pane;

import entity.base.Agent;
import entity.base.Weapon;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class StatusPane extends GridPane {
    private Text nameStatus;
    private Text hpStatus;
    private Text flashedStatus;
    private Text smokedStatus;
    private Text molliedStatus;
    private Text weaponStatus;
    public StatusPane(){
        Text name = new Text("NAME : ");
        name.setFill(Color.WHITE);
        Text hp = new Text("HP : ");
        hp.setFill(Color.WHITE);
        Text weapon = new Text("Weapon : ");
        weapon.setFill(Color.WHITE);
        Text flashed = new Text("Flashed : ");
        flashed.setFill(Color.WHITE);
        Text smoked = new Text("Smoked : ");
        smoked.setFill(Color.WHITE);
        Text mollied = new Text("Mollied : ");
        mollied.setFill(Color.WHITE);
        nameStatus = new Text("");
        nameStatus.setFill(Color.WHITE);
        hpStatus = new Text("");
        hpStatus.setFill(Color.WHITE);
        weaponStatus = new Text("");
        weaponStatus.setFill(Color.WHITE);
        flashedStatus = new Text("");
        flashedStatus.setFill(Color.WHITE);
        smokedStatus = new Text("");
        smokedStatus.setFill(Color.WHITE);
        molliedStatus = new Text("");
        molliedStatus.setFill(Color.WHITE);
        add(name,0,0);
        add(nameStatus,1,0);
        add(hp,0,1);
        add(hpStatus,1,1);
        add(weapon,0,2);
        add(weaponStatus,1,2);
        add(flashed,0,3);
        add(flashedStatus,1,3);
        add(smoked,0,4);
        add(smokedStatus,1,4);
        add(mollied,0,5);
        add(molliedStatus,1,5);
    }
    
    public void setStatus(Agent agent) {
        setNameStatus(agent.getName());
        setFlashedStatus(agent.getFlashed());
        setHpStatus(agent.getHp());
        setSmokedStatus(agent.getSmoked());
        setMolliedStatus(agent.getMollied());
        setWeaponStatus(agent.getWeapon());
    }


    public void setNameStatus(String name) {
        this.nameStatus.setText(name);
    }


    public void setHpStatus(int hp) {
        this.hpStatus.setText(String.valueOf(hp));
    }


    public void setFlashedStatus(int flashed) {
        this.flashedStatus.setText(String.valueOf(flashed));
    }


    public void setSmokedStatus(int smoked) {
        this.smokedStatus.setText( String.valueOf(smoked));
    }


    public void setMolliedStatus(int mollied) {
        this.molliedStatus.setText(String.valueOf(mollied));
    }


    public void setWeaponStatus(Weapon weapon) {
        this.weaponStatus.setText(weapon.toString());
    }
}
