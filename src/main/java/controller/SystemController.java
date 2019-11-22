package controller;

import java.util.ArrayList;

public class SystemController {
    private GameController gameController;
    private ViewController viewController;
    private boolean gameOver=false;

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

    public void teleportPlayerCar(int playerId, int dieRoll, boolean toJail){
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

        //Updates the active players point (maybe he passed start)
        if (!toJail){
            int updatedPlayerPoints = gameController.newBalanceAfterRoll(playerId,oldFieldId,dieRoll);
            viewController.updatePlayerBalance(playerId,updatedPlayerPoints);
        }

        //Moves the guiPlayer to the new position
        viewController.setPlayerOnField(newFieldId,playerId);


    }

    public void movePlayerCar(int playerId,int dieRoll, boolean fromJail){
        for(int i=0;i<dieRoll;i++){
            teleportPlayerCar(playerId,1, fromJail);
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

    public void playGame(){
        int activePlayerId = 0;
        int roll=0;
        int newFieldId;
        String activePlayerName;
        while (!gameOver){

            //Gets the name of the active player
            activePlayerName=gameController.getPlayerController().getPlayers()[activePlayerId].getName();

            //Displays who's turn it is
            //viewController.showMessage("Det er "+ activePlayerName + "s tur.");

            String prisonMessage = gameController.executePlayerInPrison(activePlayerId);
            if (!prisonMessage.equals("")){
                viewController.showMessage(prisonMessage);
            }


            //rolls the die
            roll= gameController.getRoll();
            viewController.showDie(roll);


            //Updates the position of the active player
            movePlayerCar(activePlayerId,roll,false);
            newFieldId = gameController.getPlayerController().getPlayerFieldId(activePlayerId);
            playTurnOnNewField(activePlayerId,newFieldId);

            //Checks if game is over


            //Gives the turn to the next player
            activePlayerId++;
            activePlayerId= activePlayerId%gameController.getNumberOfPlayers();

        }


    }


    public void playTurnOnNewField(int playerId, int newFieldId){
        //Gets name of active player
        String activePlayerName = gameController.getPlayerController().getPlayers()[playerId].getName();
        switch (gameController.getBoardController().getCurrentField(newFieldId).getType()){
            case 'p':
                landedOnProperty(playerId,newFieldId);
                break;
            case 'c':
                landedOnChanceCardField(playerId,newFieldId);
                break;
            case 'j':
                landedOnJail(playerId);
                break;
            case 'v':
                viewController.showMessage( activePlayerName+ " er på besøg i fængsel.");
                break;
            case 's':
                viewController.displayLandedOnNewField(activePlayerName,"start");
                break;
            case 'f':
                viewController.displayLandedOnNewField(activePlayerName,"gratis parkering");
                break;
        }
    }

    public void landedOnJail(int playerId){
        viewController.displayLandedOnNewField(gameController.getPlayerController().getPlayers()[playerId].getName(),"Fængslet");
        movePlayerCar(playerId,12,true);
        gameController.setPlayerInPrison(playerId,true);
        updatePlayerBalances();
    }

    public void updatePlayerBalances(){
        int newBalance;
        for (int i=0; i<gameController.getNumberOfPlayers();i++){
            newBalance = gameController.getPlayerController().getPlayerBalances()[i];
            viewController.updatePlayerBalance(i,newBalance);
        }
    }


    public void landedOnProperty(int playerId, int fieldId){
        String statusMessage = gameController.landedOnProperty(playerId,fieldId);
        viewController.showMessage(statusMessage);
        updatePlayerBalances();
        checkIfGameOver();

    }

    public void landedOnChanceCardField(int playerId,int fieldId){
        String activePlayerName = gameController.getPlayerController().getPlayers()[playerId].getName();

        //Displays the chancecard-text
        String ccText = gameController.getCcd_controller().getChanceCardDeck().getChanceCards()[0].getText();
        viewController.displayChanceCard(ccText);
        //Displays message
        viewController.showMessage(activePlayerName + " er landet på et chancekortfelt! Læs kortet i midten af brættet.");


        //Gets the id of the chanceCard
        int ccId = gameController.getCcd_controller().getChanceCardDeck().getChanceCards()[0].getId();

        //Puts the card in the bottom of the deck
        gameController.getCcd_controller().getChanceCardDeck().draw();

        String selection="";
        switch (ccId){
            case 0://Ryk frem til start
                movePlayerCar(playerId,24-fieldId,false);
                break;
            case 1://Ryk frem til standpromenaden
                movePlayerCar(playerId,23-fieldId,false);
                break;
            case 3://Ryk op til 5 felter frem
                selection = viewController.getUserSelection("Hvor mange felter vil du rykke frem?","1","2","3","4","5");
                int chosenDieRoll = Integer.parseInt(selection);
                movePlayerCar(playerId,chosenDieRoll,false);
                break;
            case 4: //Ryk 1 felt frem eller tag et nyt kort
                selection = viewController.getUserButtonPressed("Hvad vælger du?","Ryk et felt frem","Tag et chancekort mere");
                if(selection.equals("Tag et chancekort mere")){
                    landedOnChanceCardField(playerId,fieldId);
                }



                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            case 15:
                break;
            case 16:
                break;
            case 17:
                break;
            case 18:
                break;
            case 19:
                break;


        }









    }






    public void checkIfGameOver(){
        if(gameController.isGameOver()){
            String winningMessage = gameController.returnWinnerMessage();
            viewController.showMessage(winningMessage);
            gameOver=true;
        }
    }











}
