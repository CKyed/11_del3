package controller;

public class SystemController {
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








}
