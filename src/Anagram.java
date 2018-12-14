import java.util.Arrays;

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

    public boolean isAnagram2(String firstWord, String secondWord) {
        boolean isAnagram = false;
        if(firstWord.length() != secondWord.length()){
            return isAnagram;
        } else {
            isAnagram = true;
        }
        int[] countFirstLetters = getAlphabetCountArray(firstWord.toCharArray());
        int[] countSecondLetters = getAlphabetCountArray(secondWord.toCharArray());
        for(int index=0 ; index<countFirstLetters.length ; index++){
            if(countFirstLetters[index] != countSecondLetters[index]){
                return false;
            }
        }
        return isAnagram;
    }

    public static int[] getAlphabetCountArray(char[] characters){
        int[] countArray = new int[26];
        for(Character character : characters){
            int index = character-'a';
            countArray[index]++;
        }
        return countArray;
    }

    public static void main(String[] args) {

        Anagram anagram = new Anagram();

        String word1 = "heyya";
        String word2 = "ywhey";

//        System.out.println("isAnagram: "+anagram.isAnagram2(word1,word2));
                                                                            //abba is a even example
//        System.out.println("isPalindrome:" +anagram.isPalindrome("amoreroma"));
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
