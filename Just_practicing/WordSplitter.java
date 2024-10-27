import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSplitter {

    private Set<String> dictionary;

    public WordSplitter(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> splitIntoSentences(String text) {
        List<String> sentences = new ArrayList<>();
        splitIntoSentencesHelper(text, 0, new StringBuilder(), sentences);
        return sentences;
    }

    private void splitIntoSentencesHelper(String text, int start, StringBuilder currentSentence, List<String> sentences) {
        if (start == text.length()) {
            sentences.add(currentSentence.toString().trim());
            return;
        }

        for (int end = start + 1; end <= text.length(); end++) {
            String word = text.substring(start, end);
            if (dictionary.contains(word)) {
                currentSentence.append(word).append(" ");
                splitIntoSentencesHelper(text, end, currentSentence, sentences);
                // Backtrack: remove the added word to explore other possibilities
                currentSentence.delete(currentSentence.length() - word.length() - 1, currentSentence.length());
            }
        }
    }

    public static void main(String[] args) {
        // Example dictionary
        Set<String> dictionary = new HashSet<>();
        dictionary.add("this");
        dictionary.add("is");
        dictionary.add("a");
        dictionary.add("test");
        dictionary.add("string");

        String text = "thisisateststrings";

        WordSplitter splitter = new WordSplitter(dictionary);
        List<String> sentences = splitter.splitIntoSentences(text);

        for (String sentence : sentences) {
            System.out.println(sentence);
        }
    }
}