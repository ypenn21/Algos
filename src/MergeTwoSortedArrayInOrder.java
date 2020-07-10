import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MergeTwoSortedArrayInOrder {


    public List mergeTwoSortedArrayInOrder(List<Integer> list1, List<Integer> list2) {
        int len1 =list1.size();
        int len2 = list2.size();

        int ind1 = 0;
        int ind2 = 0;

        List<Integer> result = new ArrayList();

        while(ind1< len1 && ind2 <len2) {
            if(list1.get(ind1) < list2.get(ind2)) {
                result.add(list1.get(ind1));
                ind1++;
            } else {
                result.add(list2.get(ind2));
                ind2++;
            }
        }


        for(ind1=ind1;ind1 < list1.size(); ind1++) {
            result.add(list1.get(ind1));
        }

        for(ind2=ind2;ind2 < list2.size(); ind2++) {
            result.add(list2.get(ind2));
        }

        return result;
    }

    public List mergeSortNSortedArrays(List<List<Integer>> nSortedArrays){

        List result = new ArrayList();

        for( List<Integer> array :  nSortedArrays ) {
            result = mergeTwoSortedArrayInOrder(result, array);
        }

        return result;
    }


    @Test
    public void testmergeSortNSortedArrays() {
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        List list3 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(5);
        list1.add(10);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(10);
        list3.add(3);
        list3.add(4);
        list3.add(5);
        list3.add(10);
        List listofLists = new ArrayList();
        listofLists.add(list1);
        listofLists.add(list2);
        listofLists.add(list3);
        List result = mergeSortNSortedArrays(listofLists);
        System.out.println(result);

    }


    @Test
    public void testMergeTwoSortedArrayInOrder() {
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(5);
        list1.add(10);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(10);
        List result = mergeTwoSortedArrayInOrder(list1, list2);
        System.out.println(result);

    }

    @Test
    public void testMergeTwoSortedArrayInOrder2() {
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        list1.add(1);
        list1.add(1);
        list1.add(5);
        list1.add(10);
        list2.add(1);
        list2.add(4);
        list2.add(5);
        list2.add(15);
        List result = mergeTwoSortedArrayInOrder(list1, list2);
        System.out.println(result);

    }


    @Test
    public void testMergeTwoSortedArrayInOrder3() {
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        list1.add(1);
        list1.add(1);
        list1.add(5);
        list1.add(10);
        list2.add(11);
        list2.add(13);
        list2.add(14);
        list2.add(30);
        List result = mergeTwoSortedArrayInOrder(list1, list2);
        System.out.println(result);

    }

    @Test
    public void testMergeTwoSortedArrayInOrder4() {
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        list1.add(1);
        list1.add(1);
        list1.add(5);
        list1.add(10);
        list2.add(1);
        list2.add(10);
        list2.add(14);
        list2.add(30);
        List result = mergeTwoSortedArrayInOrder(list1, list2);
        System.out.println(result);

    }




}
