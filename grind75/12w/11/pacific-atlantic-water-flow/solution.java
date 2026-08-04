class Solution {
    int[] neighs = new int[] { -1, 0, 1, 0, -1 };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<List<Integer>> ans = new ArrayList<>();
        boolean[][] p = new boolean[m][n];
        boolean[][] a = new boolean[m][n];
        for (int j = 0; j < n; j++) {
            func(p, 0, j, heights);
            func(a, m - 1, j, heights);
        }
        for (int i = 0; i < m; i++) {
            func(p, i, 0, heights);
            func(a, i, n - 1, heights);
        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (a[i][j] && p[i][j])
                    ans.add(Arrays.asList(i, j));
        return ans;
    }

    public void func(boolean[][] vis, int x, int y, int[][] heights) {
        vis[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int X = x + neighs[i];
            int Y = y + neighs[i + 1];
            if (X < 0 || Y < 0 || X >= vis.length || Y >= vis[0].length || vis[X][Y] || heights[X][Y] < heights[x][y])
                continue;
            func(vis, X, Y, heights);
        }
    }
}