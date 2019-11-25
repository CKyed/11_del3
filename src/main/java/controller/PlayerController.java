package controller;

import model.*;

public class PlayerController {
    private Player[] players;
    private int numberOfPlayers;


    public PlayerController(String[] playerNames){
        this.numberOfPlayers = playerNames.length;
        int startPoint=0;
        players = new Player[numberOfPlayers];
        switch (this.numberOfPlayers){
            case 2:
                startPoint=20;
                break;
            case 3:
                startPoint=18;
                break;
            case 4:
                startPoint=16;
                break;
        }

        for (int i=0;i<numberOfPlayers;i++){
            players[i] = new Player(playerNames[i],startPoint,i);
        }

    }


    public int[] getPlayerBalances(){
        int[] playerBalances = new int[numberOfPlayers];
        for (int i=0;i<numberOfPlayers;i++){
            playerBalances[i] = players[i].getAccountBalance();
        }
        return playerBalances;
    }

    public Player[] getPlayers(){
        return this.players;
    }

    public int getPlayerFieldId(int playerId){
        return this.players[playerId].getCurrentFieldId();
    }

    public void setPlayerFieldId(int playerId,int newPlayerFieldId){
        this.players[playerId].setCurrentFieldId(newPlayerFieldId);
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void addPointsToPlayer(int playerId, int amount){
        if (amount<0){
            players[playerId].getAccount().withdraw(amount);
        } else if (amount>0){
            players[playerId].deposit(amount);
        }


    }

    public boolean isPlayerInPrison(int playerID){
        return players[playerID].isInPrison();

    }

    public void setPlayerInPrison(int playerID,boolean inPrison){
        players[playerID].setInPrison(inPrison);
    }

    public boolean safeTransferToBank(int playerId,int amount){
        //Returns true if transfer is sucessfull. Otherwise returns false and ends game.
        boolean succes;
        if(amount<=players[playerId].getAccountBalance()){
            succes=true;
            addPointsToPlayer(playerId, -amount);
        } else {
            succes=false;
            addPointsToPlayer(playerId,-players[playerId].getAccountBalance());
        }
        return succes;
    }

    public boolean safeTransferToPlayer(int fromPlayerId, int amount, int toPlayerId){
        //Returns true if transfer is sucessfull. Otherwise returns false and ends game.

        boolean succes;
        if(players[fromPlayerId].getAccountBalance()-amount>=0){
            succes=true;
            addPointsToPlayer(fromPlayerId, -amount);
            addPointsToPlayer(toPlayerId, amount);
        } else {
            succes=false;
            int possibleAmount;
            possibleAmount =-players[fromPlayerId].getAccount().withdraw(amount);
            players[toPlayerId].getAccount().deposit(possibleAmount);
        }
        return succes;
    }


}
