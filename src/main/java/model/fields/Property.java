package model.fields;

import model.fields.Field;

public class Property extends Field {
    private int price;

    public Property(String name, int index,String color ,int price, char type) {
        super(name,index,color, type);
        this.price=price;
    }
}
