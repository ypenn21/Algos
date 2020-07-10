package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MergeNSortedArraysFindNFrequentElements {

    class MyNode {

        public Integer val;
        public MyNode next;
        public MyNode prev;

        public MyNode(int val) {
            this.val = val;
        }

    }

    class MyLinkedList {

        MyNode root;

        public MyLinkedList(int val){
            this.root = new MyNode(val);
        }

    }

    //
//        while(crawler1!=null) {
//            if(crawler1.val < crawler2.val) {
//                crawler1.next = crawler2;
//                ind1++;
//            } else {
//                crawler2.next = crawler1;
//                ind2++;
//            }
//        }
//
//
//        for(ind1=ind1;ind1 < list1.size(); ind1++) {
//            result.add(list1.get(ind1));
//        }
//
//        for(ind2=ind2;ind2 < list2.size(); ind2++) {
//            result.add(list2.get(ind2));
//        }


    public MyLinkedList mergeTwoSortedArrayInOrder(MyLinkedList list1, MyLinkedList list2, boolean doubleLinked) {
        if(list2.root.val < list1.root.val) {
            return mergeTwoSortedArray(list2, list1,  doubleLinked);

        } else {
            return mergeTwoSortedArray(list1, list2, doubleLinked);
        }

    }

    public MyLinkedList mergeTwoSortedArray(MyLinkedList list1, MyLinkedList list2, boolean doubleLinked) {
        if(list1 == null || list1.root==null) {
            return list2;
        }

        MyNode crawler1 = list1.root;
        MyNode crawler2 = list2.root;

        MyNode previous = null;

        while(crawler2!=null && crawler1 !=null) {
            if(crawler1!=null) {
                if (doubleLinked) {
                    addNodeDoubleLinkedList(crawler1, new MyNode(crawler2.val), list1.root);
                } else {
                    addNode(crawler1, new MyNode(crawler2.val), previous);
                }
            }
            previous = crawler1;
            crawler1 = crawler1.next;
            System.out.println(crawler2.val);
            crawler2 = crawler2.next;
        }

        return list1;
    }

    public MyNode addNodeDoubleLinkedList(MyNode myPos, MyNode addNode, MyNode root) {
        while(myPos!=null){
            MyNode nextNode = myPos.next;
            if(myPos.val == addNode.val || (myPos.val < addNode.val && myPos.next !=null && myPos.next.val > addNode.val)) {
                myPos.next = addNode;
                addNode.prev = myPos;
                addNode.next = nextNode;
                nextNode.prev = addNode;
                break;
            }
            else if(myPos.val > addNode.val && myPos.val==root.val) {
                addNode.next = myPos;
                myPos.prev = addNode;
                break;
            }
            if(myPos.next==null){
                myPos.next = addNode;
                addNode.prev = myPos;
                break;
            }
            myPos = myPos.next;
        }

        return myPos;

    }

    public MyNode addNode(MyNode myPos, MyNode addNode, MyNode previous) {
        while(myPos!=null){
            MyNode nextNode = myPos.next;
            if( myPos.val == addNode.val || (myPos.val < addNode.val && myPos.next !=null && myPos.next.val > addNode.val)) {
                myPos.next = addNode;
                addNode.next = nextNode;
                break;
            }
            else if(myPos.val > addNode.val) {
                if (previous != null){
                    previous.next = addNode;
                    addNode.next = myPos;
                }
                else {
                    MyNode tmp = new MyNode(myPos.val);
                    tmp.next = nextNode;
                    myPos.val = addNode.val;
                    myPos.next = tmp;
                }
                break;
            }
            if(myPos.next==null){
                myPos.next = addNode;
                break;
            }
            myPos = myPos.next;
        }

        return myPos;

    }

    @Test
    public void testMergeTwoSortedLinkedListInOrder4() {
        MyLinkedList myLinkedList = new MyLinkedList(2);


        MyLinkedList myLinkedList2 = new MyLinkedList(1);
        MyLinkedList output = mergeTwoSortedArrayInOrder(myLinkedList, myLinkedList2, false);

        MyNode crawl = output.root;
        while (crawl!=null) {
            System.out.println(crawl.val);
            crawl = crawl.next;
        }
    }

    @Test
    public void testMergeTwoSortedDoubleLinkedListInOrder() {
        int[] input = {1, 1, 1, 3, 3, 3, 5,0, 0};
        MyLinkedList myLinkedList = new MyLinkedList(1);
        myLinkedList.root.next = new MyNode(2);
        myLinkedList.root.next.prev = myLinkedList.root;
        myLinkedList.root.next.next = new MyNode(5);
        myLinkedList.root.next.next.prev = myLinkedList.root.next;
        myLinkedList.root.next.next.next = new MyNode(10);
        myLinkedList.root.next.next.next.prev = myLinkedList.root.next.next;


        MyLinkedList myLinkedList2 = new MyLinkedList(3);
        myLinkedList2.root.next = new MyNode(4);
        myLinkedList2.root.next.next = new MyNode(8);
        myLinkedList2.root.next.next.next = new MyNode(11);
        MyLinkedList output = mergeTwoSortedArrayInOrder(myLinkedList, myLinkedList2, true);

        MyNode crawl = output.root;
        while (crawl!=null) {
            System.out.println(crawl.val);
            crawl = crawl.next;
        }
    }

    @Test
    public void testMergeTwoSortedLinkedListInOrder3() {
        MyLinkedList myLinkedList = null;


        MyLinkedList myLinkedList2 = new MyLinkedList(3);
        myLinkedList2.root.next = new MyNode(4);
        myLinkedList2.root.next.next = new MyNode(8);
        myLinkedList2.root.next.next.next = new MyNode(11);
        myLinkedList2.root.next.next.next.next = new MyNode(12);
        myLinkedList2.root.next.next.next.next.next = new MyNode(13);
        myLinkedList2.root.next.next.next.next.next.next = new MyNode(14);
        MyLinkedList output = mergeTwoSortedArrayInOrder(myLinkedList, myLinkedList2, false);

        MyNode crawl = output.root;
        while (crawl!=null) {
            System.out.print(crawl.val+" ");
            crawl = crawl.next;
        }
    }


    @Test
    public void testMergeTwoSortedLinkedListInOrder2() {
        MyLinkedList myLinkedList = new MyLinkedList(-11);
        myLinkedList.root.next = new MyNode(1);


        MyLinkedList myLinkedList2 = new MyLinkedList(3);
        myLinkedList2.root.next = new MyNode(4);
        myLinkedList2.root.next.next = new MyNode(8);
        myLinkedList2.root.next.next.next = new MyNode(11);
        myLinkedList2.root.next.next.next.next = new MyNode(12);
        myLinkedList2.root.next.next.next.next.next = new MyNode(13);
        myLinkedList2.root.next.next.next.next.next.next = new MyNode(14);
        MyLinkedList output = mergeTwoSortedArrayInOrder(myLinkedList, myLinkedList2, false);

        MyNode crawl = output.root;
        while (crawl!=null) {
            System.out.print(crawl.val+" ");
            crawl = crawl.next;
        }
    }

    @Test
    public void testMergeTwoSortedLinkedListInOrder6() {
        MyLinkedList myLinkedList = new MyLinkedList(-2);
        myLinkedList.root.next = new MyNode(5);


        MyLinkedList myLinkedList2 = new MyLinkedList(-9);
        myLinkedList2.root.next = new MyNode(-6);
        myLinkedList2.root.next.next = new MyNode(-3);
        myLinkedList2.root.next.next.next = new MyNode(-1);
        myLinkedList2.root.next.next.next.next = new MyNode(1);
        myLinkedList2.root.next.next.next.next.next = new MyNode(6);
        MyLinkedList output = mergeTwoSortedArrayInOrder(myLinkedList, myLinkedList2, false);

        MyNode crawl = output.root;
        while (crawl!=null) {
            System.out.print(crawl.val+" ");
            crawl = crawl.next;
        }
    }




    @Test
    public void testMergeTwoSortedLinkedListInOrder() {
        MyLinkedList myLinkedList = new MyLinkedList(1);
        myLinkedList.root.next = new MyNode(2);
        myLinkedList.root.next.prev = myLinkedList.root;
        myLinkedList.root.next.next = new MyNode(5);
        myLinkedList.root.next.next.prev = myLinkedList.root.next;
        myLinkedList.root.next.next.next = new MyNode(10);
        myLinkedList.root.next.next.next.prev = myLinkedList.root.next.next;


        MyLinkedList myLinkedList2 = new MyLinkedList(1);
        myLinkedList2.root.next = new MyNode(1);
        myLinkedList2.root.next.next = new MyNode(8);
        myLinkedList2.root.next.next.next = new MyNode(11);
        myLinkedList2.root.next.next.next.next = new MyNode(12);
        myLinkedList2.root.next.next.next.next.next = new MyNode(13);
        myLinkedList2.root.next.next.next.next.next.next = new MyNode(14);
        MyLinkedList output = mergeTwoSortedArrayInOrder(myLinkedList, myLinkedList2, false);

        MyNode crawl = output.root;
        while (crawl!=null) {
            System.out.print(crawl.val+" ");
            crawl = crawl.next;
        }
    }

    public int[] mergeSortNSortedArrays(int[][] nSortedArrays){
        int [] result = new int[12];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
        for(int i=0; i<nSortedArrays.length; i++) {
            for(int ind=0;ind<nSortedArrays[i].length;ind++){
                minHeap.add(nSortedArrays[i][ind]);
            }
        }

        int resultInd=0;
        while(minHeap.peek()!=null) {
            result[resultInd] = minHeap.poll();
            resultInd++;
        }

        return result;
    }

    public List findNFrequentElements(int[] nums, int n){
        PriorityQueue<Map.Entry<Integer, Integer >> minHeap = new PriorityQueue<>((x, y) -> Integer.compare(x.getValue(), y.getValue()));
        Map<Integer, Integer> numAndFrequency = new HashMap();
        for(int i=0; i< nums.length; i++) {
            int frequency = numAndFrequency.get(nums[i]) == null ? 1 : numAndFrequency.get(nums[i])+1;
            numAndFrequency.put(nums[i], frequency);
        }

        for ( Map.Entry<Integer, Integer> entry : numAndFrequency.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > n ) {
                Map.Entry eTemp = minHeap.poll();
                System.out.println("get rid of entry: "+eTemp);
            }
        }

        List<Integer> result = new ArrayList<>();
        Iterator<Map.Entry<Integer, Integer> > iterator = minHeap.iterator();

        while(iterator.hasNext()){
            result.add(iterator.next().getKey());
        }
        System.out.println(result);
        return result;
    }

    @Test
    public void testFindNFrequentElements() {
        int[] input = {1, 1, 1, 3, 3, 3, 5,0, 0};
        List output = findNFrequentElements(input, 1);

        for (int i=0; i<output.size(); i++) {
            System.out.println(output.get(i));
        }
    }


    @Test
    public void testMergeSortNSortedArrays () {
        int[][] input = { {10, 20, 30}, {15, 25, 35}, {22, 44, 66}, {33, 55, 77} };

        int[] output = mergeSortNSortedArrays(input);
        for (int i=0; i<output.length; i++) {
            System.out.println(output[i]);
        }
    }


    //only works for 2 arrays
    public void mergeConstantSpace(int[] arr1, int[] arr2)
    {
        // Iterate through all elements of ar2[] starting from
        // the last element
        for (int i=arr2.length-1; i>=0; i--)
        {
            /* Find the smallest element greater than ar2[i]. Move all
               elements one position ahead till the smallest greater
               element is not found */
            int j, last = arr1[arr1.length-1];
            for (j=arr1.length-2; j >= 0 && arr1[j] > arr2[i]; j--)
                arr1[j+1] = arr1[j];

            // If there was a greater element
            if (j != arr1.length-2 || last > arr2[i])
            {
                arr1[j+1] = arr2[i];
                arr2[i] = last;
            }
        }
    }

    @Test
    public void testMergeSortNSortedArraysConstantSpace () {
        int arr1[] = new int[]{1, 5, 9, 10, 15, 20};
        int arr2[] = new int[]{2, 3, 8, 13};

        mergeConstantSpace(arr1, arr2);
        for (int i=0; i<arr1.length; i++) {
            System.out.println(arr1[i]);
        }

        for (int i=0; i<arr2.length; i++) {
            System.out.println(arr2[i]);
        }
    }

}
