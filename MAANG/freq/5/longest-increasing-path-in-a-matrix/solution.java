/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    HashMap<Integer, Integer> cache = new HashMap<>();
    int[] neighs = new int[] { -1, 0, 1, 0, -1 };
    int ans = 1;

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(matrix, i, j));
            }
        }
        return ans;
    }

    public int dfs(int[][] matrix, int i, int j) {
        int start = i * matrix[0].length + j;
        if (cache.containsKey(start))
        return cache.get(start);
        int total = 1;
        for (int k = 0; k < 4; k++) {
            int I = i + neighs[k];
            int J = j + neighs[k + 1];
            if (I < 0 || J < 0 || I == matrix.length || J == matrix[0].length || matrix[I][J] <= matrix[i][j])
                continue;
            total = Math.max(total, 1 + dfs(matrix, I, J));
        }
        cache.put(start, total);
        return total;
    }
}
