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
    int ans=0;
    public int averageOfSubtree(TreeNode root) {
        func(root);
        return ans;
    }
    public int[] func(TreeNode root){
        if(root==null) return new int[]{0,0};
        int[] l = func(root.left);
        int[] r = func(root.right);
        int sum = l[0]+r[0]+root.val;
        int num = l[1]+r[1]+1;
        int avg = sum/num;
        if(root.val==avg)ans++;
        return new int[]{sum,num};
    }
  
}