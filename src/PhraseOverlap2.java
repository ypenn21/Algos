import javax.print.attribute.HashAttributeSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by yannipeng on 3/26/18.
 */
@SuppressWarnings("Duplicates")
public class PhraseOverlap2 {


    public static void main (String [] args ) {

        PhraseOverlap2 phraseOverlap = new PhraseOverlap2();

        String phrase1 = "boys don't cry";
        String phrase2 = "don't cry for me. Argentina";
        String phrase3 = "don't cry for me. Argentina is great";
        String phrase4 = "no overlap at all";
        String phrase8 = "no overlap at alll alll alll";
        String phrase5 = "Those boys don't cry";
        String phrase11 = "don't cry for me. I don't want to know is true";
        String phrase6 = "don't cry for me. Argentina is";
        String phrase10 = "don't cry for me. Argentina is okay great cool ting";
        String phrase9 = "don't cry for me. Argentina is awesome yoooo";
        String phrase7 = "don't cry for me. Argentina is awesome foooolllllllllllllll";
//        String output = "Those boys don't cry for me. Argentina is great";

        Trie2 myTrie = new Trie2();

        myTrie.insert(phrase1);
        myTrie.insert(phrase2);
        myTrie.insert(phrase3);
        myTrie.insert(phrase4);
        myTrie.insert(phrase5);
        myTrie.insert(phrase6);
        myTrie.insert(phrase7);
        myTrie.insert(phrase8);
        myTrie.insert(phrase9);
        myTrie.insert(phrase10);
        myTrie.insert(phrase11);
        System.out.println(myTrie.findOverlappingPharse());
        String result ="";
        TrieNode found = myTrie.findFirstHalf(myTrie.root, "don't", result);
        System.out.println(found.getValue());
//        System.out.println(result);

//        lastPointerToPhrase.getChildren();

        List<String> pharses = Arrays.asList(phrase1, phrase5, phrase2, phrase3, phrase4);
        //List<String> pharses = Arrays.asList(phrase1, phrase2);
        System.out.println("End Result Should Be: \nThose boys don't cry for me. Argentina is great");
//        System.out.println(phraseOverlap.findOverlappingPharse(pharses));

    }
}


class Trie2 {

    protected TrieNode root;

    public Trie2()   {     root = new TrieNode('*'+"");       }

    int phrases = 0;

    //not threadSafe
    List<TrieNode> endOfPhrase = new ArrayList<>();

    // Method to insert a new word to Trie
    public void insert (String phrase)  {

        String[] wordsArray = phrase.split(" ");
//            root = new TrieNode(wordsArray[0]);
        // Find length of the given word
        int length = wordsArray.length;
        TrieNode crawl = this.root;

        Map<String, TrieNode> rootChildren = this.root.getChildren();

        // Traverse through all words of given phrase
        for( int level = 0; level < length; level++)
        {
            Map<String, TrieNode> child = crawl.getChildren();
             if (child.containsKey(wordsArray[level])) {
                 crawl = child.get(wordsArray[level]);
                 crawl.setCount(crawl.getCount() + 1);
             }
//             else if((level!=0 && rootChildren.containsKey(wordsArray[level]))) {
//                 TrieNode rootChild = rootChildren.get(wordsArray[level]);
//                 crawl.getChildren().put(rootChild.getValue(), rootChild);
//                 rootChildren.remove(wordsArray[level]);
//                 crawl.setCount(crawl.getCount() + 1);
//                 crawl = rootChild;
//             }
             else   // Else create a child
             {
                 boolean createNewChild = true;
//                 if(level == 0) {
//                     for (TrieNode node :endOfPhrase) {
//                         if (node.equals(wordsArray[level])) {
//                             createNewChild=false;
//                             crawl = child.get(wordsArray[level]);
//                             crawl.setCount(crawl.getCount() + 1);
//                         }
//                     }
//                 }
                 if(createNewChild){
                     TrieNode temp = new TrieNode(wordsArray[level]);
                     child.put(wordsArray[level], temp);
                     crawl = temp;
                 }
             }
        }

        // Set bIsEnd true for last word
        crawl.setIsEnd(true);
        endOfPhrase.add(crawl);
        crawl.wholeString = phrase;
        phrases++;
    }

    public String findOverlappingPharse () {
        Set largestPhrases=new HashSet<>();
        Map<String,TrieNode> children = this.root.getChildren();
        Map.Entry<String, TrieNode> entryToFindOverlap = null;
        for(Map.Entry<String, TrieNode> entry : children.entrySet()){
            if(entry.getValue().getCount() > 1) {
                entryToFindOverlap = entry;
                getLongestString(entryToFindOverlap.getValue(), largestPhrases, new LinkedHashSet());
            }
        }
        String largest = largestPhrases.stream().max(Comparator.comparing(String::length)).get().toString();

        return largest;
    }

    public TrieNode findFirstHalf(TrieNode startNode, String search, String result){

        // Initialize reference to traverse through Trie
//        TrieNode rootChild = rootChildren.get(wordsArray[level]);
//        crawl.getChildren().put(rootChild.getValue(), rootChild);
//        rootChildren.remove(wordsArray[level]);
//        crawl.setCount(crawl.getCount() + 1);
//        crawl = rootChild;

        HashMap<String,TrieNode> children = startNode.getChildren();
        // ways to implement find second half
        // maintain reverse order than return the node to find first half or loop through list of strings to find subString of search word to the end of string
        // then see if that matches with beginning of the second half if it does then replace that with the other half
        for( Map.Entry<String, TrieNode> entry : children.entrySet() )
        {
            for( Map.Entry<String, TrieNode> entry2 : entry.getValue().getChildren().entrySet() ){
                if( children.containsKey(search) )
                {
                    return children.get(search);
                }
            }
        }
        return null;
    }

    public void getLongestString(TrieNode startNode, Set<String> phrases, Set<TrieNode> phrase)  {
        if(phrase.isEmpty()){
            phrase.add(startNode);
        }

        TrieNode crawl = startNode;

        HashMap<String,TrieNode> children = crawl.getChildren();
        if(crawl.isEnd()){
            phrases.add(crawl.wholeString);
        }

        if(isLastNode(crawl)){
            return;
        }

        for( Map.Entry<String, TrieNode> entry : children.entrySet()){
            if(!children.isEmpty()) {
                phrase.add(entry.getValue());
                getLongestString(entry.getValue(), phrases, phrase);
            }
        }
    }

    public boolean isLastNode(TrieNode node)
    {
        if (node.getChildren().isEmpty())
            return true;
        return false;
    }

    public TrieNode findNode(String input)  {
        int wordCount=0;
        String result = ""; // Initialize resultant string
        String[] wordsArray = input.split(" ");
        int length = wordsArray.length;  // Find length of the input string

        // Initialize reference to traverse through Trie
        TrieNode crawl = this.root;

        // Iterate through all characters of input string 'str' and traverse
        // down the Trie
        int level;
        for( level = 0 ; level < length; level++ )
        {
            // Find current character of str
            String word = wordsArray[level];

            // HashMap of current Trie node to traverse down
            HashMap<String,TrieNode> child = crawl.getChildren();

            // See if there is a Trie edge for the current character
            if( child.containsKey(word) )
            {
                wordCount++;
                result += " "+word;
                result = result.trim();//Update result
                crawl = child.get(word); //Update crawl to move down in Trie
            }
            else  break;
        }

        return result.equals(input) ? crawl : null;
    }
}
