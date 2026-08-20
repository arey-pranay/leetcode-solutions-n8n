# Sliding Window Maximum

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Queue` `Sliding Window` `Heap (Priority Queue)` `Monotonic Queue` `Range Minimum/Maximum Query`  
**Time:** O(n)  
**Space:** O(k)

---

## Solution (java)

```java
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
```

---

---
## Quick Revision
Given an array and a window size `k`, find the maximum element in each sliding window of size `k`. This is efficiently solved using a deque to maintain candidate maximums.

## Intuition
As the window slides, we need to efficiently find the maximum. A naive approach of iterating through each window is O(nk). We can do better by maintaining a data structure that stores potential maximums. A deque is ideal because it allows us to:
1. Remove elements that are no longer in the window from the front.
2. Remove elements that are smaller than the current element from the back, as they can never be the maximum once the current element is in the window.
3. Quickly access the current maximum (which will always be at the front of the deque).

## Algorithm
1. Initialize an empty deque `dq` to store indices of `nums`.
2. Initialize an array `ans` of size `n - k + 1` to store the results.
3. Iterate through the input array `nums` from index `i = 0` to `n-1`:
    a. **Remove out-of-window elements:** If the deque is not empty and the index at the front of the deque (`dq.peekFirst()`) is less than or equal to `i - k`, remove it from the front (`dq.pollFirst()`). This ensures that only indices within the current window are considered.
    b. **Remove smaller elements:** While the deque is not empty and the element at the index at the back of the deque (`nums[dq.peekLast()]`) is less than the current element (`nums[i]`), remove the index from the back (`dq.pollLast()`). These smaller elements can never be the maximum in any future window that includes `nums[i]`.
    c. **Add current element:** Add the current index `i` to the back of the deque (`dq.offerLast(i)`).
    d. **Record maximum:** If `i` is greater than or equal to `k - 1` (meaning the window has fully formed), the maximum for the current window is `nums[dq.peekFirst()]`. Store this in `ans[i - (k - 1)]`.
4. Return the `ans` array.

## Concept to Remember
*   **Deque (Double-Ended Queue):** A data structure that allows efficient insertion and deletion from both ends. Crucial for maintaining ordered candidates.
*   **Monotonic Queue:** The deque in this problem effectively becomes a monotonic decreasing queue (of values, based on indices). This property is key to its efficiency.
*   **Sliding Window Technique:** A common algorithmic pattern for problems involving a contiguous sub-array/sub-string of a fixed size that moves across the input.

## Common Mistakes
*   Forgetting to remove elements that are no longer in the window from the front of the deque.
*   Incorrectly handling the condition for when to start recording results (i.e., when the window is fully formed).
*   Storing values directly in the deque instead of indices, which makes it hard to check if an element is still within the window.
*   Off-by-one errors when calculating the index for the `ans` array.

## Complexity Analysis
*   **Time:** O(n) - Each element is added to and removed from the deque at most once. The loop runs `n` times, and deque operations are O(1).
*   **Space:** O(k) - In the worst case, the deque can store up to `k` indices (e.g., if the array is strictly decreasing within a window).

## Commented Code
```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length; // Get the length of the input array.
        // Initialize the result array. Its size is n - k + 1 because there are n - k + 1 possible windows of size k.
        int[] ans = new int[n - (k - 1)];
        // Initialize a deque (double-ended queue) to store indices of elements.
        // This deque will maintain indices of elements in decreasing order of their values,
        // and only keep indices that are within the current window.
        Deque<Integer> dq = new ArrayDeque<>();

        // Iterate through the input array.
        for (int i = 0; i < n; i++) {
            // Step 3a: Remove indices from the front of the deque that are no longer in the current window.
            // An index is out of the window if it's less than or equal to i - k.
            if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst(); // Remove the index from the front.
            }

            // Step 3b: Remove indices from the back of the deque whose corresponding values are smaller than the current element.
            // These smaller elements can never be the maximum in any future window that includes the current element nums[i].
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast(); // Remove the index of the smaller element from the back.
            }

            // Step 3c: Add the current index to the back of the deque.
            dq.offerLast(i);

            // Step 3d: If the window has fully formed (i.e., we have processed at least k elements),
            // record the maximum for the current window. The maximum is always at the front of the deque.
            if (i >= k - 1) {
                // The index of the maximum element for the current window is at the front of the deque.
                // We store the value of this element in the result array.
                // The index in the ans array is i - (k - 1) to correctly map the window to its result.
                ans[i - (k - 1)] = nums[dq.peekFirst()];
            }
        }
        // Return the array containing the maximums for each sliding window.
        return ans;
    }
}
```

## Interview Tips
*   Clearly explain the purpose of the deque and why it's used to store indices, not values.
*   Walk through an example manually using the deque to demonstrate your understanding.
*   Emphasize the two key deque operations: removing out-of-window elements and removing smaller preceding elements.
*   Be prepared to discuss the time and space complexity and justify them.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the naive O(nk) solution and its drawbacks.
- [ ] Grasp the intuition behind using a deque for optimization.
- [ ] Implement the deque logic correctly: adding, removing from front/back.
- [ ] Handle edge cases: empty array, k=1, k=n.
- [ ] Calculate the result array index correctly.
- [ ] Analyze time and space complexity.

## Similar Problems
*   1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
*   239. Sliding Window Maximum (this problem)
*   862. Shortest Subarray with Sum at Least K
*   76. Minimum Window Substring

## Tags
`Array` `Deque` `Monotonic Queue` `Sliding Window`
