class Solution {
    public int minKBitFlips(int[] nums, int k) {
        // select any subarray of length k 
        // change all 0s to 1 and all 1s to zero
        // return min number of ops to make array with no 0s
        int count =0;
        int n = nums.length;
        // even times flips => no net change in value => 0 flips
        // odd times flips => no net change in value => 1 flip
        
        Queue<Integer> flips = new LinkedList<>();
        for(int i=0;i<n;i++){
            
            if(!flips.isEmpty()){ // discard the flip entries which are expired now
               int oldestFlipLimit = flips.peek();
               if(i>oldestFlipLimit) flips.poll(); 
               // jese hi old hojaata hai tb hum flip krdenge kyonki ab aur koi nayi sliding window m nhi aayega vo 
            }
            
            int netFlips = flips.size() % 2;
            
            if(netFlips==1) nums[i] = nums[i]==0?1:0;
            
            if(nums[i]==0) {
                if(i+k >n) return -1;
                count++;                
                flips.offer(i+k-1);  // is flip ki expiry agle k indices tk hai
            }
           
          
        }
        
        return count;
    }
}

