class Solution {
    public boolean uniformArray(int[] nums1) {
      //even ko odd bnane ke liye smaller odd number
      //odd ko even bnane ke liye smaller odd number

      int smallest = Integer.MAX_VALUE;
      boolean allEven = true;
      
      for(int num : nums1){ smallest = Math.min(smallest,num); if(num % 2 == 1) allEven = false;} // agr sbko even bnana hai, to jo even hai wo fine, and agr koi odd hai to usko even bnane ke liye usse smaller odd chahiye hume, therefore not possible
      
      boolean makeEven  = smallest% 2 == 0 ? true : false; // kyuki 0th number update nhi ho skta. humko saare uske jese bnane hoge
      
      if(!makeEven) return true; // agr sbko odd bnana hai, then jo odd hai wo fine, and jo even hai unme se 0th element hata denge
      else return allEven; // agr sbko even bnana hai, to jo even hai wo fine, but agr koi odd hai to usko even bnane ke liye usse smaller odd chahiye and smallest odd number se zyada smaller odd number nahi mil skta hai
      
      
    }
}