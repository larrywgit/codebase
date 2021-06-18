package algorithms;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class LongestKSubString {

    public String longestSubstring(String s, int d) {

        Map<Character, Integer> letterMaps = new HashMap<>();
        String result = "";
        int begin = 0, end = 0;
        while (end < s.length()) {
            if (letterMaps.size() <= d) {
                letterMaps.merge(s.charAt(end), 1, Integer::sum);
            } else {
                if (end - begin > result.length()) {
                    result = s.substring(begin, end);
                }

                letterMaps.merge(s.charAt(begin), -1, Integer::sum);
                letterMaps.entrySet().removeIf(entry -> entry.getValue() == 0);
                begin++;
            }

            end++;
        }

        if (letterMaps.size() == d && end - begin + 1 > result.length()) {
            result = s.substring(begin);
        }

        return result;
    }


    @Test
    public void test1() {
        String res = new LongestKSubString().longestSubstring("abcadcacacaca", 3);
        Assert.assertEquals("cadcacacaca", res);
    }

    @Test
    public void test2() {
        String res = new LongestKSubString().longestSubstring("aaaaa", 3);
        Assert.assertEquals("", res);
    }

    @Test
    public void test3(){
        String res = new LongestKSubString().longestSubstring("abababab", 3);
        Assert.assertEquals("", res);
    }

    @Test
    public void test4() {
        String res = new LongestKSubString().longestSubstring("aaaaa", 1);
        Assert.assertEquals("aaaaa", res);
    }

}
