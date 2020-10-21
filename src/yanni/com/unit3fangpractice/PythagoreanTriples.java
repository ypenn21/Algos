package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PythagoreanTriples {

    public List findPythagoreanTriples (List<Integer> nums) {
        Set<Integer> numSquared = nums.stream().map(element -> (Integer) element * (Integer) element).collect(Collectors.toSet());
        for ( Integer num : nums) {
            for ( Integer numb : nums ) {
                if(num==numb)
                    continue;
                if (numSquared.contains(num*num + numb*numb) ) {
                    return Arrays.asList(num, numb);
                }
            }
        }
        return Arrays.asList();
    }

    public List findPythagoreanTriples2(List<Integer> nums) {
        //this stream returns a set of squared numbers
        List<Integer> numSquared = nums.stream().map(element -> (Integer) element * (Integer) element).collect(Collectors.toList());
        for(int i=0;i<nums.size();i++) {
            for(int ind=i+1;ind<nums.size();ind++){
                int num1 = nums.get(i);
                int num2 = nums.get(ind);
                if(ind < nums.size() && numSquared.contains(num1^2+num2^2)) {
                    return Arrays.asList(nums.get(i), nums.get(ind));
                }
            }
        }

        return Arrays.asList();
    }













    public List findPythagoreanTriples3(List<Integer> nums) {
        Set<Integer> squaredNums = nums.stream().map(item-> item^2).collect(Collectors.toSet());

        for(int i = 0;i<squaredNums.size()-1;i++) {
            for(int ind=i+1;ind<squaredNums.size()-1;ind++){
                int num1 = nums.get(i);
                int num2 = nums.get(ind);
                if(squaredNums.contains(num1^2+num2^2)) {
                    return Arrays.asList(num1, num2);
                }
            }
        }


        return new ArrayList();
    }




    @Test
    public void testFindPythagoreanTriples () {
        List<Integer> nums = Arrays.asList(3, 5, 12, 5, 13);
        List result = findPythagoreanTriples3(nums);
        System.out.println(result);
    }

}
