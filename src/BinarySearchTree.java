import java.util.PriorityQueue;

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
    void insert(int key) {
        Node returned = insertRec(root, key);
        System.out.println("the returned node is:"+returned.key);
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

    boolean find(int val)  {
//        System.out.println("Tree:");
//        System.out.println(" "+root.key);
        Node myNode = new Node(-1);
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

    // Driver Program to test above functions 
    public static void main(String[] args) { 
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