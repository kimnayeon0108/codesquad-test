public class RubiksCube {
    private char[][] cubeB = new char[3][3];
    private char[][] cubeW = new char[3][3];
    private char[][] cubeO = new char[3][3];
    private char[][] cubeG = new char[3][3];
    private char[][] cubeY = new char[3][3];
    private char[][] cubeR = new char[3][3];

    private void getCube(){
        addElement(cubeB,'B');
        addElement(cubeW,'W');
        addElement(cubeO,'O');
        addElement(cubeG,'G');
        addElement(cubeY,'Y');
        addElement(cubeR,'R');
    }

    private void addElement(char[][] cube, char a){
        for(int i = 0; i < cube.length; i++){
            for(int j = 0; j < cube[i].length; j++){
                cube[i][j] = a;
            }
        }
    }

    public static void main(String[] args) {
        RubiksCube r = new RubiksCube();

        r.getCube();

    }
}
