import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MaxSubarraySumCircular {
    public int maxSubarraySumCircular(int[] array) {
        int N = array.length;

        int ans = array[0], cur = array[0];
        for (int i = 1; i < N; ++i) {
            cur = array[i] + Math.max(cur, 0);
            ans = Math.max(ans, cur);
        }

        // ans is the answer for 1-interval subarrays.
        // Now, let's consider all 2-interval subarrays.
        // For each i, we want to know
        // the maximum of sum(A[j:]) with j >= i+2

        // rightsums[i] = A[i] + A[i+1] + ... + A[N-1]
        int[] rightsums = new int[N];
        rightsums[N-1] = array[N-1];
        for (int i = N-2; i >= 0; --i)
            rightsums[i] = rightsums[i+1] + array[i];

        // maxright[i] = max_{j >= i} rightsums[j]
        int[] maxright = new int[N];
        maxright[N-1] = array[N-1];
        for (int i = N-2; i >= 0; --i)
            maxright[i] = Math.max(maxright[i+1], rightsums[i]);

        int leftsum = 0;
        for (int i = 0; i < N-2; ++i) {
            leftsum += array[i];
            ans = Math.max(ans, leftsum + maxright[i+2]);
        }

        return ans;
    }

    public static int maxSubarraySumCircular2(int[] A) {
        int S = 0;  // S = sum(A)
        for (int x: A)
            S += x;

        int ans1 = kadane(A, 0, A.length-1, 1);
        int ans2 = S + kadane(A, 1, A.length-1, -1);
        int ans3 = S + kadane(A, 0, A.length-2, -1);
        return Math.max(ans1, Math.max(ans2, ans3));
    }

    public static int kadane(int[] A, int i, int j, int sign) {
        // The maximum non-empty subarray for array
        // [sign * A[i], sign * A[i+1], ..., sign * A[j]]
        int ans = Integer.MIN_VALUE;
        int cur = Integer.MIN_VALUE;
        for (int k = i; k <= j; ++k) {
            cur = sign * A[k] + Math.max(cur, 0);
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    public static int yanniKadane(int[] A) {
        // The maximum non-empty subarray for array
        // [sign * A[i], sign * A[i+1], ..., sign * A[j]]
        List<Integer> elements = new ArrayList<>();
        elements.add(A[0]);
        int max_so_far = A[0];
        int index = 0;
        for (Integer element : A) {
            if(index ==0) {
                index++;
                continue;
            }
            if( element > elements.stream().mapToInt(Integer::intValue).sum()+element){
                Integer[] temp = {element};
                elements = Arrays.asList(temp);
            } else {
                elements.add(element);
            }
            max_so_far = Math.max(max_so_far, elements.stream().mapToInt(Integer::intValue).sum());
            index++;
        }
        return max_so_far;
    }

    public static int yanniKadaneNoArray(int[] A) {
        // The maximum non-empty subarray for array
        // [sign * A[i], sign * A[i+1], ..., sign * A[j]]
        int max_so_far = A[0];
        int index = 0;
        int answer=0;
        for (Integer element : A) {
            if(index ==0) {
                index++;
                continue;
            }
            max_so_far = max_so_far + element;
            answer = Math.max(answer, max_so_far);
            index++;
        }
        return answer;
    }

    public static int yanniKadaneNotConnected(int[] A) {
        // The maximum non-empty subarray for array
        // [sign * A[i], sign * A[i+1], ..., sign * A[j]]
        int max_so_far = A[0];
        int index = 0;
        int answer= A[0];
        for (Integer element : A) {
            if(index ==0) {
                index++;
                continue;
            }
            max_so_far = Math.max(max_so_far, max_so_far + element);
            index++;
        }
        return max_so_far;
    }

    public static void main(String args[]) {

        int[] array = {8,-1,-1,-1, 8};
        System.out.println(maxSubarraySumCircular2(array));
        System.out.println(yanniKadaneNoArray(array));
        System.out.println(yanniKadane(array));
        System.out.println(yanniKadaneNotConnected(array));
    }
}