class Solution {
    int[] fenwick = new int[20002];
    // btata hai ki Fenwick Array me us index pe kitne numbers ka sum already hai. Instead of adding 7 elements individually, Fenwick breaks it into large chunks.
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        // We need to do 1-based indexing for fenwick trees => ans[1] = -1000 ka answer => ans[2001] = 1000 ka answer (range oif numbers is -1000 to 1000)
        // So the conversion would be arr[num+1001]
        Integer[] ans = new Integer[n];
        for(int i=n-1 ; i>=0 ; i--){
          int index = nums[i]+10001;
          ans[i] = add(index);
          update(index+1);
       }
      return Arrays.asList(ans);
    }
    public int add(int index){
      int ans = 0;
      while(index > 0){
        ans += fenwick[index]; //ikattha kiya answer, har index se, jinpe mai depend krta hu
        index -= (index & -index);
      }
      return ans;
    }
   public void update(int index){
      while(index < 20002){
        fenwick[index]++;
        index += (index & -index); // update kara frequence, har index ka, jo mujhpe depend krte hai
      }
    }
}