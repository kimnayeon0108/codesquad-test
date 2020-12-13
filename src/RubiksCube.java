import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Scanner;

public class RubiksCube {
    private char[][][] cube = new char[6][3][3];
    private char[][][] initialCube;
    private String input;
    private String[] alphaArr;
    private boolean end = false;
    private long startTime;
    private Scanner s = new Scanner(System.in);

    private RubiksCube() {
        startTime = System.currentTimeMillis();

        for (int z = 0; z < cube.length; z++) {
            getCube(z);
        }
        getInitialCube();
        
        printCube(cube);
        start();
    }

    private void getCube(int z) {
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

    private char[][][] addElement(int z, char a) {
        for (int i = 0; i < cube[z].length; i++) {
            for (int j = 0; j < cube[z][i].length; j++) {
                cube[z][i][j] = a;
            }
        }
        return cube;
    }

    private char[][][] getInitialCube(){
        initialCube = new char[6][3][3];
        for (int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube[i].length; j++) {
                for(int z = 0; z < cube[i][j].length; z++){
                    initialCube[i][j][z] = cube[i][j][z];
                }
            }
        }
        return initialCube;
    }

    private void printCube(char[][][] cube) {
        // B면 출력
        printPage(0, cube);

        // W, O, G, Y 면 출력
        for (int i = 0; i < cube[0].length; i++) {
            printFourP(i, cube);
        }
        System.out.println();

        // R면 출력
        printPage(5, cube);
    }

