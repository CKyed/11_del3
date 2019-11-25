package controller;
import model.*;
import model.fields.Field;
import model.fields.Property;

public class BoardController {
    private GameBoard gameBoard;
    private String[] names = {"START","BURGERBAREN","PIZZARIAET","CHANCE","SLIKBUTIKKEN","ISKIOSKEN",
            "PÅ BESØG I FÆNGSLET","MUSEET","BIBLIOTEKET","CHANCE","SKATEPARKEN","SVØMMEBASSINNET","GRATIS PARKERING",
            "SPILLEHALLEN","BIOGRAFEN","CHANCE","LEGETØJSBUTIKKEN","DYREHANDLEN","GÅ I FÆNGSEL","BOWLINGHALLEN","ZOOLOGISK HAVE",
            "CHANCE","VANDLANDET","STRANDPROMENADEN"};

    private char[] types = {'s','p','p','c','p','p','v','p','p','c','p','p','f','p','p','c','p','p','j','p','p','c','p','p'};
    private int[] prices = {0,1,1,0,1,1,0,2,2,0,2,2,0,3,3,0,3,3,0,4,4,0,5,5};
    private String[] colors = {"WHITE","BROWN","BROWN","WHITE","CYAN","CYAN","WHITE","MAGENTA","MAGENTA","WHITE",
            "ORANGE","ORANGE","WHITE","RED","RED","WHITE","YELLOW","YELLOW","WHITE","GREEN","GREEN","WHITE","BLUE","BLUE"};

    public BoardController(){
        this.gameBoard=new GameBoard(this.names,this.types,this.prices,this.colors);
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

    public int[] getPrices() {
        return prices;
    }

    public String[] getColors() {
        return colors;
    }

    public Field getCurrentField(int fieldId){
        return getGameBoard().getFields()[fieldId];
    }

    public int[] getPropertiesOwnedByIds(){
        int[] list = new int[24];
        for (int i=0;i<24;i++){
            if(gameBoard.getFields()[i].getType()!='p'){
                list[i]=-2;
            } else {
             list[i]=((Property)gameBoard.getFields()[i]).getOwnedByPlayerId();
            }
        }
        return list;
    }
}