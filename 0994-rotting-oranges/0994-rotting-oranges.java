class Solution {
    public int orangesRotting(int[][] grid) {
        //check if the grid is null or not
        if (grid == null || grid.length == 0) return -1;
        
        //Creating m,n variables and counters
        int m = grid.length, n = grid[0].length;
        int freshCount = 0; //fresh oranges counter
        Queue<int[]> rottenQueue = new LinkedList<>(); //queue for counting rotten oranges

        //count fresh oranges and enqueue rotten oranges
        //using two for loops to iterate over all grids
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                //if value of grid is 1, then grid contains good/fresh orange
                if(grid[i][j] == 1){
                    freshCount++; //fresh orange counter gets updated
                }
                //if value is 2 then that grid contains rotten orange that needs to be added into rotten queue 
                else if(grid[i][j] == 2){
                    rottenQueue.offer(new int[]{i,j}); //adding bad oranges into queue
                }
            }
        }

        if(freshCount == 0) return 0; //No fresh oranges

        int minutes = 0;

        //create diections array to iterate over all the neighbours for any particular cell
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        //BFS
        while(!rottenQueue.isEmpty()){ //iterate till the given queue is not empty
            int size = rottenQueue.size(); //check size of given queue

            for(int i = 0; i < size; i++){             //for any ith position, if it is less than size of queue, pop/remove one element out of queue
                int[] rotten = rottenQueue.poll();

                //iterate over directions array
                for(int[] dir : directions){

                    //using direction var calculate x,y positions of all edges and cells
                    int x = rotten[0] + dir[0];
                    int y = rotten[1] + dir[1];
                    //check array is not out of bounds
                    if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1){
                        grid[x][y] = 2; //turn fresh oranges to rotten
                        freshCount--; //reduce fresh oranges counter 
                        rottenQueue.offer(new int[]{x,y}); //adding new rotten oranges to queue
                    } 
                
                }
            }
            minutes++; //updating minutes variable by incrementing timer with each level of BFS operation
        }
        return freshCount == 0 ? minutes -1 : -1; // return minutes if no. of fresh oranges is 0 and return -1 of fresh oranges are still there and adjust for extra increment

    }


}