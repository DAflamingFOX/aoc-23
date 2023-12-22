import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class Solution05Pt2 {

    public static class AlminacMap {
        public String source;
        public String dest;
        List<AlminacEntry> entries = new ArrayList<>();

        public AlminacMap(String src, String dst) {
            source = src;
            dest = dst;
        }

        public long convertSourceToDest(long src) {
            for (AlminacEntry entry : entries) {
                if (entry.isWithinRange(src))
                    return entry.convertSourceToDest(src);
            }
            return src;
        }
    }

    public static class AlminacEntry {
        public long sourceRangeStart;
        public long destRangeStart;
        public long range;

        public AlminacEntry(long drs, long srs, long range) {
            sourceRangeStart = srs;
            destRangeStart = drs;
            this.range = range;
        }

        public boolean isWithinRange(long num) {
            if (sourceRangeStart <= num && num < sourceRangeStart + range)
                return true;
            return false;
        }

        public long convertSourceToDest(long source) {
            return (source - sourceRangeStart) + destRangeStart;
        }
    }

    public static Map<String, AlminacMap> alminac = new HashMap<>();

    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        Scanner scanner = new Scanner(new File("./input/input05.txt"));


        List<Long> seedInput = getSeeds(scanner.nextLine().substring(7));

        // clear blank line
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String mapStr = new String();
            String temp = scanner.nextLine();

            do {
                mapStr += temp + "\n";
                temp = scanner.nextLine();
            } while (!temp.isBlank() && scanner.hasNextLine());

            AlminacMap map = decodeMap(mapStr.trim());
            alminac.put(map.source, map);
        }

        long min = Long.MAX_VALUE;
        long count = 0;
        for (int i = 0; i < seedInput.size()
        ; i += 2) {
            long start = seedInput.get(i);
            long range = seedInput.get(i + 1);
            //System.out.println("Searching " + range + " seeds:");
            while (range >= 0) {
                long val = seedToLocation(start + range--);
                if (val < min)
                    min = val;
                count++;
                if (count % 250_000_000 == 0)
                    System.out.println("Searched " + count + " seeds, current min: " + min);
            }
            System.out.println("Searched " + count + " seeds, current min: " + min);
        }

        System.out.println(min);
        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;
        System.out.printf("Solution found in: %2d:%2d\n", (int) (timeTaken/1e9/60), (int) (timeTaken/1e9%60));
    }

    public static long seedToLocation(long seed) {
        String source = "seed";
        do {
            AlminacMap map = alminac.get(source);
            seed = map.convertSourceToDest(seed);
            source = map.dest;
        } while (!source.equals("location"));
        return seed;
    }

    public static List<Long> getSeeds(String seedsString) {
        Scanner seedScanner = new Scanner(seedsString);

        List<Long> seeds = new ArrayList<>();
        while (seedScanner.hasNextLong())
            seeds.add(seedScanner.nextLong());

        return seeds;
    }

    public static AlminacMap decodeMap(String map) {
        String src = map.substring(0, map.indexOf("-"));
        String dst = map.substring(map.lastIndexOf("-")+1, map.indexOf(" "));
        map = map.substring(map.indexOf("\n"));

        AlminacMap retMap = new AlminacMap(src, dst);

        Scanner scanner = new Scanner(map);

        while (scanner.hasNextLine()) {
            retMap.entries.add(new AlminacEntry(scanner.nextLong(), scanner.nextLong(), scanner.nextLong()));
        }

        return retMap;
    }
}
