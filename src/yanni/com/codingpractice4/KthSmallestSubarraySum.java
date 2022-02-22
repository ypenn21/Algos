package yanni.com.codingpractice4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSmallestSubarraySum {

    @Test
    public void testKthSmallestSubarraySum() {
        int [] input = {2,1,3};
        int result = kthSmallestSubarraySum(input, 5);
        assert( result == 4 );
    }

    //for list integers you can do to sum: Integer sum = integers.stream().reduce(0, (a, b) -> a + b);
    public int kthSmallestSubarraySum(int[] nums, int k) {
        int lo = 1, hi = Arrays.stream(nums).sum();

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (countLessEqual(nums, mid) >= k) hi = mid;
            else lo = mid + 1;
        }

        return lo;
    }

    private int countLessEqual(int[] nums, int target) {
        int count = 0, sum = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum > target) {
                sum -= nums[left];
                left++;
            }
            count += right - left + 1;
        }
        return count;
    }

//    public int kthSmallestSubarraySum(int[] nums, int k) {
//        Arrays.sort(nums);
//        int result = kthSmallestSubarraySum(nums, k, 0);
//        return result ;
//    }
//
//    int count=0;
//    int endResult;
//
//    public int kthSmallestSubarraySum(int[] nums, int k, int index) {
//        int result = 0;
//        if(index>=nums.length) {
//            return result;
//        }
//
//        for(int i=index; i<nums.length; i++) {
//            result = nums[i];
//            count++;
//            result = result+kthSmallestSubarraySum(nums, k, index + 1);
//            if( k == count) {
//                endResult= result;
//                return endResult;
//            }
//        }
//
//        return endResult;
//    }

}
