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
    int count = 0 ;
    public int countNodes(TreeNode root) {
        int lh = leftHeight(root);
        int rh =  rightHeight(root);
        if(lh == rh) return (int)Math.pow(2,lh) - 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    public int leftHeight(TreeNode root){
        int h = 0;
        while(root!=null){
            root=root.left;
            h++;
        }
        return h;
    }
    public int rightHeight(TreeNode root){
        int h = 0;
        while(root!=null){
            root=root.right;
            h++;
        }
        return h;
    }
}