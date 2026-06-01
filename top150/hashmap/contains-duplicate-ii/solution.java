class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        int n = nums.length;
        for(int i =0; i<n; i++){
            if(hm.containsKey(nums[i])) if(i-hm.get(nums[i]) <= k) return true;
            hm.put(nums[i],i);
        }
        return false;
    }
}