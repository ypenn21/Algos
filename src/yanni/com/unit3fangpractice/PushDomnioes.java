package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PushDomnioes {

    public String pushDominoes (String dominoes) {
        Integer length = dominoes.length();
        int [] dominoesNum = new int[length];

        int distance =0;

        int i =0;
        char[] domChar = dominoes.toCharArray();
        // populate Rs for dominoesNum
        for(Character lr : domChar){
            if (lr.equals('R')){
                distance = length;
            } else if (lr.equals('L')){
                distance = 0 ;
            } else {
                distance = Math.max(distance-1, 0);
            }
            dominoesNum[i] += distance;
            i++;
            //Math.max()
        }

        // populate Ls for dominoesNum
        for(int ind = domChar.length-1; ind>=0 ; ind--) {
            if (domChar[ind] == 'L'){
                distance = length;
            } else if (domChar[ind] == 'R'){
                distance = 0 ;
            } else {
                distance = Math.max(distance-1, 0);
            }
            dominoesNum[ind] -= distance;
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            if (dominoesNum[index] == 0) {
                result += ".";
            } else if (dominoesNum[index] > 0) {
                result += "R";
            } else {
                result += "L";
            }
        }

        return result;
    }



    @Test
    public void testPushDominoes () {
        PushDomnioes pushDom = new PushDomnioes();

        String result = pushDom.pushDominoes(".L.R...LR..L..");
        System.out.println(result);
    }




}
