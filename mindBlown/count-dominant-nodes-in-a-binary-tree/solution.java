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
    int count = 0;
    public int countDominantNodes(TreeNode root) {
        maxi(root);
        return count;
    }
    
    // mere children ne aur maine milke max kya nikaala
    public int maxi(TreeNode root){
        if(root==null) return 0;
        int max = Math.max(root.val,Math.max(maxi(root.left),maxi(root.right)));
        if(root.val >= max) count++;
        return max;
    }
}