// Time Complexity: O(HWexponentailN * HW)
// Space Complexity: List + recursive stack space O(n)
import java.util.*;
public class OptimalBldg
{
    int min;
    int H, W;
    public int findMinDistance(int h, int w, int n)
    {  
        this.min = Integer.MAX_VALUE;
        this.H = h;
        this.W = w;

        // to record placement of the buildings
        int [][] bldg = new int[h][w];
        // 0 parking lot, 1 bldg
        
        // finding building positions start with r = 0 c = 0
        backtracking(bldg, 0, 0, n);

        return min;
    }

    private void backtracking(int[][]bldg, int r, int c, int n)
    {
        // base case
        if(n == 0)
        {
            bfs(bldg);
            return;
        }

        // logic
        if(c == W)
        {
            r++;
            c = 0;
        }
        for(int i = r; i < H; i++)
        {
            for(int j = c; j < W; j++)
            {
                // action 
                bldg[i][j] = 1;
                // recursion
                backtracking(bldg, i, j+1, n-1);
                // backtrack
                bldg[i][j] = 0;
            }
        }
    }

    // to calculate the distance placement for a building
    // O(H*W)
    private void bfs(int [][] bldg)
    {
        boolean visited[][] = new boolean[H][W]; // visited for all parking spaces
        Queue<int[]> q = new LinkedList<>();

        for(int i = 0 ; i < H; i ++)
        {
            for(int j = 0 ; j < W; j++)
            {
                if(bldg[i][j] == 1) // building present
                {
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int [][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        int dist = 0;
        // calculate the distance around 
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int i = 0 ; i < size; i ++)
            {
                int [] curr = q.poll();
                for(int dir[] : dirs)
                {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    if(nr >= 0 && nr < H && nc >= 0 && nc < W && !visited[nr][nc])
                    {
                        q.offer(new int []{nr,nc}); // parking lot present
                        visited[nr][nc] = true;
                    }
                }
            } // checked all neig for bldg placements
            dist++;
        }
        min = Math.min(min, dist-1); // dist-1 because we have increased dist  
    }

    public static void main(String args[])
    {
        OptimalBldg obj = new OptimalBldg();
        System.out.println(obj.findMinDistance(4,4,2)); // 3
        System.out.println(obj.findMinDistance(3,2,1)); // 2
        System.out.println(obj.findMinDistance(4,4,3)); // 2
    }
}
