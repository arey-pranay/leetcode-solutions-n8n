// replace node to be deleted with inorder successor or inorder predecessor
// inorder pred means just chhota element curr se. so left subtree ka rightmost child
       
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null) return root;
     
        if(key > root.val) root.right = deleteNode(root.right,key); 
        else if(key < root.val) root.left = deleteNode(root.left,key);            
        else {
            if(root.right == null) return root.left;
            if(root.left == null) return root.right;
            
            TreeNode inPre = rightmostChildOf(root.left);
            root.val = inPre.val;
            root.left = deleteNode(root.left, inPre.val);
            
            // TreeNode inSuc = leftmostChildOf(root.right);
            // root.val = inSuc.val;
            // root.right = deleteNode(root.right, inSuc.val);
        }
        
        return root;
    }
    public TreeNode rightmostChildOf(TreeNode root){
        while(root!=null && root.right!=null) root = root.right;
        return root;
    }
    // public TreeNode leftmostChildOf(TreeNode root){
    //     while(root!=null && root.left!=null) root = root.left;
    //     return root;
    // }
} 
// yaa to right waale ka saara left left yaa right waale a saara right right