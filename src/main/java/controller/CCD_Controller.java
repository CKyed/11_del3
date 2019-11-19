package controller;

import model.ChanceCardDeck;

public class CCD_Controller {
    /*
    Fileversion:0.2
    Classname: CCD_Controller

    Classfunction: controls the logic behind Chancecard

    Importent varibels:


    Importent functions:
        CCD_Controller(): generate the card deck.
     */
    private ChanceCardDeck chanceCardDeck;
    private String[] chanceCardTexts = new String[20];

    public CCD_Controller(){

        this.chanceCardDeck = new ChanceCardDeck(chanceCardTexts);
    }

    public void swap(int a, int b){


    }

}
