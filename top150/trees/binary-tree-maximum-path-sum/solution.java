
// public class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;
//     TreeNode() {}
//     TreeNode(int val) { this.val = val; }
//     TreeNode(int val, TreeNode left, TreeNode right) {
//         this.val = val;
//         this.left = left;
//         this.right = right;
//     }
// }

class Solution {
    int ans = Integer.MIN_VALUE;
    // HashMap<TreeNode,ArrayList<TreeNode>> hm = new HashMap<>();
    public int maxPathSum(TreeNode root) {
        func(root);
        return ans;
    }
    
    
    public int func(TreeNode root){
       
        if(root == null) return 0;
        
        // agr child ne apna best diya lekin fir bhi -ve aagya, to usko consider mt kro
        int leftKaMax = Math.max(0,func(root.left)); 
        int rightKaMax = Math.max(0,func(root.right));     
            
        // current node pe rehte hue best answer jo aa skta hai -> pathViaRoot (because leftKaMax and rightKaMax aer non-negative always)
        int pathViaRoot = leftKaMax + rightKaMax + root.val;
        ans = Math.max(ans, pathViaRoot);
        
        // since /\ shape wale children ko node ko kbhi parent se nhi jod skte hai
        // therefore child returns to parent that -> agr aap mujhe loge, to mai apane left aur right walo me se max chain leke aapko itna de skta hu
        return root.val + Math.max(leftKaMax,rightKaMax);    
        
    }
    
    
    
    
    
    
    
    
    
    // public int getBestPath(TreeNode t, TreeNode par){
    //     int b1 = 0;
    //     int b2 = 0;
    //     for(TreeNode neigh : hm.get(t)){
    //         if(neigh==par) continue;
    //         int currAns = getBestPath(neigh,t);
    //         if(currAns > b1){
    //             b2 = b1;
    //             b1 = currAns;
    //         } else if(currAns >b2) b2 = currAns;
    //     }
    //     ans = Math.max(ans, t.val + b1 + b2);
    //     return t.val + b1;
    // }
    
    
    // public void treeToGraph(TreeNode root){
    //     if(root==null) return;
    //     ArrayList<TreeNode> arr = hm.getOrDefault(root, new ArrayList<>());
    //     ArrayList<TreeNode> arr2 = new ArrayList<>();
    //     if(root.left!=null){
    //         arr.add(root.left);
    //         arr2 = hm.getOrDefault(root.left, new ArrayList<>());
    //         arr2.add(root);
    //         hm.put(root.left,arr2);
    //     }
    //     if(root.right!=null){
    //         arr.add(root.right);
    //         arr2 = hm.getOrDefault(root.right, new ArrayList<>());
    //         arr2.add(root);
    //         hm.put(root.right,arr2);
    //     }
    //     hm.put(root,arr);
    //     treeToGraph(root.left);
    //     treeToGraph(root.right);
    // }
}