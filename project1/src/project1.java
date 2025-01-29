import java.io.*;
import java.util.*;

public class project1 {
    public static void main(String[] args) throws IOException {
        List<Integer> numbers = readNumbersFromFile("D:\\projects\\project1\\src\\input2.txt"); //"input1.txt" gia valid comb)

        if (numbers.size() < 7 || numbers.size() > 49) {
            System.out.println("File must contain between 7 and 49 numbers.");
            return;
        }

        Collections.sort(numbers);

        List<int[]> combinations = generateCombinations(numbers);

        List<int[]> validCombinations = new ArrayList<>();
        for (int[] combo : combinations) {
            if (isValidCombination(combo)) {
                validCombinations.add(combo);
            }
        }

        if (validCombinations.isEmpty()) {
            System.out.println("No valid comb found");
        } else {
            writeCombinationsToFile(validCombinations, "D:\\projects\\project1\\src\\output.txt");
            System.out.println("Valid comb");
        }
    }

    public static List<Integer> readNumbersFromFile(String filename) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                for (String token : tokens) {
                    int num = Integer.parseInt(token);
                    if (num >= 1 && num <= 49) {
                        numbers.add(num);
                    }
                }
            }
        }
        return numbers;
    }

    public static List<int[]> generateCombinations(List<Integer> numbers) {
        List<int[]> combinations = new ArrayList<>();
        int n = numbers.size();
        int[] temp = new int[7]; // Change combination size to 7
        generateCombinationsRecursive(numbers, combinations, temp, 0, 0, n, 7);
        return combinations;
    }

    private static void generateCombinationsRecursive(List<Integer> numbers, List<int[]> combinations,
                                                      int[] temp, int start, int index, int n, int r) {
        if (index == r) {
            combinations.add(temp.clone());
            return;
        }
        for (int i = start; i < n; i++) {
            temp[index] = numbers.get(i);
            generateCombinationsRecursive(numbers, combinations, temp, i + 1, index + 1, n, r);
        }
    }

    public static boolean isValidCombination(int[] combo) {
        return hasAtMostNEven(combo, 5) &&
                hasAtMostNOdd(combo, 5) &&
                hasAtMostNConsecutive(combo, 3) &&
                hasAtMostNSameEnding(combo, 4) &&
                hasAtMostNSameDecade(combo, 4);
    }

    public static boolean hasAtMostNOdd(int[] combo, int max) {
        int count = 0;
        for (int num : combo) {
            if (num % 2 != 0) count++;
        }
        return count <= max;
    }

    public static boolean hasAtMostNConsecutive(int[] combo, int max) {
        int consecutiveCount = 1;
        for (int i = 1; i < combo.length; i++) {
            if (combo[i] == combo[i - 1] + 1) {
                consecutiveCount++;
                if (consecutiveCount > max) return false;
            } else {
                consecutiveCount = 1;
            }
        }
        return true;
    }

    public static boolean hasAtMostNSameEnding(int[] combo, int max) {
        int[] endings = new int[10];
        for (int num : combo) {
            endings[num % 10]++;
        }
        for (int count : endings) {
            if (count > max) return false;
        }
        return true;
    }

    public static boolean hasAtMostNSameDecade(int[] combo, int max) {
        int[] decades = new int[5];
        for (int num : combo) {
            decades[(num - 1) / 10]++;
        }
        for (int count : decades) {
            if (count > max) return false;
        }
        return true;
    }

    public static boolean hasAtMostNEven(int[] combo, int max) {
        int count = 0;
        for (int num : combo) {
            if (num % 2 == 0) count++;
        }
        return count <= max;
    }

    public static void writeCombinationsToFile(List<int[]> combinations, String filename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (int[] combo : combinations) {
                bw.write(Arrays.toString(combo));
                bw.newLine();
            }
        }
    }
}
