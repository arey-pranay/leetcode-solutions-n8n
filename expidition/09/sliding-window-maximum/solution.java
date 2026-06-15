class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        int n = nums.length;
        int[] ans = new int[n - k + 1];

        for (int i = 0; i < n; i++) {
            // Remove out-of-window indices
            if(!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // Remove smaller elements
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }

            dq.addLast(i);

            if (i >= k - 1) {
                ans[i - k + 1] = nums[dq.peekFirst()];
            }
        }

        return ans;
    }
}

// class Solution {
//     public int[] maxSlidingWindow(int[] nums, int k) {
//         int n = nums.length;
//         int[] ans = new int[n- (k-1)];
//         int j=0;
//         PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[0]-a[0]);
//         for(int i=0;i<n;i++){
//             pq.offer(new int[]{nums[i],i});
//             while(!pq.isEmpty() && pq.peek()[1] <= i-k) pq.poll();
            
//             if(i<k-1) continue;
//             ans[j++] = pq.peek()[0];
//         }
//         return ans;
//     }
    
// }
