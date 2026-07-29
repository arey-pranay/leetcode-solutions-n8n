class Solution {
    int rem;
    int ans=-1;
    public int kthSmallest(TreeNode root, int k) {
        rem = k;
        inorder(root);
        return ans;
    }
    public void inorder(TreeNode root){
      
        if(ans!=-1 || root==null) return;
      
        inorder(root.left);
      
        rem--;
        if(rem==0) ans=root.val;

        inorder(root.right);
        
    }
}