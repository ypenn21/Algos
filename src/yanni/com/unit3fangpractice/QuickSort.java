package yanni.com.unit3fangpractice;

// Java program for implementation of QuickSort
class QuickSort 
{ 
	/* This function takes last element as pivot, 
	places the pivot element at its correct 
	position in sorted array, and places all 
	smaller (smaller than pivot) to left of 
	pivot and all greater elements to right 
	of pivot */
	int partition(int arr[], int low, int high) 
	{ 
		int pivot = arr[high]; 
		int i = low; // index of smaller element
		for (int j=low; j<high; j++) 
		{ 
			// If current element is smaller than or 
			// equal to pivot 
			if (arr[j] <= pivot) 
			{

				// swap arr[i] and arr[j] 
				int temp = arr[i]; 
				arr[i] = arr[j]; 
				arr[j] = temp;
				i++;
			} 
		} 

        //		{4, 7, 8, 9, 1, 5};
		// i is the biggest number or equal to the left of it (not the biggest of the whole array until it finishes looping) after moving all the number that is less the pivot to the left
  		int temp = arr[i];
		arr[i] = arr[high];
		arr[high] = temp; 

		return i;
	} 


	/* The main function that implements QuickSort() 
	arr[] --> Array to be sorted, 
	low --> Starting index, 
	high --> Ending index */
	void sort(int arr[], int low, int high) 
	{ 
		if (low < high) 
		{ 
			/* pi is partitioning index, arr[pi] is 
			now at right place */
			int pi = partition(arr, low, high);
			System.out.println("pivot: "+pi +" value is: "+arr[pi]);

			// Recursively sort elements before 
			// partition and after partition 
			sort(arr, low, pi-1); 
			sort(arr, pi+1, high); 
		} 
	} 

	/* A utility function to print array of size n */
	static void printArray(int arr[]) 
	{ 
		int n = arr.length; 
		for (int i=0; i<n; ++i) 
			System.out.print(arr[i]+" "); 
		System.out.println(); 
	}






	public void sort2(int arr[], int low, int high) {

		if(low<high) {
			System.out.println("low:"+low+" high:"+high);
			int partition = partition2(arr, low, high);
			System.out.println(" partition: "+high);
			sort2(arr, partition+1, high);
			sort2(arr, low, partition-1);
		}
	}

	public int partition2(int arr[], int low, int high) {
		int pivot = high;
		int indexLow = low;

		for (int index=low;index<high;index++){

			if ( arr[pivot]>= arr[index]) {
				int tmp = arr[indexLow];
				arr[indexLow] = arr[index];
				arr[index] = tmp;
				indexLow++;
			}
		}


		int tmp = arr[indexLow];
		arr[indexLow] = arr[high];
		arr[high] = tmp;
		pivot = indexLow;

		return pivot;
	}

	// Driver program 
	public static void main(String args[]) 
	{ 
		int arr[] = {4, 7, 3, 5, 1, 3};
		int n = arr.length; 

		QuickSort ob = new QuickSort(); 
		ob.sort2(arr, 0, n-1);

		System.out.println("sorted array"); 
		printArray(arr); 
	} 
} 