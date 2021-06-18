package algorithms;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class LongestNoRepeatingSubstring {

    public int lengthOfLongestSubstring(String s) {

        Set<Character> letterSet = new HashSet<>();

        int start = 0, end = 0, noRepeatingLen = 0;

        while(end < s.length()){

            char e = s.charAt(end);
            if (!letterSet.contains(e)){
                letterSet.add(e);
            }
            else{

                while(letterSet.contains(e)) {
                    char sc = s.charAt(start);
                    letterSet.remove(sc);
                    start++;
                }
                letterSet.add(e);
            }
            int len = end - start + 1;
            if (noRepeatingLen < len)
                noRepeatingLen = len;

            end++;
        }
        return noRepeatingLen;
    }

    @Test
    public void test1(){
        int res = new LongestNoRepeatingSubstring().lengthOfLongestSubstring("abcabcbb");
        assertEquals(3, res);
    }

    @Test
    public void test2(){
        int res = new LongestNoRepeatingSubstring().lengthOfLongestSubstring("bbbbb");
        assertEquals(1, res);
    }

    @Test
    public void test3(){
        int res = new LongestNoRepeatingSubstring().lengthOfLongestSubstring("pwwkew");
        assertEquals(3, res);
    }

    @Test
    public void test4(){
        int res = new LongestNoRepeatingSubstring().lengthOfLongestSubstring("");
        assertEquals(0, res);
    }

    @Test
    public void test5(){
        int res = new LongestNoRepeatingSubstring().lengthOfLongestSubstring(" ");
        assertEquals(1, res);
    }
}
