package logic;

import application.Main;
import entity.agent.Brimstone;
import entity.agent.Omen;
import entity.agent.Phoenix;
import entity.agent.Skye;
import entity.base.Agent;
import entity.base.Skill;
import entity.skills.Flash;
import entity.skills.Heal;
import entity.skills.Molly;
import entity.skills.Smoke;
import pane.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameLogic {
    private List<Agent> allAgents;
    private List<AgentInGame> allAgentsInGame = new ArrayList<>();
    private int player1Credits;
    private int player2Credits;
    private List<Integer> turns;
    private SixArrayPane versusPane;
    private StatusActionPane statusActionPane;
    private static GameLogic instance = null;
    private AgentInGameP1Pane p1,p2,p3;
    private AgentInGameP2Pane p4,p5,p6;
    private Agent currentAgent;
    private int currentTurn;
    private int currentAgentTurn;
    private boolean gameOver;
    private MoneyTurnPane moneyTurnPane;
    private int turnCount;
    private int playerWin;


    public GameLogic(){
        this.versusPane = new SixArrayPane();
        this.allAgents = new ArrayList<>();
        this.turns = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            turns.add(i);
        }
        player1Credits = 800;
        player2Credits = 800;
        shuffleTurns();
        gameOver = false;
        turnCount = 0;
        playerWin = 0;
    }
    public void createMoneyTurnPane(){
        this.moneyTurnPane = new MoneyTurnPane();
    }

    public static GameLogic getInstance() {
        if(instance == null){
            instance = new GameLogic();
        }
        return instance;
    }
    public void createStatusActionPane(){
        if(statusActionPane == null){
            setCurrentAgent(getTurns().get(0));
            allAgentsInGame.get(getTurns().get(0)).setCurrent(true);
            currentTurn = 0;
            currentAgentTurn = getTurns().get(0);
            this.statusActionPane = new StatusActionPane(allAgents.get(currentAgentTurn));
        }
    }
    public static void clearInstance(){
        instance = null;
    }
    public void addAgent(Agent agent){
        if(allAgents.size()<6){
            if (agent instanceof Phoenix)
                allAgents.add(new Phoenix());
            else if (agent instanceof Brimstone)
                allAgents.add(new Brimstone());
            else if (agent instanceof Skye)
                allAgents.add(new Skye());
            else if (agent instanceof Omen)
                allAgents.add(new Omen());
        }
    }
    public void removeLastAgent() {
        if (!allAgents.isEmpty()) {
            allAgents.remove(allAgents.size()-1);
        }
    }
    public void createAllAgent(){
        p1 = new AgentInGameP1Pane(allAgents.get(0));
        p2 = new AgentInGameP1Pane(allAgents.get(1));
        p3 = new AgentInGameP1Pane(allAgents.get(2));
        p4 = new AgentInGameP2Pane(allAgents.get(3));
        p5 = new AgentInGameP2Pane(allAgents.get(4));
        p6 = new AgentInGameP2Pane(allAgents.get(5));
        allAgentsInGame.add(p1);
        allAgentsInGame.add(p2);
        allAgentsInGame.add(p3);
        allAgentsInGame.add(p4);
        allAgentsInGame.add(p5);
        allAgentsInGame.add(p6);
    }

    public List<Integer> shuffleTurns() {
        Collections.shuffle(turns);
        return turns;
    }
    public List<Integer> getTurns(){
        return turns;
    }

    public List<Agent> getAllAgents() {
        return allAgents;
    }

    public SixArrayPane getVersusPane() {
        return versusPane;
    }

    public StatusActionPane getStatusActionPane() {
        return statusActionPane;
    }

    public AgentInGameP1Pane getP1() {
        return p1;
    }

    public AgentInGameP1Pane getP2() {
        return p2;
    }

    public AgentInGameP1Pane getP3() {
        return p3;
    }

    public AgentInGameP2Pane getP4() {
        return p4;
    }

    public AgentInGameP2Pane getP5() {
        return p5;
    }

    public AgentInGameP2Pane getP6() {
        return p6;
    }

    public int getPlayer1Credits() {
        return player1Credits;
    }

    public int getPlayer2Credits() {
        return player2Credits;
    }

    public void setCurrentAgent(int index) {
        currentAgent = allAgentsInGame.get(index).getAgent();
        currentAgentTurn = index;
        if (!currentAgent.isAlive()) {
            System.out.println("isDead");
            if (index < 3) {
                for (int i = 0; i < 3; i++) {
                    if (allAgentsInGame.get(i).getAgent().isAlive()) {
                        currentAgent = allAgentsInGame.get(i).getAgent();
                        currentAgentTurn = i;
                        return;
                    }
                }
            } else for (int i = 3; i < 6; i++) {
                if (allAgentsInGame.get(i).getAgent().isAlive()) {
                    currentAgent = allAgentsInGame.get(i).getAgent();
                    currentAgentTurn = i;
                    return;
                }
            }
        }
    }

    public Agent getCurrentAgent() {
        return allAgentsInGame.get(currentAgentTurn).getAgent();
    }

    public void nextTurn() {
        turnCount++;
        System.out.println("Next turn");
        getCurrentAgentsInGame().setCurrent(false);
        currentTurn = turns.get(turnCount%6);
        if (turnCount%6 == 0) {
            addCredit(1,1000);
            addCredit(2,1000);
            GameLogic.getInstance().shuffleTurns();
            moneyTurnPane.setWhoseTurn();
            getCurrentAgentsInGame().setCurrent(false);
            currentTurn = turns.get(turnCount%6);
        }
        update();
        if (!gameOver) {
            setCurrentAgent(currentTurn);
            getCurrentAgentsInGame().setCurrent(true);
            statusActionPane.setImage(getCurrentAgent());
            statusActionPane.getStatus().setStatus(getCurrentAgent());
            statusActionPane.setButton(getCurrentAgent());
            statusActionPane.setPlayerTurn();
            statusActionPane.resetButtonsExcept(null);
        }
        if(turnCount%6 != 0){
            moneyTurnPane.setCurrentTurn(turnCount%6);
        }
    }

    public void action(AgentInGame target) {
        boolean targetisAlive = target.getAgent().isAlive();
        if (statusActionPane.getShoot().isActive()) {
            int a = currentAgent.attack(target.getAgent());
            switch (a) {
                case 4 : statusActionPane.setOutput("Hit " + target.getAgent().getName() + " body (Dead)"); break;
                case 1 : statusActionPane.setOutput("Hit " + target.getAgent().getName() + " for " + getCurrentAgentsInGame().getAgent().getWeapon().getDamage() + " damage."); break;
                case 2 : statusActionPane.setOutput("Critical Hit !!! \n"
                        + "Hit " + target.getAgent().getName() + " for " + getCurrentAgentsInGame().getAgent().getWeapon().getCriticalDamage() + " damage."); break;
                case 0 : statusActionPane.setOutput("MISS !! \n" + "Accuracy :  " + getCurrentAgentsInGame().getAgent().getAccuracy());
                    if (target.getAgent().isSmoked()) statusActionPane.addOutput("\nTarget is smoked."); break;
            }
            nextTurn();
        } else if (statusActionPane.getSkill1().isActive()) {
            currentAgent.getSkill1().perform(target);
            statusActionPane.setOutput(skillOutput(getCurrentAgent().getSkill1(),target));
            nextTurn();
        } else if (statusActionPane.getSkill2().isActive()) {
            currentAgent.getSkill2().perform(target);
            statusActionPane.setOutput(skillOutput(getCurrentAgent().getSkill2(),target));
            nextTurn();
        }
        if (targetisAlive && !target.getAgent().isAlive()) {
            if (target == p1 || target == p2 || target == p3 ) player1Credits += 300;
            if (target == p4 || target == p5 || target == p6 ) player2Credits += 300;
            GameLogic.getInstance().getStatusActionPane().addOutput("\n" + target.getAgent().getName() + " has died.");
            moneyTurnPane.setMoney(player1Credits,player2Credits);
        }
    }

    public MoneyTurnPane getMoneyTurnPane() {
        return moneyTurnPane;
    }

    public boolean canBuy(int price) {
        return getCurrentPlayerCredit() >= price;
    }
    public boolean isGameOver(){
        return gameOver;
    }

    public AgentInGame getCurrentAgentsInGame() {
        return allAgentsInGame.get(currentAgentTurn);
    }

    public void spendCredits(int amount) {
        int player = getCurrentPlayer();
        if (player == 1) {
            player1Credits -= amount;
            moneyTurnPane.setMoney1(player1Credits);
        }
        else {
            player2Credits -= amount;
            moneyTurnPane.setMoney2(player2Credits);
        }
    }

    public void addCredit(int player ,int amount) {
        if (player == 2)
            player2Credits += amount;
        if (player == 1)
            player1Credits += amount;
    }

    public void update() {
        for (AgentInGame a : allAgentsInGame) {
            a.update();
        }
        if (!p1.getAgent().isAlive() && !p2.getAgent().isAlive() && !p3.getAgent().isAlive()) {
            playerWin = 2;
            System.out.println("Player 2 Wins !!!");
            statusActionPane.setOutput("Player 2 Wins !!!");
            gameOver = true;
        }
        if (!p4.getAgent().isAlive() && !p5.getAgent().isAlive() && !p6.getAgent().isAlive()) {
            playerWin = 1;
            System.out.println("Player 1 Wins !!!");
            statusActionPane.setOutput("Player 1 Wins !!!");
            gameOver = true;

        }
        if (gameOver) {
            Main.setGameOver(getPlayerWin());
        }
    }

    public int getCurrentPlayer() {
        if (currentAgentTurn < 3) return 1;
        return 2;
    }

    public int getCurrentPlayerCredit() {
        if (getCurrentPlayer() == 1) return player1Credits;
        else return player2Credits;
    }

    public String skillOutput(Skill skill, AgentInGame target){
        String s = new String();
        if (skill instanceof Flash) {
            s += "Flashed ";
        } else if (skill instanceof Smoke) {
            s += "Smoked ";
        } else if (skill instanceof Heal) {
            s += "Healed ";
        } else if (skill instanceof Molly) {
            s+= "Mollied ";
        }
        s += target.getAgent().getName();
        if (!target.getAgent().isAlive()) s += "(Dead)";
        s += "\nfor " +  skill.getEffect();
        if (skill instanceof Heal) s += " HP.";
        else s += " turns.";
        return s;
    }
    public int getPlayerWin() {
        return playerWin;
    }

    public List<AgentInGame> getAllAgentsInGame() {
        return allAgentsInGame;
    }
}
