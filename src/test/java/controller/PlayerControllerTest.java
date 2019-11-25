package controller;
import org.junit.Test;
import model.*;
import java.math.*;
import static org.junit.Assert.*;

public class PlayerControllerTest {

    @Test
    public void addPointsToPlayer() {
    }

    @Test
    public void safeTransferToBank() {
        String[] playerNames = {"TorbenTest","ThomasTest"};
        PlayerController pc = new PlayerController(playerNames);


        for(int i =0;i<100;i++){
            pc.getPlayers()[0].getAccount().setBalance((int)(Math.random()*1000));
            pc.safeTransferToBank(0,(int)(Math.random()*2000-1000));
            assertTrue(pc.getPlayers()[0].getAccountBalance()>=0);
        }
    }

    @Test
    public void safeTransferToPlayer() {
        String[] playerNames = {"TorbenTest","ThomasTest"};
        PlayerController pc = new PlayerController(playerNames);
        int r1;
        int r2;
        int r3;

        for(int i =0;i<100;i++){
            r1 =(int)(Math.random()*1000);
            r2 = (int)(Math.random()*1000);
            r3 = (int)(Math.random()*2000-1000);

            pc.getPlayers()[0].getAccount().setBalance(r1);
            pc.getPlayers()[1].getAccount().setBalance(r2);
            pc.safeTransferToPlayer(0,r3,1);
            assertTrue(pc.getPlayers()[1].getAccountBalance()>=0);
            assertTrue(pc.getPlayers()[0].getAccountBalance()>=0);
        }
    }
}