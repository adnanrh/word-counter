import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class WordCounter {

    /**
     * Method to find the number of occurrences of each word in a comma separated list in a text file.
     * @param inputFile the text file containing a comma separated list of words (ie. green,green,red,green,red).
     * @return a multi-line string containing a word and its number of occurrences on each line, with words listed
     * in the order they are found from the beginning of the file.
     * @throws IOException if file not found.
     */
    public String getWordCountsFromWordList(String inputFile) throws IOException {
        // get list of words from input file as a List of Strings
        List<String> wordList = getWordListFromFile(inputFile);
        // if the list is empty, return an empty string
        if (wordList.isEmpty()) return "";

        // get a hashmap where keys are words in the list and values are number of occurrences of each word
        HashMap<String, Integer> wordCounts = getWordCountsFromWordList(wordList);

        // return multi-line string in format: word, # of occurrences in each line
        return buildOutputString(wordCounts);
    }

    /**
     * Gets a list of words from the input file.
     * @param inputFile the text file containing a comma separated list of words (ie. green,green,red,green,red)
     * @return a List of String containing all words separated by commas.
     * @throws IOException if file not found.
     */
    private List<String> getWordListFromFile(String inputFile) throws IOException {
        // get the file content as a string
        String list = new String(Files.readAllBytes(Paths.get(inputFile)));
        // if file is empty, return an empty list.
        if (list.length() == 0) return new ArrayList<>();
        // split the list using comma as a delimiter (as well as remove trailing and leading whitespace)
        return Arrays.asList(list.split("\\s*,\\s*"));
    }

    /**
     * Finds the number of occurrences of each word in the provided list and stores it in a map.
     * @param wordList a list of words.
     * @return a hashmap containing words as keys and number of occurrences of words as values.
     */
    private HashMap<String, Integer> getWordCountsFromWordList(List<String> wordList) {
        // use a LinkedHashMap so that keys are ordered in the order they are added
        HashMap<String, Integer> wordCounts = new LinkedHashMap<>();
        // iterate over word list
        for (String word : wordList) {
            // add a new key-value pair if word is not already in the map
            Integer previousValue = wordCounts.putIfAbsent(word, 1);
            // otherwise, increment original value by 1
            if (previousValue != null) {
                wordCounts.replace(word, previousValue + 1);
            }
        }
        return wordCounts;
    }

    /**
     * Creates and returns a multi-line string containing each key and value in the provided map.
     * @param wordCounts a hashmap containing words as keys and number of occurrences of words as values.
     * @return a string containing the keys and values in the provided map.
     */
    private String buildOutputString(HashMap<String, Integer> wordCounts) {
        StringBuilder builder = new StringBuilder();
        // iterate over keys in map
        for (String key : wordCounts.keySet()) {
            // create a new line for each key-value pair in the format key,value
            builder.append(key).append(",").append(wordCounts.get(key)).append("\n");
        }
        return builder.toString();
    }
}
