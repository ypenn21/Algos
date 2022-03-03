package yanni.com.codingpractice4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SmartSheet {

    // subarraySum
// int[] n

// n = [1, 3, 7]
// [ ]  +  [1]  + [3]  + [7] +
// [1, 3]  + [3, 7]  +
// [1, 3, 7] = 36

// X [1, 7]
// X [3, 1]
        static Integer sum = 0;

        public int findSubArray(int[] input, int pos, List<Integer> combos) {
            System.out.println(combos);
            sum+=combos.stream().reduce(0, (subtotal, element) -> subtotal + element);

            for (int i= pos; i< input.length; i++) {
                combos.add(input[i]);
                findSubArray( input, i+1, combos);
                combos.remove(combos.size()-1);
            }

            return sum;

        }

        public int findSubArraySum(int[] input) {

            findSubArray(input, 0, new ArrayList<Integer>());
            return sum;
        }

    // Computes sum all sub-array
    public static long subArraySum(int arr[], int n)
    {
        long result = 0,temp=0;

        // Pick starting point
        for (int i = 0; i < n; i ++)
        {
            // Pick ending point
            temp=0;
            for (int j = i; j < n; j ++)
            {
                // sum subarray between current
                // starting and ending points
                temp+=arr[j];
                result += temp ;
            }
        }
        return result ;
    }



//        public List<String> findKFrequentWords(List<String> input, int k) {
//            Map<String, Integer> wordsCount = new HashMap();
//            for(String s : input) {
//                wordsCount.put(s, wordsCount.getOrDefault(s, 0) + 1);
//            }
//
//            List<String> result = new ArrayList();
//            PriorityQueue<String> p = new PriorityQueue ( (a, b) ->
//                    wordsCount.get(b) == wordsCount.get(a) ?  a.toString().toCharArray()[0]-b.toString().toCharArray()[0]
//                            : wordsCount.get(b) - wordsCount.get(a));
//
//            for(String s : wordsCount.keySet()) {
//                p.add(s);
//            }
//
//            int count = 0;
//            int prevCount = 0;
//            int added = 0;
//
//            while ( added <= k && !p.isEmpty()) {
//                String w = p.poll();
//                count = wordsCount.get(w);
//
//                if(added < k) {
//                    result.add(w);
//                }
//
//
//                if(count !=  prevCount) {
//                    added++;
//                }
//                else if(count ==  prevCount){
//                    result.add(w);
//                }
//                prevCount = count;
//            }
//
//            return result;
//        }

    public List<String> findKFrequentWords(List<String> input, int k) {
        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>((a, b) ->
                a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue());

        Map<String, Integer> wordsCount = new HashMap<>();

        for (String s : input) {
            wordsCount.put(s, wordsCount.getOrDefault(s, 0) + 1);
        }

        heap.addAll(wordsCount.entrySet());

        List<String> result = new ArrayList();

        while (!heap.isEmpty() && k-- > 0 ) {
            String c = heap.poll().getKey();
            result.add(c);
        }

        return result;
    }

    @Test
    public void testFindKFrequentWords() {
        SmartSheet smart  = new SmartSheet();
        List<String> input = new ArrayList<>();
        input.add("a");
        input.add("b");
        input.add("b");
        input.add("c");
        input.add("d");

        List result = smart.findKFrequentWords(input, 2);
        System.out.println(result);
    }

        @Test
        public void testFindSubArray() {
            SmartSheet smart  = new SmartSheet();
            int[] input = {1,3,7};
            long result = smart.subArraySum(input,  input.length);
            System.out.println(result);
        }
    }
