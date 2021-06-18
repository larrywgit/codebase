package algorithms;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Anagrams {
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> letterMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        p.chars().forEach(c ->
        letterMap.merge((char) c, 1, (a,b) ->a+b));
        int counter= letterMap.size();

        int start=0,end=0;
        while(end< s.length()){
            if (letterMap.keySet().contains(s.charAt(end))){
                int count = letterMap.compute(s.charAt(end), (k,v)->v=v-1);
                if (count == 0) {
                    counter--;
                }
            }

            while(counter ==0){
                if (letterMap.keySet().contains(s.charAt(start))){
                    if (end - start == p.length() -1 )
                        result.add(start);
                    int count = letterMap.compute(s.charAt(start), (k,v)->v=v+1);
                    if (count > 0) {
                        counter++;
                    }
                }
                start ++;
            }

            end++;
        }

        return result;
    }


    @Test
    public void test1(){
        List<Integer> res = new Anagrams().findAnagrams("cbaebabacd" ,"abc");
        List<Integer> exp = Arrays.asList(0,6);
        assertEquals(exp, res);

    }

    @Test
    public void test2(){
        List<Integer> res = new Anagrams().findAnagrams("baa" ,"aa");
        List<Integer> exp = Arrays.asList(1);
        assertEquals(exp, res);

    }

}
