class Solution {
    public int subarraySum(int[] nums, int k) {
        int ans=0;
        HashMap<Integer,Integer> hm = new HashMap<>();
        hm.put(0,1);
        int sum = 0;  
        for(int num : nums){
            sum+=num;
            ans += hm.getOrDefault(sum-k,0); // hum sum-k chhod skte hai
            hm.put(sum,hm.getOrDefault(sum,0)+1);
        }
        return ans;
    }
}
