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
    int ans =Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        func(root);
        return ans;
    }
    public int func(TreeNode root){
        if(root==null) return 0;
        int lsum = Math.max(0,func(root.left));
        int rsum = Math.max(0,func(root.right));
        ans = Math.max(ans,lsum+rsum+root.val);
        return root.val+Math.max(lsum,rsum); 
        //kyuki mera parent, mere through -> ye est path le skta hai. (me + leftSubtree) or (me+rightSubTree)
    }
}