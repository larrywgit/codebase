package algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class FindPermutation {

    public boolean checkInclusion(String s1, String s2) {

        Map<Character, Integer> letterMap = new HashMap<>();
        s1.chars().forEach(c -> letterMap.merge((char) c, 1, Integer::sum));

        int start = 0, end = 0, counter = letterMap.size();

        while (end < s2.length()) {
            char e = s2.charAt(end);
            if (letterMap.keySet().contains(e)) {
                int count = letterMap.compute(e, (k, v) -> v = v - 1);
                if (count ==0) counter--;
            }

            while (counter == 0) {
                if (end - start == s1.length() - 1) {
                    return true;
                }
                char s = s2.charAt(start);
                if (s1.indexOf(s) >= 0) {
                    int count = letterMap.compute(s, (k,v) -> v= v+1);
                    if ( count == 1)
                        counter ++;
                }
                start++;
            }
            end++;
        }

        return false;
    }

    @Test
    public void test1() {
        boolean result = new FindPermutation().checkInclusion("ab", "eidbaooo");
        Assert.assertEquals(true, result);
    }


    @Test
    public void test2() {
        boolean result = new FindPermutation().checkInclusion("ab", "eidboaoo");
        Assert.assertEquals(false, result);
    }

    @Test
    public void test3() {
        boolean result = new FindPermutation().checkInclusion("adc", "dcda");
        Assert.assertEquals(true, result);
    }

}
