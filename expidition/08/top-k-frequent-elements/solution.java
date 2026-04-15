class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Arrays.sort(nums);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->b[1]-a[1]);
        
        int num = nums[0];
        int freq = 1;
        
        for(int i=1;i<nums.length;i++){
            if(nums[i]==num) freq++;
            else{
                pq.add(new int[]{num,freq});
                num = nums[i];
                freq = 1;
            }
        }
        pq.add(new int[]{num,freq});
        
        int[] ans = new int[k];
        for(int i=0;i<k;i++) ans[i] = pq.remove()[0];
        return ans;
    }
}