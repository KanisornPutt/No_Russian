package application;

import component.SquareButton;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import logic.GameLogic;
import pane.AgentInGame;
import pane.AgentPane;

public class Main extends Application {
    private Scene startGame;
    private VBox startGameComponent;
    private Scene endGame;
    private VBox endGameComponent;
    private static Text endGameText;
    private Scene agentSelect;
    private static BorderPane agentselectcomponent;
    private Scene inGame;
    private static BorderPane inGameComponent;
    private static Stage stage;
    private ImageView myImageView;
    private Media media;
    private MediaPlayer mediaPlayer;
    private ImageView map;
    private ImageView agentSelectImage;
    private boolean isMute = false;
    private static Thread thread;
    private static SquareButton endGameButton;
    private static Text playerWin;
    public static Font myfont;

    @Override
    public void start(Stage Primarystage) throws Exception {
        myfont = Font.loadFont(getClass().getResourceAsStream("/ARCADECLASSIC.TTF"),50 );
        stage = Primarystage;
        stage.setTitle("No Russian");
        String image_path = ClassLoader.getSystemResource("image/valo.png").toString();
        myImageView = new ImageView(new Image(image_path));
        String mapString = new String( ClassLoader.getSystemResource("image/map.png").toString());
        map = new ImageView(new Image(mapString));
        String agentSelectImageName = new String( ClassLoader.getSystemResource("image/agent.png").toString());
        agentSelectImage = new ImageView(new Image(agentSelectImageName));
        stage.getIcons().add(new Image(image_path));
        createAgentSelect();
        createStartGame();
        createEndGame();
        stage.setScene(startGame);
        stage.setResizable(false);
        stage.show();
        String mediaUrl = ClassLoader.getSystemResource("song/Die_For_U_8-bit.mp3").toString();
        media = new Media(mediaUrl);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
    }
    private Scene createStartGame(){
        startGameComponent = new VBox();
        Scene scene = new Scene(startGameComponent,1280,720, Color.BLACK);
        startGameComponent.setSpacing(100);
        startGameComponent.setAlignment(Pos.CENTER);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setSpacing(300);
        startGame = scene;
        myImageView.setLayoutX(490);
        myImageView.setLayoutY(100);
        myImageView.setPreserveRatio(true);
        myImageView.setFitWidth(300);
        //----------------start---------------------//
        SquareButton start = new SquareButton("START");
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                switchScene(agentSelect);
            }
        });
        //----------------exit---------------------//
        SquareButton exit = new SquareButton("EXIT");
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.exit(0);
            }
        });
        //----------------mute---------------------//
        String muteText = "MUTE";
        if (isMute) muteText = "UNMUTE";
        SquareButton mute = new SquareButton(muteText);
        mute.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (isMute) {
                    isMute = false;
                    mediaPlayer.setMute(false);
                    mute.setText("MUTE");
                }
                else {
                    isMute = true;
                    mute.setText("UNMUTE");
                    mediaPlayer.setMute(true);
                }
            }
        });
        hBox.setSpacing(100);
        hBox.getChildren().addAll(exit,mute,start);
        startGameComponent.getChildren().addAll(myImageView,hBox);
        return startGame;
    }

    private Scene createAgentSelect(){
        BorderPane borderPane = new BorderPane();
        HBox hBox = new HBox();
        agentselectcomponent = borderPane;
        Scene scene = new Scene(agentselectcomponent,1280,720, Color.BLACK);
        agentSelect = scene;
        Text text = new Text("Player 1\nSelect Your  Agent");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(Font.font(Main.myfont.getFamily(), 90));
        text.setFill(Color.WHITE);
        agentSelectImage.setLayoutY(0);
        agentSelectImage.setLayoutY(0);
        agentselectcomponent.getChildren().add(agentSelectImage);
        agentselectcomponent.setTop(text);
        AgentPane agentPane = new AgentPane();
        agentPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(GameLogic.getInstance().getAllAgents().size() == 3){
                    text.setText("Player 2\nSelect Your  Agent");
                } else if (GameLogic.getInstance().getAllAgents().size() == 6) {
                    text.setText("All Agent\nAre Ready");
                }
            }
        });
        agentPane.setAlignment(Pos.CENTER);
        //----------------start---------------------//
        SquareButton start = new SquareButton("START");
        start.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(GameLogic.getInstance().getAllAgents().size() == 6) start.setHovering(true);
            }
        });
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(GameLogic.getInstance().getAllAgents().size() == 6){
                    createInGame();
                    switchScene(inGame);
                }
            }
        });
        //---------------back-----------------------//
        SquareButton back = new SquareButton("BACK");
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(GameLogic.getInstance().getAllAgents().size()>0){
                    GameLogic.getInstance().removeLastAgent();
                    GameLogic.getInstance().getVersusPane().deleteIcons();
                    if(GameLogic.getInstance().getAllAgents().size()==2){
                        text.setText("Player 1\nSelect Your  Agent");
                    } else if (GameLogic.getInstance().getAllAgents().size()==5) {
                        text.setText("Player 2\nSelect Your  Agent");
                    }
                }else{
                    switchScene(startGame);
                }
            }
        });
        //---------------get_children----------------//
        hBox.getChildren().addAll(back,agentPane,start);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        agentselectcomponent.setBottom(hBox);
        BorderPane.setAlignment(text,Pos.CENTER);
        agentselectcomponent.setCenter(GameLogic.getInstance().getVersusPane());
        //agentselectcomponent.getChildren().addAll(start,back);
        return agentSelect;
    }
    private Scene createInGame(){//not finish yet
        inGameComponent = new BorderPane();
        inGame = new Scene(inGameComponent,1280,720, Color.BLACK);
        endGameButton = new SquareButton("End Game", 240, 75);
        playerWin = new Text("Player i wins !!!");
        playerWin.setFont(Font.font(Main.myfont.getFamily(), 50));
        playerWin.setFont(new Font(50));
        playerWin.setFill(Color.WHITE);
        playerWin.setVisible(false);
        endGameButton.setVisible(false);
        endGameButton.setDisable(true);
        endGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GameLogic.clearInstance();
                clear();
                switchScene(endGame);
            }
        });
        GameLogic.getInstance().createAllAgent();
        GameLogic.getInstance().createStatusActionPane();
        //------------center-------------------------------------------//
        map.setLayoutY(0);
        map.setLayoutY(0);
        playerWin.setLayoutX(480);
        playerWin.setLayoutY(330);
        endGameButton.setLayoutX(640);
        endGameButton.setLayoutY(400);
        GameLogic.getInstance().getP1().setLayoutX(140);
        GameLogic.getInstance().getP1().setLayoutY(160);
        GameLogic.getInstance().getP2().setLayoutX(100);
        GameLogic.getInstance().getP2().setLayoutY(300);
        GameLogic.getInstance().getP3().setLayoutX(140);
        GameLogic.getInstance().getP3().setLayoutY(440);
        GameLogic.getInstance().getP4().setLayoutX(920);
        GameLogic.getInstance().getP4().setLayoutY(160);
        GameLogic.getInstance().getP5().setLayoutX(960);
        GameLogic.getInstance().getP5().setLayoutY(300);
        GameLogic.getInstance().getP6().setLayoutX(920);
        GameLogic.getInstance().getP6().setLayoutY(440);
        inGameComponent.getChildren().addAll(map,GameLogic.getInstance().getP1(),GameLogic.getInstance().getP2(),GameLogic.getInstance().getP3(),GameLogic.getInstance().getP4(),GameLogic.getInstance().getP5(),GameLogic.getInstance().getP6(),endGameButton,playerWin);
        //----------------top------------------------------------------//
        GameLogic.getInstance().createMoneyTurnPane();
        inGameComponent.setTop(GameLogic.getInstance().getMoneyTurnPane());
        GameLogic.getInstance().getMoneyTurnPane().setAlignment(Pos.CENTER);
        inGameComponent.setBottom(GameLogic.getInstance().getStatusActionPane());
        GameLogic.getInstance().getStatusActionPane().setAlignment(Pos.CENTER);
        thread = new Thread(()->{
            while(!GameLogic.getInstance().isGameOver()){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 6; i++)  {
                            AgentInGame agentInGame = GameLogic.getInstance().getAllAgentsInGame().get(i);
                            if(agentInGame.getAgent().isAlive()) {
                                if ( i < 3) {
                                    agentInGame.changeDefaultView(agentInGame.getAgent().getPost2Image());
                                    agentInGame.changeWeaponView(agentInGame.getAgent().getWeapon().getPost2Image());
                                }
                                else {
                                    agentInGame.changeDefaultView(agentInGame.getAgent().getPost2FlipImage());
                                    agentInGame.changeWeaponView(agentInGame.getAgent().getWeapon().getPost2FlipImage());
                                }
                            }
                        }
                    }
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 6; i++)  {
                            AgentInGame agentInGame = GameLogic.getInstance().getAllAgentsInGame().get(i);
                            if(agentInGame.getAgent().isAlive()) {
                                if ( i < 3) {
                                    agentInGame.changeDefaultView(agentInGame.getAgent().getPost1Image());
                                    agentInGame.changeWeaponView(agentInGame.getAgent().getWeapon().getPost1Image());
                                }
                                else {
                                    agentInGame.changeDefaultView(agentInGame.getAgent().getPost1FlipImage());
                                    agentInGame.changeWeaponView(agentInGame.getAgent().getWeapon().getPost1FlipImage());
                                }
                            }
                        }
                    }
                });
            }
        });
        thread.start();
        return inGame;
    }

    private Scene createEndGame(){
        endGameComponent = new VBox();
        Scene scene = new Scene(endGameComponent,1280,720, Color.BLACK);
        endGameComponent.setSpacing(100);
        endGameComponent.setAlignment(Pos.CENTER);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setSpacing(300);
        endGame = scene;
        endGameText = new Text("Congrats to Player i !!! \n Do you want to play again?");
        endGameText.setFont(new Font(50));
        endGameText.setFill(Color.WHITE);
        SquareButton playAgain = new SquareButton(" PLAY AGAIN",330 , 75);
        //start.setLayoutX(320);
        //start.setLayoutY(550);
        playAgain.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                createStartGame();
                switchScene(startGame);
            }
        });
        //option button;
        SquareButton exit = new SquareButton("EXIT");
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.exit(0);
            }
        });
        String muteText = "MUTE";
        if (isMute) muteText = "UNMUTE";
        SquareButton mute = new SquareButton(muteText);
        mute.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (isMute) {
                    isMute = false;
                    mediaPlayer.setMute(false);
                    mute.setText("MUTE");
                }
                else {
                    isMute = true;
                    mute.setText("UNMUTE");
                    mediaPlayer.setMute(true);
                }
            }
        });
        hBox.setSpacing(100);
        hBox.getChildren().addAll(exit,mute,playAgain );
        endGameComponent.getChildren().addAll(endGameText,hBox);
        return endGame;

    }

    public static void switchScene(Scene scene){
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public static void clear(){
        thread.interrupt();
        inGameComponent.setBottom(GameLogic.getInstance().getStatusActionPane());
        GameLogic.getInstance().getAllAgentsInGame().clear();
        agentselectcomponent.setCenter(GameLogic.getInstance().getVersusPane());
    }

    public static void setGameOver(int i) {
        endGameButton.setVisible(true);
        endGameButton.setDisable(false);
        playerWin.setVisible(true);
        playerWin.setText("Player " + i + " wins !!!");
        endGameText.setText("Congratulation to Player " + i + " !!! \n Do you want to play again?");
    }
}