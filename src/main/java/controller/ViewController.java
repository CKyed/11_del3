package controller;
import gui_fields.*;
import gui_main.GUI;

import javax.swing.text.View;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class ViewController {
    private GUI gui;
    private GUI_Field[] guiFields;
    private GUI_Player[] guiPlayers;
    private GUI_Car[] guiCars = {new GUI_Car(Color.BLUE, Color.BLUE, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.RED, Color.RED, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.GREEN, Color.GREEN, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.YELLOW, Color.YELLOW, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL)};




    //This is the logic for the ViewController, that translates the models colors to awt-colors
    private Color[] colors = {Color.WHITE,Color.getHSBColor((float)0.1,(float)0.75,(float)0.60),Color.CYAN,Color.MAGENTA,Color.ORANGE,Color.RED,Color.YELLOW,Color.GREEN,Color.BLUE};
    private String[] colorNames= {"WHITE","BROWN","CYAN","MAGENTA",
            "ORANGE","RED","YELLOW","GREEN","BLUE"};


    public ViewController(String[] names, String[] colorNames, int[] prices, char[] types){
        boardSetup(names,colorNames,prices,types);
    }

    //Sets the board with the information given
    public void boardSetup(String[] names, String[] colorNames, int[] prices, char[] types){
        GUI_Field[] fields = new GUI_Field[names.length];
        Color newColor=Color.WHITE;
        for (int i=0;i<names.length;i++){
            switch (types[i]){
                case 'p': //Property
                    fields[i] = new GUI_Street();
                    fields[i].setSubText("M"+prices[i]); //SubText er under "Overskriften. Hvis ingen SubText metode vil der stå "SubText" på Boardet
                    fields[i].setDescription("Ledig");
                    break;
                case 's': //Start
                    fields[i] = new GUI_Start();
                    fields[i].setSubText("");
                    fields[i].setDescription("");
                    break;
                case 'c': //ChancecardField
                    fields[i] = new GUI_Chance();
                    fields[i].setSubText("");
                    fields[i].setDescription("");
                    break;
                case 'j': //Jail
                    fields[i] = new GUI_Jail();
                    fields[i].setSubText("Gå i fængsel");
                    fields[i].setDescription("");
                    break;
                case 'v'://VisitJail
                    fields[i] = new GUI_Jail();
                    fields[i].setSubText("På besøg");
                    fields[i].setDescription("");
                    break;
                case 'f': //FreeParking
                    fields[i] = new GUI_Refuge();
                    fields[i].setSubText("");
                    fields[i].setDescription("");
                    break;
            }
            for (int j=0; j<this.colors.length;j++){
                if (colorNames[i].equals(this.colorNames[j])){
                    newColor = this.colors[j];
                }
            }

            fields[i].setBackGroundColor(newColor);
            fields[i].setTitle(names[i]);
        }
        this.guiFields = fields;
        this.gui = new GUI(fields,Color.WHITE);
    }

    public String[] createPlayers(){
        int playerSelection = 2;
        playerSelection = Integer.parseInt(gui.getUserButtonPressed("Vælg antal spillere","2","3","4"));
        String[] playerNames = new String[playerSelection];
        String input;

        for (int i=0;i<playerSelection;){
            input = gui.getUserString("Spiller "+(i+1) + " skriv dit navn");
            if(playerNames[0] != null ) {
                if (!checkPlayerexsistance(playerNames, input)) {
                    playerNames[i] = input;
                    i++;
                } else {
                    gui.showMessage("To spillere må ikke have samme navn! prøv " + input + "1");
                }
            }
            else if(playerNames[0] == null ){
                playerNames[i] = input;
                i++;
            }

        }

        return playerNames;
    }

    public void setupGuiPlayers(String[] names, int[] startPoint, int startFieldId){
        guiPlayers = new GUI_Player[names.length];
        for (int i=0;i<names.length;i++){
            guiPlayers[i] = new GUI_Player(names[i],startPoint[i],guiCars[i]);
            gui.addPlayer(guiPlayers[i]);
            setPlayerOnField(startFieldId,i);
        }

    }

    public void setPlayerOnField(int fieldId, int playerId){
        this.guiFields[fieldId].setCar(guiPlayers[playerId],true);

    }

    public void removeAllPlayersFromField(int fieldId){
        this.guiFields[fieldId].removeAllCars();
    }

    public void updatePlayerBalance(int playerId, int newBalance){
        guiPlayers[playerId].setBalance(newBalance);
    }

    public void displayLandedOnNewField(String name, String fieldName){
        gui.showMessage(name + " er landet på " + fieldName +".");
    }

    public void showDie(int die){
        gui.setDie(die);
    }

    public void showMessage(String msg){
        gui.showMessage(msg);
    }
    public static boolean checkPlayerexsistance(String[] playerName,String typedName){
        //boolean playerexsistance = Arrays.stream(playerName).anyMatch(typedName::equals);
        //return playerexsistance;
        for( int i = 0; i < playerName.length; i++){

            if(playerName[i] != null && playerName[i].equals(typedName))
                return true;
        }

        return false;

    }


}
