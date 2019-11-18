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
            players[i] = new Player(playerNames[i],startPoint);
        }

    }


    public int[] getPlayerBalances(){
        int[] playerBalances = new int[numberOfPlayers];
        for (int i=0;i<numberOfPlayers;i++){
            playerBalances[i] = players[i].getAccountBalance();
        }
        return playerBalances;
    }

}
