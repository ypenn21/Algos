package yanni.com.codingpractice4;

import org.junit.jupiter.api.Test;

public class KthGrammar {
    String zero ="01";
    String one = "10";
    String row1 = "0";

    // N rows and K index
    public int kthGrammar(int N, int K) {
        String kStr = "";
        String str = createKthSymStr(N, row1);
        System.out.println("result:"+str);
        char[] myChars = str.toCharArray();
        return Integer.parseInt(myChars[K-1]+"");
    }

    // need to optimize too slow
    public String createKthSymStr(int N, String kStr) {
        String newResult = "";
        if(N==1){
            return row1;
        } else {
            kStr = createKthSymStr(N-1, kStr);
            char[] myChars = kStr.toCharArray();
            for(int i = 0; i < myChars.length; i++) {
                if(myChars[i]=='0') {
                    newResult+=zero;
                } else if(myChars[i]=='1') {
                    newResult+=one;
                }
            }
        }
        return newResult;
    }

    @Test
    void testKthGrammar() {
       int result = kthGrammar(3, 3);
       assert(result==1);
    }


}
