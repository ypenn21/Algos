import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class RotateNumbers {

    public static void main(String[] args) {
        int total = 5;
        int rotations = 4;
        int a[] = {1,2,3,4,5};
        int reminder = -1;
        if(rotations>=total){
            reminder = rotations%total;
        }
        if( reminder == 0 ) {
            for (int a_i = 0; a_i < total; a_i++) {
                System.out.print(a[a_i] + " ");
            }
            return;
        }
        if( reminder > 0 ) {
            rotateNumbers(a, total, reminder);
        } else if ( rotations < total ) {
            rotateNumbers(a, total, rotations);
        }
    }
    
    public static void rotateNumbers(int array[],  int total, int indexOldArray){
            if(total == 0){
                return;
            }
            if (indexOldArray>array.length-1){
                indexOldArray = 0;
            }

            System.out.print(array[indexOldArray]+" ");

            rotateNumbers(array, total-1, indexOldArray+1);
    }
}