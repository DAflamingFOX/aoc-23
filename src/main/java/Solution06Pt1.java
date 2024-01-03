import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

public class Solution06Pt1 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./input/input06.txt"));

        int[] times = Stream.of(sc.nextLine().substring(5).split("[^\\S]\\s*[^\\S]")).filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt).toArray();
        int[] dists = Stream.of(sc.nextLine().substring(9).split("[^\\S]\\s*[^\\S]")).filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt).toArray();


        long waysToWin = 1;

        for (int i = 0; i < dists.length; i++) {
            int time = times[i];
            int dist = dists[i];

            Function<Integer, Integer> func = (x) -> time * x - (x * x);

            int left = 0, right = dist;

            int x = 0;
            while (func.apply(x++) <= dist)
                ;
            x--;
            left = x;
            right = time - x; // because it is centered, same distance other side.

            // System.out.println(left + " " + right + " -> " + (right-left + 1));

            waysToWin *= (right - left + 1);
        }

        System.out.println(waysToWin);



    }
}