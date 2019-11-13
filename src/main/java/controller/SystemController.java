package controller;

public class SystemController {
    private GameController gameController;
    private ViewController viewController;

    public SystemController(){
        this.gameController = new GameController();
        this.viewController = new ViewController(gameController.getBoardController().getNames()
                ,gameController.getBoardController().getColors()
                ,gameController.getBoardController().getPrice()
                ,gameController.getBoardController().getTypes());

    }


}
