package controller;
import model.ChanceCardDeck;

public class CCD_Controller {
    private ChanceCardDeck chanceCardDeck;

    public CCD_Controller(int numberOfPlayers){
        this.chanceCardDeck = new ChanceCardDeck(chanceCardTexts,numberOfPlayers);
        this.chanceCardDeck.shuffle();
    }

    public ChanceCardDeck getChanceCardDeck(){
        return this.chanceCardDeck;
    }

    private String[] chanceCardTexts = new String[]{
            //Move to certain destination 0-3
            "Ryk frem til START.\nModtag M2.",
            "Ryk frem til Strandpromenaden",
            "Ryk op til 5 felter frem.",
            "Ryk 1 felt frem, eller tag et chancekort mere.",

            //Free field 4-11
            "GRATIS FELT\nRyk frem til et orange felt.\nHvis det er ledigt, får du det GRATIS. Ellers skal du BETALE leje til ejeren.",
            "GRATIS FELT\nRyk frem til et orange eller grønt felt.\nHvis det er ledigt, får du det GRATIS! Ellers skal du BETALE leje til ejeren.",
            "GRATIS FELT\nRyk frem til et lyseblåt felt.\nHvis det er ledigt, får du det GRATIS! Ellers skal du BETALE leje til ejeren.",
            "GRATIS FELT\nRyk frem til Skaterparken for at lave det perfekte grind!\nHvis ingen ejer den, får du den GRATIS! Ellers skal du BETALE leje til ejeren.",
            "GRATIS FELT\nRyk frem til et rødt felt.\nHvis det er ledigt, får du det GRATIS. Ellers skal du betale leje til ejeren.",
            "GRATIS FELT\nRyk frem til et pink eller mørkeblåt felt.\nHvis det er ledigt, får du det GRATIS! Ellers skal du betale leje til ejeren.",
            "GRATIS FELT\nRyk frem til et lyseblåt eller rødt felt.\nHvis det er ledigt, får du det GRATIS! Ellers skal du betale leje til ejeren.",
            "GRATIS FELT\nRyk frem til et brunt eller gult felt.\nHvis det er ledigt, får du det GRATIS! Ellers skal du betale leje til ejeren.",

            //Money transfer 12-14
            "Du har spist for meget slik.\nBetal M2 til banken.",
            "Du har lavet alle dine lektier! Modtag M2 fra banken.",
            "Det er din fødselsdag!\n Alle giver dig M1.\nTillykke med fødselsdagen!",

            //Escape prison card 15
            "Du løslades uden omkostninger.\nBehold dette kort, indtil du får brug for det",

            //Give card to other player 16-19
            "Giv dette kort til den blå bil, og tag et chancekort mere.\n Blå bil: På din næste tur skal du køre frem til et hvilket som helst ledigt felt og købe det. Hvis der ikke er nogen ledige felter, får du et lift til start!",
            "Giv dette kort til den røde  bil, og tag et chancekort mere.\nRød bil: På din næste tur skal du køre frem til et hvilket som helst ledigt felt og købe det. Hvis der ikke er nogen ledige felter, får du et lift til start!",
            //Only when 3 or more players
            "Giv dette kort til den grønne bil, og tag et chancekort  mere.\nGrøn bil: På din næste tur skal du køre frem til et hvilket som helst ledigt felt og købe det. Hvis der ikke er nogen ledige felter, får du et lift til start!",
            //Only when 4 or more players
            "Giv dette kort til den gule bil, og tag et chancekort mere.\nGul bil:  På din næste tur skal du køre frem til et hvilket som helst ledigt felt og købe det. Hvis der ikke er nogen ledige felter, får du et lift til start!",
    };
}