class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        HashMap<Integer,Integer> countOf = new HashMap<>();
        int ans = 0;
        int i =0,j=0;
        int collected = 0;
        int n = nums.length;
        int unique=0;
        while(j < n){
            int x = nums[j];
            countOf.put(x,countOf.getOrDefault(x,0)+1);
            if(countOf.get(x) == 1) unique++; // agr hm m naya naya vo aaya hai value to vo add krdiya aur uniqie hoga 
            while(unique > k){ // phir humnein dekha ki unique jyada horhe to shift krdi window
                int y = nums[i];
                countOf.put(y,countOf.get(y)-1);
                if(countOf.get(y)==0){countOf.remove(y); unique--;}
                collected=0;
                i++;
            }
            
            // niche humnein agr unique utne hi hai jitne pehle ki window m tha toh aur jo i p element hai voi waala window se badha hai to vo i waale ka count kam krdete hai
            while(unique==k && countOf.get(nums[i]) > 1){ // remove duplicated numbers from left
                int y=nums[i];
                if(countOf.get(y)>1) countOf.put(y,countOf.get(y)-1);
                i++;
                collected++;
                y = nums[i];
            }
            if(unique==k) ans += collected+1; // phir bhi agr unique k hai mtlb yes this window is needed.
            j++;
        }
        return ans;
    }
}