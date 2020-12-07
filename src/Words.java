import java.util.Scanner;

public class Words {
    private String line;
    private String[] arr;
    private String word;
    private int num;
    private boolean direction;
    private char[] wordArr;
    private Scanner s;

    public Words() {
        this.s = new Scanner(System.in);

        while(true) {
            this.splitLine();
            this.setDirec(this.direction, this.num);
            System.out.println(String.valueOf(this.wordArr));
        }
    }

    private void splitLine() {
        System.out.print("> ");
        this.line = this.s.nextLine();
        this.arr = this.line.split("\\s+");
        this.word = this.arr[0];
        this.num = Integer.parseInt(this.arr[1]);
        if (this.arr[2].equalsIgnoreCase("l")) {
            this.direction = true;
        }

        if (this.arr[2].equalsIgnoreCase("r")) {
            this.direction = false;
        }

        this.wordArr = this.word.toCharArray();
    }

    private void setDirec(Boolean direction, int num) {
        if (num < 0) {
            num *= -1;
            direction = !direction;
        }

        for(int i = 0; i < num; ++i) {
            if (direction) {
                this.pushToL();
            }

            if (!direction) {
                this.pushToR();
            }
        }

    }

    private char[] pushToR() {
        char temp = this.wordArr[this.wordArr.length - 1];

        for(int i = this.wordArr.length - 1; i > 0; --i) {
            this.wordArr[i] = this.wordArr[i - 1];
        }

        this.wordArr[0] = temp;
        return this.wordArr;
    }

    private char[] pushToL() {
        char temp = this.wordArr[0];

        for(int i = 0; i < this.wordArr.length - 1; ++i) {
            this.wordArr[i] = this.wordArr[i + 1];
        }

        this.wordArr[this.wordArr.length - 1] = temp;
        return this.wordArr;
    }

    public static void main(String[] args) {
        new Words();
    }
}
