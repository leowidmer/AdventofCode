import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    private static ArrayList<char[]> wordpuzzle = new ArrayList<>();
    private static long xmasCounter = 0;
    private static long x_masCounter = 0;

    public static void main(String[] args) {
        try {
            File file = new File("2024/data/Input4.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                wordpuzzle.add(sc.nextLine().toCharArray());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        countXMAS();

        System.out.println(xmasCounter);
        System.out.println(x_masCounter);

    }

    public static void countXMAS() {
        for (int y = 0; y < wordpuzzle.size(); y++) {
            for (int x = 0; x < wordpuzzle.getFirst().length; x++) {
                //Task 1
                searchHorizontal(x,y);
                searchVertical(x,y);
                searchLeftDiagonal(x,y);
                searchRightDiagonal(x,y);
                //Task 2
                searchX_MAS(x,y);
            }
        }
    }

    private static void searchVertical(int x, int y) {
        String puzzleLine = "";
        for (int i = 0; i < 4; i++) {
            if (isValidField(x,y+i)) {
                puzzleLine = puzzleLine + getChar(x,y+i);
            }
        }

        if(puzzleLine.matches("XMAS") || puzzleLine.matches("SAMX")) {
            xmasCounter++;
        }
    }

    private static void searchHorizontal(int x, int y) {
        String puzzleLine = "";
        for (int i = 0; i < 4; i++) {
            if (isValidField(x+i,y)) {
                puzzleLine = puzzleLine + getChar(x+i,y);
            }
        }

        if(puzzleLine.matches("XMAS") || puzzleLine.matches("SAMX")) {
            xmasCounter++;
        }
    }

    private static void searchRightDiagonal(int x, int y) {
        String puzzleLine = "";
        for (int i = 0; i < 4; i++) {
            if (isValidField(x+i,y+i)) {
                puzzleLine = puzzleLine + getChar(x+i,y+i);
            }
        }

        if(puzzleLine.matches("XMAS") || puzzleLine.matches("SAMX")) {
            xmasCounter++;
        }

    }

    private static void searchLeftDiagonal(int x, int y) {
        String puzzleLine = "";
        for (int i = 0; i < 4; i++) {
            if (isValidField(x-i,y+i)) {
                puzzleLine = puzzleLine + getChar(x-i,y+i);
            }
        }

        if(puzzleLine.matches("XMAS") || puzzleLine.matches("SAMX")) {
            xmasCounter++;
        }

    }

    private static void searchX_MAS(int x, int y) {
        String rightDiagonal = "";
        String leftDiagonal = "";

        if(getChar(x,y) == 'A') {
            if(isValidField(x+1,y+1) && isValidField(x-1,y-1)) {
                rightDiagonal = getChar(x+1,y+1) + "A" + getChar(x-1,y-1);
            }
            if(isValidField(x+1,y-1) && isValidField(x-1,y+1)) {
                leftDiagonal = getChar(x+1,y-1) + "A" + getChar(x-1,y+1);
            }
        }

        if((rightDiagonal.matches("MAS") || rightDiagonal.matches("SAM")) && (leftDiagonal.matches("MAS") || leftDiagonal.matches("SAM"))) {
            x_masCounter++;
        }
    }

    private static boolean isValidField(int x, int y) {
        return (x >= 0 && x < wordpuzzle.getFirst().length) && (y >= 0 && y < wordpuzzle.size());
    }

    private static char getChar(int x, int y) {
        return wordpuzzle.get(y)[x];
    }
}