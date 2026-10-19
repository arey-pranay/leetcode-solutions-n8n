class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int less = 0, equal = 0;
        int n = nums.length;
        for(int i = 0;i<n;i++){
            if(nums[i]==target) equal++;
            if(nums[i] < target) less++;
        }
        ArrayList<Integer> ans = new ArrayList<>();        
        for(int i=less;i<less+equal;i++) ans.add(i);
        return ans;  
    }
}