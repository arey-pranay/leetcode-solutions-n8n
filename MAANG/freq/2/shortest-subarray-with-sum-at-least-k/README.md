# Shortest Subarray With Sum At Least K

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Queue` `Sliding Window` `Heap (Priority Queue)` `Prefix Sum` `Monotonic Queue`  
**Time:** O(N)  
**Space:** O(N)

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
The core idea is to efficiently find pairs of indices `(i, j)` such that `prefixSum[j] - prefixSum[i] >= k` and `j - i` is minimized.
A naive approach would be O(N^2) by checking all subarrays.
We can optimize this by observing that for a fixed `j`, we want to find the smallest `i < j` such that `prefixSum[i] <= prefixSum[j] - k`.
This suggests a sliding window or a data structure that can quickly query for such `i`.
The prefix sums can be negative, which breaks standard sliding window techniques.
A monotonic deque (specifically, a deque storing indices of increasing prefix sums) helps us maintain potential `i` candidates.
When we consider `prefixSum[j]`:
1. We check if any `prefixSum[i]` (where `i` is an index in the deque) satisfies `prefixSum[j] - prefixSum[i] >= k`. If so, we've found a valid subarray ending at `j`. Since the deque stores indices in increasing order of prefix sums, the *first* element (`deq.peekFirst()`) will give us the smallest `i` that satisfies the condition, thus minimizing `j - i`. We remove this `i` because any future `j'` will only result in a longer subarray with this `i`.
2. We maintain the monotonic property of the deque. If `prefixSum[j]` is less than or equal to the prefix sum at the last index in the deque (`preSum[deq.peekLast()]`), it means `prefixSum[j]` is a "better" candidate for a starting point `i` for future `j'`s because it's smaller (allowing more room for `prefixSum[j'] - prefixSum[i] >= k`) and it's at a later index (potentially leading to a shorter subarray). So, we remove such "worse" candidates from the end of the deque.
3. Finally, we add the current index `j` to the deque.

## Algorithm
1. Initialize `n` to the length of the input array `nums`.
2. Create a prefix sum array `preSum` of size `n + 1`. `preSum[0]` will be 0.
3. Calculate the prefix sums: `preSum[i+1] = preSum[i] + nums[i]` for `i` from 0 to `n-1`.
4. Initialize `min` to `Integer.MAX_VALUE` to store the minimum length of a valid subarray.
5. Initialize a `Deque` (e.g., `LinkedList`) called `deq` to store indices. This deque will maintain indices of `preSum` in increasing order of their values.
6. Add `0` to the `deq` (representing `preSum[0]`).
7. Iterate through the `preSum` array from index `j = 0` to `n` (inclusive).
    a. **Check for valid subarrays:** While the `deq` is not empty AND `preSum[j] - preSum[deq.peekFirst()] >= k`:
        i. This means the subarray from index `deq.peekFirst()` to `j-1` (inclusive) has a sum of at least `k`.
        ii. Update `min = Math.min(min, j - deq.peekFirst())`.
        iii. Remove the first element from `deq` (`deq.removeFirst()`) because it has served its purpose for the current `j` and any future `j'` will only yield a longer subarray with this `i`.
    b. **Maintain monotonic property:** While the `deq` is not empty AND `preSum[j] <= preSum[deq.peekLast()]`:
        i. The current `preSum[j]` is smaller than or equal to the prefix sum at the last index in the deque. This means `j` is a better candidate for a starting index `i` for future calculations because it's smaller (allowing more room for the sum to reach `k`) and it's at a later position (potentially leading to a shorter subarray).
        ii. Remove the last element from `deq` (`deq.removeLast()`).
    c. **Add current index:** Add the current index `j` to the end of the `deq` (`deq.addLast(j)`).
8. After the loop, if `min` is still `Integer.MAX_VALUE`, it means no subarray with sum at least `k` was found, so return -1.
9. Otherwise, return `min`.

## Concept to Remember
*   **Prefix Sums:** Efficiently calculating sums of contiguous subarrays. `sum(i, j) = preSum[j+1] - preSum[i]`.
*   **Monotonic Deque:** A double-ended queue where elements are maintained in a specific order (e.g., increasing or decreasing). Useful for sliding window problems where we need to find optimal elements within a window.
*   **Handling Negative Numbers:** Prefix sums can decrease, which invalidates standard sliding window approaches. The monotonic deque handles this by ensuring we always consider the most "optimal" starting points.
*   **Two Pointers (Implicit):** The deque effectively manages two pointers: `deq.peekFirst()` as a potential start `i` and the current loop index `j` as a potential end.

