package algorithms;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import org.junit.Test;

public class SlidingWindowsV2 {

    public String minWindow(String s, String t) {

        if (t.length() ==1 ) return s.indexOf(t)>=0?t:"";
        if (t.length() > s.length()) return "";
        Counter counter = new Counter(t);
        int start = 0, end = 0, minLen = 9999;
        String result = "";
        while (end < s.length()) {
            if (counter.moveWindowEnd(s.charAt(end)) == 0) {
                while (start < s.length()) {
                    if (counter.moveWindowStart(s.charAt(start)) == 1) {
                        if (end - start + 1 < minLen) {
                            minLen = end - start + 1;
                            result = s.substring(start, end + 1 > s.length() ? end : end + 1);
                        }
                        start++;
                        break;
                    }
                    start++;
                }
            }
            end++;
        }

        return result;
    }


    private static class Counter {

        String matcher;
        int remaining = 0;
        Integer[] count;

        public Counter(String s) {
            int len = s.length();
            this.count = new Integer[len];
            Arrays.fill(count, 1);
            this.remaining = len;
            this.matcher = s;
        }

        int moveWindowEnd(char c) {
            int index = matcher.indexOf(c);

            while (index >=0 && count[index] == 0 ){
                index = matcher.indexOf(c, index+1);
            }

            if (index <0) index = matcher.indexOf(c);

            if (index >= 0) {
                count[index]--;
                if (count[index] == 0) {
                    remaining--;
                }
            }
            return remaining;
        }

        int moveWindowStart(char c) {
            int index = matcher.indexOf(c);

            while(index >=0 && count[index] == 1){
                index = matcher.indexOf(c, index +1);
            }

            if (index <0) index = matcher.indexOf(c);
            if (index >= 0) {
                count[index]++;
                if (count[index] == 1) {
                    remaining++;
                }
            }
            return remaining;
        }
    }

    @Test
    public void test1() {
        String res = new SlidingWindowsV2().minWindow("ADOBECODEBANC", "ABC");
        assertEquals("BANC", res);
    }

    @Test
    public void test2() {
        String res = new SlidingWindowsV2().minWindow("ADOBEACAODEABANC", "ABCA");
        assertEquals("BEACA", res);
    }

    @Test
    public void test3(){
        String res = new SlidingWindows().minWindow("abc", "ab");
        assertEquals("ab", res);
    }

    @Test
    public void test4(){
        String res = new SlidingWindows().minWindow("aa", "aa");
        assertEquals("aa", res);
    }
    @Test
    public void test5() throws Exception{

        try (InputStream input = new FileInputStream("src/resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String s = prop.getProperty("string-to-search");
            String t = prop.getProperty("matcher");

            String res = new SlidingWindows().minWindow(s, t);
            assertEquals(
                "sehoeyslmrsxseyxdkpnanapjjfophmhnwxswpdijxhbbiyhspwudwlofsewztdprchnsmxbkhenpcujuqdxoqntjspkluzcxirrvouhvyukcptwhytwpjrybjiofksesjvnzpuvnrhqidmpbinsrhsusbixlccflztmppjegruoujgxrvqkckgulquysyefxzrmqbrxunnqwtnprfbtqhqdxmerkkwjloybrraleobdjquayywqfovfazymlvvwlvacmaptoswaksciqyyymwfmvdajywflrfpggezwyvyjpbrgzsgoolclodupzcasjqyruxovuoempvurpahfljtbmpqnrtibjgsfgiaczeqqckjtkqzxauzojrcdkkgtsabajbfkivakikfscgscattmkvpvhvqbvtcgvjqfetrofwhhdbmfufrecgbjdumbnohkxapevguafbjiexnyehdipgttcguqudcufsaaaucfyopcnfdsmiadowwrcsjyylsdfugirkppyftmmwgaeidvecogwzfukzaswgcnqreryzfmwlmcvszcuniqmplzrltntvcjogcpfhbduqiqihscvcujuhilubanyczpibepjhvdxdvhkplhsgronbzidzxdbwslyycjixofckpnbawvgpjwrigwjdzmauzauclcbzkelztnzpkifugyemuopvcrrctmgeqhgalrbegdurlbntzrftfwqkoimhwsomzuplnqrwtlngoazntgizdbjrjxahpqtqkidybvwwijfxlemenxfqyqhjpjsganxmwamzxivgtafehtlwqsmqlvbaethghgtfgfggodmjetqnmbjdvxvgsxjyssfbyaigfomvsyfyrvneyyjvvgenfnlyhsbfsklayyxsyeqsbdyzbhxypbnvqztxtwemgpohplzqqqirvgtzpqxetlzlmukfrotxfhevvgnlwvetdzssrsykdyxruhylvslbb...", res);

        }catch (Exception e){

        }

    }

}
