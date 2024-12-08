import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {
        String data = "";
        try {
            File file = new File("2024/data/Input3.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                 data = data + sc.nextLine();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(calculateAllMultiplications(data));
        System.out.println(calculateAllMultiplicationsWithoutDont(data));
    }

    /**
     * Task 1
     */
    private static int calculateAllMultiplications(String input) {
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(input);

        int result = 0;

        while (matcher.find()) {
            result += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
        }
        return result;
    }

    /**
     * Task 2
     */
    private static int calculateAllMultiplicationsWithoutDont(String input) {
        //Replaces everything between don't() and do() with empty string
        input = input.replaceAll("don't\\(\\).*?do\\(\\)|don't.*$","");

        //now we continue like task 1
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(input);

        int result = 0;

        while (matcher.find()) {
            result += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
        }
        return result;
    }
}