package pane;

import component.CharacterImageBox;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class SixArrayPane extends HBox {
    public SixArrayPane(){
        setSpacing(20);
        setAlignment(Pos.CENTER);
    }
    public void addToIcons(CharacterImageBox charactorImageBox){
        this.getChildren().add(charactorImageBox);
    }
    public void deleteIcons(){
        this.getChildren().remove(this.getChildren().size()-1);
    }
}
