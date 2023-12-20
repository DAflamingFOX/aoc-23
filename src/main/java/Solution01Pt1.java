import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Solution01Pt1 {

    public static void main(String[] args) throws IOException {
        Stream<String> input = Files.lines(Path.of("input/input01.txt"));

        int sum = input.map((s) -> removeLetters(s)).filter((s) -> !s.isBlank()).mapToInt((s) -> calculateInt(s)).sum();

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

}
