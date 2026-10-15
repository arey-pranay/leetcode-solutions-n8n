class Solution {
    public int sumCounts(List<Integer> nums) {
        int ans = 0;
        int n = nums.size();
        for(int i=0;i<n;i++){
            boolean[] seen = new boolean[101];
            int distinct=0;
            for(int j=i;j<n;j++){
                int x = nums.get(j);
                if(!seen[x]){seen[x]=true; distinct++;}
                ans+= (distinct*distinct);
            }
        }
        return ans;
    }
}
// 1 2 1
// i
// j

// i=0
//     j=0 => 1
//     j=1 => 1,2
// ans =  1 + 2