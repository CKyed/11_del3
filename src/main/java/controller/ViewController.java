package controller;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
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
        Color newColor=Color.BLACK;
        for (int i=0;i<names.length;i++){
            switch (types[i]){
                case 'p':
                    fields[i] = new GUI_Street();
                    fields[i].setSubText("M"+prices[i]);
            }



            if (colorNames[i].equals("WHITE")){
                newColor = colors[i];
            } else if (colorNames[i].equals("CYAN")){
                newColor = colors[i];
            }
            fields[i].setBackGroundColor(newColor);
            fields[i].setTitle(names[i]);






        }

    }


}
