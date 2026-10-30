import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WordCounterTest {

    @Test
    public void testReadReturnsNull(){
        DataSource source = new DataSource("test") {
            @Override
            public List<String> read() {
                return null;
            }
        };
        WordCounter wordCounter = WordCounter.getInstance();
        wordCounter.setDataSource(source);

        Map<String, Integer> expected = Map.of();
        Map<String, Integer> actual = wordCounter.countWords();

        assertEquals(expected, actual);
    }

    @Test
    public void testReadReturnsEmptyList(){
        DataSource source = new DataSource("test") {
            @Override
            public List<String> read() {
                return new ArrayList<>();
            }
        };
        WordCounter wordCounter = WordCounter.getInstance();
        wordCounter.setDataSource(source);

        Map<String, Integer> expected = Map.of();
        Map<String, Integer> actual = wordCounter.countWords();

        assertEquals(expected, actual);
    }

    @Test
    public void testWordInMultipleStrings(){
        DataSource source = new DataSource("test") {
            @Override
            public List<String> read() {
                return Arrays.asList("the dog barked", "a cat and a dog", "my dog sleeps");
            }
        };
        WordCounter wordCounter = WordCounter.getInstance();
        wordCounter.setDataSource(source);

        int expected = 3;
        int actual = wordCounter.countWords().get("dog");

        assertEquals(expected, actual);
    }

    @Test
    public void testWordInDifferentCases(){
        DataSource source = new DataSource("test") {
            @Override
            public List<String> read() {
                return Arrays.asList("the dog barked", "the DOG ran");
            }
        };
        WordCounter wordCounter = WordCounter.getInstance();
        wordCounter.setDataSource(source);

        int expected = 2;
        int actual = wordCounter.countWords().get("dog");

        assertEquals(expected, actual);
    }

}
