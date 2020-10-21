package yanni.com.unit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


class BtreeNode{

    public BtreeNode(Integer val){
        this.val = val;
    }

    BtreeNode left;
    BtreeNode right;
    Integer val;

}

class Btree {
    BtreeNode root;
    Map<Integer, Set<BtreeNode>> depthMap = new HashMap<>();

    public Btree(Integer val){
        this.root = new BtreeNode(val);
    }

    // child left less than parent & less than root. child right greater than parent & greater than root.
    public boolean isValidBtree () {
        return isValidBtree ( root, Integer.MAX_VALUE, Integer.MIN_VALUE );
    }

    public boolean isValidBtree ( BtreeNode node, Integer upper, Integer lower ) {

        BtreeNode crawler = node;
        if (crawler == null) {
            return true;
        }

        if( crawler.val > lower && crawler.val<upper ){
            if (isValidBtree (crawler.left, crawler.val, lower)
                    && isValidBtree (crawler.right, upper, crawler.val)) {
                return true;
            }
        }
        return false;

    }

    public BtreeNode findNode ( BtreeNode node, Integer target ) {
        BtreeNode result=null;

        if(node!=null && node.val == target) {
            return node;
        } else if(node==null) {
            return result;
        }

        BtreeNode crawl = node;

        result=findNode(crawl.left, target);
        if(result==null)
            result=findNode(crawl.right, target);

        return result;
    }

    public BtreeNode findNodeBFS ( BtreeNode node, Integer target ) {
        BtreeNode result=null;
        if(node!=null && node.val == target) {
            return node;
        } else if(node==null) {
            return result;
        }
        Stack<BtreeNode> treeNodes = new Stack();
        treeNodes.add(node);

        while(!treeNodes.isEmpty()){
            BtreeNode crawl = treeNodes.pop();
            if(crawl.val==target){
                result = crawl;
                break;
            }

            if(crawl.left!=null)
                treeNodes.add(crawl.left);
            if(crawl.right!=null)
                treeNodes.add(crawl.right);
        }
        return result;
    }

    public void invertBtree() {

        Stack<BtreeNode> stackNodes = new Stack<>();
        stackNodes.add(root);

        while (!stackNodes.isEmpty()) {
            BtreeNode node = stackNodes.pop();

            if( node.left!=null ) {
                stackNodes.add(node.left);
            }

            if( node.right!=null ) {
                stackNodes.add(node.right);
            }
            BtreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }


    }
//
//    public void invertBinaryTree2(){
//
//        Stack<BtreeNode> btreeNodeStack = new Stack<>();
//
//        btreeNodeStack.add(root);
//
//        while(!btreeNodeStack.isEmpty()){
//
//            BtreeNode btreeNode = btreeNodeStack.pop();
//
//            if(btreeNode.right!=null) {
//                btreeNodeStack.add(btreeNode.right);
//            }
//
//            if(btreeNode.left!=null) {
//                btreeNodeStack.add(btreeNode.left);
//            }
//
//            BtreeNode tmp =  btreeNode.left;
//            btreeNode.left = btreeNode.right;
//            btreeNode.right = tmp;
//        }
//
//
//
//    }


    public void printBFS() {

        Queue<BtreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BtreeNode node = queue.poll();
            System.out.print(node.val);
            System.out.println("");
            if( node.left!=null ) {
                queue.add(node.left);
            }

            if( node.right!=null ) {
                queue.add(node.right);
            }
        }
    }

    public void setDepthOfTree ( BtreeNode node, int depth) {

        if(node==null) {
            return;
        }

        BtreeNode crawl = node;
        Set<BtreeNode> nodes = new LinkedHashSet<>();
        nodes = depthMap.get(depth);
        if (nodes==null)
            nodes = new LinkedHashSet();
        nodes.add(crawl);
        depthMap.put(depth, nodes);
        setDepthOfTree(crawl.left, depth+1);
        setDepthOfTree(crawl.right, depth+1);

    }

    public int maxSum = Integer.MIN_VALUE;

    public int maxPath( BtreeNode node ) {
        if(node==null) {
            return 0;
        }

        BtreeNode crawl = node;

        int leftGain = Math.max( maxPath(crawl.left), 0 );
        int rightGain = Math.max( maxPath(crawl.right), 0 );

        int sum = crawl.val + leftGain + rightGain;

        maxSum = Math.max(maxSum, sum);

        return node.val + Math.max(leftGain, rightGain);
    }

}

class Test12 {


    @Test
    public void testMaxPath() {
        Btree tree = new Btree(10);

        tree.root.left = new BtreeNode(0);
        tree.root.right = new BtreeNode(20);
        tree.root.right.left = new BtreeNode(15);
        tree.root.right.left.right = new BtreeNode(16);
        tree.root.right.left.left = new BtreeNode(9);
        tree.root.right.right = new BtreeNode(22);
        tree.root.right.right.right = new BtreeNode(23);
        tree.root.right.right.left = new BtreeNode(21);
        tree.root.right.right.left.left = new BtreeNode(10);
        tree.root.right.right.right.right = new BtreeNode(24);
        tree.root.right.right.right.left = new BtreeNode(22);
        System.out.println( tree.maxPath(tree.root) );
        System.out.println("max path is: "+tree.maxSum);
    }

    @Test
    public void testBtreeFindNode() {
        Btree tree = new Btree(5);

        tree.root.left = new BtreeNode(3);
        tree.root.right = new BtreeNode(6);
        tree.root.right.left = new BtreeNode(4);
        tree.root.right.right = new BtreeNode(7);
        tree.root.right.right.right = new BtreeNode(9);
        tree.root.right.right.right.left = new BtreeNode(8);
        BtreeNode node = tree.findNode(tree.root, 8);
        BtreeNode node2 = tree.findNodeBFS(tree.root, 8);
        tree.setDepthOfTree(tree.root, 0);
        System.out.println(node.val);
        System.out.println(node2.val);
    }

    @Test
    public void testBtreePrintBFS() {
        Btree tree = new Btree(5);
        tree.root.left = new BtreeNode(3);
        tree.root.right = new BtreeNode(6);
        tree.root.right.left = new BtreeNode(7);
        tree.root.right.right = new BtreeNode(9);
        tree.root.right.left.left = new BtreeNode(5);
        tree.root.right.left.right = new BtreeNode(6);
        tree.root.right.right.left = new BtreeNode(8);
        tree.root.right.right.right = new BtreeNode(13);

        tree.printBFS();
    }

    @Test
    public void testBtreeNodeInvert() {
        Btree tree = new Btree(5);

        tree.root.left = new BtreeNode(3);
        tree.root.right = new BtreeNode(6);
        tree.root.right.right = new BtreeNode(7);
        tree.invertBtree();

        System.out.println(tree);
        System.out.println(tree.root.val);
        System.out.println(tree.root.left.val);
        System.out.println(tree.root.right.val);
        assert(tree.root.right.right==null);
        System.out.println(tree.root.left.left.val);
        assert(tree.root.left.right==null);
        //Arrays.sort();
    }

    @Test
    public void testBtreeNode() {
        Btree tree = new Btree(5);

        tree.root.left = new BtreeNode(3);
        tree.root.right = new BtreeNode(6);

        System.out.println(tree.isValidBtree());

        //Arrays.sort();
    }

    @Test
    public void testBtreeNode2() {
        Btree tree = new Btree(5);

        tree.root.left = new BtreeNode(3);
        tree.root.right = new BtreeNode(6);
        tree.root.right.left = new BtreeNode(2);

        System.out.println(tree.isValidBtree());

        //Arrays.sort();
    }

}