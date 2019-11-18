import controller.SystemController;

public class Main {

    public static void main(String[] args) {
        SystemController systemController = new SystemController();
        for (int i=0;i<14;i++){
            for (int j=0;j<4;j++){
                systemController.movePlayerCar(j,i);
            }
        }


    }
}
