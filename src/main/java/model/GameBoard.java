package model;

public class GameBoard {
    protected Field[] fields;

    private String[] name = {"Start", "Burgerbaren","Pizzariaet","Chance","Slikbutikken","Isbutikken","Fænngsel","Muset","Bibloteket","Chance"
        };

    public GameBoard(String[] name, char[] types, int[] prices, char[] colors){
        int numberOfFields = name.length;

        for (int i=0;i<numberOfFields;i++){
            switch (types[i]){
                case 'p': this.fields[i] = new Property(name[i],i,prices[i]);
                case 'c': this.fields[i] = new ChanceCardField(name[i],i);
                case 'j': this.fields[i] = new Jail(name[i],i);
                case 'v': this.fields[i] = new VisitJail(name[i],i);
                case 's': this.fields[i] = new Start(name[i],i);
                case 'f': this.fields[i] = new FreeParking(name[i],i);
            }
        }

    }

}
