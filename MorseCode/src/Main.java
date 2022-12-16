import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("0-_--_0--__--_0--__--_0--__--_0--__--_0--__--_0-");
        System.out.println("_--_0--__--_0 TO-MORSE CONVERTER __0--__--_0--_-");
        System.out.println("__-0__--__-0__--__-0__--_0--__--_0--__--_0--__-0");
        System.out.println();
        System.out.println("Would you like to convert a word into morse? (y/n)");

        String playerChoice;

        while (true) {
            playerChoice = scanner.nextLine().toLowerCase();
            if (playerChoice.equals("y") || playerChoice.equals("n")) {
                break;
            } else {
                System.out.println(playerChoice + " is not a valid choice. Enter either (y/n).");
            }
        }

        while (playerChoice.equals("y")) {
            System.out.println("Enter a word/phrase to convert to morse: ");

            String userInput = scanner.nextLine();
            userInput.toLowerCase();
            String[] morseArray = null;
            morseArray = userInput.split("");
            String returnString = "";

            for (int i = 0; i < morseArray.length; i++) {
                if (morseArray[i].equals("e") || morseArray[i].equals("t")) {
                    if (morseArray[i].equals("e")) {
                        returnString += ".  ";
                    } else {
                        returnString += "-  ";
                    }
                } else if (morseArray[i].equals("i") || morseArray[i].equals("a")) {
                    if (morseArray[i].equals("i")) {
                        returnString += "..  ";
                    } else {
                        returnString += ".-  ";
                    }
                } else if (morseArray[i].equals("s") || morseArray[i].equals("u")) {
                    if (morseArray[i].equals("s")) {
                        returnString += "...  ";
                    } else {
                        returnString += "..-  ";
                    }
                } else if (morseArray[i].equals("h") || morseArray[i].equals("v")) {
                    if (morseArray[i].equals("h")) {
                        returnString += "....  ";
                    } else {
                        returnString += "...-  ";
                    }
                } else if (morseArray[i].equals("f") || morseArray[i].equals("l")) {
                    if (morseArray[i].equals("f")) {
                        returnString += "..-.  ";
                    } else {
                        returnString += ".-..  ";
                    }
                } else if (morseArray[i].equals("r") || morseArray[i].equals("w")) {
                    if (morseArray[i].equals("r")) {
                        returnString += ".-.  ";
                    } else {
                        returnString += ".--  ";
                    }
                } else if (morseArray[i].equals("p") || morseArray[i].equals("j")) {
                    if (morseArray[i].equals("p")) {
                        returnString += ".--.  ";
                    } else {
                        returnString += ".---  ";
                    }
                } else if (morseArray[i].equals("n") || morseArray[i].equals("m")) {
                    if (morseArray[i].equals("n")) {
                        returnString += "-.  ";
                    } else {
                        returnString += "--  ";
                    }
                } else if (morseArray[i].equals("d") || morseArray[i].equals("k")) {
                    if (morseArray[i].equals("d")) {
                        returnString += "-..  ";
                    } else {
                        returnString += "-.-  ";
                    }
                } else if (morseArray[i].equals("b") || morseArray[i].equals("x")) {
                    if (morseArray[i].equals("b")) {
                        returnString += "-...  ";
                    } else {
                        returnString += "-..-  ";
                    }
                } else if (morseArray[i].equals("c") || morseArray[i].equals("y")) {
                    if (morseArray[i].equals("c")) {
                        returnString += "-.-.  ";
                    } else {
                        returnString += "-.--  ";
                    }
                } else if (morseArray[i].equals("g") || morseArray[i].equals("o")) {
                    if (morseArray[i].equals("g")) {
                        returnString += "--.  ";
                    } else {
                        returnString += "---  ";
                    }
                } else if (morseArray[i].equals("z") || morseArray[i].equals("q")) {
                    if (morseArray[i].equals("z")) {
                        returnString += "--..  ";
                    } else {
                        returnString += "--.-  ";
                    }
                }


            }
            System.out.println(returnString);


            System.out.println("Morse another word/phrase? (y/n)");

            String playAgain;

            while (true){
                playAgain = scanner.nextLine().toLowerCase();
                if (playAgain.equals("y") || playAgain.equals("n")){
                    break;
                } else {
                    System.out.println(playAgain + " is not a valid choice. Enter either (y/n).");
                }
            }


            if (playAgain.equals("n")){
                break;
            }
        }
        System.out.println("Thanks for playing!");
    }
}