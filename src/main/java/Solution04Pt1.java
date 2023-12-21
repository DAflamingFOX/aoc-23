import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Solution04Pt1 {

    public static void main(String[] args) throws IOException {
        Stream<String> input = Files.lines(Path.of("./input/input04.txt"));

        long totalPoints = input.mapToInt(s -> calculateScore(s)).sum();

        System.out.println(totalPoints);

        input.close();
    }

    public static int calculateScore(String card) {
        card = card.substring(card.indexOf(": ") + 2);

        String[] numbers = card.split("\\|");
        ArrayList<Integer> winners = new ArrayList<>();
        ArrayList<Integer> scratched = new ArrayList<>();

        Scanner winScanner = new Scanner(numbers[0]);
        while (winScanner.hasNextInt())
            winners.add(winScanner.nextInt());

        Scanner scratchScanner = new Scanner(numbers[1]);
        while (scratchScanner.hasNextInt())
            scratched.add(scratchScanner.nextInt());

        //System.out.println("Winning Numbers:\t" + winners.toString());
        //System.out.println("Scratched Numbers:\t" + scratched.toString());
        int matches = (int) scratched.stream().filter(winners::contains).count();

        return matches == 0 ? 0 : 1 << matches-1;

    }
}
