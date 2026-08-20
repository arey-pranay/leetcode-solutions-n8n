class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n- (k-1)];
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=0;i<n;i++){
            if(!dq.isEmpty() && dq.peekFirst() <= i-k) dq.pollFirst(); // agr oldest element k se zyada difference pe hai, remove it
            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) dq.pollLast();
            // current element ke pehle jo bhi issse chhote element the, wo kbhi kaam nhi aayege, remove them
            dq.offerLast(i);
            if(i>=k-1) ans[i-(k-1)] = nums[dq.peekFirst()];
        }
        return ans;
    }
}
//offer - push 
//poll - pop
//peek - sirf dekhna
// priority ques ka retrieval nlog n hota hai 

// class Solution {
//     public int[] maxSlidingWindow(int[] nums, int k) {
//         Comparator<Integer> comparator = (a,b)-> nums[a]==nums[b] ? b-a : nums[b]-nums[a];
//         TreeSet<Integer> ts = new TreeSet<>(comparator);
//         int n= nums.length;
//         int m = n-(k-1);
//         int[] ans = new int[m];
//         for(int i=0;i<n;i++){
//             if(i>=k){
//                 ts.remove(i-k);
//             }
//             ts.add(i);//0,1,2
//             if(i>=k-1) ans[i-k+1] = nums[ts.first()];
//         }
//         // ans[m-1]  = ts.first();
//         return ans;
//     }
// }