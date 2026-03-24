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
    int index = 0;
    int[] pre;
    int[] in;
    HashMap<Integer,Integer> hm = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        pre = preorder;
        in = inorder;
        int n = preorder.length;
        for(int i=0;i<n;i++) hm.put(in[i],i);
        return func(0,n-1);   
    }
    
    // preorder is used to get roots one by one
    // inorder is used to determine which indices are for left subtree and which are for right

    public TreeNode func(int start, int end){
        if(start > end) return null;
        TreeNode root = new TreeNode(pre[index++]);
        int index = hm.get(root.val);
        root.left = func(start,index-1);
        root.right = func(index+1, end);
        return root;  
    }
    
 
}
