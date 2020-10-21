package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AutoSpellWordsWithTrieBuilder {

    TrieNode root;

    public class TrieNode {

        public void setLetter(Character letter) {
            this.letter = letter;
        }

        Character letter;
        String word;

        public TrieNode() {
        }
        public TrieNode(Character character) {
            this.letter = character;
        }

        Map<Character, TrieNode> children = new HashMap<>();
        boolean endOfWord;

    }

    public TrieNode buildTrie(String [] dictionary) {
        TrieNode node = new TrieNode();

        for(String word : dictionary) {
            TrieNode crawl = node;
            for(Character c : word.toCharArray()) {
                if(crawl.children.containsKey(c)){
                    crawl = crawl.children.get(c);
                } else {
                    crawl.children.put(c, new TrieNode(c));
                    crawl = crawl.children.get(c);
                }

            }
            crawl.word = word;
            crawl.endOfWord=true;
        }

        return node;
    }

    public List<String> autoSpellRecursion(String input) {
        List<TrieNode> words = new ArrayList<>();
        TrieNode crawl=root;

        for(Character c : input.toCharArray()){
            crawl = crawl.children.get(c);
        }

        autoSpellRecursion( crawl, words);

        String autoWord=input;
        List<String> autoSpelled = new ArrayList<>();
        if(crawl.endOfWord)
            autoSpelled.add(input);
        for(int i = words.size()-1; i>= 0 ;i--) {
            TrieNode node = words.get(i);
            autoWord += node.letter;
            if(node.endOfWord) {
                autoSpelled.add(autoWord);
                if(node.children.isEmpty())
                    autoWord=input;
            }
        }

        return autoSpelled;
    }

    public void autoSpellRecursion(TrieNode crawl, List<TrieNode> words) {
        if(crawl==null) {
            return;
        }
        Set<Character> keys = crawl.children.keySet();
        String word="";
        for(Character key : keys) {
            autoSpellRecursion(crawl.children.get(key), words);
            if (crawl!= null && crawl.children.containsKey(key)) {
                words.add(crawl.children.get(key));
            }
        }
    }

    public List<String> autoSpell(String input) {
        TrieNode crawl=root;

        Queue<TrieNode> myQueue = new LinkedList<>();

        for(Character c : input.toCharArray()){
            crawl = crawl.children.get(c);
        }
        List<String> words = new ArrayList<>();

        if(crawl==null){
            return new ArrayList();
        }
        myQueue.add(crawl);

        while(!myQueue.isEmpty()){
            TrieNode node= myQueue.poll();
            if(node.endOfWord) {
                words.add(node.word);
            }
            Set<Character> keys = node.children.keySet();

            for(Character key : keys) {
                TrieNode letter = node.children.get(key);
                myQueue.add(letter);
            }
        }

        return words;
    }

    @Test
    public void testBuildTrie(){
        String [] dictionary = new String[]{"dog", "dogie", "doctor"};
        TrieNode node = buildTrie(dictionary);
        System.out.println(node.children.get('d'));
    }

    @Test
    public void testAutoSpellTrieRecurse(){
        String [] dictionary = new String[]{"dog", "dogie", "doctor", "dork", "crabs", "king", "do", ""};
        root = buildTrie(dictionary);
        String word="do";
        List<String> autoSpelling= autoSpellRecursion(word);
        for(String spell : autoSpelling) {
            System.out.print(spell+" ");
        }
    }

    @Test
    public void testAutoSpellTrie2(){
        String [] dictionary = new String[]{"dog", "dogie", "doctor", "dork", "crabs", "king", "do", ""};
        root = buildTrie(dictionary);
        List autoSpelling= autoSpell("do");
        System.out.println(autoSpelling);
    }

    @Test
    public void testAutoSpellTrie(){
        String [] dictionary = new String[]{"dog", "dogie", "doctor", ""};
        root = buildTrie(dictionary);
        List autoSpelling= autoSpell("do");
        System.out.println(autoSpelling);
    }


}
