class Solution {
    public int minimumDeletions(int[] nums) {
        int n= nums.length;
        int min = 100001;
        int max = -100001;
        int minI = -1;
        int maxI = -1;
        for(int i =0 ; i<n;i++){
            if(min>nums[i]){
                min = nums[i];
                minI = i;
            }
            if(max<nums[i]){
                max = nums[i];
                maxI = i;
            }
        }
    
    
        if(minI > maxI){
            int temp = minI;
            minI = maxI;
            maxI=temp;
        }
        
        
        //Now we know that, maxI > minI (minI left me hai, maxI right me)

        int a = maxI+1; //starting se hataye
        int b = minI+1 + n-maxI; // aage wale ko aage se hataya, pichhe wale ko pichhe se
        int c = n-minI; // dono ko pichhe se hataya
        return Math.min(a,Math.min(b,c));
    }
}