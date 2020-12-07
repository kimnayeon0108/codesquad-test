import java.util.Scanner;

public class Words {
    private String line;
    private String[] arr;
    private String word;
    private int num;
    private boolean direction;
    private char[] wordArr;
    private Scanner s = new Scanner(System.in);

    public Words(){
        while(true) {
            splitLine();
            setDirec(this.direction, this.num);
            System.out.println(String.valueOf(wordArr));
        }
    }

    private void splitLine() {
        System.out.print("> ");
        line = s.nextLine();
        arr = line.split("\\s+");

        word = arr[0];
        num = Integer.parseInt(arr[1]);
        if (arr[2].equalsIgnoreCase("l")) {
            direction = true;
        }
        if (arr[2].equalsIgnoreCase("r")) {
            direction = false;
        }

        wordArr = word.toCharArray();
    }

    private void setDirec(Boolean direction, int num){
        if(num < 0){
            num = num * -1;
            direction = !direction;
        }

        for(int i = 0; i < num; i++) {
            if (direction) {
                pushToL();
            }
            if (!direction) {
                pushToR();
            }
        }
    }

    private char[] pushToR() {
        char temp = wordArr[wordArr.length - 1];

        for (int i = wordArr.length - 1; i > 0; i--) {
            wordArr[i] = wordArr[i - 1];
        }
        wordArr[0] = temp;
        return wordArr;
    }

    private char[] pushToL() {
        char temp = wordArr[0];

        for (int i = 0; i < wordArr.length - 1; i++) {
            wordArr[i] = wordArr[i + 1];
        }
        wordArr[wordArr.length - 1] = temp;

        return wordArr;
    }

    public static void main(String[] args) {
        Words w = new Words();

    }
}
