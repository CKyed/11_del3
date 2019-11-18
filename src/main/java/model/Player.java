package model;

public class Player {
    private Account account = new Account(0); //Opretter ny spiller. Spillerens start værdi varierer efter hvor mange spiller der er i spillet
    private String name;
    private int currentFieldId;
    private int id;


    public Player(String name, int pointStart, int id){
        this.account.setBalance(pointStart);
        this.name = name;
        this.currentFieldId=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountBalance(){
         return this.account.getBalance();
    }

    public int getCurrentFieldId() {
        return currentFieldId;
    }

    public void setCurrentFieldId(int currentFieldId){
        this.currentFieldId=currentFieldId;
    }

    public int getId() {
        return id;
    }
}

