package yanni.com.unit3fangpractice;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// Java program to find a triplet
class FindTriplet { 
  
    // returns true if there is triplet with sum equal 
    // to 'sum' present in A[]. Also, prints the triplet 
    Set<List<Integer>> find3Numbers(int A[], int arr_size, int sum)
    {

        Set<List<Integer>> mySet = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
  
        // Fix the first element as A[i] 
        for (int i = 0; i < arr_size - 2; i++) {
            // Fix the second element as A[j]
            if (dups.add(A[i]))
                for (int j = i + 1; j < arr_size - 1; j++) {
                // Now look for the third number 
                    for (int k = j + 1; k < arr_size; k++) {
                        if (A[i] + A[j] + A[k] == sum) {
                            List<Integer> myList = new ArrayList<>();

                            PriorityQueue<Integer> myQueue = new PriorityQueue<>();
                            System.out.print("Triplet is " + A[i] + ", " + A[j] + ", " + A[k]+" ");
                            myQueue.add(A[i]);
                            myQueue.add(A[j]);
                            myQueue.add(A[k]);
                            while(!myQueue.isEmpty()) {
                                myList.add(myQueue.poll());
                            }
                            mySet.add(myList);
                        }
                    }
                }
        }
        // If we reach here, then no triplet was found 
        return mySet;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
//        Set<Pair> found = new HashSet<>();
        Set<List<Integer>> found = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; ++i)
            if (dups.add(nums[i]))
                for (int j = i + 1; j < nums.length; ++j) {
                    int complement = 0 - nums[i] - nums[j];
                    if (seen.containsKey(complement) && seen.get(complement) == i) {
//                        int v1 = Math.min(nums[i], Math.min(complement, nums[j]));
//                        int v2 = Math.max(nums[i], Math.max(complement, nums[j]));
                        PriorityQueue<Integer> myQueue = new PriorityQueue<>();
                        myQueue.add(nums[i]);
                        myQueue.add(nums[j]);
                        myQueue.add(complement);
                        List<Integer> myList = new ArrayList<>();
                        while(!myQueue.isEmpty()) {
                            myList.add(myQueue.poll());
                        }
                        if (found.add(myList))
                            res.add(myList);
                    }
                    seen.put(nums[j], i);
                }
        return res;
    }
  
    // Driver program to test above functions 
    public static void main(String[] args) 
    { 
        FindTriplet triplet = new FindTriplet();
        int A[] = {-1, 0, 1, 2, -1, -4};
        int sum = 0;
        int arr_size = A.length;
  
        triplet.find3Numbers(A, arr_size, sum);
        List<List<Integer>> myList = triplet.threeSum(A);
        System.out.println(" ");
        System.out.println(myList.toString());
    } 
} 