import java.util.Scanner;

public class RubiksCube {
    private char[][][] cube = new char[6][3][3];
    private String input;
    private String[] alphaArr;
    private Scanner s = new Scanner(System.in);

    private RubiksCube() {
        getCube();
        printCube();
        start();
    }

    private void start() {
        while (true) {
            System.out.print("\nCUBE> ");
            this.input = s.nextLine();

            if (input.equalsIgnoreCase("Q")) {
                System.out.println("프로그램이 종료되었습니다.");
                return;
            }

            getArrSize(this.input);
            getAlphaArr(this.input);


            for (int i = 0; i < alphaArr.length; i++) {
                System.out.println();
                System.out.println(alphaArr[i]);
                move(alphaArr[i]);
            }
        }
    }

    private void move(String s) {
        switch (s) {
            case "F":
                moveFtoL();
                break;
            case "F'":
                moveFtoR();
                break;
            case "R":
                moveRtoL();
                break;
            case "R'":
                moveRtoR();
                break;
            case "U":
                moveUtoL();
                break;
            case "U'":
                moveUtoR();
                break;
            case "B":
                moveBtoR();
                break;
            case "B'":
                moveBtoL();
                break;
            case "L":
                moveLtoR();
                break;
            case "L'":
                moveLtoL();
                break;
            case "D":
                moveDtoR();
                break;
            case "D'":
                moveDtoL();
                break;
        }
    }

    private String[] getAlphaArr(String input) {
        int j = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '\'') {
                continue;
            }
            // input의 마지막 글자가 아니고, 다음 글자에 " ' " 유무 여부에 따라 alphaArr에 값 넣기
            if (i != input.length() - 1 && input.charAt(i + 1) != '\'') {
                alphaArr[j] = input.substring(i, i + 1);
                j++;
            }
            if (i != input.length() - 1 && input.charAt(i + 1) == '\'') {
                alphaArr[j] = input.substring(i, i + 2);
                j++;
            }

