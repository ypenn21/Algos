package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

public class FindNBiggestNumber {
    public int findNBiggestNumber(int[] numbers, int n) {
        int right = numbers.length-1;
        int left = 0;

        int result = numbers.length - n;

        while(left<=right) {
            int pivot = partition(numbers, left, right);
            if(pivot==result){
                return numbers[pivot];
            }
            if(pivot>result){
                right=pivot-1;
            }else {
                left=pivot+1;
            }
        }
        return -1;
    }

    public int partition(int[] numbers, int left, int right){

        int previous=left;

        for(int i=left; i<right;i++){

            if (numbers[i] < numbers[right]){
                int tmp=numbers[previous];
                numbers[previous] = numbers[i];
                numbers[i]=tmp;
                previous++;
            }
        }

        int tmp = numbers[previous];
        numbers[previous]= numbers[right];
        numbers[right] = tmp;
        return previous;
    }

    @Test
    public void testFindNBiggestNumber(){

        int [] numbers= new int[]{2,3,5,1,8,9,5};

        System.out.println(findNBiggestNumber(numbers, 2));

    }

}
