package model;

import model.fields.*;

public class GameBoard {
    protected Field[] fields;
    private Property[] properties;


    public GameBoard(String[] names, char[] types, int[] prices, String[] colors){
        int numberOfFields = names.length;
        this.fields = new Field[numberOfFields];

        for (int i=0;i<numberOfFields;i++){
            switch (types[i]){
                case 'p': this.fields[i] = new Property(names[i],i,colors[i],prices[i],types[i],-1);
                break;
                case 'c': this.fields[i] = new ChanceCardField(names[i],i,colors[i],types[i]);
                break;
                case 'j': this.fields[i] = new Jail(names[i],i,colors[i],types[i]);
                break;
                case 'v': this.fields[i] = new VisitJail(names[i],i,colors[i],types[i]);
                break;
                case 's': this.fields[i] = new Start(names[i],i,colors[i],types[i]);
                break;
                case 'f': this.fields[i] = new FreeParking(names[i],i,colors[i],types[i]);
                break;
            }
        }



    }

    public Field[] getFields() {
        return this.fields;
    }




}
