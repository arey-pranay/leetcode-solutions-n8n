# Sliding Window Maximum

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Queue` `Sliding Window` `Heap (Priority Queue)` `Monotonic Queue`  
**Time:** O(n)  
**Space:** O(k)

---

## Solution (java)

```java
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

```

---

---
## Quick Revision
Given an array and a window size `k`, find the maximum element in each sliding window of size `k`.
This is efficiently solved using a deque to maintain candidate maximums within the current window.

## Intuition
The naive approach of iterating through each window and finding the maximum would be O(nk). We need a way to quickly find the maximum as the window slides. The key insight is that if we have a number `x` in the window, and a number `y` that comes later and is greater than `x`, then `x` can *never* be the maximum of any future window that includes `y`. This suggests maintaining a data structure that stores potential maximums in decreasing order. A deque (double-ended queue) is perfect for this: we can add elements to the back and remove elements from both the front and back.

## Algorithm
1. Initialize an empty deque `dq` to store indices of elements.
2. Initialize an empty result array `ans` of size `n - k + 1`.
3. Iterate through the input array `nums` from index `i = 0` to `n - 1`:
    a. **Remove out-of-window indices:** If the deque is not empty and the index at the front of the deque (`dq.peekFirst()`) is less than or equal to `i - k` (meaning it's outside the current window), remove it from the front (`dq.pollFirst()`).
    b. **Remove smaller elements:** While the deque is not empty and the element at the index at the back of the deque (`nums[dq.peekLast()]`) is less than the current element `nums[i]`, remove the index from the back (`dq.pollLast()`). This ensures that the deque only stores indices of elements in decreasing order of their values.
    c. **Add current index:** Add the current index `i` to the back of the deque (`dq.addLast(i)`).
    d. **Record maximum:** If `i` is greater than or equal to `k - 1` (meaning the window has reached its full size), the maximum for the current window is the element at the index at the front of the deque (`nums[dq.peekFirst()]`). Store this in `ans[i - k + 1]`.
4. Return the `ans` array.

## Concept to Remember
*   **Deque (Double-Ended Queue):** A data structure that allows efficient insertion and deletion from both ends. Useful for maintaining ordered sequences or candidates.
*   **Monotonic Queue/Deque:** A deque where elements are maintained in a specific order (e.g., strictly increasing or decreasing). This problem uses a monotonically decreasing deque of indices based on their corresponding values.
*   **Sliding Window Technique:** An algorithmic paradigm for processing a contiguous subsegment of a sequence, where the subsegment's start and end points move.

## Common Mistakes
*   Forgetting to remove elements from the front of the deque that are no longer within the current window.
*   Incorrectly handling the condition for adding results to the `ans` array (e.g., starting too early or too late).
*   Not correctly maintaining the monotonic property of the deque by failing to remove smaller elements from the back.
*   Using a standard queue or stack instead of a deque, which would not allow efficient removal from both ends.

## Complexity Analysis
*   Time: O(n) - Each element is added to and removed from the deque at most once. The loop runs `n` times, and deque operations are O(1) amortized.
*   Space: O(k) - In the worst case, the deque can store up to `k` indices if the elements are in strictly decreasing order within a window.

## Commented Code
```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // Deque to store indices of elements. It will maintain indices of elements in decreasing order of their values.
        Deque<Integer> dq = new ArrayDeque<>();
        // Length of the input array.
        int n = nums.length;
        // Result array to store the maximum of each sliding window. The size is n - k + 1 because there are n - k + 1 possible windows.
        int[] ans = new int[n - k + 1];

        // Iterate through the input array. 'i' represents the right boundary of the current window.
        for (int i = 0; i < n; i++) {
            // Step 1: Remove indices from the front of the deque that are no longer within the current window.
            // The current window spans from index i - k + 1 to i.
            // If the index at the front of the deque (dq.peekFirst()) is less than or equal to i - k, it's outside the window.
            if(!dq.isEmpty() && dq.peekFirst() <= i - k) {
                // Remove the index from the front.
                dq.pollFirst();
            }

            // Step 2: Remove indices from the back of the deque whose corresponding values are smaller than the current element.
            // This maintains the monotonically decreasing property of the deque (based on values).
            // If nums[dq.peekLast()] < nums[i], then nums[dq.peekLast()] can never be the maximum in any future window that includes nums[i].
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                // Remove the index from the back.
                dq.pollLast();
            }

            // Step 3: Add the current index 'i' to the back of the deque.
            dq.addLast(i);

            // Step 4: If the window has reached its full size (i.e., we have processed at least k elements), record the maximum.
            // The condition i >= k - 1 checks if the current window (ending at i) has at least k elements.
            if (i >= k - 1) {
                // The maximum element for the current window is always at the index at the front of the deque,
                // because we maintained the deque in decreasing order of values.
                // The result for the window ending at 'i' is stored at index 'i - k + 1' in the 'ans' array.
                ans[i - k + 1] = nums[dq.peekFirst()];
            }
        }

        // Return the array containing the maximums of all sliding windows.
        return ans;
    }
}
```

## Interview Tips
*   Clearly explain the intuition behind using a deque and the monotonic property.
*   Walk through an example manually to demonstrate how the deque is updated.
*   Be prepared to discuss the time and space complexity and justify it.
*   If asked about alternative solutions, mention the Priority Queue approach and its trade-offs (O(n log k) time).

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Grasp the intuition behind using a monotonic deque.
- [ ] Implement the deque logic correctly (adding, removing from front/back).
- [ ] Handle edge cases (empty array, k=1, k=n).
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution verbally.

## Similar Problems
*   LeetCode 239: Sliding Window Maximum (This problem)
*   LeetCode 1425: Constrained Subsequence Sum
*   LeetCode 862: Shortest Subarray with Sum at Least K
*   LeetCode 76: Minimum Window Substring (Different problem, but uses sliding window)

## Tags
`Array` `Deque` `Monotonic Queue` `Sliding Window`
