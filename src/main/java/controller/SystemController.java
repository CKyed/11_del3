package controller;

import java.util.ArrayList;

public class SystemController {
    /*
    Fileversion:0.2
    Classname: SystemController

    Classfunction: this class controll everything between models and view

    Importent varibels:


    Importent functions:

     */
    private GameController gameController;
    private ViewController viewController;

    public SystemController(){
        this.gameController = new GameController();
        this.viewController = new ViewController(gameController.getBoardController().getNames()
                ,gameController.getBoardController().getColors()
                ,gameController.getBoardController().getPrices()
                ,gameController.getBoardController().getTypes());

    initialize();
    }
    public void initialize(){
        //Gets the player names via the viewController
        String[] playerNames = viewController.createPlayers();

        //Creates the players in the model
        gameController.setupPlayerController(playerNames);

        //Gets the player balances and creates the guiPlayers
        int[] playerBalances = gameController.getPlayerController().getPlayerBalances();
        viewController.setupGuiPlayers(playerNames,playerBalances,0);
    }

    public void teleportPlayerCar(int playerId, int dieRoll){
        //Gets old field id
        int oldFieldId= gameController.getPlayerController().getPlayerFieldId(playerId);
        int numberOfPlayers = gameController.getPlayerController().getNumberOfPlayers();

        //Makes array of playerID's of players on oldField
        ArrayList<Integer> playersOnOldFieldId = new ArrayList<Integer>();
        for (int i=0;i<numberOfPlayers;i++){
            if (gameController.getPlayerController().getPlayerFieldId(i)==oldFieldId){
                playersOnOldFieldId.add(i);
            }

        }

        //Updates the players current fieldID
        int newFieldId = gameController.movePlayerCar(dieRoll,playerId);

        //Removes all players from old field
        viewController.removeAllPlayersFromField(oldFieldId);

        //Puts the remaining cars back again
        for (int i=0;i<playersOnOldFieldId.size();i++){
            if (playerId!=playersOnOldFieldId.get(i)){
                viewController.setPlayerOnField(oldFieldId,playersOnOldFieldId.get(i));
            }
        }

        //Moves the guiPlayer to the new position
        viewController.setPlayerOnField(newFieldId,playerId);
    }

    public void movePlayerCar(int playerId,int dieRoll){
        for(int i=0;i<dieRoll;i++){
            teleportPlayerCar(playerId,1);
            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }


    }








}
