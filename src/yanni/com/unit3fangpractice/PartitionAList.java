package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PartitionAList {

    public List partitionAList (List<Integer> myList, int n) {

        Integer high = myList.size()-1;
        Integer index = 0;
        Integer low = 0;


        while (index<=high) {
            if(myList.get(index) < n) {
                int tmpLow = myList.get(low);
                myList.set(low, myList.get(index));
                myList.set(index, tmpLow);
                index++;
                low++;
            } else if (myList.get(index) > n) {
                int tmpHigh = myList.get(index);
                myList.set(index, myList.get(high));
                myList.set(high, tmpHigh);
                high--;
            } else {
                index++;
            }
        }



        return myList;
    }


    @Test
    public void testPatitionAList3(){
//        List<Integer> myNums = IntStream.range(1, 10).boxed().collect(Collectors.toList());
        List<Integer> myNums2 = new ArrayList<Integer>();
        myNums2.add(5);
        myNums2.add(9);
        myNums2.add(8);
        myNums2.add(9);
        myNums2.add(1);
        myNums2.add(3);
        myNums2.add(8);
        myNums2.add(9);
        List result = partitionAList (myNums2, 4);
        System.out.println(result);
    }




    @Test
    public void testPatitionAList(){
//        List<Integer> myNums = IntStream.range(1, 10).boxed().collect(Collectors.toList());
        List<Integer> myNums2 = new ArrayList<>();
        myNums2.add(4);
        myNums2.add(9);
        myNums2.add(8);
        myNums2.add(9);
        myNums2.add(1);
        myNums2.add(3);
        myNums2.add(4);
        myNums2.add(5);
        List result = partitionAList (myNums2, 4);
        System.out.println(result);
    }

    @Test
    public void testPatitionAList2(){
//        List<Integer> myNums = IntStream.range(1, 10).boxed().collect(Collectors.toList());
        List<Integer> myNums2 = new ArrayList<>();
        myNums2.add(4);
        myNums2.add(2);
        myNums2.add(4);
        myNums2.add(1);
        myNums2.add(2);
        myNums2.add(4);
        myNums2.add(2);
        myNums2.add(9);
        List result = partitionAList (myNums2, 4);
        System.out.println(result);
    }


}
