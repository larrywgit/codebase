package algorithms;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class SlidingWindows {
     int start = -1, end = -1, minLen = 9999, curPos = 0;

    public String minWindow(String s, String t) {
        String result = "";
        int[] targetIndexs = new int[t.length()];
        Arrays.fill(targetIndexs, -1);

        if (t.length() ==1 ) return s.indexOf(t)>=0?t:"";
        if (t.length() > s.length()) return "";

        int toSearch = indexOfCharToSearch(targetIndexs, t);
        int cur = 0;
        while (cur < s.length()) {
            final int ind = cur;
            if (s.charAt(cur) == t.charAt(toSearch) && Arrays.stream(targetIndexs).noneMatch(i -> ind == i)) {
                targetIndexs[toSearch] = cur;
                result = checkResult(s, targetIndexs,result);
                toSearch = indexOfCharToSearch(targetIndexs, t);
                cur = start + 1;
            } else {
                cur++;
            }

        }
        return result;
    }

    private  String checkResult(String s, int[] targetIndexs, String result) {
        if (getMinIndex(targetIndexs) < 0 )
            return "";
        start = getMinIndex(targetIndexs);
        end = getMaxIndex(targetIndexs);

        if (end - start + 1 < minLen) {
            String res = s.substring(start, end + 1 > s.length() ? s.length() : end + 1);
            minLen = end - start + 1;
            return res;
        }
        else
            return result;
    }

    private int getMinIndex(int[] targetIndexs) {
        return Arrays.stream(Arrays.stream(targetIndexs).boxed().toArray(Integer[]::new)).min(Integer::compareTo).get();
    }

    private int getMaxIndex(int[] targetIndexs) {
        return Arrays.stream(Arrays.stream(targetIndexs).boxed().toArray(Integer[]::new)).max(Integer::compareTo).get();
    }

    private int indexOfCharToSearch(int[] targets, String s) {
        int min = 9999, minIndex = 0;
        for (int i = 0; i < targets.length; i++) {
            if (targets[i] < min) {
                min = targets[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    @Test
    public void test1() {
        String res = new SlidingWindows().minWindow("ADOBECODEBANC", "ABC");
        Assert.assertEquals("BANC", res);
    }

    @Test
    public void test2() {
        String res = new SlidingWindows().minWindow("ADOBEACAODEABANC", "ABCA");
        Assert.assertEquals("BEACA", res);
    }

    @Test
    public void test3(){
        String res = new SlidingWindows().minWindow("abc", "ab");
        Assert.assertEquals("ab", res);
    }

    @Test
    public void test4(){
        String res = new SlidingWindows().minWindow("aa", "aa");
        Assert.assertEquals("aa", res);
    }
}



