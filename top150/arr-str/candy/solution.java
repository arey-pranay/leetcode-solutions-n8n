class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies,1);
        for(int i=1;i<n;i++) if(ratings[i-1] < ratings[i]) candies[i] = candies[i-1] + 1;
        for(int i=n-2;i>=0;i--) if(ratings[i+1] < ratings[i]) candies[i] = Math.max(candies[i],candies[i+1] + 1);
        int ans=0;
        for(int val : candies) ans += val;
        return ans;
    }
}

// 1 2 4 0 9 1 2 10
// - 1 2 1 2 - - -1

// 1 0 2
// 2 1 2