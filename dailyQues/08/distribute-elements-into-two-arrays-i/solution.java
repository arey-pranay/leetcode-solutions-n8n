class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];   
        List<Integer> temp = new ArrayList<>();
        int i = 0;
        for(int num : nums){
            if(i==0) ans[i++] = num; 
            else if(temp.isEmpty())temp.add(num);
            else{
                if(ans[i-1] > temp.get(temp.size()-1)) ans[i++] = num;
                else temp.add(num);
            }
        }
        for(int num : temp) ans[i++] = num;
        return ans;
    }
}