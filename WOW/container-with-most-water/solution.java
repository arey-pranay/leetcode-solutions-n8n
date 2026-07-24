class Solution {
    public int maxArea(int[] height) {
        int ans = 0;
        int n = height.length;
        int i=0, j=n-1;
        while(i<j){
            ans = Math.max(ans,Math.min(height[i],height[j]) * (j-i));
            if(height[i]<height[j]) i++; else j--;
        }
        return ans;
    }
}

//kitne paani ki guarantee hai abhi -> Math.min(height[i],height[j]) * (j-i)