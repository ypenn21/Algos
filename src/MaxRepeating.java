import java.io.*; 
  
class MaxRepeating { 
  
    // Returns maximum repeating element in arr[0..n-1]. 
    // The array elements are in range from 0 to k-1 
    static int maxRepeating(int arr[], int n, int k) 
    { 
        // Iterate though input array, for every element 
        // arr[i], increment arr[arr[i]%k] by k 
        for (int i = 0; i< n; i++) 
            arr[(arr[i]%k)] += k;
  
        // Find index of the maximum repeating element 
        int max = arr[0], result = 0;
        int indexMax = 0;
        int maxEqual= -1;
        for (int i = 1; i < n; i++) 
        { 
            if (arr[i] > max) 
            { 
                max = arr[i]; 
                result = i;
                indexMax = i;
            }
            if (arr[i] == max && indexMax != i) {
                maxEqual = i;
            }
        } 
  
        /* Uncomment this code to get the original array back 
        for (int i = 0; i< n; i++) 
          arr[i] = arr[i]%k; */
  
        // Return index of the maximum element
        if (maxEqual != -1) System.out.println("There is a another number that is equal in occurence: "+maxEqual);
        return result; 
    } 
  
    /*Driver function to check for above function*/
    public static void main (String[] args) 
    { 
  
        int arr[] = {0, 0, 1, 5, 3, 1, 1, 1, 7};
        int n = arr.length; 
        int k=8; 
        System.out.println("Maximum repeating element is: " + 
                           maxRepeating(arr,n,k)); 
    } 
} 