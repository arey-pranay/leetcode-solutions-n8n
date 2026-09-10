class Solution {
    // [2,3,1]
    TreeNode prev,a,b;
    public void recoverTree(TreeNode root) {
        if(root==null) return;
        inorder(root);
        swap(a,b);
    }
    public void inorder(TreeNode root){
        if(root==null) return;
        inorder(root.left);
        if(prev != null && root.val<prev.val){
            if(a==null) a=prev;//a=3
            b=root;//b=2
        }
        prev=root;
        inorder(root.right);
    }
    public void swap(TreeNode a, TreeNode b){
        int x = a.val;
        a.val = b.val;
        b.val = x;
    }
}