package controller;
import gui_fields.*;
import gui_main.GUI;

import javax.swing.text.View;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
        gui.showMessage("Velkommen til Juniormatador-spillet udviklet af gruppe 11.");
    }

    //Sets the board with the information given
    public void boardSetup(String[] names, String[] colorNames, int[] prices, char[] types){
        GUI_Field[] fields = new GUI_Field[names.length];
        Color newColor=Color.WHITE;
        for (int i=0;i<names.length;i++){
            switch (types[i]){
                case 'p': //Property
                    fields[i] = new GUI_Street();
                    fields[i].setSubText("Pris: M"+prices[i] + "     Ingen ejer"); //SubText er under "Overskriften. Hvis ingen SubText metode vil der stå "SubText" på Boardet
                    break;
                case 's': //Start
                    fields[i] = new GUI_Start();
                    fields[i].setSubText("");
                    break;
                case 'c': //ChancecardField
                    fields[i] = new GUI_Chance();
                    fields[i].setSubText("");
                    break;
                case 'j': //Jail
                    fields[i] = new GUI_Jail();
                    fields[i].setSubText("Gå i fængsel");
                    break;
                case 'v'://VisitJail
                    fields[i] = new GUI_Jail();
                    fields[i].setSubText("På besøg");
                    break;
                case 'f': //FreeParking
                    fields[i] = new GUI_Refuge();
                    fields[i].setSubText("");
                    break;
            }
            for (int j=0; j<this.colors.length;j++){
                if (colorNames[i].equals(this.colorNames[j])){
                    newColor = this.colors[j];
                }
            }
            fields[i].setDescription(names[i]);
            fields[i].setBackGroundColor(newColor);
            fields[i].setTitle(names[i]);
        }
        this.guiFields = fields;
        this.gui = new GUI(fields,Color.WHITE);
        this.gui.setChanceCard("Prøv lykken");
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

        playerNames = sortWithYoungestFirst(playerNames);

        return playerNames;
    }

    private String[] sortWithYoungestFirst(String[] playerNames) {
        //Input: String[] with the unsorted player names
        //output: Sorted String[] of names in the correct order for the game.

        //Initializes an arraylist of remaining names and an array of sorted names
        ArrayList<String> remainingNames = new ArrayList<String>();
        String[] newNamesList = new String[playerNames.length];

        //Finds the youngest player and puts it first
        newNamesList[0] = gui.getUserButtonPressed("Den yngste spiller starter. Hvem er yngst?",playerNames);

        //Defines the remaining players in the arraylist remainingnames
        for (int i=0;i<playerNames.length;i++){
            if (!playerNames[i].equals(newNamesList[0])){
                remainingNames.add(playerNames[i]);
            }
        }

        gui.showMessage("Turene går på skift i retning med uret, dvs. mod venstre.");

        //Asks who sits to the left of the last added player
        String[] remainingNamesArray;
        for (int i =1;i<playerNames.length;i++){
            remainingNamesArray = remainingNames.toArray(new String[remainingNames.size()]);
            if (remainingNames.size()==1){
                newNamesList[i] = remainingNamesArray[0];
            } else {
                newNamesList[i] = gui.getUserButtonPressed("Hvem sidder til venstre for " + newNamesList[i - 1] + "?", remainingNamesArray);
                remainingNames.remove(newNamesList[i]);
            }
        }

        return newNamesList;
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

    public void displayChanceCard(String msg){
        gui.displayChanceCard(msg);
    }

    public String getUserSelection(String msg, String ... options){
        return gui.getUserSelection(msg,options);
    }

    public String getUserButtonPressed(String msg, String ... options){
        return gui.getUserButtonPressed(msg,options);
    }

    public void setNewPropertyOwner(int fieldId, int price, String ownerName){
        guiFields[fieldId].setSubText("Pris: M"+price + "     Ejes af "+ownerName+".");
    }

    public void closeGui(){
        gui.close();
    }


}
