import javafx.scene.layout.Priority;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by yannipeng on 2/14/18.
 */
public class Anagram {

    public boolean isAnagram(String firstWord, String secondWord) {
        char[] word1 = firstWord.replaceAll("[\\s]", "").toCharArray();
        char[] word2 = secondWord.replaceAll("[\\s]", "").toCharArray();
        Arrays.sort(word1);
        Arrays.sort(word2);
        return Arrays.equals(word1, word2);
    }

    public boolean isPalindrome(String firstWord) {
        char[] word1 = firstWord.toCharArray();
        int forward =0;
        int middle = word1.length/2;
        for (int backward = word1.length-1; backward >=middle ;backward--){
            if (word1[backward] != word1[forward]){
                return false;
            }
            forward++;
        }

//        Arrays.sort(word1);
//        Arrays.sort(word2);
        return true;
    }

    public boolean isPalindrome2(String firstWord) {
        char[] word1 = firstWord.toCharArray();

        Map<Character, Integer> myMap = new HashMap<>();

        for(Character c: word1) {
            Integer count = myMap.get(c);
            if(count==null) {
                count=1;
            } else {
                count++;
            }
            myMap.put(c, count);
        }

        String onlyOneOddNum=null;

        int index=0;
        System.out.println("my size / 2 = "+myMap.size()/2);
        for( Character character : myMap.keySet()) {
            Integer count = myMap.get(character);
            if(count%2==0) {

            } else if(onlyOneOddNum==null && word1.length%2!=0 && myMap.size()/2==index) {
                onlyOneOddNum = character+"";
            } else {
              return false;
            }
            index++;
        }
        return true;
    }



    public boolean isAnagram2(String firstWord, String secondWord) {
        boolean isAnagram = false;
        if(firstWord.length() != secondWord.length()){
            return isAnagram;
        } else {
            isAnagram = true;
        }
        int[] countFirstLetters = getAlphabetCountArray(firstWord.toCharArray());
        char[] countSecLetters = secondWord.toCharArray();

        for( Character let : countSecLetters ){
            if (countFirstLetters[let] ==0) {
                isAnagram = false;
                break;
            }
        }

//        int[] countSecondLetters = getAlphabetCountArray(secondWord.toCharArray());
//        for(int index=0 ; index<countFirstLetters.length ; index++){
//            if(countFirstLetters[index] != countSecondLetters[index]){
//                return false;
//            }
//        }
        return isAnagram;
    }


    public boolean isPalindrome3(String firstWord) {

        char[] charArray = firstWord.toCharArray();

        int i = 0;
        boolean isPalind = true;
        PriorityQueue<Integer> queue = new PriorityQueue();
        
        for (int back=charArray.length-1; back>charArray.length/2;back--) {
            if(charArray[back] != charArray[i]) {
                isPalind=false;
                break;
            }
            i++;
        }
        return isPalind;

    }

    public static int[] getAlphabetCountArray(char[] characters){
        int[] countArray = new int[256];
        for(Character character : characters){
            //int index = character-'a';
            countArray[character]++;
        }
        return countArray;
    }

    public int sherlockAndAnagrams(String s) {
        Map<String, Integer> map = new HashMap<>();
        int n=0;
        for(int i=0; i< s.length(); i++){
            for(int j = i+1; j<=s.length(); j++){
                char[] c= s.substring(i,j).toCharArray();
                Arrays.sort(c);
                String str = String.valueOf(c);
                if(map.containsKey(str))
                    map.put(str, map.get(str)+ 1);
                else
                    map.put(str,1);
            }
        }
        for(String key: map.keySet()){
            n += (map.get(key)*(map.get(key)-1))/2;
        }
        return n;
    }

    public static void main(String[] args) {

        Anagram anagram = new Anagram();

        String word1 = "heyya";
        String word2 = "yahey";

        int result = anagram.sherlockAndAnagrams(word2);

        System.out.println( "anagram is: "+result );

        System.out.println( anagram.isAnagram2(word1, word2) );

//        System.out.println("isAnagram: "+anagram.isAnagram2(word1,word2));
                                                                            //abba is a even example
        System.out.println("isPalindrome:" +anagram.isPalindrome3("amorroama"));
//        System.out.println("isPalindrome:" +anagram.isPalindrome2("amorroama"));
//        System.out.println("isPalindrome2:" +anagram.isPalindrome2("aba"));
//        System.out.println("isPalindrome2:" +anagram.isPalindrome2("aaaaa"));
        System.out.println("isPalindrome3:" +anagram.isPalindrome3("amorroama"));
        System.out.println("isPalindrome3:" +anagram.isPalindrome3("aba"));
        System.out.println("isPalindrome3:" +anagram.isPalindrome3("aaaaa"));
        System.out.println("isPalindrome3:" +anagram.isPalindrome3("abaaba"));
        System.out.println("isPalindrome3:" +anagram.isPalindrome3("ababa"));
        System.out.println("isPalindrome3:" +anagram.isPalindrome3("abada"));
        System.out.println("isPalindrome3:" +anagram.isPalindrome3("abxba"));
//        System.out.println("fib number is: "+getNFibNumber(7));
        System.out.println("First Test");
        char[] set1 = {'a', 'b'};
        int k = 3;
        printAllKLength(set1, k);

//        System.out.println("\nSecond Test");
//        char[] set2 = {'a', 'b', 'c', 'd'};
//        k = 1;
//        printAllKLength(set2, k);
    }

    public static int getNFibNumber(int n) {
        if (n<=2){
            return n-1;
        } else {
            return getNFibNumber(n-1) + getNFibNumber(n-2);
        }
    }


    // The method that prints all
// possible strings of length k.
// It is mainly a wrapper over
// recursive function printAllKLengthRec()
    static void printAllKLength(char[] set, int k)
    {
        int n = set.length;
        printAllKLengthRec(set, "", n, k);
    }

    // The main recursive method
// to print all possible
// strings of length k
    static void printAllKLengthRec(char[] set,
                                   String prefix,
                                   int n, int k)
    {

        // Base case: k is 0,
        // print prefix
        if (k == 0)
        {
            System.out.println(prefix);
            return;
        }

        // One by one add all characters
        // from set and recursively
        // call for k equals to k-1
        for (int i = 0; i < n; ++i)
        {

            // Next character of input added
            String newPrefix = prefix + set[i];

            // k is decreased, because
            // we have added a new character
            printAllKLengthRec(set, newPrefix,
                    n, k - 1);
        }
    }

}
