# Shortest Subarray With Sum At Least K

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Queue` `Sliding Window` `Heap (Priority Queue)` `Prefix Sum` `Monotonic Queue`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
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
```

---

---
## Quick Revision
Find the shortest contiguous subarray whose sum is at least K.
This is solved using prefix sums and a monotonic deque.

## Intuition
The problem asks for the shortest subarray `nums[i...j]` such that `sum(nums[i...j]) >= k`.
Using prefix sums, this condition becomes `preSum[j+1] - preSum[i] >= k`.
We want to minimize `(j+1) - i`.
For a fixed `j`, we are looking for the smallest `i` such that `preSum[i] <= preSum[j+1] - k`.
A naive approach would be to iterate through all possible `i` for each `j`, leading to O(n^2).
The key insight is that if we have two indices `i1 < i2` and `preSum[i1] >= preSum[i2]`, then `i1` is never a better starting point than `i2` for any future `j`. This is because if `preSum[j+1] - preSum[i1] >= k`, then `preSum[j+1] - preSum[i2]` will also be `>= k` (since `preSum[i2] <= preSum[i1]`), and `j+1 - i2` will be shorter than `j+1 - i1`. This suggests maintaining a data structure that stores potential starting indices `i` in increasing order, and their corresponding prefix sums also in increasing order. A monotonic deque (specifically, a deque storing indices where prefix sums are increasing) fits this requirement.

## Algorithm
1. **Prefix Sum Calculation**: Create a prefix sum array `preSum` where `preSum[i]` stores the sum of `nums[0]` to `nums[i-1]`. `preSum[0]` should be 0.
2. **Monotonic Deque Initialization**: Initialize an empty deque `deq` to store indices. This deque will maintain indices `i` such that `preSum[i]` is monotonically increasing.
3. **Iteration**: Iterate through the `preSum` array from index `j = 0` to `n` (inclusive, where `n` is the length of `nums`).
4. **Check for Valid Subarrays**: For each `j`, while the deque is not empty and `preSum[j] - preSum[deq.peekFirst()] >= k`:
    * This means the subarray ending at `j-1` and starting at `deq.peekFirst()` has a sum of at least `k`.
    * Update the minimum length found so far: `min = Math.min(min, j - deq.peekFirst())`.
    * Remove the first element from the deque (`deq.removeFirst()`) because it has now been used to find the shortest subarray ending at `j-1` and any future `j'` will result in a longer subarray starting at this index.
5. **Maintain Monotonicity**: For each `j`, while the deque is not empty and `preSum[j] <= preSum[deq.peekLast()]`:
    * This means the current index `j` offers a smaller or equal prefix sum than the last index in the deque. If we were to use the last index in the deque as a starting point for a future subarray, `j` would always be a better or equal starting point (shorter length, smaller or equal prefix sum).
    * Remove the last element from the deque (`deq.removeLast()`).
6. **Add Current Index**: Add the current index `j` to the end of the deque (`deq.addLast(j)`).
7. **Return Result**: After iterating through all `j`, if `min` is still `Integer.MAX_VALUE`, it means no subarray with sum at least `k` was found, so return -1. Otherwise, return `min`.

## Concept to Remember
*   **Prefix Sums**: Efficiently calculating sums of contiguous subarrays.
*   **Monotonic Deque**: A data structure used to maintain elements in a specific order (increasing or decreasing) to optimize lookups and removals.
*   **Sliding Window (Implicit)**: The deque helps manage potential start points for a "window" that expands to the right.
*   **Optimization of Redundant Candidates**: The deque discards indices that can never be optimal starting points.

## Common Mistakes
*   **Off-by-one errors**: Incorrectly handling prefix sum indices or subarray lengths.
*   **Not handling negative numbers**: The monotonic deque logic relies on prefix sums potentially decreasing, which is crucial for negative numbers.
*   **Incorrect deque maintenance**: Failing to remove elements from both the front (when a valid subarray is found) and the back (to maintain monotonicity).
*   **Forgetting the base case for prefix sums**: `preSum[0]` must be initialized to 0.
*   **Returning the wrong value when no solution exists**: Ensure -1 is returned if `min` remains at its initial large value.

## Complexity Analysis
- Time: O(n) - reason: Each index `j` is added to and removed from the deque at most once. The prefix sum calculation is O(n).
- Space: O(n) - reason: The prefix sum array takes O(n) space, and the deque can store up to O(n) indices in the worst case.