## Common Mistakes
*   **Incorrect Prefix Sum Calculation:** Off-by-one errors in array indexing or initialization.
*   **Not Handling Negative Numbers:** Assuming prefix sums are always non-decreasing, leading to incorrect deque maintenance.
*   **Deque Maintenance Logic:** Incorrectly adding or removing elements from the deque, breaking the monotonic property or failing to find the shortest subarray.
*   **Edge Cases:** Not considering cases where no subarray meets the condition (returning `min` directly instead of -1) or empty input arrays.
*   **Integer Overflow:** While not explicitly an issue with the provided code, for very large sums, `long` might be necessary for prefix sums.

## Complexity Analysis
- Time: O(N) - reason: Each element of `nums` is processed once to build the prefix sum array. Each index `j` from 0 to `n` is added to and removed from the deque at most once. Therefore, the overall time complexity is linear.
- Space: O(N) - reason: The `preSum` array takes O(N) space. The `deq` can store up to N+1 indices in the worst case.

## Commented Code
```java
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length; // Get the length of the input array.
        // Create a prefix sum array. preSum[i] will store the sum of nums[0]...nums[i-1].
        // Size n+1 to accommodate preSum[0] = 0 for empty prefix.
        int[] preSum = new int[n+1];
        int min = Integer.MAX_VALUE; // Initialize minimum length to a very large value.
        // Use a Deque (double-ended queue) to store indices.
        // This deque will store indices 'i' such that preSum[i] is monotonically increasing.
        Deque<Integer> deq = new LinkedList<>();
        preSum[0] = 0; // The prefix sum before any elements is 0.

        // Calculate prefix sums for the array.
        for(int i = 0; i < n; i++) {
            preSum[i+1] = preSum[i] + nums[i]; // Current prefix sum is previous sum + current element.
        }

        // Iterate through the prefix sum array. 'j' represents the end index of a potential subarray.
        // We iterate up to n (inclusive) because preSum has n+1 elements.
        int j = 0;
        while(j <= n){
            // Condition 1: Check if we can form a subarray with sum >= k ending at index j-1.
            // preSum[j] - preSum[deq.peekFirst()] represents the sum of the subarray from index deq.peekFirst() to j-1.
            while(!deq.isEmpty() && preSum[j] - preSum[deq.peekFirst()] >= k){
                // If the sum is at least k, we found a valid subarray.
                int i = deq.removeFirst(); // 'i' is the starting index of this valid subarray.
                // Update the minimum length found so far. The length is j - i.
                min = Math.min(min, j - i);
                // Remove 'i' from the deque because it has served its purpose.
                // Any future 'j'' will result in a longer subarray with this 'i'.
            }

            // Condition 2: Maintain the monotonic property of the deque.
            // If the current prefix sum preSum[j] is less than or equal to the prefix sum at the last index in the deque,
            // it means preSum[j] is a better candidate for a starting point 'i' for future calculations.
            // It's smaller (allowing more room for sum to reach k) and at a later index (potentially shorter subarray).
            while(!deq.isEmpty() && preSum[j] <= preSum[deq.peekLast()]) {
                deq.removeLast(); // Remove the last element as it's no longer optimal.
            }

            // Add the current index 'j' to the deque.
            deq.addLast(j++); // Increment j after adding it.
        }

        // If min is still its initial large value, it means no subarray with sum >= k was found.
        // Otherwise, return the minimum length found.
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
```

## Interview Tips
*   **Explain Prefix Sums First:** Start by explaining how prefix sums can help calculate subarray sums efficiently.
*   **Address Negative Numbers:** Explicitly mention why a standard sliding window might fail and how the deque addresses this.
*   **Walk Through Deque Logic:** Clearly explain the two `while` loops for deque maintenance: one for finding valid subarrays and one for maintaining monotonicity. Use a small example to illustrate.
*   **Consider Edge Cases:** Discuss what happens if `k` is very large, if all numbers are negative, or if no subarray satisfies the condition.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Implement prefix sum calculation correctly.
- [ ] Understand the role of the monotonic deque.
- [ ] Implement the two `while` loops for deque operations correctly.
- [ ] Handle the case where no valid subarray is found.
- [ ] Analyze time and space complexity.
- [ ] Practice with examples, including those with negative numbers.

## Similar Problems
*   [862] Shortest Subarray With Sum At Least K (This problem)
*   [209] Minimum Size Subarray Sum (Similar, but numbers are positive, allowing simpler sliding window)
*   [713] Subarray Product Less Than K (Uses sliding window, but with product instead of sum)
*   [560] Subarray Sum Equals K (Uses prefix sums and a hash map, for exact sum)

## Tags
`Array` `Deque` `Prefix Sum` `Monotonic Queue`
