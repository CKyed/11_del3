package model.fields;

import model.fields.Field;

public class Property extends Field {
    private int price;

    public Property(String name, int index,String color ,int price) {
        super(name,index,color);
        this.price=price;
    }
}
