package yanni.com.unit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class SwapIntegers {

    public class NumberWrapper{
        Integer val;
        public NumberWrapper(Integer num){
          this.val= num;
        }

    }

    public void swapNums(NumberWrapper a, NumberWrapper b) {
        System.out.println(a.val+" "+b.val);
        Integer temp = a.val;
        a.val=b.val;
        b.val=temp;
        System.out.println(a.val+" "+b.val);
    }

    public void swapNums2(NumberWrapper a, NumberWrapper b) {
        System.out.println(a.val+" "+b.val);
        Integer temp = a.val;
        a.val = b.val;
        b.val = temp;
        System.out.println(a.val+" "+b.val);
    }


    public int fibnumbers(int n) {
        if(n<=2){
            return n;
        }
        return fibnumbers(n-1)+ fibnumbers(n-2);
    }

    public int fibNumbersIterative(int n) {
        if(n<=2){
            return n;
        }
        HashMap<Integer, Integer> myMap = new HashMap();
        myMap.put(0, 0);
        myMap.put(1, 1);
        myMap.put(2, 2);
        int i =3;
        while(i<=n) {
            int fib = myMap.get(i-1)+myMap.get(i-2);
            myMap.put(i, fib);
            i++;
        }
        return myMap.get(n);
    }

    public int fibNumbersIterativeNMap(int n) {
        if(n<=2){
            return n;
        }
        int preprev = 1;
        int prev = 1;
        int current = 0;
        int i =3;
        while(i<=n) {
            current = prev + preprev;
            prev = current;
            preprev = prev;
            i++;
        }
        return current;
    }

    public boolean canSpell(char[]letters, String word) {
        boolean result=true;
        char[] charWord = word.toCharArray();
        int[] az = new int[256];

        for(Character c: letters) {
            az[c]++;
        }

        for(int i=0;i<charWord.length;i++) {
            int count = az[charWord[i]];
            if(count==0) {
                result=false;
                break;
            }
        }
        return result;

    }

    @Test
    public void testCanSpell() {
        //int [] myArray = new int[] {1,2,3,4,5,6,};
        System.out.println(canSpell(new char[] {'a', 'b', 'c', 'd', 'e', 'f'}, "cat"));
    }

    @Test
    public void testChar() {
        HashMap<String, List<String>> myMap = new HashMap<>();

        createMap("abc", myMap);
        createMap("bca", myMap);
        createMap("acb", myMap);

        createMap("fcm", myMap);
        createMap("mcf", myMap);

        createMap("bva", myMap);

        System.out.println(myMap);
    }

    public void createMap(String str, Map hashMap) {
        int[] myChars = new int[26];
        for(Character c : str.toCharArray()) {
            myChars[c - 'a'] += 1;
        }
        List myCharList = new ArrayList();
        for (Integer ch: myChars){
            myCharList.add(ch);
        }

        List strs = (List) hashMap.get(myCharList.toString());
        if ( strs == null )
            strs = new ArrayList();
        strs.add(str);
        hashMap.put(myCharList.toString(), strs);
    }

    @Test
    public void getRanges() {
        int[] myArray = new int[] {1,2,3,4,5,7,8,9,11,12,17,19};
        System.out.println(createRangeList(myArray));

        int[] myArray2 = new int[] {1,2,3,4,5,7,8,9,11,12,17,18, 21};
        System.out.println(createRangeList(myArray2));
    }

    public List createRangeList(int[] myArray) {

        List<String> result = new ArrayList<>();

        Integer high = myArray[0];
        Integer low = myArray[0];
        boolean added;

        for (Integer num : myArray) {
            if( high+1<num && high!=myArray[0]) {
                result.add(low+"-"+high);
                low = num;
                high =num;
                added=true;
            } else {
                high = num;
                added =false;
            }
        }

        result.add(low+"-"+high);

        return result;

    }

    @Test
    public void testFibNumbersIterative() {
        //int [] myArray = new int[] {1,2,3,4,5,6,};

        System.out.println(fibNumbersIterative(5));
        System.out.println(fibNumbersIterativeNMap(5));
    }

    @Test
    public void testFibnumbers() {

        System.out.println(fibnumbers(5));
    }

    @Test
    public void testSwapNums() {
        NumberWrapper a = new NumberWrapper(new Integer(2));
        NumberWrapper b =new NumberWrapper(3);
        swapNums( a, b );
        System.out.println(a.val+" "+b.val);

        //Arrays.sort();
    }

}
