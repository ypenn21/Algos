package yanni.com.amazon;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FindSumOfGraph {
    class GNode {

        public GNode(int val, List<GNode> children) {
            this.val = val;
            this.children=children;
        }

        public List<GNode> children = new ArrayList<>();
        public int val =-1;
    }

    public GNode root;


    public Integer findNodeMaxSumChildren (GNode root, int sum) {
        if (root==null) {
            return sum;
        }
        int sum2 =0;
        sum2 += root.val;

        for(GNode n : root.children) {
            sum2+=n.val;
            sum=findNodeMaxSumChildren (n, sum);
        }

        return sum2 > sum ? sum2 :  sum;

    }

    public Integer sumAllNodes (GNode root, int add) {
        int sum = sumAllNodes ( root )+add;
        return sum;
    }

    private Integer sumAllNodes (GNode root) {
        if (root==null) {
            return 0;
        }
        int sum2 =0;

        for(GNode n : root.children) {
            sum2+=n.val;
            sum2+=sumAllNodes (n);
        }

        return sum2;

    }

    @Test
    public void testSumAllNodes() {
        GNode root = new GNode(1, new ArrayList<>());
        GNode n1 = new GNode(2, new ArrayList<>());
        GNode n2 = new GNode(3, new ArrayList<>());
        GNode n3 = new GNode(4, new ArrayList<>());
        GNode n4 = new GNode(5, new ArrayList<>());
        GNode n5 = new GNode(6, new ArrayList<>());
        GNode n6 = new GNode(7, new ArrayList<>());
        root.children.add(n1);
        root.children.add(n2);
        root.children.add(n3);
        n3.children.add(n4);
        n3.children.add(n5);
        n5.children.add(n6);
        int sum = sumAllNodes ( root, root.val);
        assert(sum==28);
    }

    @Test
    public void testSumAllNodes2() {
        GNode root = new GNode(1, new ArrayList<>());
        GNode n1 = new GNode(1, new ArrayList<>());
        GNode n2 = new GNode(1, new ArrayList<>());
        GNode n3 = new GNode(1, new ArrayList<>());
        GNode n4 = new GNode(1, new ArrayList<>());
        GNode n5 = new GNode(1, new ArrayList<>());
        GNode n6 = new GNode(1, new ArrayList<>());
        GNode n7 = new GNode(10, new ArrayList<>());
        root.children.add(n1);
        root.children.add(n2);
        root.children.add(n3);
        n3.children.add(n4);
        n3.children.add(n5);
        n5.children.add(n6);
        n5.children.add(n7);
        int sum = sumAllNodes ( root, root.val);
        assert(sum==17);
    }

    @Test
    public void testFindNodeMaxSumChildren() {
        GNode root = new GNode(1, new ArrayList<>());
        GNode n1 = new GNode(2, new ArrayList<>());
        GNode n2 = new GNode(3, new ArrayList<>());
        GNode n3 = new GNode(4, new ArrayList<>());
        GNode n4 = new GNode(5, new ArrayList<>());
        GNode n5 = new GNode(6, new ArrayList<>());
        GNode n6 = new GNode(7, new ArrayList<>());
        root.children.add(n1);
        root.children.add(n2);
        root.children.add(n3);
        n3.children.add(n4);
        n3.children.add(n5);
        n5.children.add(n6);
        int sum = findNodeMaxSumChildren ( root, 0);
        assert(sum==15);
    }


}
