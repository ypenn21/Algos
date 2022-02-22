package yanni.com.codingpractice4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class ThreeSum {

        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> results = new ArrayList<>();
            Set<String> keys = new HashSet();
            for(int i=0;i<nums.length;i++) {
                for(int j=i+1;j<nums.length;j++) {
                    for(int k=j+1;k<nums.length;k++) {
                        if(nums[i]+nums[j]+nums[k]==0) {
                            List<Integer> addToZero = new ArrayList<>();
                            PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
                            pQueue.add(nums[i]);
                            pQueue.add(nums[j]);
                            pQueue.add(nums[k]);
                            String key="";
                            while(!pQueue.isEmpty()) {
                                Integer num = pQueue.poll();
                                addToZero.add(num);
                                key =key+""+num;
                            }
                            if(!keys.contains(key)){
                                results.add(addToZero);
                                keys.add(key);
                            }

                        }
                    }
                }
            }
            return results;

        }


    public List<List<Integer>> threeSumFast(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Map<Integer, Integer> numbers = new HashMap<>();
        Set<String> keys = new HashSet();
        for(int ind=0; ind<nums.length;ind++){
            if(numbers.containsKey(nums[ind])){
                numbers.put(nums[ind], numbers.get(nums[ind])+1);
            } else {
                numbers.put(nums[ind], 1);
            }
        }


        for(int i=0;i<nums.length;i++) {
            for(int j=i+1;j<nums.length;j++) {
                int sum2 = nums[i]+nums[j];
                if(numbers.containsKey(sum2*-1)){
                    List<Integer> addToZero = new ArrayList<>();
                    PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
                    if(sum2*-1 == nums[i] || sum2*-1 == nums[j]) {
                        if(numbers.get(sum2*-1) < 2 || (nums[i]==0 && nums[j]==0 && numbers.get(sum2*-1) < 3)){
                            continue;
                        }
                    }
                    pQueue.add(nums[i]);
                    pQueue.add(nums[j]);
                    pQueue.add(sum2*-1);
                    String key="";
                    while(!pQueue.isEmpty()) {
                        Integer num = pQueue.poll();
                        addToZero.add(num);
                        key =key+""+num;
                    }
                    if(!keys.contains(key)){
                        results.add(addToZero);
                        keys.add(key);
                    }

                }
            }
        }
        return results;

    }

    public List<List<Integer>> threeSumOptimized(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i)
            // check not same value nums[i - 1] != nums[i] to avoid dups in res
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSum(nums, i, res);
            }
        return res;
    }

    void twoSum(int[] nums, int i, List<List<Integer>> res) {
        Set<Integer> seen = new HashSet<>();
        for (int j = i + 1; j < nums.length; ++j) {
            int complement = -nums[i] - nums[j];
            if (seen.contains(complement)) {
                res.add(Arrays.asList(nums[i], nums[j], complement));
                // check not same value nums[j] == nums[j + 1] to avoid dups in res
                while (j + 1 < nums.length && nums[j] == nums[j + 1])
                    ++j;
            }
            seen.add(nums[j]);
        }
    }

    public void twoSum2 (int[] nums, int i, List<List<Integer>> res) {
        Set<Integer> seen = new HashSet<>();
        for (int j = i + 1; j < nums.length; ++j) {
            int complement = -nums[i] - nums[j];
            if (seen.contains(complement)) {
                res.add(Arrays.asList(nums[i], nums[j], complement));
                // check not same value nums[j] == nums[j + 1] to avoid dups in res
                while (j + 1 < nums.length && nums[j] == nums[j + 1])
                    ++j;
            }
            seen.add(nums[j]);
        }
    }

    public int maxSum(int arr[], int n, int k)
    {
        // n must be greater
        if (n < k) {
            System.out.println("Invalid");
            return -1;
        }

        // Compute sum of first window of size k
        int max_sum = 0;
        for (int i = 0; i < k; i++)
            max_sum += arr[i];

        // Compute sums of remaining windows by
        // removing first element of previous
        // window and adding last element of
        // current window.
        int window_sum = max_sum;
        for (int i = k; i < n; i++) {
            // 3,0  4,1  5,2  6,3
            window_sum += arr[i] - arr[i - k];
            max_sum = Math.max(max_sum, window_sum);
        }

        return max_sum;
    }

    @Test
    public void testMaxSum(){

        int arr[] = { 1, 4, 2, 10, 2, 3, 1, 0, 20 };
        int sum = maxSum(arr, arr.length  , 3);
        assert (sum>0);
        System.out.println(sum);
    }

    @Test
    public void testThreeSum(){
            int[] input = {-1,0,1,2,-1,-4};
            List result = threeSumOptimized(input);
            assert (result.size()==2);
        }

    @Test
    public void testNSum(){
        int[] input = {1,0,-1,0,-2,2};
        List result = fourSum(input, 0);
        assert (result.size()==3);
    }


    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }
    public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (start == nums.length) // add || nums[start] * k > target || target > nums[nums.length - 1] * k for better performance
            return res;
        if (k == 2)
            return twoSum2(nums, target, start);
        for (int i = start; i < nums.length; ++i)
            if (i == start || nums[i - 1] != nums[i])
                for (List set : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(set);
                }
        return res;
    }

    public List<List<Integer>> twoSum2(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i=start;i<nums.length;i++) {
            for(int j = i+1;j<nums.length;j++) {
                if(nums[i] + nums[j] == target) {
                    res.add(Arrays.asList(nums[i], nums[j]));
                }
            }
        }
        return res;
    }

    public List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int lo = start, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            if (sum < target || (lo > start && nums[lo] == nums[lo - 1]))
                ++lo;
            else if (sum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1]))
                --hi;
            else
                res.add(Arrays.asList(nums[lo++], nums[hi--]));
        }
        return res;
    }
}