    private void printPage(int a, char[][][] cube) {
        for (int i = 0; i < cube[a].length; i++) {
            System.out.print("          ");
            for (int j = 0; j < cube[a][i].length; j++) {
                System.out.print(cube[a][i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printFourP(int i, char[][][] cube) {
        for (int j = 1; j < cube.length - 1; j++) {
            for (int z = 0; z < cube[j][i].length; z++) {
                System.out.print(cube[j][i][z] + " ");
            }
            System.out.print("    ");
        }
        System.out.println();
    }

    private void start() {
        int num = 0;

        System.out.println("1을 누르면 큐브가 무작위로 섞입니다.(입력예시 : 1)");
        while (true) {
            getInput(num);
            if (end) return;
            if (input.equals("1")) {
                randomMix();
                continue;
            }

            getArrSize(input);
            getAlphaArr(input);
            move(alphaArr, cube);

            num += alphaArr.length;
        }
    }

    private void getInput(int num) {
        System.out.print("CUBE> ");
        this.input = s.nextLine();

        if (input.equalsIgnoreCase("Q")) {
            finalizeProgram(num);
            end = true;
        }
    }

    private void randomMix() {
        String[] commandArr = {"F", "F'", "R", "R'", "U", "U'",
                "B", "B'", "L", "L'", "D", "D'",
                "F2", "R2", "U2", "B2", "L2", "D2"};
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            moveByInput(commandArr[rand.nextInt(18)], cube);
        }
        printCube(cube);
    }

    private void finalizeProgram(int num) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");

        long endTime = System.currentTimeMillis();
        System.out.println("경과시간: " + formatter.format(endTime - startTime));
        System.out.println("조작개수: " + num);
        System.out.println("이용해주셔서 감사합니다.");
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

    private String[] getAlphaArr(String input) {
        int j = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '\'' || input.charAt(i) == '2') {
                continue;
            }
            if (i == input.length() - 1) {
                alphaArr[j] = String.valueOf(input.charAt(i));
                continue;
            }
            // input의 마지막 글자가 아니고, 다음 글자에 " ' " 나 "2" 유무 여부에 따라 alphaArr에 값 넣기
            if (i != input.length() - 1 &&
                    (input.charAt(i + 1) != '\'' || input.charAt(i + 1) != '2')) {
                alphaArr[j] = input.substring(i, i + 1);
            }
            if (i != input.length() - 1 &&
                    (input.charAt(i + 1) == '\'' || input.charAt(i + 1) == '2')) {
                alphaArr[j] = input.substring(i, i + 2);
            }
            j++;
        }
        return alphaArr;
    }

    private void move(String[] alphaArr, char[][][] cube) {
        for (int i = 0; i < alphaArr.length; i++) {
            System.out.println();
            System.out.println(alphaArr[i]);
            if (alphaArr[i].contains("2")) {
                moveByInput(alphaArr[i].substring(0, 1), cube);
                moveByInput(alphaArr[i].substring(0, 1), cube);
            } else {
                moveByInput(alphaArr[i], cube);
            }
            printCube(cube);
        }
    }

    private void moveByInput(String s, char[][][] cube) {
        switch (s) {
            case "F":
                turnClock(2, cube);
                moveFtoL(cube);
                break;
            case "F'":
                turnAntiClock(2, cube);
                moveFtoR(cube);
                break;
            case "R":
                turnClock(3, cube);
                moveRtoL(cube);
                break;
            case "R'":
                turnAntiClock(3, cube);
                moveRtoR(cube);
                break;
            case "U":
                turnClock(0, cube);
                moveUtoL(cube);
                break;
            case "U'":
                turnAntiClock(0, cube);
                moveUtoR(cube);
                break;
            case "B":
                turnClock(4, cube);
                moveBtoR(cube);
                break;
            case "B'":
                turnAntiClock(4, cube);
                moveBtoL(cube);
                break;
            case "L":
                turnClock(1, cube);
                moveLtoR(cube);
                break;
            case "L'":
                turnAntiClock(1, cube);
                moveLtoL(cube);
                break;
            case "D":
                turnClock(5, cube);
                moveDtoR(cube);
                break;
            case "D'":
                turnAntiClock(5, cube);
                moveDtoL(cube);
                break;
        }
    }

    private void moveFtoL(char[][][] cube) {
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
    }

    private void moveFtoR(char[][][] cube) {
        char[] temp = new char[3];
        // 1면 값 temp 에 저장
        for (int i = 0; i < temp.length; i++) {
            temp[i] = cube[1][i][2];
        }
        // 0면 -> 1면으로
        for (int i = 0; i < temp.length; i++) {
            cube[1][2 - i][2] = cube[0][2][i];
        }
        // 3면 -> 0면으로
        for (int i = 0; i < temp.length; i++) {
            cube[0][2][i] = cube[3][i][0];
        }
        // 5면 -> 3면으로
        for (int i = 0; i < temp.length; i++) {
            cube[3][i][0] = cube[5][0][2 - i];
        }
        // temp 값 -> 5면으로
        for (int i = 0; i < temp.length; i++) {
            cube[5][0][i] = temp[i];
        }
    }

    private void moveRtoL(char[][][] cube) {
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
    }

    private void moveRtoR(char[][][] cube) {
        char[] temp = new char[3];
        // 2면의 2열 temp 값으로
        for (int i = 0; i < temp.length; i++) {
            temp[i] = cube[2][i][2];
        }
        // 0면의 2열 -> 2면의 2열로
        for (int i = 0; i < temp.length; i++) {
            cube[2][i][2] = cube[0][i][2];
        }
        // 4면의 0열 -> 0면의 2열로
        for (int i = 0; i < temp.length; i++) {
            cube[0][2 - i][2] = cube[4][i][0];
        }
        // 5면의 2열 -> 4면의 0열로
        for (int i = 0; i < temp.length; i++) {
            cube[4][i][0] = cube[5][2 - i][2];
        }
        // temp -> 5면의 2열로
        for (int i = 0; i < temp.length; i++) {
            cube[5][i][2] = temp[i];
        }
    }

    private void moveUtoL(char[][][] cube) {
        char[] temp = new char[3];
        // 1면의 0행 temp 값에 담기
        for (int i = 0; i < temp.length; i++) {
            temp[i] = cube[1][0][i];
        }
        // 2면의 0행 -> 1면의 0행으로
        for (int i = 0; i < temp.length; i++) {
            cube[1][0][i] = cube[2][0][i];
        }
        // 3면의 0행 -> 2면의 0행으로
        for (int i = 0; i < temp.length; i++) {
            cube[2][0][i] = cube[3][0][i];
        }
        // 4면의 0행 -> 3면의 0행으로
        for (int i = 0; i < temp.length; i++) {
            cube[3][0][i] = cube[4][0][i];
        }
        // temp -> 4면의 0행으로
        for (int i = 0; i < temp.length; i++) {
            cube[4][0][i] = temp[i];
        }
    }

    private void moveUtoR(char[][][] cube) {
        char[] temp = new char[3];
        // 1면의 0행 -> temp
        for (int i = 0; i < temp.length; i++) {
            temp[i] = cube[1][0][i];
        }
        // 4면의 0행 -> 1면의 0행
        for (int i = 0; i < temp.length; i++) {
            cube[1][0][i] = cube[4][0][i];
        }
        // 3면의 0행 -> 4면의 0행
        for (int i = 0; i < temp.length; i++) {
            cube[4][0][i] = cube[3][0][i];
        }
        // 2면의 0행 -> 3면의 0행
        for (int i = 0; i < temp.length; i++) {
            cube[3][0][i] = cube[2][0][i];
        }
        // temp -> 2면의 0행
        for (int i = 0; i < temp.length; i++) {
            cube[2][0][i] = temp[i];
        }
    }

    private void moveBtoR(char[][][] cube) {
        char[] temp = new char[3];
        // 1면의 0열 -> temp
        for (int i = 0; i < temp.length; i++) {
            temp[i] = cube[1][i][0];
        }
        // 0면의 0행 -> 1면의 0열
        for (int i = 0; i < temp.length; i++) {
            cube[1][2 - i][0] = cube[0][0][i];
        }
        // 3면의 2열 -> 0면의 0행
        for (int i = 0; i < temp.length; i++) {
            cube[0][0][i] = cube[3][i][2];
        }
        // 5면의 2행 -> 3면의 2열
        for (int i = 0; i < temp.length; i++) {
            cube[3][i][2] = cube[5][2][2 - i];
        }
        // temp -> 5면의 2행
        for (int i = 0; i < temp.length; i++) {
            cube[5][2][i] = temp[i];
        }
    }

    private void moveBtoL(char[][][] cube) {
        char[] temp = new char[3];
        // 1면의 0열 -> temp
        for (int i = 0; i < temp.length; i++) {
            temp[i] = cube[1][i][0];
        }
        // 5면의 2행 -> 1면의 0열
        for (int i = 0; i < temp.length; i++) {
            cube[1][i][0] = cube[5][2][i];
        }
        // 3면의 2열 -> 5면의 2행
        for (int i = 0; i < temp.length; i++) {
            cube[5][2][2 - i] = cube[3][i][2];
        }
        // 0면의 0행 -> 3면의 2열
        for (int i = 0; i < temp.length; i++) {
            cube[3][i][2] = cube[0][0][i];
        }
        // temp -> 0면의 0행
        for (int i = 0; i < temp.length; i++) {
            cube[0][0][2 - i] = temp[i];
        }
    }

    private void moveLtoR(char[][][] cube) {
        char[] temp = new char[3];
        // 2면의 0열 -> temp
        for (int i = 0; i < temp.length; i++) {
            temp[i] = cube[2][i][0];
        }
        // 0면의 0열 → 2면의 0열로
        for (int i = 0; i < temp.length; i++) {
            cube[2][i][0] = cube[0][i][0];
        }
        // 4면의 2열 → 0면의 0열로
        for (int i = 0; i < temp.length; i++) {
            cube[0][2 - i][0] = cube[4][i][2];
        }
        // 5면의 0열 → 4면의 2열로
        for (int i = 0; i < temp.length; i++) {
            cube[4][2 - i][2] = cube[5][i][0];
        }
        // temp → 5면의 0열
        for (int i = 0; i < temp.length; i++) {
            cube[5][i][0] = temp[i];
        }
    }

    private void moveLtoL(char[][][] cube) {
        char[] temp = new char[3];
        // 2면의 0열 → temp
        for (int i = 0; i < temp.length; i++) {
            temp[i] = cube[2][i][0];
        }
        // 5면의 0열 → 2면의 0열
        for (int i = 0; i < temp.length; i++) {
            cube[2][i][0] = cube[5][i][0];
        }
        // 4면의 2열 → 5면의 0열
        for (int i = 0; i < temp.length; i++) {
            cube[5][2 - i][0] = cube[4][i][2];
        }
        // 0면의 0열 → 4면의 2열
        for (int i = 0; i < temp.length; i++) {
            cube[4][2 - i][2] = cube[0][i][0];
        }
        // temp → 0면의 0열
        for (int i = 0; i < temp.length; i++) {
            cube[0][i][0] = temp[i];
        }
    }

    private void moveDtoR(char[][][] cube) {
        char[] temp = new char[3];
        // 1면의 2행 → temp
        for (int i = 0; i < temp.length; i++) {
            temp[i] = cube[1][2][i];
        }
        // 4면의 2행 → 1면의 2행
        for (int i = 0; i < temp.length; i++) {
            cube[1][2][i] = cube[4][2][i];
        }
        // 3면의 2행 → 4면의 2행
        for (int i = 0; i < temp.length; i++) {
            cube[4][2][i] = cube[3][2][i];
        }
        // 2면의 2행 → 3면의 2행
        for (int i = 0; i < temp.length; i++) {
            cube[3][2][i] = cube[2][2][i];
        }
        // temp → 2면의 2행
        for (int i = 0; i < temp.length; i++) {
            cube[2][2][i] = temp[i];
        }
    }

    private void moveDtoL(char[][][] cube) {
        char[] temp = new char[3];
        // 1면의 2행 → temp
        for (int i = 0; i < temp.length; i++) {
            temp[i] = cube[1][2][i];
        }
        // 2면의 2행 → 1면의 2행
        for (int i = 0; i < temp.length; i++) {
            cube[1][2][i] = cube[2][2][i];
        }
        // 3면의 2행 → 2면의 2행
        for (int i = 0; i < temp.length; i++) {
            cube[2][2][i] = cube[3][2][i];
        }
        // 4면의 2행 → 3면의 2행
        for (int i = 0; i < temp.length; i++) {
            cube[3][2][i] = cube[4][2][i];
        }
        // temp → 4면의 2행
        for (int i = 0; i < temp.length; i++) {
            cube[4][2][i] = temp[i];
        }
    }

    private void turnClock(int page, char[][][] cube) {
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

    private void turnAntiClock(int page, char[][][] cube) {
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
            // temp1 -> 0열
            cube[page][2 - i][0] = temp1[i];
            // temp2 -> 1열
            cube[page][2 - i][1] = temp2[i];
            // temp3 -> 2열
            cube[page][2 - i][2] = temp3[i];
        }
    }

    public static void main(String[] args) {
        RubiksCube r = new RubiksCube();
    }
}
