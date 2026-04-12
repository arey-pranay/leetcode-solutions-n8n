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
    long max = 0;
    long totalSum = 0;
    int MOD = 1000000007;
    public int maxProduct(TreeNode root) {
        // get sum of full tree
        // then to get sum of any 2 subtrees, you need sum of only 1, because s2 = total-s1
        // for every node, check ans = Math.max(ans,(s1 * (total-s1))
        totalSum = sumOfTree(root,0);
        pre(root);
        return (int)(max%MOD);
    }
    public long pre(TreeNode root){
        if(root==null) return 0;
        long a = pre(root.left);
        long b = pre(root.right);
        long currSum = a + b + root.val;
        max = Math.max(max, (currSum * (totalSum-currSum)));
        return currSum;
    }
    public int sumOfTree(TreeNode root, int sum){
        if(root==null) return sum;
        return sumOfTree(root.left,sum)+sumOfTree(root.right,sum)+root.val;
    }
    
}