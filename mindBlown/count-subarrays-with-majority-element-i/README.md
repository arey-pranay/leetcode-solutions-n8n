# Count Subarrays With Majority Element I

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Divide and Conquer` `Segment Tree` `Merge Sort` `Counting` `Prefix Sum`  
**Time:** O(n^2)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                cnt += (nums[j] == target ? 1 : -1);
                if (cnt > 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

---

---
## Quick Revision
This problem asks us to count subarrays where a specific `target` element appears more than half the time.
We solve this by iterating through all possible subarrays and maintaining a count of the `target` versus other elements.

## Intuition
The core idea is to transform the problem of "majority element" into a "prefix sum" or "balance" problem. If we assign a value of +1 to the `target` element and -1 to all other elements, a subarray has `target` as a majority element if and only if the sum of these values within that subarray is strictly positive. This is because if `target` appears `k` times and other elements appear `m` times, the sum is `k - m`. For `target` to be a majority, `k > m`, which implies `k - m > 0`.

## Algorithm
1. Initialize `ans` to 0. This variable will store the total count of valid subarrays.
2. Iterate through the array `nums` with an outer loop using index `i` from 0 to `n-1` (where `n` is the length of `nums`). This `i` represents the starting index of a subarray.
3. For each `i`, initialize a `cnt` variable to 0. This `cnt` will track the "balance" of `target` elements within the current subarray starting at `i`.
4. Start an inner loop with index `j` from `i` to `n-1`. This `j` represents the ending index of the current subarray `nums[i...j]`.
5. Inside the inner loop, update `cnt`: if `nums[j]` is equal to `target`, increment `cnt` by 1; otherwise, decrement `cnt` by 1.
6. After updating `cnt`, check if `cnt` is strictly greater than 0. If it is, it means the subarray `nums[i...j]` has `target` as its majority element, so increment `ans` by 1.
7. After the inner loop finishes (meaning all subarrays starting at `i` have been checked), the outer loop continues to the next `i`.
8. Once the outer loop finishes, return the final `ans`.

## Concept to Remember
*   **Subarray Enumeration:** Understanding how to systematically iterate through all possible contiguous subarrays.
*   **Prefix Sum / Balance Tracking:** Transforming a counting problem into a sum-based problem where the sign of the sum indicates a property.
*   **Majority Element Condition:** Recognizing that `count(target) > count(others)` is equivalent to `sum(values) > 0` when `target` is +1 and others are -1.

## Common Mistakes
*   **Off-by-one errors in loops:** Incorrectly defining the start or end conditions for `i` and `j`.
*   **Incorrect majority condition:** Using `cnt >= 0` instead of `cnt > 0`. A count of 0 means an equal number of `target` and other elements, not a majority.
*   **Not resetting `cnt`:** Forgetting to reset `cnt` to 0 for each new starting index `i`.
*   **Integer overflow (less likely here but good to consider):** If the array were extremely large and `cnt` could grow very big, though not an issue with typical LeetCode constraints for this problem.

## Complexity Analysis
- Time: O(n^2) - reason: We have two nested loops. The outer loop runs `n` times, and the inner loop runs up to `n` times for each iteration of the outer loop.
- Space: O(1) - reason: We are only using a few extra variables (`n`, `ans`, `cnt`, `i`, `j`) which take constant extra space.

## Commented Code
```java
class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length; // Get the length of the input array.
        int ans = 0; // Initialize the counter for valid subarrays to 0.

        // Outer loop iterates through all possible starting indices 'i' of a subarray.
        for (int i = 0; i < n; ++i) {
            int cnt = 0; // Initialize a counter for the current subarray starting at 'i'.
                         // This counter tracks the balance: +1 for target, -1 for others.

            // Inner loop iterates through all possible ending indices 'j' of a subarray,
            // starting from the current 'i'. This defines the subarray nums[i...j].
            for (int j = i; j < n; ++j) {
                // Update the counter based on the current element nums[j].
                // If nums[j] is the target, increment cnt. Otherwise, decrement cnt.
                cnt += (nums[j] == target ? 1 : -1);

                // Check if the current subarray nums[i...j] has 'target' as a majority element.
                // This is true if the balance 'cnt' is strictly positive.
                if (cnt > 0) {
                    ++ans; // If it's a majority, increment the total count of valid subarrays.
                }
            }
        }
        return ans; // Return the total count of subarrays where 'target' is the majority element.
    }
}
```

## Interview Tips
1.  **Explain the Transformation:** Clearly articulate how you convert the "majority element" condition into a "positive sum" condition by assigning +1 and -1. This shows a good understanding of problem manipulation.
2.  **Walk Through an Example:** Use a small example array (e.g., `[1, 2, 1, 1, 3]`, `target = 1`) and trace the loops and `cnt` updates to demonstrate your algorithm's logic.
3.  **Discuss Edge Cases:** Mention what happens with empty arrays (though constraints usually prevent this), arrays with only the target, or arrays with no target.
4.  **Consider Optimizations (if asked):** While O(n^2) is the straightforward solution, be prepared to discuss if there's a more efficient approach (e.g., using prefix sums and a hash map to count occurrences of `balance - target_balance` if the problem was slightly different, or if the target was not fixed). For this specific problem, O(n^2) is often expected.

## Revision Checklist
- [ ] Understand the problem statement: count subarrays where `target` is majority.
- [ ] Recognize the `+1/-1` transformation for majority.
- [ ] Implement nested loops for subarray enumeration.
- [ ] Correctly update the `cnt` balance.
- [ ] Apply the `cnt > 0` condition for majority.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the intuition and algorithm clearly.

## Similar Problems
*   LeetCode 560: Subarray Sum Equals K
*   LeetCode 974: Subarray Sums Divisible by K
*   LeetCode 1124: Longest Well-Performing Interval (similar balance concept)

## Tags
`Array` `Hash Map`
