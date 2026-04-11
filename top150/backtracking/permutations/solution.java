class Solution {
    List<List<Integer>> ans;
    public List<List<Integer>> permute(int[] nums) {
        ans = new ArrayList<>();
        func(new ArrayList<>(),nums.length,nums);
        return ans;
    }
    public void func(List<Integer> temp , int n , int[] nums){
        if(temp.size()==n){
            ans.add(new ArrayList<>(temp));
            return;
        }
        for(int i=0; i<n; i++){ 
            if(temp.contains(nums[i]))continue;
            temp.add(nums[i]);
            func(temp,n,nums);
            temp.remove(temp.size()-1);
        }
    }
}

// class Solution {
//     List<List<Integer>> ans;
//     public List<List<Integer>> permute(int[] nums) {
//         ans = new ArrayList<>();
//         func(new LinkedHashSet<>(),nums.length,nums);
//         return ans;
//     }
//     public void func(LinkedHashSet<Integer> temp , int n , int[] nums){
//         if(temp.size()==n){
//             ans.add(new ArrayList<>(temp));
//             return;
//         }
//         for(int i=0; i<n; i++){ 
//             if(temp.contains(nums[i]))continue;
//             temp.add(nums[i]);
//             func(temp,n,nums);
//             temp.remove(nums[i]);
//         }
//     }
// }