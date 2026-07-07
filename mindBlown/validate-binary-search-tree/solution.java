class Solution {
    Integer prev = null;
    public boolean isValidBST(TreeNode root) {
        if(root==null) return true;
        boolean temp = isValidBST(root.left);
        if(prev != null && prev >= root.val) return false;
        prev=root.val;
        temp &= isValidBST(root.right);
        return temp;
    }
}