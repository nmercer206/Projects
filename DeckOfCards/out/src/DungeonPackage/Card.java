package DungeonPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Card {
//The bread-and-butter of the program
    private String suit;
    private String rank;


    public Card(String rank, String suit){
        this.rank = rank;
        this.suit = suit;
    }
    public Card(){

    }

    public String toString(){
        return this.rank + " of " + this.suit;
    }


    public void randomShuffle(List<Card> cards){
        List<Card> randomlyShuffledDeck = new ArrayList<>();

        for (int i = 0; i < cards.size(); i++) {
            randomlyShuffledDeck.add(new Card(cards.get(i).rank, cards.get(i).suit));
        }
        for (int i = 0; i < randomlyShuffledDeck.size(); i++){
            System.out.println(randomlyShuffledDeck.get(i));
        }

    }
    public void sortByRank(List<Card> cards, String[] ranks){
        List<Card> ranksDeck = new ArrayList<>();
        int countOfRanks = 0;
        for (int i = 0; i < 13; i++){
            for (Card card : cards) {
                if (ranks[countOfRanks].equals(card.rank)) {
                    ranksDeck.add(new Card(card.rank, card.suit));
                }
            }
            countOfRanks++;
        }
        for (int i = 0; i < ranksDeck.size(); i++){
            if (i % 4 == 0){
                System.out.println();
            }
            System.out.println(ranksDeck.get(i));
        }
    }


    public void sortByClassThenRank(List<Card> cards, String [] ranks){
        List<String> suitsList = Arrays.asList("HEARTS", "DIAMONDS", "CLUBS", "SPADES");
        Collections.shuffle(suitsList);
        int countOfSuits = 0;
        int countOfRanks = 0;
        List<Card> suitThenRanksDeck = new ArrayList<>();

        for (int i = 0; i < 4; i++){
            for (Card card : cards) {
                if (suitsList.get(countOfSuits).equals(card.suit)) {
                    suitThenRanksDeck.add(new Card(ranks[countOfRanks], card.suit));
                    countOfRanks++;
                    if (countOfRanks == 13) {
                        countOfRanks = 0;
                    }
                }
            }
            countOfSuits++;
        }
        for (int i = 0; i < suitThenRanksDeck.size(); i++){
            if (i % 13 == 0){
                System.out.println();
            }
            System.out.println(suitThenRanksDeck.get(i));
        }
    }



}
