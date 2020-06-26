package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList, Set<String> wordsChecked ) {
        wordsChecked.add(beginWord);
        List<String> diffRoutes = new ArrayList<>();
        Integer howManytries=0;

//        while (!beginWord.equals(endWord)) {
        for (String word : wordList) {
            if(isWordRoute(beginWord, word )) {
                    diffRoutes.add(word);
            }
        }
        if(diffRoutes.stream().filter(route-> route.equals(endWord)).findAny().isPresent()){
            howManytries=2; // two because counts the begin word and all the other words to get to end word including end word itself
            return howManytries;
        }

        for(String route: diffRoutes){
            if(howManytries>0){
                break;
            }
            if(!wordsChecked.contains(route)) {
                howManytries = ladderLength(route, endWord, wordList, wordsChecked);
                wordsChecked.remove(route);
                if(howManytries>0){
                    howManytries++;
                    return howManytries;
                }
            }

        }
        return howManytries;
//        }
    }

    private boolean isWordRoute(String beginWord, String word) {
        if (beginWord.equals(word)){
            return false;
        }
        int [] wordIndex = new int[253];
        char[] beginWordArr = beginWord.toCharArray();
        char[] wordArr = word.toCharArray();
        int count=0;

        for (char ch : beginWordArr){
            wordIndex[ch]++;
        }

        for (char ch : wordArr){
            wordIndex[ch]++;
            if(wordIndex[ch]>1){
                count++;
            }
        }
        if(count>1) {
            return true;
        }
        return false;
    }

    @Test
    public void testLadderLength() {
        List<String> words = new ArrayList<>();
        words.add("hot");
        words.add("dot");
        words.add("dog");
        words.add("lot");
        words.add("log");
        words.add("cog");
        Set mySetFoundWord = new HashSet<String>();
        Integer howManyTres=0;

        howManyTres = ladderLength("hit", "cog", words, mySetFoundWord);
        System.out.println(mySetFoundWord);
        System.out.println(howManyTres);

    }


    @Test
    public void testLadderLength2() {
        List<String> words = new ArrayList<>();
        words.add("hot");
        words.add("dot");
        words.add("dogs");
        words.add("lot");
        words.add("log");
        Set mySetFoundWord = new HashSet<String>();
        Integer howManyTres=0;

        howManyTres = ladderLength("hit", "cog", words, mySetFoundWord);
        System.out.println(mySetFoundWord);
        System.out.println(howManyTres);

    }


    @Test
    public void testLadderLength3() {
        List<String> words = new ArrayList<>();
        words.add("hot");
        words.add("dot");
        words.add("doa");
        words.add("doc");
        words.add("dog");
        words.add("lot");
        words.add("log");
        words.add("cog");
        Set mySetFoundWord = new HashSet<String>();
        Integer howManyTres=0;

        howManyTres = ladderLength("hit", "dog", words, mySetFoundWord);
        System.out.println(mySetFoundWord);
        System.out.println(howManyTres);

    }
}
