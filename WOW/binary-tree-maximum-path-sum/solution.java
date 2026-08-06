class Solution {
    int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        func(root);
        return ans;
    }

    public int func(TreeNode root){
        if(root==null) return 0;
        int leftMax = Math.max(0,func(root.left));
        int rightMax = Math.max(0,func(root.right));
        ans = Math.max(ans, root.val+rightMax+leftMax);
       // parent se kahege ki if you take me-> then the best I can do is:
        return root.val + Math.max(leftMax , rightMax); 
    }
  
}