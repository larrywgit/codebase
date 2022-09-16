package algorithms;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;


public class FindSubstrings {

    public List<Integer> findSubstrings(String s, String[] words) {

        Map<String, Integer> map = new HashMap<>();
        Arrays.stream(words).forEach(w -> map.merge(w, 1, Integer::sum));

        int start = 0, end = 0, wordLen = words[0].length(), windowSize = wordLen * words.length;
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < wordLen; i++) {
            start = i;
            end = i;
            Map<String, Integer> wordMap = new HashMap<>(map);

            int counter = wordMap.size();

            while (end <= s.length() - wordLen) {
                String endWord = s.substring(end, end + wordLen);

                if (wordMap.keySet().contains(endWord)) {
                    int count = wordMap.merge(endWord, -1, Integer::sum);
                    if (count == 0) {
                        counter--;
                    }
                }

                if (end - start + wordLen == windowSize) {
                    if (counter == 0) {
                        res.add(start);
                    }

                    String startWord = s.substring(start, start + wordLen);
                    if (wordMap.keySet().contains(startWord)) {
                        int count = wordMap.merge(startWord, 1, Integer::sum);
                        if (count == 1) {
                            counter++;
                        }
                    }
                    start += wordLen;
                }
                end += wordLen;

            }

        }
        return res.stream().distinct().sorted().collect(Collectors.toList());
    }

    @Test
    public void test1() {
        List<Integer> res = new FindSubstrings().findSubstrings("barfoothefoobarman", new String[]{"foo", "bar"});
        assertEquals(Arrays.asList(0, 9), res);
    }

    @Test
    public void test2() {
        List<Integer> res = new FindSubstrings().findSubstrings("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"});
        assertEquals(Arrays.asList(), res);
    }

    @Test
    public void test3() {
        List<Integer> res = new FindSubstrings().findSubstrings("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"});
        assertEquals(Arrays.asList(6, 9, 12), res);
    }

    @Test
    public void test4() {
        List<Integer> res = new FindSubstrings().findSubstrings("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"});
        assertEquals(Arrays.asList(8), res);
    }

    @Test
    public void test5() {
        List<Integer> res = new FindSubstrings().findSubstrings("aaaaaaaaaaaaaa", new String[]{"aa", "aa"});
        assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10), res);
    }

    @Test
    public void test6() {
        List<Integer> res = new FindSubstrings()
                .findSubstrings("bcabbcaabbccacacbabccacaababcbb", new String[]{"c", "b", "a", "c", "a", "a", "a", "b", "c"});
        assertEquals(Arrays.asList(6, 16, 17, 18, 19, 20), res);
    }
}
