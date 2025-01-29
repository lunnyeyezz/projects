import java.io.*;
import java.util.*;

public class project3 {
    public static void main(String[] args) throws IOException {
        int[][] charStats = new int[128][2];

        for (int i=0;i<128;i++) {
            charStats[i][0]=i;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\projects\\project3\\src\\input.txt"))) {
            int c;
            while ((c=reader.read())!=-1) {
                if (!Character.isWhitespace(c)&&c<128) {
                    charStats[c][1]++;
                }
            }
        }

        System.out.println("Statistics Sorted by Character:");
        for (int i=0;i<128;i++) {
            if (charStats[i][1]>0) {
                System.out.printf("Character: '%c', Frequency: %d\n", charStats[i][0], charStats[i][1]);
            }
        }

        Arrays.sort(charStats, (a, b) -> b[1] - a[1]);

        System.out.println("\nStatistics Sorted by Frequency:");
        for (int i=0;i<128; i++) {
            if (charStats[i][1]>0) {
                System.out.printf("Character: '%c', Frequency: %d\n", charStats[i][0], charStats[i][1]);
            }
        }
    }
}
