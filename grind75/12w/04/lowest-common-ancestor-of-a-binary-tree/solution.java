class Solution {    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(root == p || root == q) return root;
        TreeNode pfound = lowestCommonAncestor(root.left , p , q);
        TreeNode qfound = lowestCommonAncestor(root.right , p , q);
        if(pfound!=null && qfound!=null) return root;
        if(pfound == null) return qfound;
        return pfound;
    }
}