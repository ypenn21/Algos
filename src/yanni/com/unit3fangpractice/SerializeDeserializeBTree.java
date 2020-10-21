package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;


//        PriorityQueue< Integer > queue = new PriorityQueue< >((a, b) -> Integer.compare(a, b)); get smallest number first
// PriorityQueue<Integer> pq =new PriorityQueue<>((a, b) -> Integer.compare(b, a));

//        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
//    Stack<String> myStack = new Stack();
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

//    public BTreeSNode deserializeBTree(String bTree) {
//        String[] treeNodes = bTree.split(" ");
//        boolean[] skip = new boolean[treeNodes.length];
//        Map<String, Integer> map = new HashMap<>();
//        map.put(LATEST,0);
//        return deserializeBTree(treeNodes, 0, map); // new BTreeSNode(Integer.parseInt(treeNodes[0])
//    }

//    private static final String LATEST = "latestInd";
//
//    public BTreeSNode deserializeBTree(String[] bTree, Integer ind, Map<String, Integer> map) {
//        String node  = bTree[ind];
//        if(node.equals("$")) {
//            map.put(LATEST, ind);
//            return null;
//        }
//        BTreeSNode tree = new BTreeSNode(Integer.parseInt(node));
//        if(map.get(LATEST)>ind){
//            ind = map.get(LATEST);
//        }
//        ind++;
//        tree.left=deserializeBTree(bTree, ind, map);
//        if(map.get(LATEST)>ind){
//            ind = map.get(LATEST);
//        }
//        ind++;
//        tree.right=deserializeBTree(bTree, ind, map);
//        return tree;
//    }

    public BTreeSNode rdeserialize(List<String> l) {
        // Recursive deserialization.
        if (l.get(0).equals("$")) {
            l.remove(0);
            return null;
        }

        BTreeSNode root = new BTreeSNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);

        return root;
    }

    // Decodes your encoded data to tree.
    public BTreeSNode deserialize(String data) {
        String[] data_array = data.split(" ");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        return rdeserialize(data_list);
    }

    @Test
    public void testSerializeBTree() {
//      1
//     / \
//    3   4
//   / \   \
//  2   5   7
        PriorityQueue<Integer> pq =new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        pq.add(10);
        pq.add(5);
        System.out.println(fibnoaci(6));
        System.out.println("peak "+pq.peek());
        BTreeSNode tree = new BTreeSNode(1);
        tree.left = new BTreeSNode(3);
        tree.right = new BTreeSNode(4);
        tree.left.left = new BTreeSNode(2);
        tree.left.right = new BTreeSNode(5);
        tree.right.right = new BTreeSNode(7);
        String result = serializeBTree(tree);
        System.out.println(result);
        BTreeSNode tree2 = deserialize(result);
        System.out.println(tree2.val);
    }


    @Test
    public void testSerializeTree() {
        BTreeSNode tree = new BTreeSNode(1);
        tree.left = new BTreeSNode(3);
        tree.right = new BTreeSNode(4);
        tree.left.left = new BTreeSNode(2);
        tree.left.right = new BTreeSNode(5);
        tree.right.right = new BTreeSNode(7);
        String result = serializeTree(tree);
        System.out.println(result);
    }

    public BTreeSNode dserializeTree(String tree) {
        List<String> nodes= new ArrayList(Arrays.asList(tree.split(" ")));

        return dserializeTree(nodes);
    }

    @Test
    public void testDSerializeTree() {
        BTreeSNode tree = new BTreeSNode(1);
        tree.left = new BTreeSNode(3);
        tree.right = new BTreeSNode(4);
        tree.left.left = new BTreeSNode(2);
        tree.left.right = new BTreeSNode(5);
        tree.right.right = new BTreeSNode(7);
        String result = serializeTree(tree);
        BTreeSNode myTree =dserializeTree(result);
        System.out.println(myTree.val);
        System.out.println(myTree.left.val);
        System.out.println(myTree.right.val);
    }

    @Test
    public void testInvertTree() {
        BTreeSNode tree = new BTreeSNode(1);
        tree.left = new BTreeSNode(3);
        tree.right = new BTreeSNode(4);
        tree.left.left = new BTreeSNode(2);
        tree.left.right = new BTreeSNode(5);
        tree.right.right = new BTreeSNode(7);
        String result = serializeTree(tree);
        BTreeSNode myTree =invertBTree(tree);
        System.out.println(myTree.val);
        System.out.println(myTree.left.val);
        System.out.println(myTree.right.val);
    }

    @Test
    public void testBinarySearch() {
        int[] mytest = {1,2,3,4,5,6,7,7,8};
        int foundIndex = findBinarySearch(mytest, 9, 0, mytest.length);
        assert(foundIndex==-1000);
    }

    @Test
    public void testBinarySearch3() {
        int[] mytest = {1,2,3,4,5,6,7,7,8};
        int foundIndex = findBinarySearch(mytest, 1, 0, mytest.length);
        assert(foundIndex==0);
    }

    @Test
    public void testBinarySearch4() {
        int[] mytest = {1,2,3,4,5,6,7,7,8};
        int foundIndex = findBinarySearch(mytest, 8, 0, mytest.length);
        assert(foundIndex==8);
    }

    @Test
    public void testBinarySearch5() {
        int[] mytest = {1,2,3,4,5,6,7,7,8};
        int foundIndex = findBinarySearch(mytest, 4, 0, mytest.length);
        assert(foundIndex==3);
    }

    @Test
    public void testBinarySearch6() {
        int[] mytest = {1,2,3,4,5,6,7,7,8};
        int foundIndex = findBinarySearch(mytest, 3, 0, mytest.length);
        assert(foundIndex==2);
    }

    @Test
    public void testBinarySearch2() {
        int[] mytest = {1,2,3,4,5,6,7,7,8};
        int foundIndex = findBinarySearch(mytest, -1, 0, mytest.length);
        assert(foundIndex==-1000);
    }


    public int findBinarySearch(int[] input , int findNum, int left , int right) {
        return fBinarySearch(input, findNum,  0, input.length-1);
    }


    private int fBinarySearch(int[] input , int findNum, int left , int right) {
        if(left>right){
            return -1000;
        }

        int m = (left+right)/2;
        int midNum = input[m];

        if(midNum==findNum) {
            return m;
        }

        if(findNum>midNum) {
            return fBinarySearch(input , findNum, m+1, right);
        } else {
            return fBinarySearch(input , findNum, left, m-1);
        }

    }


    public BTreeSNode invertBTree(BTreeSNode tree) {
        Stack <BTreeSNode>  bTreeSNodes = new Stack<>();

        BTreeSNode crawl = tree;


        while(crawl!=null) {
            BTreeSNode left = crawl.left;
            BTreeSNode right = crawl.right;


            bTreeSNodes.add( left );
            bTreeSNodes.add( right );

            crawl.left = right;
            crawl.right = left;
            crawl = bTreeSNodes.pop();
        }


        return tree;
    }

    private BTreeSNode dserializeTree(List<String> nodes) {
        if(nodes.size()==0 || nodes.get(0).equals("$")){
            nodes.remove(0);
            return null;
        }

        String node = nodes.get(0);

        BTreeSNode root = new BTreeSNode(Integer.parseInt(node));
        nodes.remove(0);
        root.left = dserializeTree(nodes);
        root.right = dserializeTree(nodes);

        return root;

    }

    public String serializeTree(BTreeSNode root) {
        if(root==null) {
            return "$";
        }
        String result = root.val+" ";

        result += serializeTree(root.left)+" "+serializeBTree(root.right);

        return result;
    }


    public int fibnoaci(int n) {
        if(n==0){
            return 0;
        }
        if(n<=2) {
            return 1;
        }

        return fibnoaci(n-1) + fibnoaci(n-2);
    }



}
