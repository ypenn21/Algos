import org.junit.jupiter.api.Test;

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
       //overloaded function starts recursion in there
       combination(str, count, 0, output, 0);
    }

    private void combination(char input[],int count[],int startPos, char output[],int end) {
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

    private void print(char result[],int end){
        for(int i=0; i < end; i++){
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

       if(isSumResults ( r, 7 )){
           r.forEach(r1 -> System.out.print(r1 + " "));
           System.out.println();
        } else {
           r.forEach(r1 -> System.out.print(r1 + " "));
           System.out.println();
       }
        for (int i = pos; i < input.length; i++) {
            // only happens if there are duplicate letters, characters, or numbers in the input. this would skip that dup result
            if (i != pos && input[i] == input[i-1]) {
                continue;
            }
            r.add(input[i]);
            combinationEasy(input, pos+1, r);
            r.remove(r.size() - 1);
        }
    }


    public boolean isSumResults ( List<Character> candidates, int target ) {
        int sum = 0;
        for(int i=0;i<candidates.size();i++) {
            Integer tmp = Integer.parseInt(candidates.get(i)+"");
            sum+=tmp;
        }
        return (target==sum);
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
           //reset
           nums.set(i, nums.get(start));
           nums.set(start, iValu2);
       }

//       [1, 2, 3, 4][1, 2, 4, 3][1, 3, 2, 4][1, 3, 4, 2][1, 4, 3, 2]
//[1, 4, 2, 3][2, 1, 3, 4][2, 1, 4, 3][2, 3, 1, 4][2, 3, 4, 1]
//[2, 4, 3, 1][2, 4, 1, 3][3, 2, 1, 4][3, 2, 4, 1][3, 1, 2, 4]
//[3, 1, 4, 2][3, 4, 1, 2][3, 4, 2, 1][4, 2, 3, 1][4, 2, 1, 3]
//[4, 3, 2, 1][4, 3, 1, 2][4, 1, 3, 2][4, 1, 2, 3]


        //   with these two takes taken out:
        //   nums.set(i, nums.get(start));
        //   nums.set(start, iValu2);
//        [1, 2, 3, 4][1, 2, 4, 3][1, 4, 2, 3][1, 4, 3, 2][1, 2, 3, 4]
//[1, 2, 4, 3][2, 1, 4, 3][2, 1, 3, 4][2, 3, 1, 4][2, 3, 4, 1]
//[2, 1, 4, 3][2, 1, 3, 4][3, 1, 2, 4][3, 1, 4, 2][3, 4, 1, 2]
//[3, 4, 2, 1][3, 1, 2, 4][3, 1, 4, 2][2, 1, 4, 3][2, 1, 3, 4]
//[2, 3, 1, 4][2, 3, 4, 1][2, 1, 4, 3][2, 1, 3, 4]
    }

    public static void findCombinationsSameSizeInput(int[] input) {

        findCombsSameSizeInput(input, 0);
    }

    public static void findCombsSameSizeInput(int[] input, int start) {
        if ( start==input.length-1 ) {
            for(Integer num : input) {
                System.out.print(num+" ");
            }
            System.out.println("");
        }

        // start is constant only changes by adding 1 in the recursion
       for(int i=start; i < input.length;i++) {
            int tmp = input[i];
            //i is dynamic and incrementing by 1 in the for loop
           input[i] = input[start];
           input[start] = tmp;
           findCombsSameSizeInput(input, start+1);
           int tmp2 = input[i];
           input[i] = input[start];
           input[start] = tmp2;
       }

    }


    @Test
    public void test() {
        int[]numArray = {1,2,3,4};
        findCombinationsSameSizeInput(numArray);
        for(int i=0;i<numArray.length;i++)
            System.out.print(numArray[i]+" ");
    }

    @Test
    public void testCombinationEasy() {
        Combination c = new Combination();
        c.combinationEasy("3241".toCharArray());
    }


    public static void main(String args[]){
            int arr[] = {1, 2, 3, 4, 5, 6};
            int r = 3;
            int n = arr.length;
            Combination2.printCombination(arr, n, r);

        Combination c = new Combination();
//        c.combination("abcd".toCharArray());
        System.out.println("solution 2");
        c.combinationEasy("2367".toCharArray());
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