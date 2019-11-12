package model;

public class Property extends Field {
    private int price;

    public Property(String name, int index, int price) {
        super(name, index);
        this.price=price;
    }
}
