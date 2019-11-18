package model;

public class Player {
    private Account account = new Account(0); //Opretter ny spiller. Spillerens start værdi varierer efter hvor mange spiller der er i spillet
    private String name;

    public Player(String name, int pointStart){
        this.account.setBalance(pointStart);
        this.name = name;
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

}

