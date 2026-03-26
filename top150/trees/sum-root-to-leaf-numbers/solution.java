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
    int s = 0;
    int sum = 0 ;
    public int sumNumbers(TreeNode root) {
        if(root==null) return 0;
        s = s*10 + root.val;
        if(root.left==null && root.right==null){
            sum += s;
            s /= 10;
            return sum;
        }  
        sumNumbers(root.left);
        sumNumbers(root.right);
        s /= 10;
        return sum;
    }
}