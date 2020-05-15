package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PythagoreanTriples {

    public List findPythagoreanTriples (List nums) {
        Set<Integer> numSquared = ( (List<Integer>) nums ).stream().map(element -> (Integer) element * (Integer) element).collect(Collectors.toSet());
        for ( Integer num : ( (List<Integer>) nums )) {
            for ( Integer numb : ( (List<Integer>) nums) ) {
                if (numSquared.contains(num*num + numb*numb) ) {
                    return Arrays.asList(num, numb);
                }
            }
        }
        return Arrays.asList();
    }

    @Test
    public void testFindPythagoreanTriples () {
        List<Integer> nums = Arrays.asList(3, 5, 12, 5, 13);
        List result = findPythagoreanTriples (nums);
        System.out.println(result);
    }

}
