import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    private static final List<Integer> leftList = new ArrayList<>();
    private static final List<Integer> rightList = new ArrayList<>();

    public static void main(String[] args) {

        try {
            File file = new File("2024/data/Input1.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] splitInput = data.split(" {3}");
                leftList.add(Integer.parseInt(splitInput[0]));
                rightList.add(Integer.parseInt(splitInput[1]));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(computeTotalDistance());
        System.out.println(computeSimilarityScore());

    }
    /**
     * Task 1
     */
    public static int computeTotalDistance() {
        int totalDistance = 0;
        // Passing null to sort() sorts automatically
        leftList.sort(null);
        rightList.sort(null);

        for (int i = 0; i < leftList.size(); i++) {
            totalDistance += Math.abs(leftList.get(i) - rightList.get(i));
        }
        return totalDistance;
    }

    /**
     * Task 2
     */
    public static int computeSimilarityScore() {
        HashMap<Integer,Integer> occurrenceRight = new HashMap<>();

        for(int number : rightList) {
            if (occurrenceRight.containsKey(number)) {
                occurrenceRight.put(number, occurrenceRight.get(number) + 1);
            } else {
                occurrenceRight.put(number, 1);
            }
        }

        int similarityScore = 0;

        for (int number : leftList) {
            if (occurrenceRight.containsKey(number)) {
                similarityScore += number * occurrenceRight.get(number);
            }
        }

        return similarityScore;
    }
}