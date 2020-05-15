package yanni.com.unit2;// Java program program to merge two
// sorted arrays with O(1) extra space. 
  
import org.junit.jupiter.api.Test;

import java.util.Arrays;
  
class SortMultipleSortedArrays
{ 
//    static int arr1[] = new int[]{10, 15, 9, 5, 1, 15, 20};
//    static int arr2[] = new int[]{2, 3, 8, 13, 14, 16, 1};

    static int arr1[] = new int[]{1, 2, 3, 4, 5, 6,11};
    static int arr2[] = new int[]{9, 8, 8, 13, 14, 16};
      
    public void merge(int array1Size, int array2Size)
    { 
        // Iterate through all elements of ar2[] starting from 
        // the last element 
        for (int array2Index=array2Size-1; array2Index>=0; array2Index--)
        { 
            /* Find the smallest element greater than ar2[i]. Move all 
               elements one position ahead till the smallest greater 
               element is not found */
            int secLastIndA1 = array1Size-2;
            int lastArray1 = arr1[array1Size-1];
            System.out.println("lastArray1 "+ lastArray1);
            System.out.println("");
            for(int i = 0;i<arr1.length; i++ ) {
                System.out.print(arr1[i]+" ");
            }
            // secLastIndA1 subtract by 1 again here
            for (secLastIndA1=secLastIndA1; secLastIndA1 >= 0 && arr1[secLastIndA1] > arr2[array2Index]; secLastIndA1--) {
//                System.out.println("arr1[secLast + 1] = "+arr1[secLastIndA1 + 1] + " arr1[secLast] = "+arr1[secLastIndA1]);
                arr1[secLastIndA1 + 1] = arr1[secLastIndA1];
            }
       
            // If there was a greater element 
            if (secLastIndA1 != array1Size-2 || lastArray1 > arr2[array2Index])
            { 
                arr1[secLastIndA1+1] = arr2[array2Index];
                arr2[array2Index] = lastArray1;
            }
            System.out.println("");
        } 
    } 
      
    @Test
    public void testMerge()
    { 
        merge(arr1.length,arr2.length);
        System.out.print("After Merging nFirst Array: "); 
        System.out.println(Arrays.toString(arr1)); 
        System.out.print("Second Array:  "); 
        System.out.println(Arrays.toString(arr2)); 
    }
} 