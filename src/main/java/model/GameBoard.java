package model;

public class GameBoard {
    protected Field[] fields;

    private String[] name = {"START","BURGERBAREN","PIZZARIAET","CHANCE","SLIKBUTIKKEN","ISKIOSKEN",
            "PÅ BESØG I FÆNGSLET","MUSEET","BIBLIOTEKET","CHANCE","SKATEPARKEN","SVØMMEBASSINNET","GRATIS PARKERING",
            "SPILLEHALLEN","BIOGRAFEN","CHANCE","LEGETØJSBUTIKKEN","DYREHANDLEN","GÅ I FÆNGSEL","BOWLINGHALLEN","ZOOLOGISK HAVE",
            "CHANCE","VANDLANDET","STRANDPROMENADEN"};
    private char[] types = {'s','p','p','c','p','p','v','p','p','c','p','p','f','p','p','c','p','p','j','p','p','c','p','p'};
    private int[] price = {0,1,1,0,1,1,0,2,2,0,2,2,0,3,3,0,3,3,0,4,4,0,5,5};
    private String[] colors = {"START","BROWN","BROWN","CHANCE","CYAN","CYAN","PRISON","RED","RED","CHANCE",
            "ORANGE","ORANGE","PARKING","RED","RED","CHANCE","YELLOW","YELLOW","PRISON","GREEN","GREEN","CHANCE","BLUE","BLUE"};

    public GameBoard(String[] name, char[] types, int[] prices, String[] colors){
        int numberOfFields = name.length;

        for (int i=0;i<numberOfFields;i++){
            switch (types[i]){
                case 'p': this.fields[i] = new Property(name[i],i,prices[i]);
                break;
                case 'c': this.fields[i] = new ChanceCardField(name[i],i);
                break;
                case 'j': this.fields[i] = new Jail(name[i],i);
                break;
                case 'v': this.fields[i] = new VisitJail(name[i],i);
                break;
                case 's': this.fields[i] = new Start(name[i],i);
                break;
                case 'f': this.fields[i] = new FreeParking(name[i],i);
                break;
            }
        }

    }

}
