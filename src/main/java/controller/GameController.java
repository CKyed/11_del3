package controller;
import model.*;
import model.fields.ChanceCardField;

import java.util.ArrayList;

public class GameController {
    private DiceCup diceCup;

    private int startBonus=2;
    private BoardController boardController;

    private PlayerController playerController;

    public GameController() {
        this.boardController = new BoardController();
        this.diceCup = new DiceCup(1,6);
    }

    public BoardController getBoardController() {
        return this.boardController;
    }

    public void setupPlayerController(String[] playerNames){
        this.playerController = new PlayerController(playerNames);
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public int movePlayerCar(int dieRoll, int playerId){
        //Gets the number of fields on the board
        int numberOfFields = boardController.getGameBoard().getFields().length;

        //Gets old fieldID and calculates the new one
        int oldFieldId = playerController.getPlayerFieldId(playerId);
        int newFieldId = (oldFieldId + dieRoll)%numberOfFields;


        //sets player on the new field
        playerController.setPlayerFieldId(playerId,newFieldId);

        return newFieldId;
    }

    public int newBalanceAfterRoll(int playerId, int oldFieldId, int dieRoll){
        //If player passes start, adds M2 to players balance
        if (oldFieldId+dieRoll>boardController.getNames().length-1){
            this.playerController.addPointsToPlayer(playerId,startBonus);
        }

        return this.playerController.getPlayerBalances()[playerId];
    }

    public int getRoll(){
        //Rolls the die
        diceCup.rollDice();
        int roll = diceCup.getDieSum();
        return roll;


    }

    public int getNumberOfPlayers(){
        return playerController.getNumberOfPlayers();
    }

    public boolean isPlayerInPrison(int playerID){
        return playerController.isPlayerInPrison(playerID);

    }

    public void setPlayerInPrison(int playerID,boolean inPrison){
        playerController.setPlayerInPrison(playerID,inPrison);
    }

    public String executePlayerInPrison(int activePlayerId){
        //Checks if player is in prison. If true it checks if the player has a "Prison chancecard", else it withdraw 1 points from playercard
        String msg="";
        String activePlayerName =playerController.getPlayers()[activePlayerId].getName();
        if(isPlayerInPrison(activePlayerId)){
            msg = activePlayerName + " er i fængsel.\n";
            if(getPlayerController().getPlayers()[activePlayerId].isHasPrisonCard()){
                getPlayerController().getPlayers()[activePlayerId].setHasPrisonCard(false);
                msg += activePlayerName +" bruger sit løsladelseskort til at forlade fængslet uden bøde.";
            }
            else{
                getPlayerController().addPointsToPlayer(activePlayerId,-1);
                msg += activePlayerName + " betaler M1 i bøde og forlader fængslet.";

            }
           setPlayerInPrison(activePlayerId,false);

        }
        return msg;
    }


}

