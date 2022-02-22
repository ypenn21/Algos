import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

// Java program to demonstrate insert operation in binary search tree
class BinarySearchTree {
  
    /* Class containing left and right child of current node and key value*/
    class Node { 
        int key; 
        Node left, right; 
  
        public Node(int item) { 
            key = item; 
            left = right = null; 
        } 
    } 
  
    // Root of BST 
    Node root; 
  
    // Constructor 
    BinarySearchTree() {  
        root = null;  
    } 
  
    // This method mainly calls insertRec() 
    void insert(final int key) {
        Node returned = insertRec(root, key);
        //System.out.println("the returned node is:"+returned.key);
        root = returned;
    }

    /* A recursive function to insert a new key in BST */
    boolean isBst() {
        return isBst(root);
    }

    boolean isBst(Node root) {

        /* If the tree is empty, return a new node */
        if (root == null) {
            return true;
        }
        if( (root.left!=null && root.key<root.left.key) || (root.right!=null && root.key>root.right.key)){
            return false;
        }
        if(!isBstLeft(root.left, root)){
            return false;
        }
        return isBstRight(root.right, root);
    }



    private boolean isBstLeft(Node node, Node root) {
        /* If the tree is empty, return true */
        if (node == null) {
            return true;
        }
        if( (node.left!=null && node.key<node.left.key) || (node.right!=null && node.key>node.right.key)
                || node.key > root.key){ // diff between  isBstLeft and isBstRight is here this little or condition comparing current node with root
            return false;
        }
        boolean isBst = isBstLeft(node.left, root);
        if(!isBst){
            return isBst;
        }
        return isBstLeft(node.right, root);
    }

    private boolean isBstRight(Node node, Node root) {
        /* If the tree is empty, return true */
        if (node == null) {
            return true;
        }
        if( (node.left!=null && node.key<node.left.key) || (node.right!=null && node.key>node.right.key)
                || node.key < root.key){ // diff between  isBstLeft and isBstRight is here this little or condition comparing current node with root
            return false;
        }
        boolean isBst = isBstRight(node.left, root);
        if(!isBst){
            return isBst;
        }
        return isBstRight(node.right, root);

    }

    int getMaxDepth()
    {
        return maxDepth(root);
    }

    int maxDepth(Node node)
    {
        if (node == null)
            return 0;
        else
        {
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);

            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }

    /* A recursive function to insert a new key in BST */
    Node insertRec(Node root, int key) { 
  
        /* If the tree is empty, return a new node */
        if (root == null) { 
            root = new Node(key); 
            return root; 
        } 
  
        /* Otherwise, recur down the tree */
        if (key < root.key) 
            root.left = insertRec(root.left, key); 
        else if (key > root.key) 
            root.right = insertRec(root.right, key); 
  
        /* return the (unchanged) node pointer */
        return root; 
    } 
  
    // This method mainly calls InorderRec() 
    void inorder()  { 
       inorderRec(root); 
    }

    // This method mainly calls InorderRec()
    void printTree()  {
//        System.out.println("Tree:");
//        System.out.println(" "+root.key);
        printTreeRec(root, 0);
    }

    void printInorder(Node root) {

        if(root==null) {
            return;
        }

         Stack<Node> s = new Stack();
         Node crawl = root;
         boolean remove= false;
         s.add(root);
        while (crawl != null || s.size() > 0)
        {

            /* Reach the left most Node of the
            curr Node */
            while (crawl !=  null)
            {
                /* place pointer to a tree node on
                   the stack before traversing
                  the node's left subtree */
                s.push(crawl);
                crawl = crawl.left;
            }

            /* Current must be NULL at this point */
            crawl = s.pop();

            System.out.print(crawl.key + " ");

            /* we have visited the node and its
               left subtree.  Now, it's right
               subtree's turn */
            crawl = crawl.right;
        }
    }


