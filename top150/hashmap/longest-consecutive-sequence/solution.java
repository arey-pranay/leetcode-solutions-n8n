class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();
        for(int i :nums) hs.add(i);
        int max = 0;
        for(int num : hs){
            if(!hs.contains(num-1)){
                int curr = num;
                int count = 1;
                while(hs.contains(curr+1)){
                    curr++;
                    count++;
                }
                max = Math.max(max,count);
            }
        }
        return max;         
    }
}