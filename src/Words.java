import java.util.Scanner;

public class Words {
    private String line;
    private String[] arr = new String[3];
    private String word;
    private int num;
    private boolean direction;
    private Scanner s = new Scanner(System.in);


    public void splitWords(){
        line = s.nextLine();
        arr = line.split( "\\s+");

        word = arr[0];
        num = Integer.parseInt(arr[1]);
        if (arr[2].equalsIgnoreCase("l")){
            direction = true;
        }
        if (arr[2].equalsIgnoreCase("r")){
            direction = false;
        }
    }


    public static void main(String[] args) {
       

    }
}
