package controller;
import gui_fields.*;
import gui_main.GUI;

import javax.swing.text.View;
import java.awt.*;

public class ViewController {
    private GUI gui;
    private GUI_Player[] guiPlayers;

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
                    fields[i].setSubText("M"+prices[i]);
                    fields[i].setDescription("Ledig");
                    break;
                case 's': //Start
                    fields[i] = new GUI_Start();
                    fields[i].setDescription("");
                    break;
                case 'c': //ChancecardField
                    fields[i] = new GUI_Chance();
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
        this.gui = new GUI(fields,Color.WHITE);
    }

    public String[] createPlayers(){
        String[] playerNames = {"Player1","Player2","Player3","Player4"};
        //Bare en skabelon. Den færdige funktion skal bruge GUIen til at spørge brugeren om navne på spilleren og returnere dem.

        return playerNames;
    }

}
