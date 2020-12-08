public class RubiksCube {
    private char[][][] cube = new char[6][3][3];

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
        // B 면 출력
        for (int i = 0; i < cube[0].length; i++) {
            System.out.print("         ");
            for (int j = 0; j < cube[0][i].length; j++) {
                System.out.print(cube[0][i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        // W, O, G, Y 면 출력
        for(int i = 0; i < cube[0].length; i++) {
            for (int j = 1; j < cube.length - 1; j++) {
                for (int z = 0; z < cube[j][i].length; z++) {
                    System.out.print(cube[j][i][z] + " ");
                }
                System.out.print("   ");
            }
            System.out.println();
        }
        System.out.println();

        // R 면 출력
        for (int i = 0; i < cube[5].length; i++) {
            System.out.print("         ");
            for (int j = 0; j < cube[5][i].length; j++) {
                System.out.print(cube[5][i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RubiksCube r = new RubiksCube();
        r.getCube();
        r.printCube();

    }
}
