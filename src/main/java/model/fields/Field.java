package model.fields;

public abstract class Field {
    private String name;
    private int index;
    private String color;

    public Field(String name, int index,String color){
        this.name=name;
        this.index=index;
        this.color=color;
    }


}
