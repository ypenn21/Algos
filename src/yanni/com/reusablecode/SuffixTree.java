package yanni.com.reusablecode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
 
// A Trie of all suffixes
public class SuffixTree {

    // root node is just a place holder.
    SuffixTrieNode root = new SuffixTrieNode();
 
    // Constructor (Builds a trie of suffies of the
    // given text)
    public SuffixTree(String txt) {
     
        // Consider all suffixes of given string and
        // insert them into the Suffix Trie using 
        // recursive function insertSuffix() in 
        // yanni.com.reusablecode.SuffixTrieNode class
        for (int i = 0; i < txt.length(); i++)
            root.insertSuffix(txt.substring(i), i);
    }
 
    /* Prints all occurrences of pat in the Suffix Trie S
    (built for text) */
    public List search_tree(String pat) {
     
        // Let us call recursive search function for 
        // root of Trie.
        // We get a list of all indexes (where pat is 
        // present in text) in variable 'result'
        List<Integer> result = root.search(pat);
        List<Integer> output = new ArrayList<>();
        // Check if the list of indexes is empty or not
        if (result == null)
            System.out.println("Pattern not found");
        else {
 
            int patLen = pat.length();
            for (Integer i : result) {
                System.out.println("Pattern found at position " +
                        (i - patLen));
                output.add((i - patLen));
            }
        }
        return output;
    }

    class SuffixTrieNode {

        final static int MAX_CHAR = 256;

        SuffixTrieNode[] children = new SuffixTrieNode[MAX_CHAR];
        List<Integer> indexes;
        Character ch;

        SuffixTrieNode() // Constructor
        {
            // Create an empty linked list for indexes of
            // suffixes starting from this node
            indexes = new LinkedList<Integer>();

            // Initialize all child pointers as NULL
            for (int i = 0; i < MAX_CHAR; i++)
                children[i] = null;
        }

        SuffixTrieNode(Character cha) // Constructor
        {
            this.ch = cha;
            // Create an empty linked list for indexes of
            // suffixes starting from this node
            indexes = new LinkedList<Integer>();

            // Initialize all child pointers as NULL
            for (int i = 0; i < MAX_CHAR; i++)
                children[i] = null;
        }

        // A recursive function to insert a suffix of
        // the text in subtree rooted with this node
        void insertSuffix(String s, int index) {

            // Store index in linked list
            indexes.add(index);

            // If string has more characters
            if (s.length() > 0) {

                // Find the first character
                char cIndex = s.charAt(0);

                // If there is no edge for this character,
                // add a new edge
                if (children[cIndex] == null)
                    children[cIndex] = new SuffixTrieNode(cIndex);
                else
                    System.out.print("cIndex not null");

                // Recur for next suffix
                children[cIndex].insertSuffix(s.substring(1),
                        index + 1);
            }
        }

        // A function to search a pattern in subtree rooted
        // with this node.The function returns pointer to a
        // linked list containing all indexes where pattern
        // is present. The returned indexes are indexes of
        // last characters of matched text.
        List<Integer> search(String s) {

            // If all characters of pattern have been
            // processed,
            if (s.length() == 0)
                return indexes;

            // if there is an edge from the current node of
            // suffix tree, follow the edge.
            if (children[s.charAt(0)] != null)
                return (children[s.charAt(0)]).search(s.substring(1));

                // If there is no edge, pattern doesnt exist in
                // text
            else
                return null;
        }
    }
 
    // driver program to test above functions
    public static void main(String args[]) {
         
        // Let us build a suffix trie for text 
        // "geeksforgeeks.org"
        String txt = "geeksforgeeks.org";
        SuffixTree suffixTree = new SuffixTree(txt);

        // run time is big o of n, n being the length of the input aka the suffix we are searching for
        // building the trie takes big o of n!
        System.out.println("Search for 'geeksforgeeks.org'");
        suffixTree.search_tree("geeksforgeeks.org");
 
        System.out.println("Search for 'ee'");
        suffixTree.search_tree("ee");

        System.out.println("Search for 'eee'");
        suffixTree.search_tree("eee");
 
        System.out.println("\nSearch for 'geek'");
        suffixTree.search_tree("geek");
 
        System.out.println("\nSearch for 'quiz'");
        suffixTree.search_tree("quiz");
 
        System.out.println("\nSearch for 'forgeeks'");
        suffixTree.search_tree("forgeeks");
    }
}