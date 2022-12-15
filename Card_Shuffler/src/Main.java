import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] ranks = {"TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE"};
        String[] suits = {"HEARTS", "DIAMONDS", "CLUBS", "SPADES"};
        List<Card> cards = new ArrayList<>();

        for (String rank : ranks) {
            for (String suit : suits) {
                cards.add(new Card(rank, suit));

            }
        }
        System.out.println("Welcome To Etactics' Card-O-Matic!");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nPress:\n (1) to display a deck of cards\n (2) to display a deck of cards sorted by rank\n (3) to display a deck of cards sorted by suit and rank!\n (4) Quit");
            String tempInput;

            while (true) {
                tempInput = scanner.nextLine();
                if (tempInput.equals("1") || tempInput.equals("2") || tempInput.equals("3") || tempInput.equals("4")) {
                    break;
                } else {
                    System.out.println(tempInput + " is not a valid input.");
                    System.out.println("\nPlease press:\n (1) to display a deck of cards\n (2) to display a deck of cards sorted by rank\n (3) to display a deck of cards by suit and rank!\n (4) Quit");
                }
            }
            int input = Integer.parseInt(tempInput);
            Card userCard = new Card();
            Collections.shuffle(cards);
            System.out.println("\n" + cards.size() + " cards in the deck.\n");

            if (input == 1) {
                userCard.randomShuffle(cards);
            } else if (input == 2) {
                userCard.sortByRank(cards, ranks);
            } else if (input == 3) {
                userCard.sortByClassThenRank(cards, ranks);
            }

            if (input == 4) {
                break;
            }
        }
        System.out.println("Thanks for playing!");
    }
}