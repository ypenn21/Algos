package yanni.com.unit.warmups;


// did this problem continuous subarray sum divisible by k and the one below

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.abs;

public class SortedArraysSquaredDescending {

    public List sortedArraySquared(List<Integer> numbs) {

        List<Integer> result = new ArrayList(numbs.size());
        IntStream.range(0, numbs.size()).forEach(i -> result.add(0));

        int left =0;
        int right = numbs.size()-1;


        for (int i =numbs.size()-1; i >=0 ; i-- ) {
            Integer numbl = numbs.get(left);
            Integer numbr = numbs.get(right);
            int squared = 0;
            if(abs(numbl)<abs(numbr)) {
                squared = numbr;
                right--;
            } else {
                squared = numbl;
                left ++;
            }
            result.set(i, squared*squared );
        }

        return result;
    }


    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            int square;
            if (Math.abs(nums[left]) < Math.abs(nums[right])) {
                square = nums[right];
                right--;
            } else {
                square = nums[left];
                left++;
            }
            result[i] = square * square;
        }
        return result;
    }


    @Test
    public void testSortedArraySquared() {
        List<Integer> lists = new ArrayList(){
            {
                add(-4);
                add(-3);
                add(0);
                add(1);
                add(2);
                add(3);
                add(5);
                add(6);
            }
        };
        List result = sortedArraySquared(lists);
        int [] numbs = {-4,-3,0,1,2,3,5,6};
        Arrays.sort(numbs);
        int [] squares = sortedSquares(numbs);
        assert(result.get(0)==new Integer(0));
        assert(result.get(1)==new Integer(1));
    }


}