    void printInorder2(Node root) {

        if(root==null) {
            return;
        }

        printInorder2(root.left);
        System.out.println(root.key);
        printInorder2(root.right);

    }
    boolean find(int val)  {
//        System.out.println("Tree:");
//        System.out.println(" "+root.key);
        Node myNode;
        myNode = find(root, val);
        if(myNode!=null)
            return true;
        else
            return false;
    }

    Node find(Node root, int findValue) {
        if(root == null || root.key==findValue)
            return root;
//            root=root.left;
//            System.out.println(root.left);
        if (root.key>findValue )
            return find(root.left, findValue);
        System.out.println(root.key);
//        if(root.key==findValue){
//            return root;
//        }
        return find(root.right, findValue);
    }

int height = 0 ;

    int height(Node root) {
        if(root == null )
            return -1;
        int height = height(root.left);
        int heightRight = height(root.right);
        return Math.max(height, heightRight)+1;
    }

    int size(Node root) {
        if(root == null )
            return 0;
        return size(root.left) + size(root.right)+1;
    }

    // A utility function to do inorder traversal of BST
    void printTreeRec(Node root, int space) {
        if (root != null) {
//            root=root.left;
//            System.out.println(root.left);
            space+=5;
            printTreeRec(root.right, space);

            System.out.println("");
            for (int i = 5; i < space; i++)
                System.out.print(" ");
            System.out.print(root.key);
            System.out.println("");
//            if(root.left!=null && root.right!=null) {
//                System.out.println("/\\");
//                System.out.println(root.left.key +" "+root.right.key);
//            } else {
//                if (root.left != null) {
//                    System.out.println("/");
//                    System.out.println(root.left.key);
//                }
//                if (root.right != null) {
//                    System.out.println("\\");
//                    System.out.println(root.right.key);
//                }
//            }
            printTreeRec(root.left, space);
        }
    }

    // A utility function to do inorder traversal of BST 
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
        } 
    }

    void inorderRec() {
        inorderRec(root);
    }
