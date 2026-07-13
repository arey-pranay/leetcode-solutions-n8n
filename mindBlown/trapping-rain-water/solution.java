class Solution {
    public int trap(int[] height) {
        int ans = 0;
        int n = height.length;
        int[] maxL = new int[n];
        int[] maxR = new int[n];
        maxL[0] = height[0];
        maxR[n-1] = height[n-1];
        for(int i=1;i<n;i++) maxL[i] = Math.max(maxL[i-1],height[i]);
        for(int i=n-2;i>=0;i--) maxR[i] = Math.max(maxR[i+1],height[i]);
        for(int i=0;i<n;i++) ans +=  Math.min(maxL[i], maxR[i])-height[i];
        return ans;
    }
}

// [0,1,0,2,1,0,1,3,2,1,2,1]