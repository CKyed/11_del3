package controller;

import java.util.ArrayList;

public class GameController {

    private BoardController boardController;



    private PlayerController playerController;

    public GameController() {
        this.boardController = new BoardController();
    }

    public BoardController getBoardController() {
        return boardController;
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

}

