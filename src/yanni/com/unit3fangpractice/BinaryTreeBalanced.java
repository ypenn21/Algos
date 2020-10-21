package yanni.com.unit3fangpractice;// Java program to convert a left unbalanced BST to a balanced BST
  
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.LinkedList;
  
/* A binary tree node has data, pointer to left child 
   and a pointer to right child */
  
class BinaryTreeBalanced
{

    class Node
    {
        int data;
        Node left, right;

        public Node(int data)
        {
            this.data = data;
            left = right = null;
        }
    }
    Node root; 
  
    /* This function traverse the skewed binary tree and 
       stores its nodes pointers in vector nodes[] */
    void storeBSTNodes(Node root, Vector<Node> nodes)  
    { 
        // Base case 
        if (root == null) 
            return; 
  
        // Store nodes in Inorder (which is sorted 
        // order for BST) 
        storeBSTNodes(root.left, nodes); 
        nodes.add(root); 
        storeBSTNodes(root.right, nodes); 
    } 
  
    /* Recursive function to construct binary tree */
    Node buildTreeUtil(Vector<Node> nodes, int start, 
            int end)  
    { 
        // base case 
        if (start > end) 
            return null; 
  
        /* Get the middle element and make it root */
        int mid = (start + end) / 2; 
        Node node = nodes.get(mid); 
  
        /* Using index in Inorder traversal, construct 
           left and right subtress */
        node.left = buildTreeUtil(nodes, start, mid - 1); 
        node.right = buildTreeUtil(nodes, mid + 1, end);
        System.out.println(node.data);

        return node; 
    } 
  
    // This functions converts an unbalanced BST to 
    // a balanced BST 
    Node buildTree(Node root)  
    { 
        // Store nodes of given BST in sorted order 
        Vector<Node> nodes = new Vector<Node>(); 
        storeBSTNodes(root, nodes); 
  
        // Constucts BST from nodes[] 
        int n = nodes.size(); 
        return buildTreeUtil(nodes, 0, n - 1); 
    } 
  
    /* Function to do preorder traversal of tree */
    void preOrder(Node node)  
    { 
        if (node == null) 
            return; 
        System.out.print(node.data + " "); 
        preOrder(node.left); 
        preOrder(node.right); 
    }

