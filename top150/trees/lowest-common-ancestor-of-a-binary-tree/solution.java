/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base cases
        if(root == null) return null;
        if(root.val == p.val || root.val == q.val) return root; 
        
        TreeNode foundL =lowestCommonAncestor(root.left,p,q); // p ya q left me mila ya nahi
        TreeNode foundR =lowestCommonAncestor(root.right,p,q); // p ya q right me mila ya nahi
        if(foundL!=null && foundR!=null) return root; // dono taraf 1-1 mile, mtlb ye LCA hai
        
        // ab agr koi ek null hai, mtlb dono nodes same side pe the
        if(foundL != null) return foundL; // agr left me node mila, to jo LCA tha wo hi return hua
        return foundR; // since p and q always exist, then surely right me se LCA return hua tha 

    }
}

