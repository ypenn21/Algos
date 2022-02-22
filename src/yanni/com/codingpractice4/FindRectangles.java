package yanni.com.codingpractice4;

import org.junit.jupiter.api.Test;

public class FindRectangles {
    private int oneCounts;
    private final int SAW = -100;

    public int numSubmat(int[][] mat) {

        int length = 1;
        int wid = 1;
        boolean chkLength = false;
        int row = 0;
        int col = 0;
        int count=find(length, wid, mat, row, col, new int[mat.length][mat[0].length]);
        while(count!=0) {
            int[][] seen = new int[mat.length][mat[0].length];
            wid++;
//            for(col=0;col<mat[0].length;col++){
                int tmpCount=find(length, wid, mat, row, col, seen);
                if(tmpCount==0) {
//                    break;
                } else {
                    count+=tmpCount;
                }

//            }

            if(tmpCount==0) {
                length++;
                wid=1;
                seen = new int[mat.length][mat[0].length];
                int tmp=find(length, wid, mat, row, col, seen);
                if(tmp==0) {
                    break;
                } else {
                    count+=tmp;
                }
            }
        }

        return count;
    }

    public int find(int length, int width, int[][] mat, int row, int col, int[][] seen) {
        int count = 0;
        if (col >= mat[0].length || length > seen.length) {
            return count;
        }

        if (row >= mat.length) {
            return count;
        }

        if(mat[row][col]==1 && seen[row][col]!=SAW) {
            boolean isRect=true;
            for(int l=0;l<length;l++){
                seen[row][col] = SAW;
                if(row+l<mat.length) {
                    if (mat[row + l][col] != 1) {
                        isRect=false;
                        break;
                    }
                } else {
                    isRect=false;
                    break;
                }
                for(int w=0;w<width;w++){
                    if(col + w<mat[0].length) {
//                        seen[row+l][col + w] = SAW;
                        if (mat[row+l][col + w] != 1) {
                            isRect=false;
                            break;
                        }
                    } else {
                        isRect=false;
                        break;
                    }
                }
            }
            if(isRect)
                count++;

        }

        col++;
        count += find(length, width, mat, row, col, seen);
        row++;
        col=0;
        count += find(length, width, mat, row, col, seen);

        return count;
    }

    @Test
    public void testNumSubmat() {
        int[][] mat = {
                {1,0,1},
                {1,1,0},
                {1,1,0}
        };
        int count = numSubmat(mat);
        assert(count==13);
    }


    @Test
    public void testNumSubmat2() {
        int[][] mat = {
                {0,1,1,0},
                {0,1,1,1},
                {1,1,1,0}
        };
        int count = numSubmat(mat);
        assert(count==24);
    }

}



//Example 1:
//
//        Input: mat = [[1,0,1],
//        [1,1,0],
//        [1,1,0]]
//        Output: 13
//        Explanation:
//        There are 6 rectangles of side 1x1.
//        There are 2 rectangles of side 1x2.
//        There are 3 rectangles of side 2x1.
//        There is 1 rectangle of side 2x2.
//        There is 1 rectangle of side 3x1.
//        Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
//        Example 2:
//
//        Input: mat = [[0,1,1,0],
//        [0,1,1,1],
//        [1,1,1,0]]
//        Output: 24
//        Explanation:
//        There are 8 rectangles of side 1x1.
//        There are 5 rectangles of side 1x2.
//        There are 2 rectangles of side 1x3.
//        There are 4 rectangles of side 2x1.
//        There are 2 rectangles of side 2x2.
//        There are 2 rectangles of side 3x1.
//        There is 1 rectangle of side 3x2.
//        Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
