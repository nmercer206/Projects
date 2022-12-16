import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Character> alphabet = new ArrayList<>();
        char[] abc = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (char a : abc){
            alphabet.add(a);
        }
        Collections.shuffle(alphabet);

        List<Character> sortedAlphabet = new ArrayList<>();
        for (int i = 0; i < alphabet.size(); i++){
            for (char a: abc){
                if (a == abc[i]){
                    sortedAlphabet.add(a);
                }
            }
        }
        System.out.println(sortedAlphabet);

    }
}