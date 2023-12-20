import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class Solution01Pt2 {

    public static Map<String, String> wordToNum = Map.of(
        "one", "o1e",
        "two", "t2o",
        "three", "t3e",
        "four", "f4r",
        "five", "f5e",
        "six", "s6x",
        "seven", "s7n",
        "eight", "e8t",
        "nine", "n9e",
        "zero", "z0o"
    );


    public static void main(String[] args) throws IOException {
        Stream<String> input = Files.lines(Path.of("input/input01.txt"));

        int sum = input.map((s) -> convertWordsToNum(s)).map((s) -> removeLetters(s)).filter((s) -> !s.isBlank()).mapToInt((s) -> calculateInt(s)).sum();

        System.out.println(sum);

        input.close();
    }

    public static String removeLetters(String input) {
        return input.replaceAll("[a-z]", "");
    }

    public static Integer calculateInt(String input) {
        return Integer.parseInt(input.substring(0, 1)) * 10
                + Integer.parseInt(input.substring(input.length() - 1, input.length()));
    }

    public static String convertWordsToNum(String input) {
        for (Entry<String, String> entry : wordToNum.entrySet()) {
            input = input.replaceAll(entry.getKey(), entry.getValue());
        }
        return input;
    }

}
