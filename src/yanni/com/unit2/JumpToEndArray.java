package yanni.com.unit2;

import org.junit.jupiter.api.Test;

public class JumpToEndArray {


    public int findNumberOfHopsEndArray( int[] myArray ) {

        if(myArray[0]<1){
            return 0;
        }

        int [] result = new int[myArray.length];
        for (int i=0; i<result.length;i++) {
            result[i] = Integer.MAX_VALUE;
        }

        result[0]=0;


        for (int i=0; i< myArray.length;i++) {
            int moves = myArray[i]+1;
            for(int j=1;j<moves;j++){
                if(i+j<myArray.length) {
                    result[i + j] = Math.min(result[i + j], result[i] + 1);
                } else {
                    break;
                }
            }
        }



        return result[result.length-1];
    }

    @Test
    public void testFindNumberOfHopsEndArray() {

        int[] myArray = {1,1,2,3,4,5,7};
        int result = findNumberOfHopsEndArray( myArray );
        System.out.println(result);
    }


}
