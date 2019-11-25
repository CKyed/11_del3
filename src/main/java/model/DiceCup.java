package model;
import java.util.LinkedList;
import java.util.List;

public class DiceCup {
    private int diceNumber = 1;
    private int dieSum = 0;
    private List<Die> dieList = new LinkedList<Die>();

    public DiceCup(int diceNumber, int diceSideNumber) {
        this.diceNumber = diceNumber;
        for (int i =1; i<=diceNumber;i++) {
            dieList.add(new Die(diceSideNumber));
        }
    }

    public void rollDice() {
        int sum=0;
        for (int i=0; i < diceNumber; i++) {
            dieList.get(i).roll();
            sum = sum + dieList.get(i).getFaceValue();
        }
        this.dieSum = sum;
    }

    public int getDiceNumber() {
        return diceNumber;
    }

    public void setDiceNumber(int diceNumber) {
        this.diceNumber = diceNumber;
    }

    public int getDieSum() {
        return dieSum;
    }

    public void setDieSum(int dieSum) {
        this.dieSum = dieSum;
    }
}