package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

public class SortThreeNumbersConstantSpace {

    public void sort3Numbers(int [] num3){
        int indexStart = 0;
        int indexEnd = num3.length-1;
        int i=0;

        while(i<=indexEnd) {
            int chk = num3[i];
            if(chk==1) {
                int tmp = num3[indexStart];
                num3[indexStart]=num3[i];
                num3[i]=tmp;
                indexStart++;
                i++;
            } else if (chk==2) {
                i++;
            } else if ( chk==3 ) {
                int tmp = num3[indexEnd];
                num3[indexEnd]=num3[i];
                num3[i]=tmp;
                indexEnd--;
            }
        }

    }

    @Test
    public void sortArray(){
        int [] myArray = new int[] {3,3,2,1,3,3};
        sort3Numbers(myArray);
        for(int i=0;i<myArray.length;i++) {
            System.out.println(myArray[i]);
        }
    }

    @Test
    public void sortArray2(){
        int [] myArray = new int[] {3,1,1,1,2,2};
        sort3Numbers(myArray);
        for(int i=0;i<myArray.length;i++) {
            System.out.println(myArray[i]);
        }
    }

    @Test
    public void sortArray3(){
        int [] myArray = new int[] {3,1,1,1,2,1};
        sort3Numbers(myArray);
        for(int i=0;i<myArray.length;i++) {
            System.out.println(myArray[i]);
        }
    }

    @Test
    public void sortArray4(){
        int [] myArray = new int[] {1};
        sort3Numbers(myArray);
        for(int i=0;i<myArray.length;i++) {
            System.out.println(myArray[i]);
        }
    }


}
