package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

public class MinimumSubArrayLength {

    public Integer minSubArrayLength (int nums[], int sum) {

        int leftInd = 0;
        int rightInd =0;
        int minSub = Integer.MAX_VALUE;

        int subSum=0;

        while(rightInd<nums.length) {
            subSum += nums[rightInd];

            while(subSum >= sum){
                minSub = Math.min(minSub, rightInd-leftInd+1);
                subSum -= nums[leftInd];
                leftInd++;
            }
            rightInd++;

        }

        if (minSub == Integer.MAX_VALUE) {
            return 0;
        }

        return minSub;
    }

    @Test
    public void testMinSubArrayLength() {

        int nums[] = new int[] {2, 3, 1, 2, 4, 3};

        assert(minSubArrayLength(nums, 7)==2);

    }


}
