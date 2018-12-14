// Java implementation of iterative Binary Search
class BinarySearch
{
    // Returns index of x if it is present in arr[], 
    // else return -1
    int binarySearch(int arr[], int x)
    {
        int l = 0, r = arr.length - 1;
        while (l <= r)
        {
            int m = (r+l)/2;
 
            // Check if x is present at mid
            if (arr[m] == x)
                return m;
 
            // If x greater, ignore left half
            if (arr[m] < x)
                l = m + 1;
 
            // If x is smaller, ignore right half
            else
                r = m - 1;
        }
 
        // if we reach here, then element was 
        // not present
        return -1;
    }


    int binarySearch2(int arr[], int target)
    {
        int left =0, right = arr.length-1;

        while(left<=right){

            int middle = (left+right)/2;

            if(arr[middle] == target){
                return middle;
            }

            if(arr[middle] < target) {
                left = middle+1;
            }

            if(arr[middle] > target) {
                right = middle-1;
            }

        }

        return -1;
    }
 
    // Driver method to test above
    public static void main(String args[])
    {
        BinarySearch ob = new BinarySearch();
        int arr[] = {2, 3, 4, 10, 40, 60, 80};
        int n = arr.length;
        int x = 4;
        int result = ob.binarySearch(arr, x);
        int result2 = ob.binarySearch2(arr, x);
        int y = 1 + (4-3)/2;
        System.out.println("Element : "+y);
        if (result == -1)
            System.out.println("Element not present");
        else
            System.out.println("Element found at " + 
                                   "index " + result);
    }
}