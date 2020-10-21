package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

public class BinarySearch {

    public int binarySearch(int[] array, int find) {

        int length =array.length-1;
        return binarySearch(array, 0, length, find);
    }

    public int binarySearch(int[] array, int left, int right, int find) {


        if(left>right) {
            return -100000000;
        }

        int mid = (left+right)/2;
        if(array[mid]==find) {
            return mid;
        }

        if(find<array[mid]) {
            return binarySearch(array, left, mid-1, find);
        } else {
            return binarySearch(array, mid+1, right, find);
        }
    }


    public int binarySearchIterative(int[] array, int find) {
        int length =array.length-1;
        int left=0;
        int right = length;

        while( (right>=left) ) {

            int mid = (left+right)/2;

            if(array[mid]==find) {
                return mid;
            } else if(find < array[mid]) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }


        return -100000000;
    }

    @Test
    public void testBinarySearch() {
        int [] myArray = {1,3,6,7,10,11};
        int index = binarySearch(myArray, 13);
        System.out.println(index);

    }

    @Test
    public void testBinarySearch2() {
        int [] myArray = {1,3,6,7,10,11};
        int index = binarySearch(myArray, 11);
        System.out.println(index);

    }

    @Test
    public void testBinarySearch3() {
        int [] myArray = {1,3,6,7,10,11};
        int index = binarySearch(myArray, 7);
        System.out.println(index);

    }

    @Test
    public void testBinarySearch4() {
        int [] myArray = {1,3,6,7,10,11};
        int index = binarySearch(myArray, 3);
        System.out.println(index);

    }

    @Test
    public void testBinarySearch5() {
        int [] myArray = {1,3,6,7,10,11};
        int index = binarySearch(myArray, 0);
        System.out.println(index);

    }

    @Test
    public void testBinarySearchIterative1() {
        int [] myArray = {1,3,6,7,10,11};
        int index = binarySearchIterative(myArray, 13);
        System.out.println(index);

    }

    @Test
    public void testBinarySearchIterative2() {
        int [] myArray = {1,3,6,7,10,11};
        int index = binarySearchIterative(myArray, 11);
        System.out.println(index);

    }

    @Test
    public void testBinarySearchIterative3() {
        int [] myArray = {1,3,6,7,10,11,12};
        int index = binarySearchIterative(myArray, 7);
        System.out.println(index);

    }

    @Test
    public void testBinarySearchIterative4() {
        int [] myArray = {1,3,6,7,10,11};
        int index = binarySearchIterative(myArray, 3);
        System.out.println(index);

    }

    @Test
    public void testBinarySearchIterative5() {
        int [] myArray = {1,3,6,7,10,11};
        int index = binarySearchIterative(myArray, -1);
        System.out.println(index);

    }

}
