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
    HashMap<Integer,Integer> hm = new HashMap<>();
    int index;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        for(int i =0;i<n;i++) hm.put(inorder[i],i);
        index = n-1;
        return func(0,n-1,inorder,postorder);
    }
    public TreeNode func(int start, int end, int[] inorder, int[] postorder){
        if(start > end) return null;
        TreeNode root = new TreeNode(postorder[index--]);
        int ind = hm.get(root.val);
        
        root.right = func(ind+1,end, inorder, postorder);
        root.left = func(start,ind-1, inorder, postorder);
        
        return root;
    }
}