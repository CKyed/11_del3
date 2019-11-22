
public class test {
    public static void main(String[] args) {
    String[] test = {"hej1","hej2","hej3"};

        boolean hej = checkPlayerexsistance(test,"hej3");
        System.out.println(hej);
    }
    public static boolean checkPlayerexsistance(String[] playerName,String typedName){
        for( String test: playerName){
            if(test.equals(typedName))
                return true;
        }
        return false;
    }
}
