import java.util.*;

public class AlphabetCount {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a sentence: ");
        String sentence = input.nextLine();
        Map<Character,Integer> counts=count(sentence);
        Map<Character,Integer>countSorted = new TreeMap<>(counts);

        System.out.println(countSorted);

    }

    public static Map<Character,Integer> count(String sentence) {
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        sentence = sentence.toLowerCase();
        for(char c : sentence.toCharArray()) {
            if(Character.isAlphabetic(c)) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
        }
        return map;
    }
}
