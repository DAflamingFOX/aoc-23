import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Solution02Pt1 {

    static final int MAX_RED = 12, MAX_GRN = 13, MAX_BLU = 14;

    public static void main(String[] args) throws IOException {
        Stream<String> input = Files.lines(Path.of("./input/input02.txt"));

        int ans = input.filter((s) -> isGameValid(s)).mapToInt((s) -> mapToGameNum(s)).sum();

        System.out.println(ans);

        input.close();
    }

    public static boolean isGameValid(String input) {
        // remove game number, I don't care.
        input = input.substring(input.indexOf(":") + 2);

        // split between each cube color num combo drawn out
        String[] cubesDrawn = input.split("(, |; )");

        for (String cubes : cubesDrawn) {
            // split the number and color
            int numCubes = Integer.parseInt(cubes.split(" ")[0]);
            String colorCubes = cubes.split(" ")[1];

            // if the number of color is ok, keep checking, if it isnt we know the game is bad, and it is invalid.
            if (switch (colorCubes) {
                default -> throw new RuntimeException();
                case "red" -> numCubes <= MAX_RED;
                case "green" -> numCubes <= MAX_GRN;
                case "blue" -> numCubes <= MAX_BLU;
            })
                continue;
            else
                return false;
        }
        // if all the cubes check out, it is valid.
        return true;

    }

    public static Integer mapToGameNum(String input) {
        return Integer.parseInt(input.substring(5, input.indexOf(":")));
    }
}