//wrong
//    boolean isPresent(Node root, int node1, int node2) {
//        if (root != null) {
//            inorderRec(root.left);
//            if(root.key == node1);
//            inorderRec(root.right);
//        }
//        return true;
//    }

    private Integer getDistance(Node root, int node1, int node2, Integer result) {
        if (root == null) {
            return result;
        } else if (root.key >= node1 || root.key <= node2) {
            result++;
        }
        if(root.key == node2){
            return result;
        }
        return getDistance(root.left, node1, node2, result);//+getDistance(root.right, node1, node2, result);
    }

    int getDistance(int node1, int node2) {
        Integer result=0;
        result = getDistance( root, node1, node2, result);
        return result;
    }

    public String deseralize() {
        String deseralized = deseralize( root );

        return deseralized;
    }

    public String deseralize( Node node ) {
        String result = "";
        if(node==null) {
            return "null";
        }

        Stack<Node> myStack = new Stack<>();
        Node root = node;

//        while(root!=null || !myStack.isEmpty() ) { // lvl order
//            String element = "";
//            if(root!=null) {
//                element = root.key+"";
//                myStack.add(root);
//                root = root.left;
//            } else {
//                element = "null";
//                root = myStack.pop();
//                root = root.right;
//            }
//            result += element+"";
//        }

        while(root!=null || !myStack.isEmpty() ) { // in order
            String element = "";
            if(root!=null) {
//                element = root.key+"";
                myStack.add(root);
                root = root.left;
            } else {
//                element = "null";
                root = myStack.pop();
                element = root.key+"";
                root = root.right;
            }
            result += element+"";
        }

        //  post order????

//        result = node.key + "|"+ deseralize( node.left ) +"|"+ deseralize( node.right );
        return result;
    }


    public String deseralize2( Node node ) {
        String result = "";
        if(node==null) {
            return "null";
        }

        result = node.key + "|"+ deseralize2( node.left ) +"|"+ deseralize2( node.right );
        return result;
    }

    public Node seralize( String str ) {
        String [] array = str.split("\\|");
//        Queue<Node> q
//                = new LinkedList<>();

//        Node rooot = new Node(Integer.parseInt(array[0]));
//        q.offer(rooot);
//        int ind = 0;

//        while(!q.isEmpty()) {
//            Node node = q.poll();
//            ind++;
//            if(!array[ind].equals("null")) {
//                node.left = new Node(Integer.parseInt(array[ind]));
//                q.offer(node.left);
//                continue;
//            }
//            else
//                node.left = null;
//
//            ind++;
//            if(!array[ind].equals("null")) {
//                node.right = new Node(Integer.parseInt(array[ind]));
//                q.offer(node.right);
//            }
//            else
//                node.right = null;
//        }


        return serialize( array );
    }

    int indexSerialize = 0;


    // other way to solve it by using a list and removing items from list as you traverse..
    public Node serialize( String [] array ) {
        if(array[indexSerialize].equals("null")) {
            return null;
        }
        Node rooot = new Node(Integer.parseInt(array[indexSerialize]));

        indexSerialize=indexSerialize+1;
        rooot.left = serialize(array);
        indexSerialize++;
        rooot.right = serialize(array);

        return rooot;
    }

    @Test
    public void testSeralize() {
        BinarySearchTree tree = new BinarySearchTree();

        /* Let us create following BST
               5
            /     \
           3       6
         /  \       \
        2    4       7
       /              \
      1                13
                      /
                    11      */
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
//        tree.insert(8);
//        tree.insert(9);
//        tree.insert(13);
        tree.insert(13);
        tree.insert(11);
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(4);

        String bstStr = tree.deseralize();
        System.out.println(bstStr);
        Node bst = tree.seralize(bstStr);
        assert(bst.key==5);

    }




    @Test
    public void testDeseralize() {
        BinarySearchTree tree = new BinarySearchTree();

        /* Let us create following BST
               5
            /     \
           3       6
         /  \       \
        2    4       7
       /              \
      1                13
                      /
                    11      */
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
//        tree.insert(8);
//        tree.insert(9);
//        tree.insert(13);
        tree.insert(13);
        tree.insert(11);
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(4);

        String bst = tree.deseralize();
        System.out.println(bst);

    }


    public void bsTraverisal() {
        Node crawl = root;
        Queue<Node> queue = new LinkedList<Node>();

        queue.add(crawl);

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.key);
            if(node.left!=null)
                queue.add(node.left);
            if(node.right!=null)
                queue.add(node.right);
        }
    }

    @Test
    public void testBsTraverisal() {

        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(13);
        tree.insert(11);
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(4);
//        tree.printInorder2(tree.root);
//        tree.bsTraverisal();
        tree.printInorder(tree.root);

    }

    // Driver Program to test above functions
    @Test
    public void testBSTInsert(String[] args) {
        BinarySearchTree tree = new BinarySearchTree(); 
  
        /* Let us create following BST 
              50 
           /     \ 
          30      70 
         /  \    /  \ 
       20   40  60   80 */
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
//        tree.insert(8);
//        tree.insert(9);
//        tree.insert(13);
        tree.insert(13);
        tree.insert(11);
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(4);
        System.out.println("this is a bst? "+tree.isBst());
        System.out.println("max depth? "+tree.getMaxDepth());

        // max heap
        /*  max heap
              80
           /     \
          70      60
         /  \    /  \
       69   61  59   40 */

        // min heap
                /*  min heap
              1
           /     \
          10      20
         /  \    /  \
       11    12 21   22 */
  
        // print inorder traversal of the BST 
        tree.inorderRec();
        System.out.println("the distance for node 2,4 is:"+tree.getDistance(2,4));
        tree.inorder();
        System.out.println("found:"+tree.find(1));
    }

    //phone numbers format
    // BBB-GGG-RRRR
    // need GGG-BBB-RRRR
} 