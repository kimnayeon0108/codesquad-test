import java.util.Scanner;
import java.util.StringTokenizer;

public class FlatCube {
    private final char[][] cube = {{'R', 'R', 'W'},
            {'G', 'C', 'W'},
            {'G', 'B', 'B'}};
    private String input;
    private boolean direction;
    private char a;
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


        for (int i = 0; i < input.length(); i++) {
            System.out.println();

            if (input.charAt(i) == '\'') {
                continue;
            }
            if (input.charAt(i + 1) != '\'') {  //Todo: i + 1 때문에 indexOutOfBounds 에러뜸
                direction = true;
            }
            if (input.charAt(i + 1) == '\'') {
                direction = false;
            }
            a = input.charAt(i);
            System.out.println(a);
            move(a);
        }
    }

    private void move(char a) {

        if (direction && a == 'U') {
            moveL(0);
        }
        if (!direction && a == 'U') {
            moveR(0);
        }

        if (direction && a == 'B') {
            moveR(2);
        }
        if (!direction && a == 'B') {
            moveL(2);
        }

        if (direction && a == 'R') {
            moveU(2);
        }
        if (!direction && a == 'R') {
            moveD(2);
        }

        if (direction && a == 'L') {
            moveD(0);
        }
        if (!direction && a == 'L') {
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
