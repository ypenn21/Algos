package yanni.com.codingpractice4;

import org.junit.jupiter.api.Test;

public class NextPermutation {
    // find first descending number from the last index to the first. then going forward find the first number bigger than that number. Once two numbers are found swap
    // If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void testNextPermutation(){
        int [] nums ={1, 2,3};
        nextPermutation(nums);
        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i]+" ");
        }
        System.out.println();
    }

    @Test
    public void testNextPermutation2(){
        int [] nums ={1, 2};
        nextPermutation(nums);
        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i]+" ");
        }
        System.out.println();
    }

    @Test
    public void testNextPermutation3(){
        int [] nums ={1,3,2};
        nextPermutation(nums);
        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i]+" ");
        }
        System.out.println();
    }


    @Test
    public void testNextPermutation4(){
        int [] nums ={3,2,1};
        nextPermutation(nums);
        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i]+" ");
        }
        System.out.println();
    }


}

