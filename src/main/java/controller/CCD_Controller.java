package controller;

import model.ChanceCardDeck;

public class CCD_Controller {
    private ChanceCardDeck chanceCardDeck;
    private String[] chanceCardTexts = new String[20];

    public CCD_Controller(){
      this.chanceCardDeck = new ChanceCardDeck(chanceCardTexts);
    }

    public void swap(int a, int b){


    }

}
