import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Would you like to play a game of Rock Paper Scissors? (y/n)");
            String playerChoice;

            while (true){
                playerChoice = scanner.nextLine().toLowerCase();
                if (playerChoice.equals("y") || playerChoice.equals("n") || playerChoice.equals("yes") || playerChoice.equals("no")){
                    break;
                } else {
                    System.out.println(playerChoice + " is not a valid choice. Enter either (y/n).");
                }
            }


            while (playerChoice.equals("y") || playerChoice.equals("yes")){

                String[] rps = new String[]{"rock", "paper", "scissors"};
                String computerMove = rps[new Random().nextInt(rps.length)];
                String playerMove;

                while (true) {
                    System.out.println("Please enter your move (rock, paper, or scissors): ");
                    playerMove = scanner.nextLine().toLowerCase();

                    if (playerMove.equals("rock") || playerMove.equals("paper") || playerMove.equals("scissors")) {
                        break;
                    } else {
                        System.out.println(playerMove + " is not a valid move. Enter either rock, paper, or scissors");
                    }
                }


                if (playerMove.equals(computerMove)) {
                    System.out.println("Computer picked: " + computerMove + ". The game is a tie!");
                } else if (playerMove.equals("rock")) {
                    if (computerMove.equals("paper")) {
                        System.out.println("Computer picked: " + computerMove + ". You lose!");
                    } else {
                        System.out.println("Computer picked: " + computerMove + ". You win!");
                    }
                } else if (playerMove.equals("paper")) {
                    if (computerMove.equals("scissors")){
                        System.out.println("Computer picked: " + computerMove + ". You lose!");
                    } else {
                        System.out.println("Computer picked: " + computerMove + ". You win!");
                    }
                } else if (playerMove.equals("scissors")){
                    if (computerMove.equals("rock")){
                        System.out.println("Computer picked: " + computerMove + ". You lose!");
                    } else {
                        System.out.println("Computer picked: " + computerMove + ". You win!");
                    }
                }

                System.out.println("Play again? (y/n)");
                String playAgain;

                while (true){
                    playAgain = scanner.nextLine().toLowerCase();
                    if (playAgain.equals("y") || playAgain.equals("n") || playAgain.equals("yes") || playAgain.equals("no")){
                        break;
                    } else {
                        System.out.println(playAgain + " is not a valid choice. Enter either (y/n).");
                    }
                }

                if (!playAgain.equals("y")){
                    if (!playAgain.equals("yes")){
                        break;
                    }
                }

            }
        System.out.println("Thanks for playing!");


    }
}