package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;
import yanni.com.reusablecode.SuffixTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SubstringConcateAllWords {

    public List<Integer> findSubstring(String s, String[] words) {
        // building the tree takes summation of Sn (n(n+1))/2 same with space complexity.
        // tree size also n! i believe
        SuffixTree tree = new SuffixTree(s);
        Map<String, List> wordPosition = new HashMap<>();
        Map<Integer, String> indexWord = new HashMap<>();
        List<Integer> output = new ArrayList();
        for (int i=0; i< words.length; i++) {
            String word = words[i];
            List<Integer> foundWord = tree.search_tree(word);
            for(Integer ind : foundWord) {
                indexWord.put(ind, word);
            }
            if(foundWord.size()>0)
                wordPosition.put(word, foundWord);
            else
                return output;
        }
        List wordsList = new ArrayList();
        Arrays.stream(words).forEach(element-> wordsList.add(element));
        return findSubstring(wordPosition, indexWord, wordsList);
    }

    // run time worst case big o n^3
    private List findSubstring(Map<String, List> wordPosition, Map<Integer, String> indexWord, List<String> words) {
        List<Integer> output = new ArrayList<>();
        for ( String key : wordPosition.keySet()) {
            List<Integer> positions = wordPosition.get(key);
            for (Integer pos : positions) {
//                int indexLeft = findIndexSubStr(key, words.subList(0, words.size()), indexWord, pos, pos, true);
                List<String> wordsCopy = words.stream().collect(Collectors.toList());
                wordsCopy.remove(key);
                int indexRight = findIndexSubStr(key, wordsCopy, indexWord, pos, pos);
//                if (indexLeft >= 0) {
//                    output.add(indexLeft);
//                }
                if (indexRight >= 0) {
                    output.add(indexRight);
                }
            }
        }
        return output;
    }

    //try to do this iterative way
    private Integer findIndexSubStr(String key, List words, Map<Integer, String> indexWord, int position, Integer smallestIndex) {
        if(words.size()==0) {
            return smallestIndex;
        }
            // in this case smallestIndex don't change
        String word = indexWord.get(position + key.length());
        if (word != null && words.contains(word)) {
            position = position + key.length();
            words.remove(word);
            return findIndexSubStr(key, words, indexWord, position, smallestIndex);
        } else {
            return -1;
        }
    }

//    private Integer findIndexSubStr(String key, List words, Map<Integer, String> indexWord, int position, Integer smallestIndex, boolean goingLeft) {
//        if(words.size()==1 || words.size()==0) {
//            return smallestIndex;
//        }
//        if (goingLeft) {
//            String word = indexWord.get(position - key.length());
//            if (word != null) {
//                smallestIndex = position - key.length();
//                words.remove(word);
//                return findIndexSubStr(key, words, indexWord, smallestIndex, smallestIndex, true);
//            } else {
//                return -1;
//            }
//        } else {
//            // in this case smallestIndex don't change
//            String word = indexWord.get(position + key.length());
//            if (word != null) {
//                position = position + key.length();
//                words.remove(word);
//                return findIndexSubStr(key, words, indexWord, position, smallestIndex, false);
//            } else {
//                return -1;
//            }
//        }
//    }

    public List<Integer> findSubstring2(String s, String[] words) {

        Map<Character, Integer> letterOccurances = new HashMap();

//        for(String word : words) {
//            for (Character ch : word.toCharArray() ){
//                Integer occurrence = letterOccurances.get(ch) == null ? 0 : letterOccurances.get(ch) ;
//                if (letterOccurances.get(ch) > 0) {
//                    letterOccurances.put(ch, occurrence++);
//                } else {
//                    letterOccurances.put(ch, occurrence);
//                }
//            }
//        }

        Map<Character, Integer> letterOccuranceZeros = new HashMap();
        for (String word : words) {
            for (Character ch : word.toCharArray() ){
                Integer occurrence = letterOccurances.get(ch) ;
                letterOccuranceZeros.put(ch, 0);
                if ( occurrence == null ) {
                    letterOccurances.put(ch, 1);
                }
                else {
                    occurrence++;
                    letterOccurances.put(ch, occurrence);
                }
            }
        }


        Map<Character, Integer> letterOccurancesClone = new HashMap(letterOccurances);
        boolean returnNothing = true;

        for (Character ch : s.toCharArray()) {
            Integer chCount = letterOccurancesClone.get(ch);
            if (chCount == null) {
                letterOccurancesClone = new HashMap(letterOccurances);
                continue;
            } else {
                if (chCount < 0 ){
                    letterOccurancesClone = new HashMap(letterOccurances);
                    continue;
                }
                letterOccurancesClone.put(ch, chCount--);
            }
            if (letterOccuranceZeros.hashCode() == letterOccurancesClone.hashCode()) {
                letterOccurancesClone = new HashMap(letterOccurances);
                returnNothing = false;
            }
        }

        if (returnNothing)
            return null;



        return null;
    }

    @Test
    public void testFindSubString() {

        String input = "barfoothefoobarman";
        String [] words = {"foo","bar"}; // all words exactly once no interruption any order. for this input Output: [0,9]
        SubstringConcateAllWords substringConcateAllWords = new SubstringConcateAllWords();

        List indexes = substringConcateAllWords.findSubstring(input, words);
        System.out.println(indexes);

//        input = "wordgoodgoodgoodbestword";
//        String [] words2 = {"word","good","best","word"};
//
//        indexes = substringConcateAllWords.findSubstring(input, words2);
//        System.out.println(indexes);
    }


    @Test
    public void testFindSubString2() {


        String input = "wordgoodgoodgoodbestword";
        String [] words2 = {"word","good","best","word"};
        SubstringConcateAllWords substringConcateAllWords = new SubstringConcateAllWords();

        List indexes = substringConcateAllWords.findSubstring(input, words2);
        System.out.println(indexes);
    }

    @Test
    public void testFindSubString3() {


        String input = "wordgoodgoodgoodbestword";
        String [] words2 = {"good","good","best","word"};
        SubstringConcateAllWords substringConcateAllWords = new SubstringConcateAllWords();

        List indexes = substringConcateAllWords.findSubstring(input, words2);
        System.out.println(indexes);
    }

}
