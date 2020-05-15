import java.util.*;

public class Combination {

   public void combination(char input[]){
       Map<Character, Integer> countMap = new TreeMap<>();
       for (char ch : input) {
           countMap.compute(ch, (key, val) -> {
               if (val == null) {
                   return 1;
               } else {
                   return val + 1;
               }
           });
       }
       char str[] = new char[countMap.size()];
       int count[] = new int[countMap.size()];
       int index = 0;
       for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
           str[index] = entry.getKey();
           count[index] = entry.getValue();
           index++;
       }
       char[] output = new char[input.length];
       combination(str, count, 0, output, 0);
    }

    private void combination(char input[],int count[],int startPos, char output[],int end){
        print(output, end);
        for(int i=startPos; i < input.length; i++){
            if (count[i] == 0) {
                continue;
            }
            output[end] = input[i];
            count[i]--;
            combination(input, count, i, output, end + 1);
            count[i]++;
        }
    }

    private void print(char result[],int pos){
        for(int i=0; i < pos; i++){
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }

    public void combinationEasy(char[] input) {
        List<Character> r = new ArrayList<>();
        Arrays.sort(input);
        combinationEasy(input, 0, r);
    }

    private void combinationEasy(char[] input, int pos, List<Character> r) {

        r.forEach(r1 -> System.out.print(r1 + " "));
        System.out.println();
        for (int i = pos; i < input.length; i++) {
            if (i != pos && input[i] == input[i-1]) {
                continue;
            }
            r.add(input[i]);
            combinationEasy(input, i + 1, r);
            r.remove(r.size() - 1);
        }
    }

    public List<List<Integer>> permute(List nums) {
       List<List<Integer>> results = new ArrayList<>();
        permuteHelper(0, results, nums);
       return results;
    }

    private void permuteHelper(int start, List<List<Integer>> results, List<Integer> nums) {
       if ( start==nums.size()-1 ) {
           results.add(new ArrayList<>(nums));
       }

       for(int i =start; i<nums.size(); i++) {
           int iValu = nums.get(i);
           nums.set(i, nums.get(start));
           nums.set(start, iValu);
           permuteHelper(start+1, results, nums);
           int iValu2 = nums.get(i);
           nums.set(i, nums.get(start));
           nums.set(start, iValu2);
       }
    }


    public static void main(String args[]){
            int arr[] = {1, 2, 3, 4, 5, 6};
            int r = 3;
            int n = arr.length;
            Combination2.printCombination(arr, n, r);

        Combination c = new Combination();
        c.combination("abcd".toCharArray());
        System.out.println("solution 2");
        c.combinationEasy("abcd".toCharArray());
        System.out.println("solution 3 with numbers");
        Integer [] numArray = {1,2,3,4};
        List<List<Integer>> results = c.permute(Arrays.asList(numArray));
        int index =0;
        for ( List<Integer> result : results) {
            System.out.print(result);
            if(index++ == 4) {
                index=0;
                System.out.println();
            }
        }

    }
    
}

class Combination2 {

    /* arr[]  ---> Input Array
    data[] ---> Temporary array to store current combination
    start & end ---> Staring and Ending indexes in arr[]
    index  ---> Current index in data[]
    r ---> Size of a combination to be printed */
    static void combinationUtil(int arr[], int n, int r, int index,
                                int data[], int i)
    {
        // Current combination is ready to be printed, print it
        if (index == r)
        {
            for (int j=0; j<r; j++)
                System.out.print(data[j]+" ");
            System.out.println("");
            return;
        }

        // When no more elements are there to put in data[]
        if (i >= n)
            return;

        // current is included, put next at next location
        data[index] = arr[i];
        combinationUtil(arr, n, r, index+1, data, i+1);

        // current is excluded, replace it with next (Note that
        // i+1 is passed, but index is not changed)
        combinationUtil(arr, n, r, index, data, i+1);
    }

    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    static void printCombination(int arr[], int n, int r)
    {
        // A temporary array to store all combination one by one
        int data[]=new int[r];

        // Print all combination using temprary array 'data[]'
        combinationUtil(arr, n, r, 0, data, 0);
    }
}