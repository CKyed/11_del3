package controller;
import model.*;
import model.fields.ChanceCardField;
import model.fields.Property;

import java.util.ArrayList;

public class GameController {
    private DiceCup diceCup;
    private boolean gameOver = false;
    private int startBonus=2;
    private BoardController boardController;

    private CCD_Controller ccd_controller;


    private PlayerController playerController;

    public GameController() {
        this.boardController = new BoardController();
        this.diceCup = new DiceCup(1,6);
    }

    public BoardController getBoardController() {
        return this.boardController;
    }

    public CCD_Controller getCcd_controller() {
        return ccd_controller;
    }

    public void setupPlayerController(String[] playerNames){
        this.playerController = new PlayerController(playerNames);
        ccd_controller = new CCD_Controller(playerNames.length);
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
            else{     //He doesnt have the card
                boolean canAfford =playerController.safeTransferToBank(activePlayerId,1);
                if (canAfford){
                  msg += activePlayerName + " betaler M1 i bøde og forlader fængslet.";
                } else{
                    msg+= activePlayerName + " har ikke råd til at betale bøden.";
                    gameOver=true;
                }


            }
           setPlayerInPrison(activePlayerId,false);

        }
        return msg;
    }

    public String landedOnProperty(int playerId, int fieldId,boolean free){
        //Checks who, if anyone, owns the field
        int propertyOwnerId = boardController.getPropertiesOwnedByIds()[fieldId];
        int activePlayerBalance = playerController.getPlayerBalances()[playerId];
        int propertyPrice = boardController.getGameBoard().getFields()[fieldId].getPrice();
        String propertyOwnerName="Ingen";
        if(propertyOwnerId>=0){
            propertyOwnerName = playerController.getPlayers()[propertyOwnerId].getName();
        }
        String propertyName = boardController.getGameBoard().getFields()[fieldId].getName();
        String activePlayerName = playerController.getPlayers()[playerId].getName();
        String msg="";
        boolean canAfford;


        if (playerId==propertyOwnerId){
           msg+=activePlayerName+" ejer selv " + propertyName +".";
        }
        else if(propertyOwnerId>=0){ //If it is owned
            msg+= propertyOwnerName + " ejer " + propertyName + ".\n";
            canAfford = playerController.safeTransferToPlayer(playerId,propertyPrice,propertyOwnerId);
            if (canAfford){
                msg+= activePlayerName +" betaler M" + propertyPrice + " til " +propertyOwnerName +".\n";
            } else {
                msg+= activePlayerName +" skal betale M" + propertyPrice + " til " +propertyOwnerName +", men har ikke råd.\n";
                gameOver=true;
            }

        } else if (free){ //If it is owned and the player gets it for free
            msg+= propertyOwnerName + " ejer " + propertyName + ".\n";
            msg+= activePlayerName +" får " + propertyName + " gratis på grund af sit chancekort!";
            ((Property)boardController.getGameBoard().getFields()[fieldId]).setOwnedByPlayerId(playerId);

        }else if (propertyOwnerId==-1){//If it is not owned and it is not free
            msg+= propertyOwnerName + " ejer " + propertyName + ".\n";
            canAfford = playerController.safeTransferToBank(playerId,propertyPrice);
             if (canAfford){
                msg+= activePlayerName +" betaler M" + propertyPrice + " til Banken og ejer nu "+propertyName+".\n";
                ((Property)boardController.getGameBoard().getFields()[fieldId]).setOwnedByPlayerId(playerId);

            } else {
                msg+= activePlayerName +" skal betale M" + propertyPrice + " til banken, men har ikke råd.\n";
                gameOver=true;
            }

        }

        return msg;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String returnWinnerMessage(){
        String winnerMessage="";
        int currentHighest=0;
        int[] playerBalances = playerController.getPlayerBalances();
        for (int i=0;i<getNumberOfPlayers();i++){
            if(playerBalances[i]>currentHighest){
                currentHighest=playerBalances[i];
                winnerMessage=playerController.getPlayers()[i].getName();
            } else if (playerBalances[i]==currentHighest){
                winnerMessage+="og " + playerController.getPlayers()[i].getName();
            }
        }

        winnerMessage+= " har vundet spillet med en endelig score på M" + currentHighest + ".";



        return winnerMessage;
    }





}

