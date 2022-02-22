package yanni.com.facebook;

import org.junit.jupiter.api.Test;

public class Facebook {
/*
Write a function that returns whether the given string, ignoring punctuation and capitalization, is a palindrome.

An example of palindrome is:
     "Race car!"
     "race"
     "big string"
     "face.?!"
*/

        public boolean isPalindrome(String input) {
            String word = replaceSpacePunctuation(input);
            // racecar
            char[] wordChar = word.toCharArray();

            int start = 0;
            boolean isPal = true;

            for (int i= wordChar.length-1;i>wordChar.length/2; i--) {
                if( wordChar[i]!=wordChar[start] ) {
                    isPal = false;
                    break;
                }
                start++;
            }

            return isPal;
        }

        public String replaceSpacePunctuation(String input) {
            input = input.toLowerCase();
            input = input.replaceAll(" ", "");
            // replace punctuation regex
            input = input.replaceAll("" , "");
            return input;
        }
/*
Given a sorted array of integers, find the range (starting and ending position) of a given target.

Example:
Input: nums [5, 7 , 7, 7, 7, 7, 9, 20], target 7
Output: [1, 5]
*/

        public int[] findTarget(int[] nums, int target) {

            int index = binarySearch2( nums, target);
            int [] result = new int[2];
            if( index == -1) {
                result[0]=-1;
                result[1]=-1;
            } else {

            }

            return result;


        }

        @Test
        public void testNums() {
            int[] nums = {1,2,3,5,8,9,13, 13, 13,14};

            int i = binarySearch2( nums, 13);

            int l = binarySearchLeft( nums, 13);
            int r = binarySearchRight( nums, 13);

            assert(l == 6);
            assert(r == nums.length-2);
        }


    public int binarySearchRight(int[] nums, int target) {
        int m = 0;
        int l = 0;
        int r = nums.length-1;

        while(l<=r) {
            m = (l+r+1) /2 ;

            if (l==r) {
                return l;
            }

            if(nums[m] > target) {
                r = m - 1;
            } else {
                l = m;
            }
        }

        return -1;
    }

    public int binarySearchLeft(int[] nums, int target) {
        int m = 0;
        int l = 0;
        int r = nums.length;

        while(l<=r) {
            m = (l+r) /2 ;

            if (l==r) {
                return l;
            }

            if(nums[m] < target) {
                l = m+1;
            } else {
                r = m;
            }
        }

        return -1;
    }

    public int binarySearch2(int[] nums, int target) {
            int m = 0;
            int l = 0;
            int r = nums.length;

            while(l<r) {
                m = (l+r) /2 ;

                if (nums[m] == target) {
                    return m;
                }

                if(nums[m] < target) {
                    l = m +1;
                }
                else {
                    r = m -1;
                }

            }

            return -1;
    }





        public int binarySearch(int[] nums, int target) {

            int right = nums.length;
            int left = 0;
            int found = -10000000;

            while(left<right) {
                int m = (right+left) / 2;

                if(nums[m] == target) {
                    found = m;
                    break;
                }

                if(nums[m] > target) {
                    right = m-1;
                } else if (nums[m] <target) {
                    left = m+1;
                }

            }

            return found;
        }
}
