class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();
        for(int i : nums)hs.add(i);
        int max = 0;
        for(int num : hs){
            if(!hs.contains(num-1)){
                int count = 1;
                int curr = num;
                while(hs.contains(curr+1)){
                    count++;
                    curr++;
                }
                max = Math.max(count,max);
            }
        }
        return max;
    }
}