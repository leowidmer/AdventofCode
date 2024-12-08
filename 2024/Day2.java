import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    private static int safeReportCounter = 0;
    private static int safeReportCounterWithDampener = 0;

    public static void main(String[] args) {

        try {
            File file = new File("2024/data/Input2.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] parts = data.split(" ");
                int[] intArray = new int[parts.length];

                for (int i = 0; i < parts.length; i++) {
                    intArray[i] = Integer.parseInt(parts[i]);
                }
                countSafeReports(intArray);
                countSafeReportsWithDampener(intArray);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(safeReportCounter);
        System.out.println(safeReportCounterWithDampener);
    }

    /**
     * Task 1
     */
    private static void countSafeReports(int[] numbers) {
        boolean isSafe = false;

        //Descending
        if(numbers[0] > numbers[1] && Math.abs(numbers[0] - numbers[1]) <= 3) {
            for(int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] > numbers[i + 1] && Math.abs(numbers[i] - numbers[i + 1]) <= 3) {
                    isSafe = true;
                } else {
                    isSafe = false;
                    break;
                }
            }
        //Ascending
        } else if (numbers[0] < numbers[1] && Math.abs(numbers[0] - numbers[1]) <= 3) {
            for(int i = 0; i < numbers.length - 1; i++) {
                if( numbers[i] < numbers[i + 1] && Math.abs(numbers[i] - numbers[i + 1]) <= 3) {
                    isSafe = true;
                } else {
                    isSafe = false;
                    break;
                }
            }
        }

        if (isSafe) {
            safeReportCounter++;
        }
    }

    /**
     * Task 2
     */
    private static void countSafeReportsWithDampener(int[] numbers) {
        boolean isSafe = false;
        int errorcount = 0;
        //Descending
        if(numbers[0] >= numbers[1]) {
            for(int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] > numbers[i + 1] && Math.abs(numbers[i] - numbers[i + 1]) <= 3) {
                    isSafe = true;
                } else if(errorcount < 1) {
                    isSafe = true;
                    errorcount++;
                } else {
                    isSafe = false;
                    break;
                }
            }
            //Ascending
        } else {
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] < numbers[i + 1] && Math.abs(numbers[i] - numbers[i + 1]) <= 3) {
                    isSafe = true;
                } else if (errorcount < 1) {
                    isSafe = true;
                    errorcount++;
                } else {
                    isSafe = false;
                    break;
                }
            }
        }
        if (isSafe) {
            safeReportCounterWithDampener++;
        }
    }
}