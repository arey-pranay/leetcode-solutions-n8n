class Solution {
    int[] ans = new int[]{-1,-1};
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        if(n==0) return ans;
        bs(0,n-1,nums,target);
        return ans;
    }
    public void bs(int start, int end , int[]nums, int target){
        if(start > end) return;
        int mid = start + (end-start)/2;
        
        if(nums[mid]==target){
            
            if(ans[0]==-1 || ans[0] > mid){
                // System.out.println("Found smaller start");
                ans[0] = mid;
            }
            if(ans[1]==-1 || ans[1] < mid){
                // System.out.println("Found larger end");
                ans[1] = mid;
            }
            bs(start,mid-1,nums,target);
            bs(mid+1,end,nums,target);
            return;
        }
        if(nums[mid]<target) bs(mid+1,end,nums,target);
        else bs(start,mid-1,nums,target);
        return; 
    }
} 
