package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class SerializeDeserializeBTree {

    class BTreeSNode {
        int val;

        BTreeSNode left;
        BTreeSNode right;

        public BTreeSNode(int val){
            this.val = val;
        }
    }

    public String serializeBTree(BTreeSNode root) {
        if(root==null) {
            return "$";
        }

        return root.val+" "+serializeBTree(root.left)+" "+ serializeBTree(root.right);
    }

    public BTreeSNode deserializeBTree(String bTree) {
        String[] treeNodes = bTree.split(" ");
        boolean[] skip = new boolean[treeNodes.length];
        Map<String, Integer> map = new HashMap<>();
        map.put(LATEST,0);
        return deserializeBTree(treeNodes, 0, map); // new BTreeSNode(Integer.parseInt(treeNodes[0])
    }

    private static final String LATEST = "latestInd";

    public BTreeSNode deserializeBTree(String[] bTree, Integer ind, Map<String, Integer> map) {
        String node  = bTree[ind];
        if(node.equals("$")) {
            map.put(LATEST, ind);
            return null;
        }
        BTreeSNode tree = new BTreeSNode(Integer.parseInt(node));
        if(map.get(LATEST)>ind){
            ind = map.get(LATEST);
        }
        ind++;
        tree.left=deserializeBTree(bTree, ind, map);
        if(map.get(LATEST)>ind){
            ind = map.get(LATEST);
        }
        ind++;
        tree.right=deserializeBTree(bTree, ind, map);
        return tree;
    }

    @Test
    public void testSerializeBTree() {
//      1
//     / \
//    3   4
//   / \   \
//  2   5   7
        BTreeSNode tree = new BTreeSNode(1);
        tree.left = new BTreeSNode(3);
        tree.right = new BTreeSNode(4);
        tree.left.left = new BTreeSNode(2);
        tree.left.right = new BTreeSNode(5);
        tree.right.right = new BTreeSNode(7);
        String result = serializeBTree(tree);
        System.out.println(result);
        BTreeSNode tree2 = deserializeBTree(result);
        System.out.println(tree2.val);
    }



}
