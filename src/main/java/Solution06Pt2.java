import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

public class Solution06Pt2 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./input/input06.txt"));

        int[] times = Stream.of(sc.nextLine().substring(5).split("[^\\S]\\s*[^\\S]")).filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt).toArray();
        int[] dists = Stream.of(sc.nextLine().substring(9).split("[^\\S]\\s*[^\\S]")).filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt).toArray();

        long time = 0, dist = 0;
        for (int i = 0; i < dists.length; i++) {
            time = time * (long) Math.pow(10, (long) Math.floor((long) Math.log10(times[i])) + 1) + times[i];
            dist = dist * (long) Math.pow(10, (long) Math.floor((long) Math.log10(dists[i])) + 1) + dists[i];
        }

        final long t = time;

        Function<Long, Long> func = (x) -> t * x - (x * x);

        long left = 0, right = dist;

        long x = 0;
        while (func.apply(x++) <= dist)
            ;
        x--;
        left = x;
        right = time - x; // because it is centered, same distance other side.

        //System.out.println(left + " " + right + " -> " + (right-left + 1));

        System.out.println(right - left + 1);

    }
}