class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n+1];
        int min = Integer.MAX_VALUE;
        Deque<Integer> deq = new LinkedList<>();
        preSum[0] = 0;
        for(int i=0;i<n;i++)preSum[i+1] = preSum[i]+nums[i];
        int j =0;
        while(j<=n){
            while(!deq.isEmpty() && preSum[j]-preSum[deq.peekFirst()] >= k){
                int i = deq.removeFirst();
                min = Math.min(min,j-i);
            }
            while(!deq.isEmpty() && preSum[j]<=preSum[deq.peekLast()]) deq.removeLast();
            deq.addLast(j++);
        }
        return min==Integer.MAX_VALUE ? -1 : min;
    }
}