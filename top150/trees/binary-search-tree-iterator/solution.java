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
class BSTIterator {
    Queue<Integer> inorder = new LinkedList<>();
    public BSTIterator(TreeNode root) {
        func(root);
    }
    
    public int next() {
        return inorder.poll();
    }
    
    public boolean hasNext() {
        return inorder.size() != 0;
    }
    
    public void func(TreeNode root){
        if(root==null) return;
        func(root.left);
        inorder.add(root.val);
        func(root.right);
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */