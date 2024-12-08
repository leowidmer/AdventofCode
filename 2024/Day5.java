import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day5 {
    public static void main(String[] args) {
        List<String> updates = new ArrayList<>();
        List<String> rules = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("2024/data/Input5.txt"))) {
            String line;
            boolean isRuleSection = true;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    isRuleSection = false;
                    continue;
                }
                if (isRuleSection) {
                    rules.add(line);
                } else {
                    updates.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> middlePages = new ArrayList<>();
        for (String update : updates) {
            if (isCorrectOrder(update, rules)) {
                middlePages.add(findMiddlePage(update));
            }
        }

        int sum = middlePages.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum of middle pages: " + sum);
    }

    private static boolean isCorrectOrder(String update, List<String> rules) {
        List<Integer> pages = parsePages(update);
        for (String rule : rules) {
            String[] parts = rule.split("\\|");
            int before = Integer.parseInt(parts[0]);
            int after = Integer.parseInt(parts[1]);
            if (pages.contains(before) && pages.contains(after) && pages.indexOf(before) > pages.indexOf(after)) {
                    return false;
                }
        }
        return true;
    }

    private static List<Integer> parsePages(String update) {
        String[] parts = update.split(",");
        List<Integer> pages = new ArrayList<>();
        for (String part : parts) {
            pages.add(Integer.parseInt(part));
        }
        return pages;
    }

    private static int findMiddlePage(String update) {
        List<Integer> pages = parsePages(update);
        return pages.get(pages.size() / 2);
    }
}