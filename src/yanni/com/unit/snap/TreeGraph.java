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


    public int findLargest(GraphNode node) {
        if(node.children==null) {
            return node.val;
        }
        List<GraphNode> nodes = node.children;
        Integer largest = node.val;


        for(GraphNode graphNode:nodes) {
            if(largest< graphNode.val){
                largest = graphNode.val;
            }
            largest = findLargest(graphNode);
        }

        return largest;
    }

    @Test
    public void testFindLargest() {

        GraphNode tree = new GraphNode(1);
        tree.children.add(new GraphNode(2));
        tree.children.add(new GraphNode(2));
        GraphNode tree3 = new GraphNode(3);
        tree3.children.add(new GraphNode(4));
        GraphNode tree5=new GraphNode(5);
        tree5.children.add(new GraphNode(10));
        tree5.children.add(new GraphNode(10));
        tree3.children.add(tree5);
        tree.children.add(tree3);

        Integer largest = findLargest(tree);
        System.out.println(largest);
    }


}

