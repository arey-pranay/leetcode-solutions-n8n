class Solution {
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new ArrayList<>();
        Arrays.sort(candidates);
        func(candidates,target,0,new ArrayList<>());
        return ans;
    }
    public void func(int[] arr, int needed, int index, List<Integer> temp){
        if(needed < 0) return;
        if(needed == 0){
            ans.add(new ArrayList<>(temp));
            return;
        }
        for(int i=index;i<arr.length;i++){
            temp.add(arr[i]);
            func(arr,needed-arr[i],i,temp);
            temp.remove(temp.size()-1);       
        }
        return;
    }
}