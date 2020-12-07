import java.util.Scanner;

public class FlatCube {
    private char[][] cube = {{'R', 'R', 'W'},
            {'G', 'C', 'W'},
            {'G', 'B', 'B'}};
    private String input;
    private boolean direction;
    private Scanner s = new Scanner(System.in);

    public FlatCube() {
        printCube();
        move();
    }

    private void printCube() {
        for (int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube[i].length; j++) {
                System.out.print(cube[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void move() {
        System.out.print("\nCUBE> ");
        input = s.nextLine();

        setDirec(input);

        if(direction && input.charAt(0) == 'U'){
            moveL(0);
        }
        if(!direction && input.charAt(0) == 'U'){
            moveR(0);
        }

        if(direction && input.charAt(0) == 'B'){
            moveL(2);
        }
        if(!direction && input.charAt(0) == 'B'){
            moveR(2);
        }

        printCube();
    }

    private boolean setDirec(String input){
        if (!input.contains("'")) {
            direction = true;
        }
        if (input.contains("'")) {
            direction = false;
        }

        if (input.charAt(0) == 'L' || input.charAt(0)== 'B'){
            direction = !direction;
        }
        return  direction;
    }

    private void moveL(int idx) {
        char temp = cube[idx][0];

        for (int i = 0; i < cube.length - 1; i++) {
            cube[idx][i] = cube[idx][i + 1];
        }
        cube[idx][2] = temp;
    }
    
    private void moveR(int idx){
        char temp = cube[idx][cube[idx].length - 1];

        for(int i = cube[idx].length - 1; i > 0; i--){
            cube[idx][i] = cube[idx][i - 1];
        }
        cube[idx][0] = temp;
    }

    private void moveU(int idx){

    }

    private void moveD(int idx){

    }

    public static void main(String[] args) {
        FlatCube fc = new FlatCube();

    }
}
