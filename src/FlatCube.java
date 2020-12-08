import java.util.Scanner;

public class FlatCube {
    private final char[][] cube = {{'R', 'R', 'W'},
            {'G', 'C', 'W'},
            {'G', 'B', 'B'}};
    private String input;
    private int alphabet;
    private String[] alphaArr;

    private final Scanner s = new Scanner(System.in);

    public FlatCube() {
        printCube();
        getInput();
    }

    private void printCube() {
        for (int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube[i].length; j++) {
                System.out.print(cube[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void getInput() {
        System.out.print("\nCUBE> ");
        input = s.nextLine();

        getAlphaNum();
        alphaArr = new String[alphabet];

        if (input.equals("Q")) {
            System.out.println("Bye Bye~");
            return;
        }
        getAlphaArr();

    }

    private void getAlphaArr() {
        for (int i = 0; i < input.length(); i++) {
            // input의 마지막 글자가 아니면, 다음글자에 /이 있는지 확인하기
            if (i != input.length() - 1 || input.charAt(i) != '\'') {
                int j = 0;

                if (input.charAt(i + 1) != '\'') {
                    alphaArr[j] = input.substring(i, i + 1);
                }
                if (input.charAt(i + 1) == '\'') {
                    alphaArr[j] = input.substring(i, i + 2);
                }
                j++;
            }
        }
    }

    private void getAlphaNum() {

        alphabet = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'U' || input.charAt(i) == 'L'
                    || input.charAt(i) == 'R' || input.charAt(i) == 'B') {
                alphabet++;
            }
        }
    }


    private void move(String a) {

        if (a.equals("U")) {
            moveL(0);
        }
        if (a.equals("U'")) {
            moveR(0);
        }
        if (a.equals("B")) {
            moveR(2);
        }
        if (a.equals("B'")) {
            moveL(2);
        }
        if (a.equals("R")) {
            moveU(2);
        }
        if (a.equals("R'")) {
            moveD(2);
        }
        if (a.equals("L")) {
            moveD(0);
        }
        if (a.equals("L'")) {
            moveU(0);
        }

        printCube();
    }

    private void moveL(int idx) {
        char temp = cube[idx][0];

        for (int i = 0; i < cube.length - 1; i++) {
            cube[idx][i] = cube[idx][i + 1];
        }
        cube[idx][cube.length - 1] = temp;
    }

    private void moveR(int idx) {
        char temp = cube[idx][cube[idx].length - 1];

        for (int i = cube[idx].length - 1; i > 0; i--) {
            cube[idx][i] = cube[idx][i - 1];
        }
        cube[idx][0] = temp;
    }

    private void moveU(int idx) {
        char temp = cube[0][idx];

        for (int i = 0; i < cube.length - 1; i++) {
            cube[i][idx] = cube[i + 1][idx];
        }
        cube[cube.length - 1][idx] = temp;
    }

    private void moveD(int idx) {
        char temp = cube[cube.length - 1][idx];
        for (int i = cube.length - 1; i > 0; i--) {
            cube[i][idx] = cube[i - 1][idx];
        }
        cube[0][idx] = temp;

    }


    public static void main(String[] args) {
        FlatCube fc = new FlatCube();

    }
}
