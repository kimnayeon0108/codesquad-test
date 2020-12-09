public class RubiksCube {
    private char[][][] cube = new char[6][3][3];

    private RubiksCube() {
        getCube();
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
        char[] temp = new char[3];

        for (int i = 0; i < temp.length; i++) {
            // 1면 값 temp 에 저장
            temp[i] = cube[1][i][2];

            // 5면 -> 1면으로
            cube[1][i][2] = cube[5][0][i];

            // 3면 -> 5면으로
            cube[5][0][i] = cube[3][i][0];

            // 0면 -> 3면으로
            cube[3][i][0] = cube[0][2][i];

            // temp 값 -> 0면으로
            cube[0][2][i] = temp[i];
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
        char[] temp = new char[3];
        for (int i = 0; i < temp.length; i++) {
            // 2면의 2열 temp 에 담기
            temp[i] = cube[2][i][2];
            // 5면의 2열 -> 2면의 2열로
            cube[2][i][2] = cube[5][i][2];
            // 4면의 0열 -> 5면의 2열로
            cube[5][i][2] = cube[4][i][0];
            // 0면의 2열 -> 4면의 0열로
            cube[4][i][0] = cube[0][i][2];
            // temp 값 -> 0면의 2열로
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
        for (int i = 0; i < temp.length; i++){
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

    private void moveBtoL(){
        char[] temp = new char[3];
        for(int i = 0; i < temp.length; i++){
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

    public static void main(String[] args) {
        RubiksCube r = new RubiksCube();
        r.moveBtoL();
    }
}
