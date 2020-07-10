package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.Queue;
import java.util.LinkedList;


//https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
public class BinaryTree {

    Node root;
    Integer heightTreeLeft=0;
    Integer heightTreeRight=0;

    public BinaryTree() {
    }

//    public BinaryTree(int value) {
//        this.root = new Node(value);
//    }

    class Node {
        public Node(int value){
            this.value=value;
        }
        Integer value;
        Node left;
        Node right;
    }

    public void addNode(int val) {
      Node leaf = new Node(val);
      if(root==null){
        root = leaf;
        return;
      }
        traverseNodeAndAppendNodeAdd(root, leaf);
    }

    public void traverseNodeAndAppendNodeAdd(Node crawl, Node nodeAdd) {
        if(crawl.value<nodeAdd.value) {
            if(crawl.right!=null)
                traverseNodeAndAppendNodeAdd(crawl.right, nodeAdd);
            else {
                crawl.right = nodeAdd;
            }
        } else {
            if(crawl.left!=null)
                traverseNodeAndAppendNodeAdd(crawl.left, nodeAdd);
            else {
                crawl.left = nodeAdd;
            }
        }
    }

    public void printLevelOrder()
    {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty())
        {

            /* poll() removes the present head.
            For more information on poll() visit
            http://www.tutorialspoint.com/java/util/linkedlist_poll.htm */
            Node tempNode = queue.poll();
            System.out.print(tempNode.value + " ");

            /*Enqueue left child */
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            /*Enqueue right child */
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }

    public Integer findTreeHeight(){
        System.out.println(root.value);
        Integer height = 1+(Math.max(traverseTreeFindHeight(root.left, 0), traverseTreeFindHeight(root.right, 0)));
        return height;

    }

    public Integer traverseTreeFindHeight(Node crawl, Integer height){
        if(crawl !=null) {
            System.out.println(crawl.value);
        }
        if(crawl ==null){
            return height;
        }
        if(crawl.left==null && crawl.right==null) {
            height++;
            return height;
        }

//        if(crawl.left==null) {
//            return traverseTreeFindHeight(crawl.left, height + 1);
//        }
//        if(crawl.right==null) {
//            return traverseTreeFindHeight(crawl.right, height + 1);
//        }
        return (Math.max(traverseTreeFindHeight(crawl.left, height+1), traverseTreeFindHeight(crawl.right, height+1)));
    }

    public void traverseBtree(Node crawl){
        if(crawl ==null){
            return;
        }
        traverseBtree(crawl.left);
        traverseBtree(crawl.right);
        if(crawl !=null) {
            System.out.println(crawl.value);
        }

    }

    @Test
    public void testBinaryTree()
    {
        BinaryTree btree = new BinaryTree();
        btree.addNode(1);
        btree.addNode(2);
        btree.addNode(3);
        btree.addNode(4);
//        btree.addNode(2);
//        btree.root = new Node(1);
//        btree.root.left = new Node(2);
//        btree.root.left.left = new Node(4);
//        btree.root.left.left.right = new Node(5);
//        btree.root.right = new Node(3);
        //System.out.println(btree.findTreeHeight());
        btree.traverseBtree(btree.root);
    }

}
