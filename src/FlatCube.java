import java.util.Arrays;
import java.util.Scanner;

public class FlatCube {
    private char[][] cube = {{'R', 'R', 'W'},
                        {'G', 'C', 'W'},
                        {'G', 'B', 'B'}};
    private String input;
    private Scanner s = new Scanner(System.in);

    public FlatCube(){
        printCube();
        move();
    }

    private void printCube(){
        for(int i = 0; i < cube.length; i++){
            for(int j = 0; j < cube[i].length; j++){
                System.out.print(cube[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void move(){
        System.out.print("CUBE> ");
        input = s.nextLine();
        if(input.equalsIgnoreCase("U")){
            moveUpL();
        }

        printCube();
    }

    private void moveUpL() {
        char temp = cube[0][0];

        for(int i = 0; i < cube.length - 1; i++){
            cube[0][i] = cube[0][i + 1];
        }
        cube[0][2] = temp;
    }

    public static void main(String[] args) {
        FlatCube fc = new FlatCube();
    }
}
