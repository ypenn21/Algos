import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

// Trie Node, which stores a character and the children in a HashMap
class TrieNode {
    public TrieNode(String ch)  {
        value = ch;
        children = new HashMap<String,TrieNode>();
        bIsEnd = false;
        count = 1;
    }
    public HashMap<String,TrieNode> getChildren() {   return children;  }
    public String getValue()                           {   return value;     }
    public void setIsEnd(boolean val)                {   bIsEnd = val;     }
    public boolean isEnd()                           {   return bIsEnd;    }
    private Integer count;
    public Integer getCount()                           {   return count;     }
    public void setCount(Integer val)                           {   count = val;     }
    private String value;
    private HashMap<String,TrieNode> children;
    private boolean bIsEnd;
    public String wholeString;
}
 
// Implements the actual Trie
class Trie {
    // Constructor
    public Trie()   {     root = new TrieNode('*'+"");       }
 
    // Method to insert a new word to Trie
    public void insert(String word)  {
 
        // Find length of the given word
        int length = word.length();
        TrieNode crawl = root;
 
        // Traverse through all characters of given word
        for( int level = 0; level < length; level++)
        {
            HashMap<String,TrieNode> child = crawl.getChildren();
            char ch = word.charAt(level);
 
            // If there is already a child for current character of given word
            if( child.containsKey(ch+""))
                crawl = child.get(ch+"");
            else   // Else create a child
            {
                TrieNode temp = new TrieNode(ch+"");
                child.put( ch+"", temp );
                crawl = temp;
            }
        }
 
        // Set bIsEnd true for last character
        crawl.setIsEnd(true);
        crawl.wholeString = word;
    }

    public void traverse() {
        System.out.println(root.getValue());
        Map<String,TrieNode> map = root.getChildren();
        for (Map.Entry<String,TrieNode> entry : map.entrySet()) {
            System.out.print(entry.getValue().getValue()+"");
            traverse(entry.getValue());
            System.out.println("");
        }
    }

    public void traverse(TrieNode node) {
        Map<String,TrieNode> map = node.getChildren();;
        for (Map.Entry<String,TrieNode> entry : map.entrySet()) {
            System.out.print(entry.getValue().getValue()+"");
            traverse(entry.getValue());
        }

    }
 
    // The main method that finds out the longest string 'input'
    public String getMatchingPrefix(String input)  {
        String result = ""; // Initialize resultant string
        int length = input.length();  // Find length of the input string       
 
        // Initialize reference to traverse through Trie
        TrieNode crawl = root;   
 
        // Iterate through all characters of input string 'str' and traverse
        // down the Trie
        int level, prevMatch = 0;
        for( level = 0 ; level < length; level++ )
        {
            // Find current character of str
            char ch = input.charAt(level);    
 
            // HashMap of current Trie node to traverse down
            HashMap<String,TrieNode> child = crawl.getChildren();
 
            // See if there is a Trie edge for the current character
            if( child.containsKey(ch+"") )
            {
               result += ch+"";          //Update result
               crawl = child.get(ch+""); //Update crawl to move down in Trie
 
               // If this is end of a word, then update prevMatch
               if( crawl.isEnd() )
                    prevMatch = level + 1;
            }
            else
                break;
        }
 
        // If the last processed character did not match end of a word,
        // return the previously matching prefix
        if( !crawl.isEnd() )
                return result.substring(0, prevMatch);        
 
        else return result;
    }
 
    protected TrieNode root;

    @Test
    public void testTraverse() {
        Trie dict = new Trie();
        dict.insert("cater");
        dict.insert("catere");
        dict.insert("base");
        dict.insert("cat");
        dict.insert("area");
        dict.insert("cat");
        dict.insert("basement");
        dict.traverse();
    }

    @Test
    public void testGetMatchingPrefix() {
        Trie dict = new Trie();
        dict.insert("cater");
        dict.insert("catere");
        dict.insert("base");
        dict.insert("cat");
        dict.insert("area");
        dict.insert("cat");
        dict.insert("basement");

        String input = "caterer";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));

        input = "basement";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));

        input = "are";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));

        input = "arex";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));

        input = "basemexz";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));

        input = "xyz";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));
    }
}