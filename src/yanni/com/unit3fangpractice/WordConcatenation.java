package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WordConcatenation {

    public List findAllConcatenatedWords(List<String> input) {

        Map<String, Integer> dictionary = new HashMap<>();

        for(String word : input) {
            if(dictionary.containsKey(word)) {
                dictionary.put(word, dictionary.get(word)+1);
            } else {
                dictionary.put(word, 1);
            }
        }


        List result = findAllConcatenatedWords(input, dictionary);
        return result;
    }

    private List findAllConcatenatedWords(List<String> input, Map<String, Integer> dictionary) {
        List<String> result = new ArrayList();
        for (String word : input) {
            if(findAllConcatenatedWords(word, dictionary).size()>0) {
                result.add(word);
            }
        }

        return result;
    }

    // if you want to be able to make this work for more than one word concatenating together then you must use recursion
    private List findAllConcatenatedWords(String input, Map<String, Integer> dictionary) {
        List<String> result = new ArrayList();
        char[] myLetters = input.toCharArray();
        for (int i=0;i<myLetters.length;i++) {
            String prefix = input.substring(0, i);
            String suffix = input.substring(i, myLetters.length);
            if(dictionary.containsKey(prefix) ) {
                if(dictionary.containsKey(suffix) || findAllConcatenatedWords(suffix, dictionary).size()>0){
                    result.add(input);
                }
            }
        }
        return result;
    }


    // if you want to be able to make this work for more than one word concatenating together then you must use recursion
    // try to implement this with a trie actually way more complicated not worth doing...
    public List findAllConcatenatedWordsWithTrie(List<String> input) {
        AutoSpellWordsWithTrieBuilder trie = new AutoSpellWordsWithTrieBuilder();
        String[] inputArray = new String[input.size()];
        trie.root = trie.buildTrie(input.toArray(inputArray));

        List<String> result = new ArrayList();
        Set<String> cache = new HashSet();

        for (String word : input) {
            AutoSpellWordsWithTrieBuilder.TrieNode node = trie.root;
            Integer wordCount = 0;
            for(Character letter: word.toCharArray()) {
                node = node.children.get(letter);
                String preffix="";
                final String suffix;
                if(node.endOfWord) {
                    wordCount++;
                    preffix = node.word;
                    suffix = word.substring(preffix.length());
                    cache.add(node.word);
                } else {
                    suffix =null;
                }

                if(suffix!=null && ( cache.contains(suffix) || input.stream().filter(wrd -> wrd.equals(suffix.toString())).findFirst().isPresent() )) {
                    if(suffix.equals(preffix)) {
                        List sameWords = input.stream().filter(wrd -> wrd.equals(suffix.toString())).collect(Collectors.toList());
                        if(sameWords.size()>1) {
                            result.add(word);
                        }
                        System.out.println(sameWords);
                    } else {
                        result.add(word);
                    }
                }
            }
        }
        return result;
    }

    @Test
    public void testFindAllConcatenatedWordsWithTrie() {
//        AutoSpellWordsWithTrieBuilder

        List<String> input = new ArrayList<String>() {{
            add("cat");
            add("cat");
            add("cat");
            add("catcatcat");
            add("cats");
            add("dog");
            add("catsdog");
            add("catsdogdog");
        }};

        List results = findAllConcatenatedWordsWithTrie(input);
        System.out.println(results);
    }


    @Test
    public void testFindAllConcatenatedWords() {
//        AutoSpellWordsWithTrieBuilder

        List<String> input = new ArrayList<String>() {{
            add("cat");
            add("cat");
            add("cat");
            add("catcatcat");
            add("cats");
            add("dog");
            add("catsdog");
            add("catsdogdog");
        }};

        List results = findAllConcatenatedWords(input);
        System.out.println(results);
    }
}
