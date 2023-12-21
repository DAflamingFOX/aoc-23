import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Solution04Pt2 {

    public static void main(String[] args) throws IOException {
        Stream<String> input = Files.lines(Path.of("./input/input04.txt"));
        ArrayList<Integer> copies = new ArrayList<>();

        input.forEach(card -> {
            int cardNum = Integer.parseInt(card.substring(4, card.indexOf(":")).strip());
            cardNum--; // array uses index 0
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

            int matches = (int) scratched.stream().filter(winners::contains).count();

            // copies.ensureCapacity(cardNum + matches + 1);
            while (copies.size() < cardNum + matches + 1)
                copies.add(1);
            // fill copies
            while (matches > 0) {
                int index = cardNum + matches;
                int oldVal = copies.get(index);
                int copiesThisCard = copies.get(cardNum);
                copies.set(index, oldVal + copiesThisCard);
                matches--;
            }
        });

        System.out.println(copies.stream().mapToInt(x -> x).sum());

        input.close();
    }
}
