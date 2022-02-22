package yanni.com.codingpractice4;

import org.junit.jupiter.api.Test;

public class ReverseStringInPlace {

    public void reverseString(char [] word) {

        int size = word.length;
        int m = size/2;
        int reverse = size-1;

        for (int i=0;i<size;i++) {
            if(i==m && m%2!=0) {
               break;
            }  else if (i>m && m%2==0) {
                break;
            }
            char tmp = word[i];
            word[i] = word[reverse];
            word[reverse] = tmp;
            reverse--;
        }
    }


    @Test
    public void testReverseString() {

        String word = "fuckit";
        char[] myWord = word.toCharArray();

        reverseString(myWord);
        assert(myWord[0] == 't');
        assert(myWord[myWord.length-1] == 'f');
    }

    @Test
    public void testReverseString2() {

        String word = "poopyface";
        char[] myWord = word.toCharArray();

        reverseString(myWord);
        assert(myWord[0] == 'e');
        assert(myWord[myWord.length-1] == 'p');
    }
}
