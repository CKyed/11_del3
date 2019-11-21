package model.fields;

import model.fields.Field;

public class Property extends Field {
    private int price;
    private int ownedByPlayerId=-1;

    public Property(String name, int index,String color ,int price, char type, int ownedByPlayerId) {
        super(name,index,color, type);
        this.price=price;
        this.ownedByPlayerId=ownedByPlayerId;
    }

    public int getOwnedByPlayerId() {
        return ownedByPlayerId;
    }

    public void setOwnedByPlayerId(int id){
        this.ownedByPlayerId=id;
    }

    public int getPrice() {
        return price;
    }


}
