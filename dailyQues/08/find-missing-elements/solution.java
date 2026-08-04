class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        boolean[] vis = new boolean[101];
        int min = 101, max = -1;
        for(int i : nums){min = Math.min(min,i); max = Math.max(max,i); vis[i] = true;}
        List<Integer> ans = new ArrayList<>();
        for(int i=min;i<max;i++) if(!vis[i]) ans.add(i);
        return ans;
    }
}