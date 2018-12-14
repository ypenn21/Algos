package yanni.com.unit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ArrayProblems {

    @Test
    public void printPyramid() {

        printPyramid(5);

    }

    private void printPyramid(int nLvls) {
        for(int i=0;i<nLvls;i++) {
            for(int j=0;j<nLvls-i;j++) {
                System.out.print(" ");
            }
            for (int k = 0; k <= i; k++) {
                System.out.print("$ ");
            }
            System.out.println();
        }
    }

    private void printPyramid3(int nLvls) {
        int [] lvlcount = new int[nLvls];
        for(int i=0;i<nLvls;i++) {
            for(int j=0;j<nLvls-i;j++) {
                System.out.print(" ");
            }
            if(i==0) {
                lvlcount[i] = 1;
                System.out.print("$");
            } else {
                for (int k = 0; k < lvlcount[i-1] + 2; k++) {
                    System.out.print("$");
                    lvlcount[i] = lvlcount[i-1] + 2;
                }
            }
            System.out.println();
        }
    }

    private void printPyramid2(int nLvls) {
        int midpoint =  new Double(Math.floor((nLvls * 2 -1) / 2)).intValue();
        for(int lvl=0;lvl<nLvls;lvl++) {
            for(int col=0;col<1*nLvls-1;col++) {
                if(midpoint - lvl <= col && midpoint + lvl >= col){
                    System.out.print("$");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    @Test
    public void testNestedChild(){
        int a2[] = {1, 2};
        List<List<Integer>> chunkedArray = chunkArray(a2, 2);
        System.out.println(chunkedArray);
    }

    @Test
    public void testKCombinations(){
        char chars[] = {'a','b', 'c', 'd'};
        //findComboCharsOfKLength(chars, "", chars.length);
        combination(chars);
    }

    void findComboCharsOfKLength(char chars[], String prefix, int k){

        if(prefix.length()==k){
            System.out.println(prefix);
            return;
        }

        for (int i=0; i<chars.length; i++) {
            // if you just did "prefix += chars[i]" will get stack overflow
            String newPrefix = prefix + chars[i];
            findComboCharsOfKLength(chars, newPrefix, k);
        }

    }

    void findAllComboChars(char input[], int count[], char output[], int startPos, int end){

        for( int start = startPos; start < end ; start++){
            System.out.print(output[start]);
        }
        System.out.println("");
        System.out.println("----");

        for (int i=startPos; i<input.length; i++) {
            if(count[i] == 0) {
                continue;
            }
            // if you just did "prefix += chars[i]" will get stack overflow
            output[end] = input[i];
            count[i]--;
            findAllComboChars(input, count, output, startPos, end+1);
            count[i]++;
        }

    }

    public void combination(char input[]){
        Map<Character, Integer> countMap = new TreeMap<>();
        for (char ch : input) {
            countMap.compute(ch, (key, val) -> {
                if (val == null) {
                    return 1;
                } else {
                    return val + 1;
                }
            });
        }
        char str[] = new char[countMap.size()];
        int count[] = new int[countMap.size()];
        int index = 0;
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            str[index] = entry.getKey();
            count[index] = entry.getValue();
            index++;
        }
        char[] output = new char[input.length];
        findAllComboChars(str, count, output, 0, 0);
    }

    private List chunkArray(int array[], int chunkLength) {
        List<List<Integer>> chunkedArray = new ArrayList();
        for (int i=0; i<array.length;i=i+0) {
            List<Integer> chunkedSubArray = new ArrayList();
            int incrementIndexBy=0;
            for(int index=i;index<i+chunkLength;index++){
                if(index<array.length) {
                    chunkedSubArray.add(array[index]);
                    incrementIndexBy++;
                }
            }
            chunkedArray.add(chunkedSubArray);
            i+=incrementIndexBy;

        }
        return chunkedArray;
    }


}
