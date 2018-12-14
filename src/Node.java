/**
 * Created by yannipeng on 1/17/18.
 */
class Node {
    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
    }

//    boolean checkTraverseBST(Node root) {
//        checkBST(root);
//    }

    boolean checkBST() {
        return checkBST(this);
    }

    boolean checkBST(Node root) {
        if (root == null)
            return true;
        System.out.print(root.data+" ");
//        Boolean isTree = checkBSTBothLegs( root );
//        if(!isTree) return isTree;
//        if (root == null)
//            return true;
//        Boolean isTree = checkBSTLeftLegs( root );
//        if(!isTree) return isTree;
//        return checkBSTRightLegs( root );
        return ( checkBSTBothLegs( root ) && checkBSTLeftLegs( root ) && checkBSTRightLegs( root ) );
    }

    boolean checkBSTRightLegs(Node root){
        Boolean isTree = true;
        if(root.right!=null) {
            isTree = ( root.data<root.right.data );
            if(!isTree){
                return isTree;
            }
            isTree = checkBST(root.right);
        }
        return isTree;
    }

    boolean checkBSTLeftLegs(Node root){
        Boolean isTree = true;
        if(root.left!=null){
            isTree = ( root.data>root.left.data );
            if(!isTree){
                return isTree;
            }
            isTree = checkBST(root.left);
            if(!isTree){
                return isTree;
            }
        }
        return isTree;
    }

    boolean checkBSTBothLegs(Node root){
        Boolean isTree = true;
        if(root.left!=null && root.right!=null){
            isTree = ( root.data>root.left.data && root.data<root.right.data );
            if(!isTree){
                return isTree;
            }
        }
        return isTree;
    }

    void printInReserveorder()
    {
        printInReserveorder(this);
    }

    /* Given a binary tree, print its nodes in inorder*/
    void printInReserveorder(Node node)
    {
        if (node == null)
            return;

        /* first recur on left child */
//        printInorder(node.left);

        /* then print the data of node */
//        System.out.println(node.data + " ");

        /* now recur on right child */
        printInReserveorder(node.right);
        System.out.println(node.data + " ");
        printInReserveorder(node.left);
    }

//    boolean checkBinary(Node root) {
//        if (root == null)
//            return true;
//
//        /* first recur on left child */
//        printInorder(node.left);
//
//        /* then print the data of node */
//        System.out.print(node.data + " ");
//
//        /* now recur on right child */
//        printInorder(node.right);
//        return true;
//    }

    public boolean checkBT() {
        return checkBT( this );
    }

    private boolean checkBT(Node root) {
        return checkBT( root, null );
    }

    private boolean checkBT(Node root, Node prev) {
        // traverse the tree in inorder fashion and
        // keep a track of previous node
        if (root != null)
        {
            System.out.print(root.data+" ");
            if (!checkBT(root.left, prev))
                return false;

            // allows only distinct values node
            if (prev != null && root.data <= prev.data )
                return false;
            prev = root;
            return checkBT(root.right, prev);
        }
        return true;
    }

    public static void main(String[] args) {
        Node node8 = new Node(8);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        Node node13 = new Node(13);
        Node node14 = new Node(14);
        Node node15 = new Node(15);
        Node node16 = new Node(16);
        Node node17 = new Node(17);
        Node node18 = new Node(18);

        node4.left = node3;
        node5.left = node4;
        node5.right = node6;
        node6.right = node7;
        node7.right= node9;
        node9.right = node18;
        node18.left = node10;
        node10.left = node8;
        node8.right = node2; // wrong node
        //node8.left = node2;
        node10.right = node17;
        node8.right = new Node(9);
//        System.out.println(node8.checkBT());
//        Boolean isBinaryTree = node5.checkBST();
//        //node5.printInorder(node5);
//        System.out.println("");
//        System.out.println(isBinaryTree);
//        Boolean isBinaryTree = node5.checkBT();
        //node5.printInorder(node5);
        node5.printInReserveorder();
        System.out.println("");
//        System.out.println(isBinaryTree);

    }

}
