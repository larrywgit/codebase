package algorithms;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

public class SlidingWindowsV3 {
    public String minWindow(String s, String t) {

        Map<Character,Integer> letterCount = new HashMap<>();

        t.chars().forEach(c -> letterCount.merge((char) c, 1, (v1,v2)-> v2 + v1));

        int end=0,begin=0, counter = letterCount.size();
        String res="";
        while(end<s.length()){
            if (letterCount.containsKey(s.charAt(end))){
                int count = letterCount.compute(s.charAt(end), (k,v) -> v-1);
                if (count == 0) counter--;
            }

            while(counter == 0){
                if (res.equals("") || end - begin + 1 < res.length()){
                    res = s.substring(begin, end + 1);
                }

                if (letterCount.containsKey(s.charAt(begin))){
                    letterCount.compute(s.charAt(begin), (k,v) -> v+1);
                    if (letterCount.get(s.charAt(begin))>0)
                        counter++;
                }
                begin++;
            }
            end++;

        }

        return res;
    }

    @Test
    public void test1() {
        String res = new SlidingWindowsV3().minWindow("ADOBECODEBANC", "ABC");
        assertEquals("BANC", res);
    }

    @Test
    public void test2() {
        String res = new SlidingWindowsV3().minWindow("ADOBEACAODEABANC", "ABCA");
        assertEquals("BEACA", res);
    }

    @Test
    public void test3(){
        String res = new SlidingWindowsV3().minWindow("abc", "ab");
        assertEquals("ab", res);
    }

    @Test
    public void test4(){
        String res = new SlidingWindowsV3().minWindow("aa", "aa");
        assertEquals("aa", res);
    }
    @Test
    public void test5() throws Exception{

        try (InputStream input = new FileInputStream("src/resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String s = prop.getProperty("string-to-search");
            String t = prop.getProperty("matcher");

            String res = new SlidingWindowsV3().minWindow(s, t);
            assertEquals(
                "sehoeyslmrsxseyxdkpnanapjjfophmhnwxswpdijxhbbiyhspwudwlofsewztdprchnsmxbkhenpcujuqdxoqntjspkluzcxirrvouhvyukcptwhytwpjrybjiofksesjvnzpuvnrhqidmpbinsrhsusbixlccflztmppjegruoujgxrvqkckgulquysyefxzrmqbrxunnqwtnprfbtqhqdxmerkkwjloybrraleobdjquayywqfovfazymlvvwlvacmaptoswaksciqyyymwfmvdajywflrfpggezwyvyjpbrgzsgoolclodupzcasjqyruxovuoempvurpahfljtbmpqnrtibjgsfgiaczeqqckjtkqzxauzojrcdkkgtsabajbfkivakikfscgscattmkvpvhvqbvtcgvjqfetrofwhhdbmfufrecgbjdumbnohkxapevguafbjiexnyehdipgttcguqudcufsaaaucfyopcnfdsmiadowwrcsjyylsdfugirkppyftmmwgaeidvecogwzfukzaswgcnqreryzfmwlmcvszcuniqmplzrltntvcjogcpfhbduqiqihscvcujuhilubanyczpibepjhvdxdvhkplhsgronbzidzxdbwslyycjixofckpnbawvgpjwrigwjdzmauzauclcbzkelztnzpkifugyemuopvcrrctmgeqhgalrbegdurlbntzrftfwqkoimhwsomzuplnqrwtlngoazntgizdbjrjxahpqtqkidybvwwijfxlemenxfqyqhjpjsganxmwamzxivgtafehtlwqsmqlvbaethghgtfgfggodmjetqnmbjdvxvgsxjyssfbyaigfomvsyfyrvneyyjvvgenfnlyhsbfsklayyxsyeqsbdyzbhxypbnvqztxtwemgpohplzqqqirvgtzpqxetlzlmukfrotxfhevvgnlwvetdzssrsykdyxruhylvslbb...", res);

        }catch (Exception e){

        }

    }
}
