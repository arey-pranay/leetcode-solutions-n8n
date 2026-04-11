class Solution {
    List<List<Integer>> ans;
    public List<List<Integer>> combine(int n, int k) {
        ans = new ArrayList<>();
        func(new ArrayList<>(),n,k,0);
        return ans;
    }
    public void func(List<Integer> temp , int n , int k  , int curr){
        if(temp.size()==k){
            ans.add(new ArrayList<>(temp));
            return;
        }
        for(int i=curr+1; i<=n; i++){        
            temp.add(i);
            func(temp,n,k,i);
            temp.remove(temp.size()-1);
        }
    }
}