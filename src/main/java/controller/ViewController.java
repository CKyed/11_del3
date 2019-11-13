package controller;
import gui_fields.*;
import gui_main.GUI;

import javax.swing.text.View;
import java.awt.*;

public class ViewController {
    private GUI gui;
    private GUI_Player[] guiPlayers;
    private Color[] colors = {Color.WHITE,Color.getHSBColor((float)0.1,(float)0.75,(float)0.60),Color.CYAN,Color.MAGENTA,Color.ORANGE,Color.RED,Color.YELLOW,Color.GREEN,Color.BLUE};
    private String[] colorNames= {"WHITE","BROWN","CYAN","MAGENTA",
            "ORANGE","RED","YELLOW","GREEN","BLUE"};


    public ViewController(String[] names, String[] colorNames, int[] prices, char[] types){
        GUI_Field[] fields = new GUI_Field[names.length];
        Color newColor=Color.WHITE;
        for (int i=0;i<names.length;i++){
            switch (types[i]){
                case 'p':
                    fields[i] = new GUI_Street();
                    fields[i].setSubText("M"+prices[i]);
                    break;
                case 's':
                    fields[i] = new GUI_Start();
                    break;
                case 'c':
                    fields[i] = new GUI_Chance();
                    break;
                case 'j':
                    fields[i] = new GUI_Jail();
                    break;
                case 'v':
                    fields[i] = new GUI_Jail();    //SKal ændres til besøg i fængsel
                    break;
                case 'f':
                    fields[i] = new GUI_Refuge();
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


}
