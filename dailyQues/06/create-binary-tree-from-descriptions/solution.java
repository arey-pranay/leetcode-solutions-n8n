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
    TreeNode[] tarr = new TreeNode[100001];
    public TreeNode createBinaryTree(int[][] descriptions) {
        for(int[] temp : descriptions) tarr[temp[1]] = new TreeNode(temp[1]); 
        TreeNode root = new TreeNode(-1);
        for(int[] temp : descriptions){
            if(tarr[temp[0]] == null){
                tarr[temp[0]] =  new TreeNode(temp[0]);
                root = tarr[temp[0]];
            }
            TreeNode par = tarr[temp[0]];
            if(temp[2] == 1) par.left = tarr[temp[1]];
            else par.right = tarr[temp[1]];
        }    
        return root;
    }
}
// 20 = node(20) 
// 15 = node(15)
// 17 = node(17)
// 50 = null
// 80 = node(80) 
// 19 = node(19)