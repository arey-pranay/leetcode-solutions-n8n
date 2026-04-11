class Solution {
    HashMap<Integer,List<Integer>> hm;
    public Solution(int[] nums) {
        this.hm = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            List<Integer> list = hm.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            this.hm.put(nums[i],list);
        }
    }
    
    public int pick(int target) {
        List<Integer> list = hm.get(target);
        double randi =  Math.random(); // this give a number from 0 to 1
        
        // 0.2*3 = 0
        // 0.5*3 = 1
        // 0.9*3 = 2
       
        int index = (int)(Math.floor(randi* list.size())); // convert this random number to be from 0 to size-1, so that we can use that number as a random valid index
        return list.get(index);
      
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */