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
    // 1 = covered, -1 = notCovered, 0 = camera
    int cams = 0;
    public int minCameraCover(TreeNode root) {
        boolean rootCovered = func(root) != -1; 
        if(!rootCovered) cams++;// since we are coming bottom-up, there is no parent to root, so root coverage must be checked explicitly by us.
        return cams;
    }
    public int func(TreeNode root){
        if(root==null) return 1;
        int left = func(root.left);
        int right = func(root.right);
        if(left == -1 || right == -1){cams++; return 0;} // agr koi bhi child uncovered hai, we need a camera
        if(left == 0 || right == 0) return 1; // agr koi bhi child covered hai, we are covered
        return -1; // else we are not covered, parent will handle it
    }
    
}