package yanni.com.facebook;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class LeetCodeProblem {

    @Test
    public void testLargestValsFromLabels() {
        int[] vals = {5,4,3,2,1};
        int[] labels = {1,1,2,2,3};

        int result = largestValsFromLabels(vals, labels, 3, 1);

        assert(result==9);
    }


    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        List<Label> l = new ArrayList();

        for(int i=0;i< values.length;i++) {
            Label tmpL = new Label(values[i], labels[i]);
            l.add(tmpL);
        }
        System.out.println("wtf");
        PriorityQueue<Label> p = new PriorityQueue<>((a, b) -> b.val-a.val);
        p.addAll(l);

        Map<Integer, Integer> labCount = new HashMap<>();
        int result = 0;
        int count = 0;
        for(int i = 0; i<values.length;i++) {
            Label lab = p.poll();
            labCount.put(lab.labels, labCount.getOrDefault(lab.labels, 0)+1);
            if(labCount.get(lab.labels) > useLimit ) {
                continue;
            } else {
                result+=lab.val;
            }
            count++;
            if(numWanted == count) {
                break;
            }
        }


        return result;


    }

    public class Label {
        int val;
        int labels;

        public Label (int val, int labels) {
            this.val= val;
            this.labels = labels;
        }

    }
}
