package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LongestIncreasingSubsequence {

    public List findLongestSeq(List<Integer> myList) {

        List<Integer> count = new ArrayList(myList.size()){{
        }};
        List<Integer> result = new ArrayList();

        for(int i=0; i<myList.size();i++) {
            count.add(1);
            result.add(Integer.MIN_VALUE);
        }

        for (int i=0; i < myList.size(); i++) {
            for (int j=0; j<i; j++) {
                if(myList.get(i) > myList.get(j)) {
                    count.set(i, Math.max(count.get(i), count.get(j)+1));
                }
            }
        }

//        int min = count.stream().min(Comparator.naturalOrder()).get();
//        int max = count.stream().max(Comparator.naturalOrder()).get();

        int indexLow=0;
        int indexHigh=0;

        int lowest=count.get(0);
        int highest=0;

        for(int i=0; i<count.size();i++) {
            int number = count.get(i);
            if( number > highest) {
                highest = number;
                indexHigh = i;
            }

            if( number < lowest) {
                lowest = number;
                indexLow = i;
            }
        }

//        final int high = indexHigh;
//        List<Integer> result = new ArrayList(){{
//            for(int i=0; i<high;i++)
//                add(1);
//        }};

        int forwardIndexcount=-1;
        for(int i=indexLow; i<=indexHigh;i++) {
            int indexCount = count.get(i);
            if(indexCount > forwardIndexcount) {
                forwardIndexcount = indexCount;
            }
            if( result.get(indexCount)==Integer.MIN_VALUE) {
                result.set(indexCount, myList.get(i));
            } else if( result.get(indexCount)!=Integer.MIN_VALUE && result.get(indexCount) > myList.get(i) && indexCount >= forwardIndexcount ) {
                result.set(indexCount, myList.get(i));
            }
        }

        return result;
    }

    @Test
    public void testFindLongestSeq2() {

        Integer test[] = {0, 8, 4, 12, 2, 10, 1, 14, 1, 9, 5, 13, 3};

        List<Integer> myList = Arrays.asList(test);
        System.out.println( findLongestSeq(myList) );
    }

    @Test
    public void testFindLongestSeq() {

        Integer test[] = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3};

        List<Integer> myList = Arrays.asList(test);
        System.out.println( findLongestSeq(myList) );
    }

}
