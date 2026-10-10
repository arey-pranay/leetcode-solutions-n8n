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
This problem asks to minimize the sum of squared differences between two arrays by reducing elements using a total budget `k`.
We solve this by greedily reducing the largest differences first, using a frequency map to efficiently track and update differences.

## Intuition
The core idea is that to minimize the sum of squares, we should prioritize reducing larger differences. If we have a difference of 5 and a difference of 1, reducing the 5 to 4 (a change of 1) reduces the sum of squares by $5^2 - 4^2 = 25 - 16 = 9$. Reducing the 1 to 0 (a change of 1) reduces the sum of squares by $1^2 - 0^2 = 1 - 0 = 1$. This clearly shows that larger differences yield a greater reduction in the sum of squares for the same amount of reduction.

## Algorithm
1. Calculate the absolute difference between `nums1[i]` and `nums2[i]` for all `i`. Store these differences.
2. Calculate the total sum of squared differences initially.
3. Calculate the total sum of all absolute differences.
4. If the total available reduction `k` (sum of `k1` and `k2`) is greater than or equal to the total sum of absolute differences, we can make all differences zero, so return 0.
5. Create a frequency map (an array `freq`) to store the count of each absolute difference value. The size of this array should be `maxDiff + 1`, where `maxDiff` is the largest absolute difference found.
6. Iterate through the `freq` array from the largest difference (`maxDiff`) down to 1.
7. For each difference `d` with a non-zero frequency:
    a. Determine how many units we can reduce from this difference. This is the minimum of the remaining `k` and the count of elements with difference `d` (`freq[d]`). Let this be `move`.
    b. Update the total sum of squared differences:
        - Subtract the contribution of `freq[d]` elements with difference `d` ($freq[d] * d^2$).
        - Add the contribution of `freq[d] - move` elements with difference `d` ($(freq[d] - move) * d^2$).
        - Add the contribution of `move` elements reduced by 1, now having difference `d-1` ($move * (d-1)^2$).
    c. Update the frequency map: decrease `freq[d]` by `move` and increase `freq[d-1]` by `move`.
    d. Decrease the remaining `k` by `move`.
8. If `k` becomes 0 during the iteration, stop and return the current sum.
9. After iterating through all possible differences, return the final sum.

## Concept to Remember
*   Greedy Approach: Making locally optimal choices (reducing largest differences first) leads to a globally optimal solution.
*   Frequency Mapping: Efficiently counting occurrences of values to process them in groups.
*   Sum of Squares Minimization: Understanding how changes in values affect the sum of their squares.
*   Difference Reduction: The impact of reducing a difference `d` by 1 on the sum of squares.

## Common Mistakes
*   Not handling the case where `k` is large enough to make all differences zero.
*   Incorrectly updating the sum of squares when reducing differences.
*   Inefficiently processing differences (e.g., iterating through each element `k` times instead of using a frequency map).
*   Integer overflow issues when calculating sums of squares or large `k` values.
*   Off-by-one errors when updating frequencies or calculating new differences.

## Complexity Analysis
- Time: O(N + M), where N is the length of the arrays and M is the maximum possible difference. The initial pass to calculate differences and frequencies takes O(N). The loop to reduce differences iterates up to `maxDiff` times, and each iteration is O(1) due to the frequency map. In the worst case, `maxDiff` can be up to 10^5, so it's O(N + maxDiff).
- Space: O(M), where M is the maximum possible difference. This is for the `freq` array.

## Commented Code
```java
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length; // Get the number of elements in the arrays.
        long k = (long) k1 + k2; // Combine the two reduction budgets into a single long variable to avoid overflow.

        int maxDiff = 0; // Variable to store the maximum absolute difference found.
        long totalDiff = 0; // Variable to store the sum of all absolute differences.
        long sum = 0; // Variable to store the initial sum of squared differences.

        int[] diff = new int[n]; // Array to store the absolute differences between corresponding elements.

        // First pass: Calculate initial differences, max difference, total difference, and sum of squares.
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]); // Calculate the absolute difference for the current pair.
            maxDiff = Math.max(maxDiff, diff[i]); // Update maxDiff if the current difference is larger.
            totalDiff += diff[i]; // Add the current difference to the total sum of differences.
            sum += (long) diff[i] * diff[i]; // Add the square of the current difference to the total sum of squares.
        }

        // If the total reduction budget is enough to make all differences zero, return 0.
        if (k >= totalDiff) return 0;

        // Create a frequency map (array) to count occurrences of each difference value.
        // The size is maxDiff + 1 because differences can range from 0 to maxDiff.
        long[] freq = new long[maxDiff + 1];

        // Populate the frequency map.
        for (int d : diff) {
            freq[d]++; // Increment the count for the difference 'd'.
        }

        // Iterate from the largest possible difference down to 1.
        // We greedily reduce the largest differences first.
        for (int d = maxDiff; d > 0 && k > 0; d--) {
            // If there are no elements with the current difference 'd', skip to the next.
            if (freq[d] == 0) continue;

            long count = freq[d]; // Number of elements with the current difference 'd'.
            // Determine how many units we can move from difference 'd'.
            // This is limited by the remaining budget 'k' and the count of elements with difference 'd'.
            long move = Math.min(k, count);

            // Update the sum of squares:
            // 1. Subtract the original contribution of 'count' elements with difference 'd'.
            sum -= count * d * d;
            // 2. Add the contribution of the remaining 'count - move' elements that still have difference 'd'.
            sum += (count - move) * d * d;
            // 3. Add the contribution of the 'move' elements that are reduced by 1, now having difference 'd-1'.
            sum += move * (long) (d - 1) * (d - 1);

            // Update the frequency map:
            freq[d] -= move; // Decrease the count of elements with difference 'd'.
            freq[d - 1] += move; // Increase the count of elements with difference 'd-1'.
            k -= move; // Decrease the remaining reduction budget.
        }

        // Return the minimized sum of squared differences.
        return sum;
    }
}
```

## Interview Tips
*   Explain the greedy strategy clearly: why reducing larger differences is always better.
*   Discuss the use of the frequency map and why it's more efficient than iterating `k` times.
*   Be prepared to discuss edge cases like `k` being very large or all initial differences being zero.
*   Mention potential integer overflow issues and how `long` is used to mitigate them.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the greedy approach for minimizing sum of squares.
- [ ] Implement difference calculation and initial sum.
- [ ] Handle the edge case where `k` is sufficient to make all differences zero.
- [ ] Design and implement the frequency map.
- [ ] Correctly iterate from `maxDiff` down to 1.
- [ ] Accurately update the sum of squares during reduction.
- [ ] Correctly update the frequency map.
- [ ] Manage the remaining `k` budget.
- [ ] Consider and handle potential integer overflows.

## Similar Problems
*   [1846. Maximum Element After Decreasing and Rearranging](https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/) (Similar greedy idea with array manipulation)
*   [1792. Maximum Average Pass Ratio](https://leetcode.com/problems/maximum-average-pass-ratio/) (Greedy approach based on marginal gain)
*   [452. Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/) (Greedy interval scheduling)

## Tags
`Array` `Greedy` `Hash Map` `Sorting` `Binary Search`
