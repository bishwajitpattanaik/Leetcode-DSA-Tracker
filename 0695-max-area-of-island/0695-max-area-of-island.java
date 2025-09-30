class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0; //Edge Case
        int maxArea = 0; //initialise maxArea
        //Two for loops to iterate the process over each elements of m*n
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){

                //check if the grid element is 1 or not
                if(grid[i][j] == 1){
                    int area = dfs(grid,i,j);  //find curr area by calling dfs method resursively
                    maxArea = Math.max(maxArea, area); //update maxArea
                }

            }
        }
        return maxArea; //return max area
    }

    private int dfs(int[][] grid, int i, int j) {

        //check if the given grid is going out of bounds or it contains 0
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0; // Marking the cell as visited by updating curr grid to 0 after process or you can have a m*n matrix as new instance to store visited grids
        int area = 1; // Current cell
        // Explore 4-directionally and adding each 1 to total area using dfs operation recursively
        area += dfs(grid, i + 1, j);
        area += dfs(grid, i - 1, j);
        area += dfs(grid, i, j + 1);
        area += dfs(grid, i, j - 1);
        return area;
    }
}