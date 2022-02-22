package yanni.com.codingpractice4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {

        if(cur.length()==max*2) {
            ans.add(cur.toString());
            return;
        }


        if(open<max) {
            cur.append("(");
            backtrack( ans, cur, open+1, close, max);
            cur.deleteCharAt(cur.length()-1);
        }

        if(close<open) {
            cur.append(")");
            backtrack( ans, cur, open, close+1, max);
            cur.deleteCharAt(cur.length()-1);
        }
    }

    @Test
    public void testGenerateParenthesis() {
        List<String> ans = generateParenthesis(3);
        System.out.println(ans);
    }

}
