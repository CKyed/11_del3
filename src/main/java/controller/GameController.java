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
        int numberOfFields = boardController.getGameBoard().getFields().length;
        int oldFieldId = playerController.getPlayerFieldId(playerId);
        int newFieldId = (oldFieldId + dieRoll)%numberOfFields;
        playerController.setPlayerFieldId(playerId,newFieldId);

        return newFieldId;
    }

}

