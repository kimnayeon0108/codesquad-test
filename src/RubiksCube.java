public class RubiksCube {
    private char[][][] cube = new char[6][3][3];

    private void getCube() {
        for (int z = 0; z < cube.length; z++) {
            switch (z) {
                case 0:
                    addElement(z, 'B');
                    break;
                case 1:
                    addElement(z,'W');
                    break;
                case 2:
                    addElement(z,'O');
                    break;
                case 3:
                    addElement(z, 'G');
                    break;
                case 4:
                    addElement(z,'Y');
                    break;
                case 5:
                    addElement(z,'R');
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
        for(int i = 0; i<cube.length; i++){
            for(int j = 0; j < cube[i].length; j++){
                for(int z = 0; z < cube[i][j].length; z++){
                    System.out.print(cube[i][j][z] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        RubiksCube r = new RubiksCube();
        r.getCube();
        r.printCube();

    }
}
