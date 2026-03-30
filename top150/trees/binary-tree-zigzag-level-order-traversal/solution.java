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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>>  ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root==null) return ans;
        q.add(root);
        boolean flag = false;
        while(!q.isEmpty()){
            List<Integer> arr = new ArrayList<>();
            int sz = q.size();
            for(int i =0; i<sz;i++){
                TreeNode curr = q.poll();
                arr.add(curr.val);
                if(curr.left != null)q.add(curr.left);
                if(curr.right != null)q.add(curr.right);
            }
            if(flag) Collections.reverse(arr);
            ans.add(arr);
            flag = !flag;
        }
        return ans;
    }
}