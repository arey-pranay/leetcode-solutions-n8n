class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> outer = new ArrayList<>();
        if(root==null) return outer;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            List<Integer> inner = new ArrayList<>();
            int sz = q.size();
            for(int i=0;i<sz;i++){
                TreeNode curr = q.poll();
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
                inner.add(curr.val);
            }
            outer.add(inner);
        }
        return outer;
    }
}