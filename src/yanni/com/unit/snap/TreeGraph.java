package yanni.com.unit.snap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TreeGraph {

    public class GraphNode {
        Integer val;
        List<GraphNode> children = new ArrayList<>();
        public GraphNode(int val){
            this.val=val;
        }
    }


    //Integer largeChildren = 0; this works too
    public int findLargest(GraphNode node) {
        if(node.children==null) {
            return node.val;
        }
        List<GraphNode> nodes = node.children;
        Integer largest = node.val;
        Integer largeChildren = 0;

        for(GraphNode graphNode:nodes) {
            if(largest< graphNode.val){
                largest = graphNode.val;
            }
            largeChildren = findLargest(graphNode);
            if(largeChildren > largest) {
                largest = largeChildren;
            }
        }

        return largest;
    }

    @Test
    public void testFindLargest() {

        //      1 node tree
        //  /   |   \
        // 2    3    3 node 3
        //         / |
        //       11  5  node 5
        //         /   \
        //        10   10

        GraphNode tree = new GraphNode(1);
        tree.children.add(new GraphNode(2));
        tree.children.add(new GraphNode(3));
        GraphNode node3 = new GraphNode(3);
        tree.children.add(node3);
        node3.children.add(new GraphNode(11));
        GraphNode node5=new GraphNode(5);
        node5.children.add(new GraphNode(10));
        node5.children.add(new GraphNode(10));
        node3.children.add(node5);

        Integer largest = findLargest(tree);
        System.out.println(largest);
    }


}

