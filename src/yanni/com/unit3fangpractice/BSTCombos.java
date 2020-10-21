package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BSTCombos {

    class BSTRange {
        int start;
        int stop;

        int increment=1;

        public BSTRange(int start, int end) {
            this.start = start;
            this.stop = end;
        }

        public int size() {
            return stop-start;
        }

        public List toList() {
            return IntStream.rangeClosed(start, stop-1).boxed().collect(Collectors.toList());
        }
    }

    class BSTNode {
        BSTNode left;
        BSTNode right;

        int val;

        public BSTNode(BSTNode left, BSTNode right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }

        public BSTNode(int val) {
            this.val = val;
        }

        public String toString(){
            String result= null;
            if (left!=null && right!=null )
                result = val+" "+left.val +" "+right.val;
            else if (left==null && right==null )
                result = val+" "+null +" "+null;
            else if (left==null)
                result = val+" "+null +" "+right.val;
            else
                result = val+" "+left.val +" "+null;
            return result;
        }
    }


    public List<BSTNode> getBSTCombos( BSTRange nums) {
        if (nums.size() ==0 ) {
            List<BSTNode> empty = new ArrayList<>();
            empty.add(null);
            return empty;
        }
        List<BSTNode> trees = new ArrayList<>();
        if (nums.size() == 1 ) {
            trees.add(new BSTNode(nums.start));
            return trees;
        }

        List<Integer> numList = nums.toList();
        for(int n: numList) {
            System.out.println(nums.start+" "+n);

            List<BSTNode> lefts = getBSTCombos( new BSTRange(nums.start, n));
            List<BSTNode> rights = getBSTCombos(new BSTRange(n+1, nums.stop));

            for(BSTNode left: lefts) {
                for (BSTNode right: rights) {
                    trees.add(new BSTNode(left, right, n));
                }
            }

        }

        return trees;
    }

    public List<BSTNode> getBSTCombos(int n) {
        return getBSTCombos(new BSTRange(1, n+1));
    }

    @Test
    public void testGetBSTCombos(){
        List<BSTNode> results = getBSTCombos(3);

        System.out.println(results);
    }

}
