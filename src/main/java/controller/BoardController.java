package controller;
import model.*;

public class BoardController {
    private GameBoard gameBoard;
    private String[] names = {"START","BURGERBAREN","PIZZARIAET","CHANCE","SLIKBUTIKKEN","ISKIOSKEN",
            "PÅ BESØG I FÆNGSLET","MUSEET","BIBLIOTEKET","CHANCE","SKATEPARKEN","SVØMMEBASSINNET","GRATIS PARKERING",
            "SPILLEHALLEN","BIOGRAFEN","CHANCE","LEGETØJSBUTIKKEN","DYREHANDLEN","GÅ I FÆNGSEL","BOWLINGHALLEN","ZOOLOGISK HAVE",
            "CHANCE","VANDLANDET","STRANDPROMENADEN"};


    private char[] types = {'s','p','p','c','p','p','v','p','p','c','p','p','f','p','p','c','p','p','j','p','p','c','p','p'};
    private int[] price = {0,1,1,0,1,1,0,2,2,0,2,2,0,3,3,0,3,3,0,4,4,0,5,5};
    private String[] colors = {"WHITE","BROWN","BROWN","CHANCE","CYAN","CYAN","PRISON","MAGENTA","MAGENTA","CHANCE",
            "ORANGE","ORANGE","PARKING","RED","RED","CHANCE","YELLOW","YELLOW","PRISON","GREEN","GREEN","CHANCE","BLUE","BLUE"};


    public BoardController(){
        this.gameBoard=new GameBoard(names,types,price,colors);
    }


    public GameBoard getGameBoard() {
        return gameBoard;
    }


    public String[] getNames() {
        return names;
    }

    public char[] getTypes() {
        return types;
    }

    public int[] getPrice() {
        return price;
    }

    public String[] getColors() {
        return colors;
    }

}
