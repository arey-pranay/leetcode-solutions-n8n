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
  
    TreeNode prev = null;
    public void flatten(TreeNode root) {
        if(root==null) return;
        flatten(root.right);
        flatten(root.left);
        root.left = null;
        root.right = prev;
        prev = root;
    }

      // ArrayList<TreeNode> arr = new ArrayList<>();
      // root.left = null;
        // TreeNode tail = root;        
        // // root.right = @12893712
        // // root.left = @1897393
        
        // for(int i = 1; i<arr.size(); i++){
        //     TreeNode curr =arr.get(i);
        //     curr.right = null;
        //     curr.left = null;
        //     tail.right = curr;
        //     tail.left = null;
        //     if(tail==root){
        //         tail = tail.right;
        //         root.right = tail;
        //     } else tail = tail.right;
        // }
}