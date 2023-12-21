import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Solution05Pt1 {

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

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("./input/input05.txt"));

        List<Long> seeds = getSeeds(scanner.nextLine().substring(7));
        Map<String, AlminacMap> alminac = new HashMap<>();

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

        String source = "seed";
        do {
            AlminacMap map = alminac.get(source);
            seeds = seeds.stream().map(x -> map.convertSourceToDest(x)).collect(Collectors.toList());
            source = map.dest;
        } while (!source.equals("location"));

        System.out.println(seeds.stream().min(Long::compare).get());
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
