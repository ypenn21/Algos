package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CircleOfChainOfWords {
    public boolean isChainedWords (String[] words) {
        Map<String, List<String>> letterWord = new LinkedHashMap<>();
        for(String word:words) {
            List<String> wordsForLetter = new ArrayList<>();
            if(letterWord.get(word.substring(0,1))!=null) {
                wordsForLetter = letterWord.get(word.substring(0,1));
                wordsForLetter.add(word);
                letterWord.put(word.substring(0, 1), wordsForLetter);
            } else {
                wordsForLetter.add(word);
                letterWord.put(word.substring(0, 1), wordsForLetter);
            }
        }
        Iterator<String> keyIter =  letterWord.keySet().iterator();
        List<String> firstWord = letterWord.get(keyIter.next());
        return isChainedWords (firstWord.get(0), letterWord, letterWord.size(), new HashSet<>());
    }

    private boolean isChainedWords (String word, Map<String, List<String>> letterWord, Integer length, Set<String> visted) {
        boolean result=false;
        if(word==null)
            return result;
        if(length==1) {
            Iterator<String> keyIter = letterWord.keySet().iterator();
            String firstWord = letterWord.get(keyIter.next()).get(0);
            if(firstWord.substring(0,1).equals(word.substring(word.length()-1))){
                result = true;
            }
            return result;
        }
        String lastLetter = word.substring(word.length()-1);
        List<String> matchingWords = letterWord.get(lastLetter);
        for(String matchingWord : matchingWords) {
            if (!visted.contains(matchingWord) && isChainedWords(matchingWord, letterWord, length - 1, visted)) {
                result = true;
            }
        }
        return result;
    }


    @Test
    public void testIsChainedWords() {
        String [] myWords = new String[] {"apple", "eggs", "snack", "karat", "tuna"};
        System.out.println(isChainedWords(myWords));
    }


}
