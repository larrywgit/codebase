package algorithms;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class LongestCharacterReplacement {

    public int characterReplacement(String s, int k) {
        int begin = 0, end = 0, result = 0, counter = k;

        Set<Character> chars = new HashSet<>();
        s.chars().forEach(c -> chars.add((char) c));

        for (char c : chars) {
            begin = 0;
            end = 0;
            counter = k;
            while (end < s.length()) {

                if (counter < 0) {
                    if (result < end - begin) {
                        result = end - begin - 1;
                    }

                    if (s.charAt(begin) != c) {
                        counter++;
                    }
                    begin++;
                } else {
                    if (s.charAt(end) != c) {
                        counter--;
                    }
                    end++;
                }
                if (result < end - begin && counter >= 0) {
                    result = end - begin;
                }
            }

        }

        return result;
    }

    @Test
    public void test1(){
        int res = new LongestCharacterReplacement().characterReplacement("ABAB",2);
        Assert.assertEquals(4, res);
    }

    @Test
    public void test2(){
        int res = new LongestCharacterReplacement().characterReplacement("AABABBA",1);
        Assert.assertEquals(4, res);
    }

    @Test
    public void test3(){
        int res = new LongestCharacterReplacement().characterReplacement("AAAA",1);
        Assert.assertEquals(4, res);
    }
}
