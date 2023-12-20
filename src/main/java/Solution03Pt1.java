import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Solution03Pt1 {

    public static void main(String[] args) throws IOException {
        Stream<String> input = Files.lines(Path.of("./input/input03.txt"));

        List<String> schematic = input.toList();
        // The array is {number, line index, start num index, end num index}
        List<Integer[]> numberMap = new ArrayList<>();
        Pattern pat = Pattern.compile("\\d+");
        long sum = 0;

        // find all the numbers, and put them in a map.
        for (int i = 0; i < schematic.size(); i++) {
            Matcher matcher = pat.matcher(schematic.get(i));
            while (matcher.find()) {
                numberMap.add(new Integer[] {Integer.parseInt(matcher.group()), i, matcher.start(), matcher.end() });
            }
        }

        // Go through the numbers, see which ones are adjacent to a symbol.
        for (Integer[] data : numberMap) {
            // System.out.println(entry.getKey() + " - " + Arrays.toString(entry.getValue()));
            int partNum = data[0];
            int i = data[1];
            int start = data[2];
            int end = data[3];

            // go around the number to find if there is a symbol. upon finding, add part number to sum, otherwise, just continue to next num.
            List<Character> adj = new ArrayList<>(); // characters to search.
            // top row
            if (i != 0)
                for (char c : schematic.get(i - 1).substring(start, end).toCharArray())
                    adj.add(c);
            // top right
            if (i != 0 && end != schematic.get(i - 1).length())
                adj.add(schematic.get(i - 1).charAt(end));
            // right
            if (end != schematic.get(i).length())
                adj.add(schematic.get(i).charAt(end));
            // bottom right
            if (i < schematic.size() - 1 && end != schematic.get(i + 1).length())
                adj.add(schematic.get(i + 1).charAt(end));
            // bottom
            if (i < schematic.size() - 1)
                for (char c : schematic.get(i + 1).substring(start, end).toCharArray())
                    adj.add(c);
            // bottom left
            if (i < schematic.size() - 1 && start != 0)
                adj.add(schematic.get(i + 1).charAt(start - 1));
            // left
            if (start != 0)
                adj.add(schematic.get(i).charAt(start - 1));
            // top left
            if (i != 0 && start != 0)
                adj.add(schematic.get(i - 1).charAt(start - 1));

            for (char c : adj) {
                if (c != '.') {
                    sum += partNum;
                    break;
                }
            }
        }

        System.out.println(sum);

        input.close();
    }
}