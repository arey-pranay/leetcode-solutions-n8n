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
Given an array and a window size `k`, find the maximum element in each sliding window of size `k`.
This is efficiently solved using a deque to maintain candidate maximums within the current window.

## Intuition
As the window slides, we need to quickly find the maximum. A naive approach of iterating through each window is O(nk). We can do better. The key insight is that if we have two elements `a` and `b` in the window, and `a` comes before `b` and `a <= b`, then `a` can never be the maximum of any future window that includes `b`. This is because `b` will always be a better candidate (larger or equal, and appears later). This suggests maintaining a data structure that stores indices of potential maximums in decreasing order of their values. A deque (double-ended queue) is perfect for this: we can add elements to the back and remove elements from both front and back efficiently.

## Algorithm
1. Initialize an empty deque `dq` to store indices of elements.
2. Initialize an array `ans` of size `n - k + 1` to store the results.
3. Iterate through the input array `nums` from index `i = 0` to `n-1`:
    a. **Remove Out-of-Window Indices:** If the deque is not empty and the index at the front of the deque (`dq.peekFirst()`) is outside the current window (i.e., `dq.peekFirst() <= i - k`), remove it from the front (`dq.pollFirst()`).
    b. **Maintain Decreasing Order:** While the deque is not empty and the element at the index at the back of the deque (`nums[dq.peekLast()]`) is less than the current element (`nums[i]`), remove the index from the back (`dq.pollLast()`). This ensures that the deque only stores indices of elements that are potentially maximums in decreasing order of their values.
    c. **Add Current Index:** Add the current index `i` to the back of the deque (`dq.offerLast(i)`).
    d. **Record Maximum:** If the current index `i` is greater than or equal to `k - 1` (meaning a full window has been formed), the maximum for the current window is the element at the index at the front of the deque (`nums[dq.peekFirst()]`). Store this in the `ans` array at the appropriate position (`ans[i - (k - 1)]`).
4. Return the `ans` array.

## Concept to Remember
*   **Monotonic Deque:** A deque where elements are maintained in a specific order (e.g., strictly increasing or decreasing). In this case, it's a monotonically decreasing deque based on the values of the elements at the stored indices.
*   **Sliding Window Technique:** Efficiently processing a contiguous sub-array (window) of a larger array by moving the window one element at a time.
*   **Amortized Analysis:** The overall time complexity is efficient because while some operations (like `while` loop removals) might take time in a single step, they happen infrequently enough that the average cost per element is constant.

## Common Mistakes
*   **Incorrectly handling the window boundaries:** Forgetting to remove elements that fall out of the window from the front of the deque.
*   **Not maintaining the monotonic property:** Failing to remove smaller elements from the back of the deque when a larger element is encountered, leading to incorrect maximums.
*   **Off-by-one errors in indexing:** Especially when calculating the index for storing results in the `ans` array.
*   **Using a data structure that doesn't support efficient front/back operations:** For example, using a standard queue or list without considering the performance implications.

## Complexity Analysis
- Time: O(n) - Each element is added to and removed from the deque at most once. The loops iterate through the array once.
- Space: O(k) - In the worst case, the deque can store up to `k` indices if the elements are in strictly decreasing order within a window.

## Commented Code
```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length; // Get the total number of elements in the input array.
        // Initialize the result array. The size is n - k + 1 because there are n - k + 1 possible windows of size k.
        int[] ans = new int[n - (k - 1)];
        // Initialize a deque (double-ended queue) to store indices.
        // This deque will maintain indices of elements in the current window,
        // such that the elements at these indices are in decreasing order.
        Deque<Integer> dq = new ArrayDeque<>();

        // Iterate through the input array from the beginning to the end.
        for (int i = 0; i < n; i++) {
            // Step 1: Remove indices from the front of the deque that are no longer in the current window.
            // An index is out of the window if it's less than or equal to i - k.
            if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst(); // Remove the index from the front.
            }

            // Step 2: Maintain the decreasing order property of the deque.
            // While the deque is not empty AND the element at the last index in the deque
            // is less than the current element nums[i], remove the last index.
            // This is because the element at dq.peekLast() can never be the maximum
            // in any future window that includes nums[i], as nums[i] is larger and appears later.
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast(); // Remove the index from the back.
            }

            // Step 3: Add the current index to the back of the deque.
            dq.offerLast(i);

            // Step 4: If the current index 'i' has reached or surpassed the end of the first full window (i.e., i >= k-1),
            // then the maximum element for the current window is at the index at the front of the deque.
            // Store this maximum value in the 'ans' array.
            // The index in 'ans' is calculated as i - (k - 1) to map the window's end to the correct result index.
            if (i >= k - 1) {
                ans[i - (k - 1)] = nums[dq.peekFirst()];
            }
        }
        // Return the array containing the maximums for each sliding window.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the Monotonic Deque:** Clearly articulate why a deque is used and how it maintains the monotonic property. This is the core of the solution.
*   **Walk Through an Example:** Use a small example array and window size to trace the algorithm step-by-step, showing how the deque changes and how the result is populated.
*   **Discuss Edge Cases:** Consider cases like an empty array, `k=1`, or `k=n`.
*   **Compare with Naive/Other Approaches:** Briefly mention why a brute-force O(nk) or a priority queue O(n log k) approach is less optimal and why the deque solution is preferred.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Grasp the intuition behind using a monotonic deque.
- [ ] Implement the deque logic for adding and removing elements correctly.
- [ ] Handle window boundary conditions (elements falling out).
- [ ] Correctly map window indices to result array indices.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) (This problem itself)
*   [1425. Constrained Subsequence Sum](https://leetcode.com/problems/constrained-subsequence-sum/) (Uses a similar deque approach for optimization)
*   [862. Shortest Subarray with Sum at Least K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/) (Another problem where a monotonic deque is crucial)

## Tags
`Array` `Deque` `Monotonic Stack`
