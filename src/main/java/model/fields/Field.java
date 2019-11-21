package model.fields;

public abstract class Field {
    private String name;
    private int index;
    private String color;
    private char type;

    public Field(String name, int index,String color, char type){
        this.name=name;
        this.index=index;
        this.color=color;
        this.type=type;
    }

    public String getColor() {
        return color;
    }

    public char getType() {
        return type;
    }

    public String getName(){
        return this.name;
    }

}
