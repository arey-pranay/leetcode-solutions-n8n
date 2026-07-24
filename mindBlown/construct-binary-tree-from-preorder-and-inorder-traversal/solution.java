class Solution {
    int[] pre;
    HashMap<Integer,Integer> hm = new HashMap<>();
    int done = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        pre = preorder;
        int n = inorder.length;
        for(int i=0;i<n;i++)hm.put(inorder[i],i);
        return func(0,n);
    }
    public TreeNode func(int start, int end){
        if(start > end || done == pre.length) return null;
        int curr = pre[done++];
        TreeNode root = new TreeNode(curr);
        
        int index = hm.get(curr);
        root.left = func(start,index-1);
        root.right = func(index+1,end);
        
        return root;
    }
   
    
}