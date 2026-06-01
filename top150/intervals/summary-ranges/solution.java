class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        int n = nums.length;
        int i=0;
        while(i<n){
            int start = nums[i]; int j = i+1;
            while(j<n && nums[j]-nums[j-1] == 1) j++;
            if(j<n+1 && start != nums[j-1])ans.add(new String(""+start+ "->" + nums[j-1]));
            else ans.add(new String(""+start));
            i=j;
        }
        return ans;
    }
}