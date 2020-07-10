package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

public class FindAndCountIslands2dArray {

    public Integer findAndCountIslands2dArray(int[][] locations, boolean[][] visted) {

        int count=0;

        for(int row=0;row<locations.length;row++){
            for(int col=0;col<locations[row].length;col++){
                if(!visted[row][col])
                    count += scanForIslands(row, col, locations, visted);
                visted[row][col]=true;
            }
        }
        return count;
    }

    public Integer scanForIslands(int row, int col, int[][] locations, boolean[][] visted) {
        int count=0;
        int[] scannerRole = {-1, 1,  0, 0, -1, 1, -1, 1};
        int[] scannerCol =  { 0, 0, -1, 1, -1, 1,  1, -1};

        if(locations[row][col] == 1) {
            // just use a try catch here to help me debug not something i would write in production
            try {
                for (int scan = 0; scan < scannerRole.length; scan++) {
                    if (isItSafe(row + scannerRole[scan], col + scannerCol[scan], locations)
                            && !visted[row + scannerRole[scan]][col + scannerCol[scan]]) {
                        visted[row + scannerRole[scan]][col + scannerCol[scan]] = true;
                        count += scanForIslands(row + scannerRole[scan], col + scannerCol[scan], locations, visted);
                    } else {
                        continue;
                    }
                }
                return 1;
            } catch(Exception e) {
                System.out.println();
                throw e;
            }
        }
        return 0;
    }

    public boolean isItSafe(int row, int col, int[][] locations) {
        boolean safe= false;
        if(row < locations.length && row >= 0 ) {
            if(col < locations[row].length && col >= 0 ) {
                safe=true;
            }
        }
        return safe;

    }

    @Test
    public void testFindAndCountIslands2dArray(){
        int[][] map = {
                        {0,0,1,0,1},
                        {0,1,1,0,0},
                        {0,0,0,0,1},
                        {1,0,1,1,1},
                        {1,0,1,1,1}
                      };
        boolean[][] visted = {
                {false,false,false,false,false},
                {false,false,false,false,false},
                {false,false,false,false,false},
                {false,false,false,false,false},
                {false,false,false,false,false}
        };
        int count = findAndCountIslands2dArray(map, visted);
        System.out.println(count);
        assert(count==4);
    }

}
