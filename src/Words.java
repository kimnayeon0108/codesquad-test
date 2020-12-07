import java.util.Scanner;

public class Words {
    private String line;
    private String[] arr = new String[3];
    private String word;
    private int num;
    private boolean direction;
    private char[] wordArr = new char[word.length()];
    private Scanner s = new Scanner(System.in);

    private void splitLine(){
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

    private void getWordArr(){
        for(int i = 0; i < wordArr.length; i++){
            wordArr[i] = word.charAt(i);
        }
    }

    private void changeChar(){



    }


    public static void main(String[] args) {
        Words w = new Words();

        w.splitLine();
        w.changeChar();
        System.out.println(w.word);

    }
}
