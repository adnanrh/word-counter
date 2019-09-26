import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class WordCounter {

    public String getWordCountsFromWordList(String inputFile) throws IOException {
        List<String> wordList = getWordListFromFile(inputFile);
        if (wordList.isEmpty()) return "";

        HashMap<String, Integer> wordCounts = getWordCountsFromWordList(wordList);

        return buildOutputString(wordCounts);
    }

    private List<String> getWordListFromFile(String inputFile) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(inputFile)));
        if (content.length() == 0) return new ArrayList<>();

        return Arrays.asList(content.split(","));
    }

    private HashMap<String, Integer> getWordCountsFromWordList(List<String> wordList) {
        HashMap<String, Integer> wordCounts = new LinkedHashMap<>();
        for (String word : wordList) {
            Integer previousValue = wordCounts.putIfAbsent(word, 1);
            if (previousValue != null) {
                wordCounts.replace(word, previousValue + 1);
            }
        }
        return wordCounts;
    }

    private String buildOutputString(HashMap<String, Integer> wordCounts) {
        StringBuilder builder = new StringBuilder();
        for (String key : wordCounts.keySet()) {
            builder.append(key).append(",").append(wordCounts.get(key)).append("\n");
        }
        return builder.toString();
    }
}
