class Solution {
    Long[][] memo;
    public long maxSubarraySum(int[] nums, int k) {
        long max = Long.MIN_VALUE;
        
        // 1. Initialize memo ONCE for the multiplication transform
        memo = new Long[3][nums.length];
        for(int i = 0; i < nums.length; i++){
            long a = func(0, nums, i, k, x -> (long) x * k);
            max = Math.max(max, a);
        }
           
        // 2. Reset and reuse memo ONCE for the division transform
        memo = new Long[3][nums.length];
        for(int i = 0; i < nums.length; i++){
            long b = func(0, nums, i, k, x -> (long) x / k);
            max = Math.max(max, b);
        }
        
        return max;
    }

    public long func(int state, int[] arr, int index, int k, java.util.function.LongUnaryOperator transform){
        if(index == arr.length) return state == 1 || state == 2 ? 0 : Long.MIN_VALUE / 4; 
        if(memo[state][index] != null) return memo[state][index];
        
        long x = arr[index];
        long y = transform.applyAsLong(x);

        if(state == 0)
            return memo[state][index] = Math.max(
                x + Math.max(0, func(0, arr, index + 1, k, transform)), 
                y + Math.max(0, func(1, arr, index + 1, k, transform)) 
            );
        if(state == 1)
             return memo[state][index] = Math.max(
                y + Math.max(0, func(1, arr, index + 1, k, transform)), 
                x + Math.max(0, func(2, arr, index + 1, k, transform))  
            );
        return memo[state][index] = x + Math.max(0, func(2, arr, index + 1, k, transform)); 
    }
}