## Commented Code
```java
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length; // Get the length of the input array.
        // Create a prefix sum array. preSum[i] will store the sum of nums[0]...nums[i-1].
        // The size is n+1 to accommodate preSum[0] = 0.
        int[] preSum = new int[n+1];
        int min = Integer.MAX_VALUE; // Initialize minimum length to a very large value.
        // Use a LinkedList as a Deque (Double-Ended Queue) to store indices.
        // This deque will maintain indices 'i' such that preSum[i] is monotonically increasing.
        Deque<Integer> deq = new LinkedList<>();

        preSum[0] = 0; // Base case: the sum before any elements is 0.

        // Calculate prefix sums.
        for(int i = 0; i < n; i++) {
            preSum[i+1] = preSum[i] + nums[i]; // preSum[i+1] is sum up to nums[i].
        }

        // Iterate through the prefix sum array. 'j' represents the end index of a potential subarray (exclusive).
        // So, preSum[j] is the sum of nums[0]...nums[j-1].
        int j = 0;
        while(j <= n) { // Iterate up to n to consider subarrays ending at nums[n-1].

            // Condition 1: Check if we can form a valid subarray ending at index j-1.
            // preSum[j] - preSum[deq.peekFirst()] represents the sum of the subarray
            // starting at index deq.peekFirst() and ending at index j-1.
            while(!deq.isEmpty() && preSum[j] - preSum[deq.peekFirst()] >= k) {
                // If the sum is at least k, we found a valid subarray.
                // The start index is deq.peekFirst(), and the end index is j-1.
                // The length of this subarray is (j-1) - deq.peekFirst() + 1 = j - deq.peekFirst().
                int i = deq.removeFirst(); // Get the start index.
                min = Math.min(min, j - i); // Update the minimum length found so far.
                // We remove deq.peekFirst() because it has now been used to find the shortest
                // subarray ending at j-1. Any future j' will result in a longer subarray
                // starting at this same index.
            }

            // Condition 2: Maintain the monotonic increasing property of prefix sums in the deque.
            // If preSum[j] is less than or equal to the prefix sum of the last index in the deque,
            // then the last index in the deque is no longer a useful candidate.
            // Why? Because 'j' is a later index with a smaller or equal prefix sum.
            // If a future index 'x' needs a starting point 'p' such that preSum[x] - preSum[p] >= k,
            // and if preSum[last_in_deque] >= preSum[j], then 'j' would be a better starting point
            // than last_in_deque because it's a later index (shorter subarray) and has a smaller
            // or equal prefix sum (easier to satisfy the >= k condition).
            while(!deq.isEmpty() && preSum[j] <= preSum[deq.peekLast()]) {
                deq.removeLast(); // Remove the last index as it's suboptimal.
            }

            // Add the current index 'j' to the deque.
            deq.addLast(j++); // Increment j after adding it.
        }

        // If min is still Integer.MAX_VALUE, it means no subarray with sum >= k was found.
        // Otherwise, return the minimum length found.
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
```

## Interview Tips
*   **Explain Prefix Sums First**: Clearly articulate how prefix sums simplify the subarray sum condition.
*   **Walk Through the Deque Logic**: Emphasize *why* elements are removed from both ends of the deque. Use a small example to illustrate the monotonic property and candidate elimination.
*   **Handle Edge Cases**: Discuss what happens with empty arrays, all negative numbers, or when no solution exists.
*   **Consider Constraints**: Ask about potential constraints on `k` and array elements (e.g., if they can be negative). This problem is specifically designed to handle negative numbers.

## Revision Checklist
- [ ] Understand the problem statement and goal.
- [ ] Implement prefix sum calculation correctly.
- [ ] Initialize the deque and prefix sum array.
- [ ] Implement the first `while` loop for finding valid subarrays and updating `min`.
- [ ] Implement the second `while` loop for maintaining deque monotonicity.
- [ ] Correctly add the current index to the deque.
- [ ] Handle the case where no solution is found (return -1).
- [ ] Analyze time and space complexity.

## Similar Problems
*   LeetCode 209: Minimum Size Subarray Sum
*   LeetCode 862: Shortest Subarray with Sum at Least K (This problem)
*   LeetCode 713: Subarray Product Less Than K
*   LeetCode 560: Subarray Sum Equals K

## Tags
`Array` `Deque` `Prefix Sum` `Monotonic Stack`
