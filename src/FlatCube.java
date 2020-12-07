import java.util.Scanner;

public class FlatCube {
    private char[][] cube = {{'R', 'R', 'W'},
            {'G', 'C', 'W'},
            {'G', 'B', 'B'}};
    private String input;
    private boolean direction;
    private char a;
    private Scanner s = new Scanner(System.in);

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

        //Todo: '가 붙어있는 경우에 따라 setDriec 호출하기 어떻게 문자열을 나누지..?


        for (int i = 0; i < input.length(); i++){
//            setDirec(input);      //Todo: setDirec 함수 위치 설정
            direction = true;
            System.out.println();
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

        if(direction && a == 'R'){
            moveU(2);
        }
        if(!direction && a == 'R'){
            moveD(2);
        }

        if(direction && a == 'L'){
            moveD(0);
        }
        if(!direction && a == 'L'){
            moveU(0);
        }

        printCube();
    }

    private boolean setDirec(String input) {
        if (input.contains("'")) {
            direction = true;
        }
        if (input.contains("'")) {
            direction = false;
        }

        return direction;
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
