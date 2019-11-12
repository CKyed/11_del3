package model;

public class ChanceCardDeck {
    private ChanceCard[] chanceCards;

    public ChanceCardDeck(String[] chanceCardTexts){
        this.chanceCards = new ChanceCard[chanceCardTexts.length];

        for (int i=0;i<chanceCardTexts.length;i++){
            chanceCards[i] = new ChanceCard(chanceCardTexts[i],i);
        }
    }




}
