package yanni.com.codingpractice4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneNumber {
    Map<String, String> phone  = new HashMap<String, String>() {{
        put("1", "");
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
        put("*", "+");
    }};

    int length = 0;
    List<String> results = new ArrayList<String>();

    @Test
    public void testLetterCombinations() {
        PhoneNumber phone = new PhoneNumber();
       List<String> mylist = phone.letterCombinations("23");
       assert(mylist.size()>3);
       System.out.println(mylist);
       System.out.println(mylist.get(0));
    }

    public List<String> letterCombinations(String digits) {
        String result = "";
        if(digits==null || digits.length()==0) {
            return results;
        }
        char[] myChars = digits.toCharArray();

        if(length==0)
            length = myChars.length;
        results = letterCombinations2( digits, 0, "");
        return results;
    }

    public List<String> letterCombinations2(String digits, int index, String result) {
        if(digits==null || digits.length()==0 || index >= digits.length()) {
            return results;
        }
        char[] myChars = digits.toCharArray();

        if(length==0)
            length = myChars.length;

//        for(int i = 0 ; i < myChars.length ;i++) {
            String letters = phone.get(myChars[index]+"");
            char[] letts = letters.toCharArray();
            for(int j =0; j<letts.length; j++) {
                result += letts[j];

                if(result.length()==length) {
                    results.add(result);
                }

                results = letterCombinations2(digits, index+1, result);
//                if(result.length() >= length && result.length()>0)
                result = result.substring(0, result.length()-1);
            }
//        }
        return results;
    }
}
