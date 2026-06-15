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
        int n = nums.length;
        int[] ans = new int[n- (k-1)];
        int j=0;
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=0;i<n;i++){
            while(!dq.isEmpty() && dq.peekFirst() <= i-k) dq.pollFirst();
            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) dq.pollLast();
            
            dq.offerLast(i);
            if(i<k-1) continue;
            ans[j++] = nums[dq.peekFirst()];
        }
        return ans;
    }
    
}
//offer - push 
//poll - pop
//peek - sirf dekhna
// priority ques ka retrieval nlog n hota hai 
```

---

---
## Quick Revision
Given an array of integers and a window size `k`, find the maximum element in each sliding window of size `k`.
This problem is efficiently solved using a deque (double-ended queue) to maintain candidate maximums.

## Intuition
The naive approach of iterating through each window and finding the maximum takes O(nk) time. We need a faster way. The key insight is that if we have a window, and a new element comes in that is larger than some elements already in the window, those smaller elements can *never* be the maximum of any future window that includes the new, larger element. This suggests we can discard smaller elements from the "back" of our consideration as we move the window. A deque is perfect for this: it allows efficient addition/removal from both ends, and we can store indices to track elements within the current window.

## Algorithm
1. Initialize an empty deque `dq` to store indices of elements.
2. Initialize an array `ans` of size `n - k + 1` to store the results.
3. Iterate through the input array `nums` from `i = 0` to `n-1`:
    a. **Remove out-of-window indices:** While the deque is not empty and the index at the front of the deque (`dq.peekFirst()`) is less than or equal to `i - k` (meaning it's outside the current window), remove it from the front (`dq.pollFirst()`).
    b. **Maintain decreasing order:** While the deque is not empty and the element at the index at the back of the deque (`nums[dq.peekLast()]`) is less than the current element `nums[i]`, remove the index from the back (`dq.pollLast()`). This ensures that the deque always stores indices of elements in decreasing order of their values.
    c. **Add current index:** Add the current index `i` to the back of the deque (`dq.offerLast(i)`).
    d. **Record maximum:** If `i` is greater than or equal to `k - 1` (meaning we have a full window), the maximum element for the current window is `nums[dq.peekFirst()]` (since the front of the deque always holds the index of the largest element within the current window). Store this value in `ans` at the appropriate position.
4. Return the `ans` array.

## Concept to Remember
*   **Monotonic Queue/Deque:** A deque where elements are maintained in a strictly increasing or decreasing order. This problem uses a monotonically decreasing deque (based on element values).
*   **Sliding Window Technique:** Efficiently processing contiguous subarrays (windows) of a fixed size by moving the window one element at a time.
*   **Amortized Analysis:** The overall time complexity is efficient because while some operations might take longer, they are infrequent, and the total work over all operations is bounded.

## Common Mistakes
*   Storing values instead of indices in the deque: This makes it hard to check if an element is still within the current window.
*   Incorrectly handling the window boundaries: Forgetting to remove elements that fall out of the window from the front of the deque.
*   Not maintaining the decreasing order property of the deque: This is crucial for ensuring the front element is always the maximum.
*   Off-by-one errors when calculating the size of the result array or when checking if a full window has been formed.

## Complexity Analysis
*   Time: O(n) - Each element is added to and removed from the deque at most once. The loops iterate through the array once.
*   Space: O(k) - In the worst case, the deque can store up to `k` indices (e.g., if the array is strictly decreasing within a window).

## Commented Code
```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length; // Get the length of the input array.
        // The result array will have n - k + 1 elements, as there are that many possible windows of size k.
        int[] ans = new int[n - (k - 1)];
        int j = 0; // Index for the result array 'ans'.
        // Use a Deque (double-ended queue) to store indices.
        // This deque will maintain indices of elements in the current window,
        // such that the elements corresponding to these indices are in decreasing order.
        Deque<Integer> dq = new ArrayDeque<>();

        // Iterate through the input array 'nums' with index 'i'.
        for (int i = 0; i < n; i++) {
            // 1. Remove indices from the front of the deque that are no longer in the current window.
            // The current window spans from index i - k + 1 to i.
            // If the index at the front of the deque (dq.peekFirst()) is less than or equal to i - k,
            // it means that element is outside the current window.
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst(); // Remove the index from the front.
            }

            // 2. Maintain the decreasing order property of the deque based on element values.
            // While the deque is not empty AND the element at the index at the back of the deque (nums[dq.peekLast()])
            // is less than the current element (nums[i]), remove that index from the back.
            // This is because the smaller element at the back can never be the maximum in any future window
            // that includes the current larger element nums[i].
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast(); // Remove the index from the back.
            }

            // 3. Add the current index 'i' to the back of the deque.
            dq.offerLast(i);

            // 4. If we have processed at least 'k' elements (i.e., formed a full window),
            // the maximum element for the current window is at the front of the deque.
            // The condition i < k - 1 means we are still filling the first window.
            if (i < k - 1) {
                continue; // Skip recording the maximum until the first full window is formed.
            }

            // The index at the front of the deque (dq.peekFirst()) always points to the maximum element
            // within the current sliding window because of steps 1 and 2.
            ans[j++] = nums[dq.peekFirst()]; // Store the maximum element in the result array.
        }
        return ans; // Return the array containing maximums for each sliding window.
    }
}
// offer - push (add to end)
// poll - pop (remove from front)
// peek - sirf dekhna (view front)
// priority ques ka retrieval nlog n hota hai (This comment is a good reminder of why a priority queue is NOT the optimal solution here, as its retrieval is O(log n), whereas deque offers O(1) for this specific problem's needs.)
```

## Interview Tips
*   Clearly explain the "monotonic deque" concept and why it's crucial for efficiency.
*   Walk through an example manually with the deque's state changes to demonstrate your understanding.
*   Discuss the trade-offs between a priority queue (O(n log k) or O(n log n)) and the deque approach (O(n)).
*   Be prepared to explain why storing indices is important, not just the values.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Grasp the intuition behind using a deque for monotonic property.
- [ ] Implement the deque logic: removing out-of-window elements and maintaining decreasing order.
- [ ] Correctly handle the window formation and result recording.
- [ ] Analyze time and space complexity.
- [ ] Practice with edge cases (empty array, k=1, k=n).

## Similar Problems
*   LeetCode 239: Sliding Window Maximum (this problem)
*   LeetCode 1425: Constrained Subsequence Sum
*   LeetCode 862: Shortest Subarray with Sum at Least K
*   LeetCode 918: Maximum Sum Circular Subarray (can be solved with variations of sliding window/deque)

## Tags
`Array` `Deque` `Monotonic Queue`
