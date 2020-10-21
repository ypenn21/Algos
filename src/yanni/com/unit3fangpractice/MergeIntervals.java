package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;

public class MergeIntervals {

    private class IntervalComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            return a[0] > b[0] ? 1 : a[0] == b[0] ? 0 : -1;
        }
    }

    public int[][] merge(int[][] intervals) {
        Collections.sort(Arrays.asList(intervals), new IntervalComparator()); //sort

        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {// this line
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }


    public boolean isIntersecting(int[] interval1, int[] interval2) {
        boolean isIntersect = false;
        if(interval2[1] >= interval1[0] || interval1[1] >= interval2[0]) {
            isIntersect = true;
        }

        return isIntersect;
    }




    public int[][] merge2(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int result=0;
                if(o1[0]<o2[0]) {
                    result = -1;
                } else if (o1[0]>o2[0]) {
                    result = 1;
                }
                return result;
            }
        });

        LinkedList<int[]> result = new LinkedList();
        for (int[] interval : intervals) {
            if(result.isEmpty()) {
                result.add(interval);
                continue;
            }
            int[] prev = result.getLast();
            if (prev[1]>=interval[0] && prev[1]<=interval[1]) {
                prev[1] = interval[1];
            } else {
                result.add(interval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }








    @Test
    public void testMerge() {
        int[][] intervals = new int[] [] {
                {1,3},
                {8,10},
                {2,6},
                {15,18},
                {15,20}
        };
        int[][] lists = merge(intervals);
        for (int[] list:lists) {
            for(Integer number : list)
                System.out.print(number+" ");
            System.out.println("");
        }
    }

    @Test
    public void testMerge3() {
        int[][] intervals = new int[] [] {
                {1,4},
                {0,4}
        };
        int[][] lists = merge(intervals);
        for (int[] list:lists) {
            for(Integer number : list)
                System.out.print(number+" ");
            System.out.println("");
        }
    }

    @Test
    public void testMerge2() {
        int[][] intervals = new int[] [] {
                {1,4},
                {4,5}
        };
        int[][] lists = merge(intervals);
        for (int[] list:lists) {
            for(Integer number : list)
                System.out.print(number+" ");
            System.out.println("");
        }
    }
}
