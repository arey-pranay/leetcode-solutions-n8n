class Solution {
    public int jump(int[] nums) {
      int jumps=0, currMax = 0, nextMax =0;
      int n = nums.length;
      for(int i=0;i<n-1;i++){
          int canGo = i+nums[i];
          nextMax = Math.max(nextMax,canGo); //2,4
          if(i==currMax){//yes
              jumps++; // abhi tk ka humara best jo tha wahan hum aagye, now we need another jump for going forward //1
              currMax=nextMax; //2
          }
      }
      return jumps;
    }
}



