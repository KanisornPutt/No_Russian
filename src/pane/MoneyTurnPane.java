package pane;

import component.NumberBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;

public class MoneyTurnPane extends HBox {
    private Text money1;
    private Text money2;
    private Text player1;
    private Text player2;
    private HBox whoseTurn;
    private NumberBox numberBox;
    public MoneyTurnPane(){
        setSpacing(20);
        player1 = new Text("Player 1\n Credits");
        player1.setFill(Color.WHITE);
        player1.setFont(Font.font(25));
        player2 = new Text("Player 2\n Credits");
        player2.setFill(Color.WHITE);
        player2.setFont(Font.font(25));
        money1 = new Text(Integer.toString(GameLogic.getInstance().getPlayer1Credits()));
        money1.setFill(Color.WHITE);
        money1.setFont(Font.font(50));
        money2 = new Text(Integer.toString(GameLogic.getInstance().getPlayer2Credits()));
        money2.setFill(Color.WHITE);
        money2.setFont(Font.font(50));
        whoseTurn = new HBox();
        getChildren().addAll(player1,money1,whoseTurn,money2,player2);
        setWhoseTurn();
    }

    public void setMoney1(int money) {
        getChildren().remove(money1);
        this.money1.setText(String.valueOf(money));
        money1.setFont(Font.font(50));
        getChildren().add(1,money1);
    }

    public void setMoney2(int money) {
        getChildren().remove(money2);
        this.money2.setText(String.valueOf(money));
        money2.setFont(Font.font(50));
        getChildren().add(3,money2);

    }

    public void setMoney(int newMoney1,int newMoney2) {
        getChildren().remove(money1);
        this.money1.setText(String.valueOf(newMoney1));
        money1.setFont(Font.font(50));
        getChildren().add(1,money1);
        getChildren().remove(money2);
        this.money2.setText(String.valueOf(newMoney2));
        money2.setFont(Font.font(50));
        getChildren().add(3,money2);
    }

    public void setWhoseTurn() {
        whoseTurn.getChildren().clear();
        for(int i = 0;i<6;i++){
            if(i != 0){
                numberBox = new NumberBox(GameLogic.getInstance().getTurns().get(i),70,70,Color.GRAY);
            }else{
                numberBox = new NumberBox(GameLogic.getInstance().getTurns().get(i),70,70,Color.GREEN);
            }
            whoseTurn.getChildren().add(numberBox);
        }
        setMoney1(GameLogic.getInstance().getPlayer1Credits());
        setMoney2(GameLogic.getInstance().getPlayer2Credits());
    }
    public void setCurrentTurn(int i){
        setMoney1(GameLogic.getInstance().getPlayer1Credits());
        setMoney2(GameLogic.getInstance().getPlayer2Credits());
        numberBox = new NumberBox(GameLogic.getInstance().getTurns().get(i),70,70,Color.GREEN);
        whoseTurn.getChildren().remove(i);
        whoseTurn.getChildren().add(i,numberBox);
        numberBox = new NumberBox(GameLogic.getInstance().getTurns().get(i-1),70,70,Color.GRAY);
        whoseTurn.getChildren().remove(i-1);
        whoseTurn.getChildren().add(i-1,numberBox);
    }
}
