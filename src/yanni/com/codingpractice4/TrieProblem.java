package yanni.com.codingpractice4;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TrieProblem {

    class Node {
        public Node(String character){
            this.character = character;
        }
        String character = "";
        Integer depth = null;
        Integer count = 0;
        Map<String, Node> children = new HashMap<>();
    }

    Node root = new Node("*");

    public void createTrie ( String [] strs ) {

        for ( int i = 0; i < strs.length; i++ ) {
            String str = strs[i];
            char[] myChar = str.toCharArray();
            Node crawl = root;
            for ( int j = 0; j < myChar.length; j++ ) {
                while ( crawl != null ) {
                    if( crawl.children.get(myChar[j]+"") == null || j==0 || j == crawl.depth) {
                        break;
                    }
                    if(crawl.depth!=null && crawl.depth!=j) {
                        crawl = crawl.children.get(myChar[j] + "");
                    }
                }
                if ( (!crawl.character.equals("*")) && (crawl.character.equals("") || crawl.depth==null || (crawl.depth==j && crawl.character.equals(myChar[j]+""))) ) {
                    crawl.character = myChar[j]+"";
                    crawl.count++;
                    crawl.depth=j;
                } else {
                    Node child = new Node(myChar[j]+"");
                    if(crawl.children.get(myChar[j]+"") != null){
                        child = crawl.children.get(myChar[j]+"");
                    }
                    child.count++;
                    child.depth = j;
                    crawl.children.put(myChar[j]+"", child);
                    crawl= child;
                }
            }
        }
    }

    @Test
    public void testCreateTrie() {
        String [] strs = {"accc", "accc", "bcc"};
        createTrie ( strs );
        System.out.println(root);
    }

}
