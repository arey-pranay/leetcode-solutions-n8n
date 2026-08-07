class Solution {
    List<List<Integer>> outer = new ArrayList<>();
    int target;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
      target= targetSum;
      func(root,new ArrayList<>(),0);
      return outer;
    }
    public void func(TreeNode root, List<Integer>inner, int currSum){
      if(root==null) return;
      if(root.left==null && root.right==null){
        if(currSum+root.val==target){
            inner.add(root.val);
            outer.add(new ArrayList<>(inner));
            inner.remove(inner.size()-1);
        }
        return;
      }
      currSum+=root.val;
      inner.add(root.val);
      func(root.left,inner,currSum);
      func(root.right,inner,currSum);
      inner.remove(inner.size()-1);
      return;
      
    }
}