            if (i == input.length() - 1) {
                alphaArr[j] = String.valueOf(input.charAt(i));
            }
        }
        return alphaArr;
    }

    private void getArrSize(String input) {
        int alphabet = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'F' || input.charAt(i) == 'R'
                    || input.charAt(i) == 'U' || input.charAt(i) == 'B'
                    || input.charAt(i) == 'L' || input.charAt(i) == 'D') {
                alphabet++;
            }
        }
        alphaArr = new String[alphabet];
    }

    private void getCube() {
        for (int z = 0; z < cube.length; z++) {
            switch (z) {
                case 0:
                    addElement(z, 'B');
                    break;
                case 1:
                    addElement(z, 'W');
                    break;
                case 2:
                    addElement(z, 'O');
                    break;
                case 3:
                    addElement(z, 'G');
                    break;
                case 4:
                    addElement(z, 'Y');
                    break;
                case 5:
                    addElement(z, 'R');
                    break;
            }
        }
    }

    private void addElement(int z, char a) {
        for (int i = 0; i < cube[z].length; i++) {
            for (int j = 0; j < cube[z][i].length; j++) {
                cube[z][i][j] = a;
            }
        }
    }

    private void printCube() {
        // B면 출력
        printPage(0);

        // W, O, G, Y 면 출력
        for (int i = 0; i < cube[0].length; i++) {
            printFourP(i);
        }
        System.out.println();

        // R면 출력
        printPage(5);
    }

    private void printPage(int a) {
        for (int i = 0; i < cube[a].length; i++) {
            System.out.print("          ");
            for (int j = 0; j < cube[a][i].length; j++) {
                System.out.print(cube[a][i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printFourP(int i) {
        for (int j = 1; j < cube.length - 1; j++) {
            for (int z = 0; z < cube[j][i].length; z++) {
                System.out.print(cube[j][i][z] + " ");
            }
            System.out.print("    ");
        }
        System.out.println();
    }

    private void moveFtoL() {
        turnClock(2);

        char[] temp = new char[3];
        // 1면 값 temp 에 저장
        for (int i = 0; i < temp.length; i++) {
            temp[i] = cube[1][i][2];
        }
        // 5면 -> 1면으로
        for (int i = 0; i < temp.length; i++) {
            cube[1][i][2] = cube[5][0][i];
        }
        // 3면 -> 5면으로
        for (int i = 0; i < temp.length; i++) {
            cube[5][0][i] = cube[3][2 - i][0];
        }
        // 0면 -> 3면으로
        for (int i = 0; i < temp.length; i++) {
            cube[3][i][0] = cube[0][2][i];
        }
        // temp 값 -> 0면으로
        for (int i = 0; i < temp.length; i++) {
            cube[0][2][2 - i] = temp[i];
        }
        printCube();
    }

    private void moveFtoR() {
        char[] temp = new char[3];

        for (int i = 0; i < temp.length; i++) {
            // 1면 값 temp 에 저장
            temp[i] = cube[1][i][2];
            // 0면 -> 1면으로
            cube[1][i][2] = cube[0][2][i];
            // 3면 -> 0면으로
            cube[0][2][i] = cube[3][i][0];
            // 5면 -> 3면으로
            cube[3][i][0] = cube[5][0][i];
            // temp 값 -> 5면으로
            cube[5][0][i] = temp[i];
        }
        printCube();
    }

    private void moveRtoL() {
        turnClock(3);
        char[] temp = new char[3];
        // 2면의 2열 temp 에 담기
        for (int i = 0; i < temp.length; i++) {
            temp[i] = cube[2][i][2];
        }
        // 5면의 2열 -> 2면의 2열로
        for (int i = 0; i < temp.length; i++) {
            cube[2][i][2] = cube[5][i][2];
        }
        // 4면의 0열 -> 5면의 2열로
        for (int i = 0; i < temp.length; i++) {
            cube[5][i][2] = cube[4][2 - i][0];
        }
        // 0면의 2열 -> 4면의 0열로
        for (int i = 0; i < temp.length; i++) {
            cube[4][i][0] = cube[0][2 - i][2];
        }
        // temp 값 -> 0면의 2열로
        for (int i = 0; i < temp.length; i++) {
            cube[0][i][2] = temp[i];
        }
        printCube();
    }

    private void moveRtoR() {
        char[] temp = new char[3];
        for (int i = 0; i < temp.length; i++) {
            // 2면의 2열 temp 값으로
            temp[i] = cube[2][i][2];
            // 0면의 2열 -> 2면의 2열로
            cube[2][i][2] = cube[0][i][2];
            // 4면의 0열 -> 0면의 2열로
            cube[0][i][2] = cube[4][i][0];
            // 5면의 2열 -> 4면의 0열로
            cube[4][i][0] = cube[5][i][2];
            // temp -> 5면의 2열로
            cube[5][i][2] = temp[i];
        }
        printCube();
    }

    private void moveUtoL() {
        char[] temp = new char[3];
        for (int i = 0; i < temp.length; i++) {
            // 1면의 0행 temp 값에 담기
            temp[i] = cube[1][0][i];
            // 2면의 0행 -> 1면의 0행으로
            cube[1][0][i] = cube[2][0][i];
            // 3면의 0행 -> 2면의 0행으로
            cube[2][0][i] = cube[3][0][i];
            // 4면의 0행 -> 3면의 0행으로
            cube[3][0][i] = cube[4][0][i];
            // temp -> 4면의 0행으로
            cube[4][0][i] = temp[i];
        }
        printCube();
    }

    private void moveUtoR() {
        char[] temp = new char[3];
        for (int i = 0; i < temp.length; i++) {
            // 1면의 0행 -> temp
            temp[i] = cube[1][0][i];
            // 4면의 0행 -> 1면의 0행
            cube[1][0][i] = cube[4][0][i];
            // 3면의 0행 -> 4면의 0행
            cube[4][0][i] = cube[3][0][i];
            // 2면의 0행 -> 3면의 0행
            cube[3][0][i] = cube[2][0][i];
            // temp -> 2면의 0행
            cube[2][0][i] = temp[i];
        }
        printCube();
    }

    private void moveBtoR() {
        char[] temp = new char[3];
        for (int i = 0; i < temp.length; i++) {
            // 1면의 0열 -> temp
            temp[i] = cube[1][i][0];
            // 0면의 0행 -> 1면의 0열
            cube[1][i][0] = cube[0][0][i];
            // 3면의 2열 -> 0면의 0행
            cube[0][0][i] = cube[3][i][2];
            // 5면의 2행 -> 3면의 2열
            cube[3][i][2] = cube[5][2][i];
            // temp -> 5면의 2행
            cube[5][2][i] = temp[i];
        }
        printCube();
    }

    private void moveBtoL() {
        char[] temp = new char[3];
        for (int i = 0; i < temp.length; i++) {
            // 1면의 0열 -> temp
            temp[i] = cube[1][i][0];
            // 5면의 2행 -> 1면의 0열
            cube[1][i][0] = cube[5][2][i];
            // 3면의 2열 -> 5면의 2행
            cube[5][2][i] = cube[3][i][2];
            // 0면의 0행 -> 3면의 2열
            cube[3][i][2] = cube[0][0][i];
            // temp -> 0면의 0행
            cube[0][0][i] = temp[i];
        }
        printCube();
    }

    private void moveLtoR() {
        char[] temp = new char[3];
        for (int i = 0; i < temp.length; i++) {
            // 2면의 0열 -> temp
            temp[i] = cube[2][i][0];
            // 0면의 0열 → 2면의 0열로
            cube[2][i][0] = cube[0][i][0];
            // 4면의 2열 → 0면의 0열로
            cube[0][i][0] = cube[4][i][2];
            // 5면의 0열 → 4면의 2열로
            cube[4][i][2] = cube[5][i][0];
            // temp → 5면의 0열
            cube[5][i][0] = temp[i];
        }
        printCube();
    }

    private void moveLtoL() {
        char[] temp = new char[3];
        for (int i = 0; i < temp.length; i++) {
            // 2면의 0열 → temp
            temp[i] = cube[2][i][0];
            // 5면의 0열 → 2면의 0열
            cube[2][i][0] = cube[5][i][0];
            // 4면의 2열 → 5면의 0열
            cube[5][i][0] = cube[4][i][2];
            // 0면의 0열 → 4면의 2열
            cube[4][i][2] = cube[0][i][0];
            // temp → 0면의 0열
            cube[0][i][0] = temp[i];
        }
        printCube();
    }

    private void moveDtoR() {
        char[] temp = new char[3];
        for (int i = 0; i < temp.length; i++) {
            // 1면의 2행 → temp
            temp[i] = cube[1][2][i];
            // 4면의 2행 → 1면의 2행
            cube[1][2][i] = cube[4][2][i];
            // 3면의 2행 → 4면의 2행
            cube[4][2][i] = cube[3][2][i];
            // 2면의 2행 → 3면의 2행
            cube[3][2][i] = cube[2][2][i];
            // temp → 2면의 2행
            cube[2][2][i] = temp[i];
        }
        printCube();
    }

    private void moveDtoL() {
        char[] temp = new char[3];
        for (int i = 0; i < temp.length; i++) {
            // 1면의 2행 → temp
            temp[i] = cube[1][2][i];
            // 2면의 2행 → 1면의 2행
            cube[1][2][i] = cube[2][2][i];
            // 3면의 2행 → 2면의 2행
            cube[2][2][i] = cube[3][2][i];
            // 4면의 2행 → 3면의 2행
            cube[3][2][i] = cube[4][2][i];
            // temp → 4면의 2행
            cube[4][2][i] = temp[i];
        }
        printCube();
    }

    private void turnClock(int page) {
        char[] temp1 = new char[3];
        char[] temp2 = new char[3];
        char[] temp3 = new char[3];
        for (int i = 0; i < cube[page].length; i++) {
            // 0행 -> temp1
            temp1[i] = cube[page][0][i];
            // 1행 -> temp2
            temp2[i] = cube[page][1][i];
            // 2행 -> temp3
            temp3[i] = cube[page][2][i];
        }

        for (int i = 0; i < cube[page].length; i++) {
            // temp1 -> 2열
            cube[page][i][2] = temp1[i];
            // temp2 -> 1열
            cube[page][i][1] = temp2[i];
            // temp3 -> 0열
            cube[page][i][0] = temp3[i];
        }
    }

    public static void main(String[] args) {
        RubiksCube r = new RubiksCube();
    }
}
