package model;

public class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public int withdraw(int amount) {
        int debt;
        if (balance-amount<0){
            debt = (balance-amount);
            balance=0;
        } else{
            debt =0;
            balance = balance - amount;
        }
        return debt;
    }

    public void deposit(int amount) {
        balance = balance + amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }



}
