package yanni.com.codingpractice4;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubStr {

    public int lengthOfLongestSubstring(String s) {
        int length =s.length();
        int ind = 0;
        String output ="";
        while(ind<length){

            char [] chars = s.toCharArray();
            Set<Character> seen = new HashSet();
            String result ="";

            for(int i=ind;i<chars.length;i++) {


                if(result.equals("") || !seen.contains(chars[i])) {
                    result +="" + chars[i];
                } else {
                    break;
                }
                seen.add(chars[i]);
            }
            if(result.length()>output.length())
                output = result;

            ind++;

        }

        return output.length();

    }

    public int lengthOfLongestSubstring2(String s) {
        if(s.length()==0) {
            return 0;
        }
        char[] myChars = s.toCharArray();
        String chk = "";
        String pal =myChars[0]+"";

        for(int i=0;i<myChars.length;i++) {
            chk=myChars[i]+"";
            int[] myCharCount = new int[256];
            myCharCount[myChars[i]]=1;
            for(int ind=i+1;ind<myChars.length;ind++) {
                if(myCharCount[myChars[ind]]==0){
                    chk+=myChars[ind]+"";
                    myCharCount[myChars[ind]]=1;
                } else {
                    break;
                }
            }
            if(chk.length() > pal.length()){
                pal = chk;
            }
        }
        return pal.length();
    }

    public int lengthOfLongestSubstringOpt(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public int lengthOfLongestSubstringOpt2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    @Test
    public void testLengthOfLongestSubstringOpt() {
        assert(lengthOfLongestSubstringOpt("pwwkew")==3);
    }

}
