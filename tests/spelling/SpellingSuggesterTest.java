package spelling;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class SpellingSuggesterTest {
    Dictionary dictionary = new DictionaryHashSet();
    SpellingSuggester spellingSuggester = new SpellingSuggester(dictionary);

    @Test
    void getSuggestions() {
        Iterable<String> actual = spellingSuggester.getSuggestions("ther");

        Iterable<String> expected = new ArrayList(asList("cher", "sher", "teer",
                "tier", "thar", "thir", "thor", "thea", "theb", "thed", "thee",
                "them", "then", "theo", "thew", "they", "her", "ter", "the"));

        assertIterableEquals(expected, actual);



    }
}