package controller;

public class GameController {

    private BoardController boardController;



    private PlayerController playerController;

    public GameController() {
        this.boardController = new BoardController();
    }

    public BoardController getBoardController() {
        return boardController;
    }



    public void createPlayers(String[] playerNames){

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

}

