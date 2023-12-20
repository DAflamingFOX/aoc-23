import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Solution02Pt2 {

    public static void main(String[] args) throws IOException {
        Stream<String> input = Files.lines(Path.of("./input/input02.txt"));

        long pwr = input.mapToInt((s) -> calculateGamePower(s)).sum();

        System.out.println(pwr);

        input.close();
    }

    public static Integer calculateGamePower(String input) {
        int maxR, maxG, maxB;
        maxR = maxG = maxB = 0;

        // remove game number, I don't care.
        input = input.substring(input.indexOf(":") + 2);

        // split between each cube color num combo drawn out
        String[] cubesDrawn = input.split("(, |; )");

        for (String cubes : cubesDrawn) {
            // split the number and color
            int numCubes = Integer.parseInt(cubes.split(" ")[0]);
            String colorCubes = cubes.split(" ")[1];

            // update max counter.
            switch (colorCubes) {
                case "red" -> maxR = Math.max(numCubes, maxR);
                case "green" -> maxG = Math.max(numCubes, maxG);
                case "blue" -> maxB = Math.max(numCubes, maxB);
            }
        }
        // return game power.
        return maxR * maxG * maxB;

    }


}
