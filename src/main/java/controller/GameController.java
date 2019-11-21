package controller;
import model.*;
import model.fields.ChanceCardField;
import model.fields.Property;

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

    public String landedOnField(int playerId, int fieldId){
        //Checks who, if anyone, owns the field
        int ownedById = boardController.getPropertiesOwnedByIds()[fieldId];
        int activePlayerBalance = playerController.getPlayerBalances()[playerId];
        int propertyPrice = ((Property)boardController.getGameBoard().getFields()[fieldId]).getPrice();
        String propertyOwnerName = playerController.getPlayers()[ownedById].getName();
        String propertyName = boardController.getGameBoard().getFields()[fieldId].getName();
        String activePlayerName = playerController.getPlayers()[playerId].getName();
        String msg="";

        //If it is owned
        if(ownedById>=0){
            msg+= propertyOwnerName + " ejer " + propertyName + ".\n";
            boolean canAfford = playerController.safeTransferToBank(playerId,propertyPrice);


            //If activePlayer can afford the rent
            if(propertyPrice<=activePlayerBalance){
                msg+= activePlayerName + " betaler " + propertyPrice + " i leje til " + propertyOwnerName+".";
            } else if(propertyPrice>activePlayerBalance){
                //IKKE FÆRDIG       - her slutter spillet
            }

        } else if (ownedById==-1){//If it is not owned
            //If player can afford the property
            if(propertyPrice<= activePlayerBalance){
                ((Property)boardController.getGameBoard().getFields()[fieldId]).setOwnedByPlayerId(playerId);


            } else if(propertyPrice>activePlayerBalance) {
                //IKKE FÆRDIG       - her slutter spillet
            }



        }


    }

    public void endGame(){                     //HOVHOVHOV

    }


}

