package yanni.com.codingpractice4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Permutations {

//    public List<List<Integer>> findAllPermutations(int[] nums, int pos){
//
//        List<List<Integer>> result = new ArrayList<>();
//
//
//        for(int i=pos;i<nums.length;i++) {
//            List<Integer> perm = createPermutations(nums, i);
//            System.out.println(perm);
//            findAllPermutations(nums, i+1);
//        }
//
//        return result;
//
//    }
//
//    public List<Integer> createPermutations(int[] nums, int pos) {
//        List<Integer> result = new ArrayList<>();
//        result.add(nums[pos]);
//        for(int i=0;i<nums.length;i++) {
//            if(i==pos) {
//                continue;
//            }
//            result.add(nums[i]);
//        }
//        return result;
//    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Set<List<Integer>> resultsSet = permuteHelper(0, nums);
        for(List l : resultsSet) {
            results.add(l);
        }
        return results;
    }

    private void swap(int[] nums, int i, int pos) {
        int iValu = nums[i];
        nums[i]=nums[pos];
        nums[pos]=iValu;
    }

    private Set<List<Integer>> permuteHelper(int pos, int[] nums) {
        IntStream.rangeClosed(0, nums.length-1).forEach(i -> System.out.print(nums[i]+" "));
        System.out.println("");
        Set<List<Integer>> results = new HashSet<>();
        if ( pos==nums.length-1 ) {
            List<Integer> list = new ArrayList<>(); //new ArrayList<>(Arrays.asList(nums)); need Integer not primitive int for that to work
            IntStream.rangeClosed(0, nums.length-1).forEach(i -> list.add(nums[i]));
            results.add(list);
            return results;
        }

        for (int i =pos; i<nums.length; i++) {
            swap(nums, i, pos);
            results.addAll(permuteHelper(pos+1, nums));
            //reset back tracking
            swap(nums, i, pos);
        }

        return results;

//       [1, 2, 3, 4][1, 2, 4, 3][1, 3, 2, 4][1, 3, 4, 2][1, 4, 3, 2]
//[1, 4, 2, 3][2, 1, 3, 4][2, 1, 4, 3][2, 3, 1, 4][2, 3, 4, 1]
//[2, 4, 3, 1][2, 4, 1, 3][3, 2, 1, 4][3, 2, 4, 1][3, 1, 2, 4]
//[3, 1, 4, 2][3, 4, 1, 2][3, 4, 2, 1][4, 2, 3, 1][4, 2, 1, 3]
//[4, 3, 2, 1][4, 3, 1, 2][4, 1, 3, 2][4, 1, 2, 3]


        //   with these two takes taken out:
        //   nums.set(i, nums.get(start));
        //   nums.set(start, iValu2);
//        [1, 2, 3, 4][1, 2, 4, 3][1, 4, 2, 3][1, 4, 3, 2][1, 2, 3, 4]
//[1, 2, 4, 3][2, 1, 4, 3][2, 1, 3, 4][2, 3, 1, 4][2, 3, 4, 1]
//[2, 1, 4, 3][2, 1, 3, 4][3, 1, 2, 4][3, 1, 4, 2][3, 4, 1, 2]
//[3, 4, 2, 1][3, 1, 2, 4][3, 1, 4, 2][2, 1, 4, 3][2, 1, 3, 4]
//[2, 3, 1, 4][2, 3, 4, 1][2, 1, 4, 3][2, 1, 3, 4]
    }


    @Test
    public void testFindAllPermutations() {

        int[] input = {1,2,3};

        List<List<Integer>> result = permute(input);

        result.stream().forEach(integers -> {
            System.out.println(integers);
        });
    }


    @Test
    public void testFindAllPermutations2() {

        int[] input = {1,1,2};

        List<List<Integer>> result = permute(input);

        result.stream().forEach(integers -> {
            System.out.println(integers);
        });

    }
}