    /* Function to do preorder traversal of tree */
    void build(Node node)
    {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public Map findMostCommonSubTreeSum(Node node) {
        Map<Integer, Integer> totalCount = new HashMap();
        buildTotalCount(totalCount, node);
        Integer mostSeenSum=0;
        Integer mostCount = 0 ;
        // this one doesn't take in to consideration the biggest sum just the count
        for (Integer key : totalCount.keySet()) {
            int count = totalCount.get(key);
            if (mostCount<count) {
                mostCount=count;
                mostSeenSum=key;
            }
        }
        return totalCount;
    }

    private int buildTotalCount(Map<Integer, Integer> totalCount, Node node) {
        if (node==null) {
            return 0;
        }

        int sum = node.data + buildTotalCount(totalCount, node.left) + buildTotalCount(totalCount, node.right);
        if(totalCount.containsKey(sum)) {
            int count = totalCount.get(sum)+1;
            totalCount.put(sum, count);
        } else {
            totalCount.put(sum, 1);
        }

        return sum;
    }

    public Map findMostCommonParentSum(Node node) {
        Map<Integer, Integer> totalCount = new HashMap();
        buildTotalWithHeapBfs(totalCount, node);
        Integer mostSeenSum=0;
        Integer mostCount = 0 ;
        // this one take in to consideration the biggest sum and the count
        for (Integer key : totalCount.keySet()) {
            int count = totalCount.get(key);
            if (mostCount<count || (mostCount==count && mostSeenSum<key)) {
                mostCount=count;
                mostSeenSum=key;
            }
        }

        return totalCount;
    }

    // different just parent node and its child one level down
    private void buildTotalWithHeapBfs(Map<Integer, Integer> totalCount, Node node) {

        Queue<Node> myqueue = new LinkedList<Node>();
        myqueue.add(node);

        while(!myqueue.isEmpty()) {
            Node parent = myqueue.poll();
            Node left = parent.left==null? new Node(0) : parent.left ;
            Node right = parent.right==null? new Node(0) : parent.right ;
            int sum = parent.data + left.data+right.data;
            if(totalCount.containsKey(sum)) {
                int count = totalCount.get(sum)+1;
                totalCount.put(sum, count);
            } else {
                totalCount.put(sum, 1);
            }

            if(parent.left!=null)
                myqueue.add(parent.left);
            if(parent.right!=null)
                myqueue.add(parent.right);
        }
    }


    @Test
    public void testFindMostCommonParentSum()
    {
         /* Constructed skewed binary tree is
                3
             /     \
            2      -3
           / \     / \
          1   7  -4   7
         /     \       \
        2       3       8
                       / \
                      3   9
                       */
        BinaryTreeBalanced tree = new BinaryTreeBalanced();
        tree.root = new Node(3);
        tree.root.left = new Node(2);
        tree.root.left.right = new Node(7);
        tree.root.left.right.right = new Node(3);
        tree.root.left.left = new Node(1);
        tree.root.left.left.left = new Node(2);
        tree.root.right = new Node(-3);
        tree.root.right.left = new Node(-4);
        tree.root.right.right = new Node(7);
        tree.root.right.right.right = new Node(8);
        tree.root.right.right.right.left = new Node(3);
        tree.root.right.right.right.right = new Node(9);

        Map results = tree.findMostCommonParentSum(tree.root);
        System.out.println(results);

        String myword = "hello";
        System.out.println(myword.substring("he".length(), myword.length()));
        System.out.println(myword.indexOf("hel",0));

    }

    @Test
    public void testFindMostCommonSubTreeSum()
    {
         /* Constructed skewed binary tree is
                3
              /   \
             2    -3
            / \   / \
           1   7 -4  7
          /
         */
        BinaryTreeBalanced tree = new BinaryTreeBalanced();
        tree.root = new Node(3);
        tree.root.left = new Node(2);
        tree.root.left.right = new Node(7);
        tree.root.left.left = new Node(1);
        tree.root.right = new Node(-3);
        tree.root.right.left = new Node(-4);
        tree.root.right.right = new Node(7);

        Map results = tree.findMostCommonSubTreeSum(tree.root);
        System.out.println(results);

    }

    @Test
    public void testFindMostCommonSubTreeSum2()
    {
         /* Constructed skewed binary tree is
                3
             /     \
            2      -3
           / \     / \
          1   7  -4   7
               \       \
                3       8
                       / \
                      3   9
                       */
        BinaryTreeBalanced tree = new BinaryTreeBalanced();
        tree.root = new Node(3);
        tree.root.left = new Node(2);
        tree.root.left.right = new Node(7);
        tree.root.left.right.right = new Node(3);
        tree.root.left.left = new Node(1);
        tree.root.right = new Node(-3);
        tree.root.right.left = new Node(-4);
        tree.root.right.right = new Node(7);
        tree.root.right.right.right = new Node(8);
        tree.root.right.right.right.left = new Node(3);
        tree.root.right.right.right.right = new Node(9);

        Map results = tree.findMostCommonSubTreeSum(tree.root);
        System.out.println(results);

    }

    // Driver program to test the above functions
    @Test
    public void testBSTBalanced()
    { 
         /* Constructed skewed binary tree is 
                10 
               / 
              8 
             / 
            7 
           / 
          6 
         / 
        5   */
        BinaryTreeBalanced tree = new BinaryTreeBalanced();
        tree.root = new Node(10); 
        tree.root.left = new Node(8); 
        tree.root.left.left = new Node(7); 
        tree.root.left.left.left = new Node(6); 
        tree.root.left.left.left.left = new Node(5); 
  
        tree.root = tree.buildTree(tree.root);
        System.out.println("Preorder traversal of balanced BST is :"); 
        tree.preOrder(tree.root); 
    }

    /* Function to do preorder traversal of tree */
    void zigZag(Node node)
    {
        if (node == null)
            return;

        Stack<Node> currlvl = new Stack();
        Stack<Node> nextlvl = new Stack();

        List<BinaryTreeBalanced.Node> myList = new ArrayList<>();

        currlvl.add(node);
//        BinaryTreeBalanced.Node myNode=null;
        boolean turnRight = false;
        while(!currlvl.isEmpty()) {
            BinaryTreeBalanced.Node myNode = currlvl.pop();
            if(myNode==null)
                continue;
            myList.add(myNode);
            System.out.println(myNode.data);
            if (turnRight) {
                if (myNode.left != null){
                    nextlvl.add(myNode.left);
                }
                if(myNode.right!=null) {
                    nextlvl.add(myNode.right);
                }
            } else {
                if(myNode.right!=null) {
                    nextlvl.add(myNode.right);
                }
                if(myNode.left!=null) {
                    nextlvl.add(myNode.left);
                }
            }
            if(currlvl.isEmpty()) {
                currlvl = nextlvl;
                turnRight = !turnRight;
                nextlvl = new Stack<>();
            }
        }


        System.out.println(myList);

    }

    @Test
    public void testBSTZigZag()
    {
         /* Constructed skewed binary tree is
        */
        BinaryTreeBalanced tree = new BinaryTreeBalanced();
        tree.root = new Node(10);
        tree.root.right = new Node(11);
        tree.root.right.right = new Node(12);
        tree.root.right.right.right = new Node(13);
        tree.root.left = new Node(9);
        tree.root.left.left = new Node(8);
        tree.root.left.left.left = new Node(7);

//        tree.root = tree.buildTree(tree.root);
        tree.zigZag(tree.root);
    }
} 