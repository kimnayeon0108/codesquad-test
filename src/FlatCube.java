import java.util.Arrays;
import java.util.Scanner;

public class FlatCube {
    private char[][] cube = {{'R', 'R', 'W'},
                        {'G', 'C', 'W'},
                        {'G', 'B', 'B'}};
    private Scanner s = new Scanner(System.in);

    public FlatCube(){
        printCube();
    }

    private void printCube(){
        for(int i = 0; i < cube.length; i++){
            for(int j = 0; j < cube[i].length; j++){
                System.out.print(cube[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        FlatCube fc = new FlatCube();
    }
}
