import controller.SystemController;

public class Main {

    public static void main(String[] args) {
        SystemController systemController;
        while (true){
            systemController = new SystemController();
            systemController.playGame();
        }
    }
}