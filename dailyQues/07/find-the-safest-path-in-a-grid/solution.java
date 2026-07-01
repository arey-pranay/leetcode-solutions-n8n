class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();

        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1)
            return 0;

        Queue<int[]> thief = new LinkedList<>();
        int[][] mat = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    thief.offer(new int[] { i, j });
                    mat[i][j] = 0;
                } else {
                    mat[i][j] = -1;
                }
            }
        }

        int[] neighs = {-1, 0, 1, 0, -1};

        while (!thief.isEmpty()) {
            int[] cur = thief.poll();

            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int X = x + neighs[i];
                int Y = y + neighs[i + 1];

                if (X < 0 || Y < 0 || X >= n || Y >= n || mat[X][Y] != -1)
                    continue;

                mat[X][Y] = mat[x][y] + 1;
                thief.offer(new int[] { X, Y });
            }
        }

        // Maximum Bottleneck Path (Priority Queue)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        boolean[][] vis = new boolean[n][n];

        pq.offer(new int[] { mat[0][0], 0, 0 });

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int safe = cur[0];
            int x = cur[1];
            int y = cur[2];

            if (vis[x][y])
                continue;

            vis[x][y] = true;

            if (x == n - 1 && y == n - 1)
                return safe;

            for (int i = 0; i < 4; i++) {
                int X = x + neighs[i];
                int Y = y + neighs[i + 1];

                if (X < 0 || Y < 0 || X >= n || Y >= n || vis[X][Y])
                    continue;

                pq.offer(new int[] {
                    Math.min(safe, mat[X][Y]),
                    X,
                    Y
                });
            }
        }

        return 0;
    }
}