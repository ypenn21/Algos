package yanni.com.unit;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;
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

    public void printBFS() {

        Queue<BtreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BtreeNode node = queue.poll();
            System.out.println(node.val);
            if( node.left!=null ) {
                queue.add(node.left);
            }

            if( node.right!=null ) {
                queue.add(node.right);
            }
        }
    }

}

class Test12 {

    @Test
    public void testBtreePrintBFS() {
        Btree tree = new Btree(5);

        tree.root.left = new BtreeNode(3);
        tree.root.right = new BtreeNode(6);
        tree.root.right.right = new BtreeNode(7);
        tree.printBFS();
        //Arrays.sort();
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