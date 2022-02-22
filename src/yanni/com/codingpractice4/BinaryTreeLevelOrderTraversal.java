package yanni.com.codingpractice4;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    public Altruist al = new Altruist();

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }


    // input
    //                3
    //               / \
    //              9  20
    //                /  \
    //               15   7
    //output
    //            [
    //                [3],
    //                [9,20],
    //                [15,7]
    //            ]
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null) {
            return new ArrayList();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        boolean first = true;
        int level = 0;
        while(!queue.isEmpty()){
            result.add( new ArrayList<Integer>() );
            int level_length = queue.size();
            for(int i = 0; i < level_length; ++i) {
                TreeNode node = queue.poll();

                // fulfill the current level
                result.get(level).add(node.val);
                if(node.left!=null) {
                    queue.add(node.left);
                }
                if(node.right!=null) {
                    queue.add(node.right);
                }
            }

            level++;

        }

        return result;
    }
}
