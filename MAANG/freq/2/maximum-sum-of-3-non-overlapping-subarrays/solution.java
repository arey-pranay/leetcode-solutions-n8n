class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        
        int sum =0;
        int n = nums.length;
        for(int i=0;i<k;i++) sum += nums[i];
        int[] sums = new int[n-k+1];
        sums[0] = sum;
        int index = 1;
        for(int i=k;i<n;i++){
            sums[index] = sums[index-1]+nums[i]-nums[i-k];
            index++;
        } // har index se start krke k length ka group bnane ka sum
        int m = sums.length;
        int[] bestLeft = new int[m]; // ki current index se left me dekhne pe highest sum ka index kaunsa hai
        int[] bestRight = new int[m]; // ki current index se right me dekhne pe highest sum ka index kaunsa hai
                
        bestLeft[0] = 0;
        for(int i=1; i<m;i++){
            if(sums[i] > sums[bestLeft[i-1]]) bestLeft[i] = i;
            else bestLeft[i] = bestLeft[i-1];
        }
        bestRight[m-1] = m-1;
        for(int i=m-2; i>=0;i--){
            if(sums[i] >= sums[bestRight[i+1]]) bestRight[i] = i;
            else bestRight[i] = bestRight[i+1];
        }
        int[] ans = new int[3];
        int maxSum = 0;
        for(int mid = k; mid<m-k; mid++){ // 3 index choose krne hai, jo middle index hai wo k se m-k ke beech hoga, let's try all values of it
        
            // mid se mid-k aur mid+k ke index unusable hai, kyuki non-overlapping hona zaruri hai.
            int l = bestLeft[mid-k]; 
            int r = bestRight[mid+k];
            int currSum = sums[mid] + sums[l] + sums[r];
            if(currSum > maxSum){
                ans[0] = l;ans[1] = mid;ans[2]=r; maxSum = currSum;
            }
        }
        return ans;
    }
    // func(sums,0, new ArrayList<>(), 0);
    // public void func(int[] nums, int index, List<Integer> list, int sum){
    //     if(list.size()==3){
    //         if(sum > maxSum){
    //             maxSum = sum;
    //             for(int i=0;i<list.size();i++) ans[i] = list.get(i);
    //         }
    //         return;
    //     }
        
    //     if(index >= nums.length) return;
        
    //     list.add(index);
    //     sum+=nums[index];
    //     func(nums, index+minGap, list, sum);//take 
    //     sum-=nums[index];
    //     list.remove(list.size()-1);
    //     func(nums, index+1, list, sum);//notTake
        
    // }
}
// 1005 1 1004 1 1003 1 999 999 999 999 999 999