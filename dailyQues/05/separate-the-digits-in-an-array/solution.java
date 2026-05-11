class Solution {
    public int[] separateDigits(int[] nums) {
        ArrayList<Integer> curr = new ArrayList<>();
        for(int i = nums.length-1; i>=0; i--){
            int num = nums[i];
            while(num > 0){
                curr.add(num%10);
                num /= 10;
            }
        }   
        int[] ans = new int[curr.size()];
        for(int i = ans.length-1;i>=0;i--) ans[ans.length-1-i] = curr.get(i);
        return ans;
    }
    
    
}