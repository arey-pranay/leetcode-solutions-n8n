# Minimum Sum Of Squared Difference

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Greedy` `Sorting` `Heap (Priority Queue)`  
**Time:** O(N + M)  
**Space:** O(M)

---

## Solution (java)

```java

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;

        int maxDiff = 0;
        long totalDiff = 0;
        long sum = 0;

        int[] diff = new int[n];

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
            totalDiff += diff[i];
            sum += (long) diff[i] * diff[i];
        }

        if (k >= totalDiff) return 0;

        long[] freq = new long[maxDiff + 1];

        for (int d : diff) {
            freq[d]++;
        }

        for (int d = maxDiff; d > 0 && k > 0; d--) {
            if (freq[d] == 0) continue;

            long count = freq[d];
            long move = Math.min(k, count);

            // Move one unit from the largest differences.
            sum -= count * d * d;
            sum += (count - move) * d * d;
            sum += move * (long) (d - 1) * (d - 1);

            freq[d] -= move;
            freq[d - 1] += move;
            k -= move;
        }

        return sum;
    }
}


```

---

---
## Quick Revision
This problem asks to minimize the sum of squared differences between two arrays by reducing elements using a total budget `k`. We achieve this by greedily reducing the largest differences first.

## Intuition
The core idea is that to minimize the sum of squares, we should prioritize reducing larger numbers. If we have a difference of 5 and a difference of 1, reducing the 5 to 4 (a change of 1) reduces the sum of squares by $5^2 - 4^2 = 25 - 16 = 9$. Reducing the 1 to 0 (a change of 1) reduces the sum of squares by $1^2 - 0^2 = 1 - 0 = 1$. This clearly shows that larger differences yield a greater reduction in the sum of squares for the same unit of change. Therefore, a greedy approach of reducing the largest differences first is optimal.

## Algorithm
1. Calculate the absolute difference for each pair of elements in `nums1` and `nums2`. Store these differences.
2. Calculate the total sum of squared differences initially.
3. Calculate the total sum of all absolute differences.
4. If the total budget `k` is greater than or equal to the total sum of absolute differences, we can make all differences zero, so return 0.
5. Create a frequency map (or an array if the maximum difference is manageable) to store the count of each absolute difference.
6. Iterate from the maximum possible difference down to 1.
7. For each difference `d`, if there are elements with this difference:
    a. Determine how many units we can reduce from this difference. This is the minimum of the remaining budget `k` and the count of elements with difference `d`.
    b. Update the sum of squared differences: subtract the contribution of `count` elements with difference `d`, add the contribution of `count - move` elements with difference `d`, and add the contribution of `move` elements with difference `d - 1`.
    c. Update the frequency map: decrease the count for difference `d` by `move` and increase the count for difference `d - 1` by `move`.
    d. Decrease the remaining budget `k` by `move`.
8. If `k` becomes 0, stop the process.
9. Return the final sum of squared differences.

## Concept to Remember
*   **Greedy Algorithms:** Making locally optimal choices at each step to achieve a globally optimal solution.
*   **Sum of Squares Minimization:** The rate of decrease in the sum of squares is higher for larger numbers when a fixed amount is subtracted.
*   **Frequency Counting:** Efficiently tracking the occurrences of different values.
*   **Difference Array/Prefix Sums (Implicitly):** While not a direct prefix sum, the frequency array and the iterative reduction from max difference to min difference effectively simulate a process similar to how prefix sums can be used to calculate range sums or update ranges.

## Common Mistakes
*   **Integer Overflow:** The sum of squared differences can become very large, requiring `long` data type.
*   **Incorrect Greedy Choice:** Not realizing that reducing the largest differences first is the optimal strategy.
*   **Inefficient Frequency Tracking:** Using a `HashMap` when an array is more efficient if the maximum difference is bounded.
*   **Off-by-One Errors:** In calculating the new sum of squares after reduction or in updating the frequency counts.
*   **Not Handling `k >= totalDiff`:** Failing to return 0 when all differences can be eliminated.

## Complexity Analysis
*   **Time:** O(N + M), where N is the number of elements in the arrays and M is the maximum possible difference. The initial pass to calculate differences and frequencies takes O(N). The loop to reduce differences iterates up to M times. If M is proportional to the maximum value in the input arrays, this could be O(N + max(nums)). In the worst case, M can be large, but if we consider the maximum possible difference as a constraint, it's O(N + max_diff).
*   **Space:** O(M), where M is the maximum possible difference. This is for the frequency array. If `maxDiff` is bounded by the input values, it's O(max(nums)).

## Commented Code
```java
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length; // Get the number of elements in the arrays.
        long k = (long) k1 + k2; // Combine the two reduction budgets into a single long variable to avoid overflow.

        int maxDiff = 0; // Initialize a variable to track the maximum absolute difference found.
        long totalDiff = 0; // Initialize a variable to store the sum of all absolute differences.
        long sum = 0; // Initialize a variable to store the sum of squared differences.

        int[] diff = new int[n]; // Create an array to store the absolute differences between corresponding elements.

        // First pass: Calculate all absolute differences, find the maximum difference, and compute the initial sum of squares and total differences.
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]); // Calculate the absolute difference for the current pair.
            maxDiff = Math.max(maxDiff, diff[i]); // Update maxDiff if the current difference is larger.
            totalDiff += diff[i]; // Add the current difference to the total sum of differences.
            sum += (long) diff[i] * diff[i]; // Add the square of the current difference to the total sum of squares. Use long to prevent overflow.
        }

        // If the total budget k is enough to make all differences zero, return 0.
        if (k >= totalDiff) return 0;

        // Create a frequency array to count occurrences of each difference value.
        // The size is maxDiff + 1 because differences can range from 0 to maxDiff.
        long[] freq = new long[maxDiff + 1];

        // Populate the frequency array.
        for (int d : diff) {
            freq[d]++; // Increment the count for the difference 'd'.
        }

        // Second pass: Greedily reduce the largest differences first.
        // Iterate from the maximum difference down to 1.
        for (int d = maxDiff; d > 0 && k > 0; d--) {
            // If there are no elements with the current difference 'd', skip to the next smaller difference.
            if (freq[d] == 0) continue;

            long count = freq[d]; // Get the number of elements that have the current difference 'd'.
            // Determine how many units we can reduce from this difference 'd'.
            // This is limited by the remaining budget 'k' and the number of elements 'count' with this difference.
            long move = Math.min(k, count);

            // Update the sum of squared differences.
            // We are reducing 'move' elements from difference 'd' to 'd-1'.
            // For the 'count' elements that originally had difference 'd':
            // 1. Subtract the original squared contribution of all 'count' elements: count * d * d
            // 2. Add back the squared contribution of the remaining (count - move) elements that still have difference 'd': (count - move) * d * d
            // 3. Add the squared contribution of the 'move' elements that are now reduced to difference 'd-1': move * (d-1) * (d-1)
            // This can be simplified:
            // sum -= count * d * d; // Remove original contribution of all 'count' elements
            // sum += (count - move) * d * d; // Add back contribution of elements that remain at 'd'
            // sum += move * (long) (d - 1) * (d - 1); // Add contribution of elements reduced to 'd-1'

            // A more direct way to update the sum:
            // The change in sum is: (count - move) * d^2 + move * (d-1)^2 - count * d^2
            // = count * d^2 - move * d^2 + move * (d^2 - 2d + 1) - count * d^2
            // = -move * d^2 + move * d^2 - 2*move*d + move
            // = move * (1 - 2d)
            // So, sum += move * (1 - 2 * d); This is incorrect. Let's stick to the explicit calculation.

            // The provided code's update logic is:
            // sum -= count * d * d; // Remove the squared value of all 'count' elements with difference 'd'.
            // sum += (count - move) * d * d; // Add back the squared value for the elements that *remain* at difference 'd'.
            // sum += move * (long) (d - 1) * (d - 1); // Add the squared value for the 'move' elements that are now reduced to difference 'd-1'.
            // This logic is correct. It effectively subtracts 'move' instances of d^2 and adds 'move' instances of (d-1)^2.
            // The net change for 'move' elements is: move * (d-1)^2 - move * d^2 = move * ((d-1)^2 - d^2) = move * (d^2 - 2d + 1 - d^2) = move * (1 - 2d).
            // So, sum = sum - move * d*d + move * (d-1)*(d-1) is the correct update.
            // The code's way of doing it is:
            // sum = sum - (count * d * d) + ((count - move) * d * d) + (move * (d-1) * (d-1))
            // sum = sum - count*d*d + count*d*d - move*d*d + move*(d-1)*(d-1)
            // sum = sum - move*d*d + move*(d-1)*(d-1)
            // This is correct.

            sum -= count * d * d; // Remove the squared contribution of all 'count' elements with difference 'd'.
            sum += (count - move) * d * d; // Add back the squared contribution for the elements that *remain* at difference 'd'.
            sum += move * (long) (d - 1) * (d - 1); // Add the squared contribution for the 'move' elements that are now reduced to difference 'd-1'.

            // Update the frequency counts.
            freq[d] -= move; // Decrease the count of elements with difference 'd'.
            freq[d - 1] += move; // Increase the count of elements with difference 'd-1' (as 'move' elements were reduced to this difference).
            k -= move; // Decrease the remaining budget by the number of units moved.
        }

        // Return the minimized sum of squared differences.
        return sum;
    }
}
```

## Interview Tips
1.  **Explain the Greedy Choice:** Clearly articulate *why* reducing the largest differences first is optimal. Use a small example to illustrate the concept of diminishing returns for smaller numbers.
2.  **Discuss Edge Cases:** Mention what happens if `k` is very large (all differences can be zeroed out) or if `k` is zero. Also, consider arrays of size 1.
3.  **Data Type Considerations:** Emphasize the need for `long` for sums and intermediate calculations to prevent integer overflow, especially with squared values.
4.  **Frequency Array vs. HashMap:** Be prepared to discuss the trade-offs. If the maximum difference is bounded and not excessively large, an array is more efficient. If differences can be sparse and very large, a `HashMap` might be considered, but it would likely lead to a worse time complexity for the reduction phase.

## Revision Checklist
- [ ] Understand the problem statement: minimize sum of squares by reducing elements.
- [ ] Identify the greedy strategy: reduce largest differences first.
- [ ] Implement difference calculation and initial sum.
- [ ] Handle the `k >= totalDiff` edge case.
- [ ] Use a frequency map/array for efficient counting.
- [ ] Implement the greedy reduction loop from max difference downwards.
- [ ] Correctly update the sum of squares during reduction.
- [ ] Correctly update frequency counts.
- [ ] Use `long` for sums to prevent overflow.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [1846. Maximum Element After Decrementing and Rearranging](https://leetcode.com/problems/maximum-element-after-decrementing-and-rearranging/) (Greedy approach, sorting)
*   [1799. Maximize Score After N Operations](https://leetcode.com/problems/maximize-score-after-n-operations/) (Greedy, bitmasking - different domain but greedy thinking)
*   [164. Maximum Gap](https://leetcode.com/problems/maximum-gap/) (Finding max difference, but not with reduction)

## Tags
`Array` `Greedy` `Math` `Counting`
