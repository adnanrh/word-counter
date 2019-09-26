import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class WordCounterTest {

    private WordCounter wordCounter;
    private static String nonexistent_file = "src/test/resources/foo.text";
    private static String empty_counts_file = "src/test/resources/counts_empty.txt";
    private static String single_counts_file = "src/test/resources/counts_single_word.txt";
    private static String small_counts_file = "src/test/resources/counts_small.txt";
    private static String medium_counts_file = "src/test/resources/counts_medium.txt";
    private static String large_counts_file = "src/test/resources/counts_large.txt";
    private static String very_large_counts_file = "src/test/resources/counts_very_large.txt";

    @Before
    public void setUp() {
        wordCounter = new WordCounter();
    }

    @Test (expected = IOException.class)
    public void test_getWordCounts_file_not_found() throws Exception {
        wordCounter.getWordCountsFromWordList(nonexistent_file);
    }

    @Test
    public void test_getWordCounts_empty_file() throws Exception {
        String output = wordCounter.getWordCountsFromWordList(empty_counts_file);
        String expectedOutput = "";
        assertEquals(expectedOutput, output);
    }

    @Test
    public void test_getWordCounts_single_word_file() throws Exception {
        String output = wordCounter.getWordCountsFromWordList(single_counts_file);
        String expectedOutput = "Calgary,1\n";
        assertEquals(expectedOutput, output);
    }

    @Test
    public void test_getWordCounts_small_file() throws Exception {
        String output = wordCounter.getWordCountsFromWordList(small_counts_file);
        String expectedOutput = "green,3\nred,2\n";
        assertEquals(expectedOutput, output);
    }

    @Test
    public void test_getWordCounts_medium_file() throws Exception {
        String output = wordCounter.getWordCountsFromWordList(medium_counts_file);
        String expectedOutput = "green,2\norange,2\nred,4\nblue,2\nmaroon,1\n";
        assertEquals(expectedOutput, output);
    }

    @Test
    public void test_getWordCounts_large_file() throws Exception {
        String output = wordCounter.getWordCountsFromWordList(large_counts_file);
        String expectedOutput = "green,6\norange,2\nred,9\nblue,7\nmaroon,4\nbrown,2\nblack,4\nyellow,1\n";
        assertEquals(expectedOutput, output);
    }

    @Test
    public void test_getWordCounts_very_large_file() throws Exception {
        String output = wordCounter.getWordCountsFromWordList(very_large_counts_file);
        String expectedOutput = "tree,2016\nriver,1976\nmountain,1979\nocean,2007\nrock,2023\n";
        assertEquals(expectedOutput, output);
    }
}
