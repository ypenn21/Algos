public class MyProgram {
    public static void main(String[] args) {
        //Create the char[][] grid:
        String map = "##....###!#...###..!#.#..#..s!####.#...!##...###.";
        String[] lines = map.split("!");
        char[][] grid = new char[lines.length][lines[0].length()];
        for (int j=0;j<grid.length;j++) for (int i=0;i<grid[j].length;i++) grid[j][i] = lines[j].charAt(i);
         
        //Run floodFill on the grid given a start position and an empty visited array
        floodFill(grid, new boolean[grid.length][grid[0].length], 2, 8); //row 2, 8 is where the 's' is
         
        //Print the resulting grid:
        for (int j=0;j<grid.length;j++) {
            for (int i=0;i<grid[j].length;i++) System.out.print(grid[j][i]);
            System.out.println();
        }
    }
     
    //Floodfill algorithm:
    static void floodFill(char[][] grid, boolean[][] visited, int r, int c) {
        //quit if off the grid:
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return;
         
        //quit if visited:
        if(visited[r][c]) return;
        visited[r][c] = true;
         
        //quit if hit wall:
        if(grid[r][c]=='#') return;
         
        //we want to visit places with periods in them:
        if(grid[r][c]=='.') grid[r][c] = '*';
         
        //recursively fill in all directions
        floodFill(grid,visited,r+1,c);
        floodFill(grid,visited,r-1,c);
        floodFill(grid,visited,r,c+1);
        floodFill(grid,visited,r,c-1);
    }
}