# Maximum Sum Circular Subarray

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Divide and Conquer` `Dynamic Programming` `Queue` `Monotonic Queue`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution{
    public int maxSubarraySumCircular(int[] nums) {
    int normalMax = kadane(nums); // standard max subarray sum

    int totalSum = 0;
    for (int i = 0; i < nums.length; i++) {
        totalSum += nums[i];
        nums[i] = -nums[i]; // invert for min subarray
    }
    int invertedMax = kadane(nums); // max of inverted = -min subarray
    int circularMax = totalSum + invertedMax;
// normal array ka min subarray sum is the negative of inverted array ka max subarray sum
    if (circularMax == 0) return normalMax; // all numbers negative case
    return Math.max(normalMax, circularMax);
}

private int kadane(int[] nums) {
    int max = nums[0], sum = nums[0];
    for (int i = 1; i < nums.length; i++) {
        sum = Math.max(nums[i], sum + nums[i]);
        max = Math.max(max, sum);
    }
    return max;
}
}
// Trick with Inversion
// If you invert the array (nums[i] = -nums[i]) and run Kadane again:

// invertedMax = kadane(invertedNums)  
// This actually finds the maximum sum of the inverted array, which equals the negative of the minimum subarray sum in the original array.

// So:

// minSubarraySum=−invertedMax

// We know that
// circularMax=totalSum−minSubarraySum
// 
// circularMax=totalSum−(−invertedMax)
// circularMax=totalSum+invertedMax
```

---

---
## Quick Revision
Find the maximum sum of a contiguous subarray in a circular array. This is solved by considering both non-circular and circular cases.
## Intuition
The maximum sum subarray can either be a "normal" subarray (not wrapping around) or a "circular" subarray (wrapping around). For the circular case, the maximum sum is the total sum of the array minus the minimum sum subarray. This is because the elements *not* included in the minimum sum subarray form the maximum sum circular subarray. We can find the minimum sum subarray by inverting all numbers and finding the maximum sum subarray of the inverted array using Kadane's algorithm.
## Algorithm
1.  **Calculate the maximum sum of a non-circular subarray:** Use Kadane's algorithm on the original array. Let this be `normalMax`.
2.  **Calculate the total sum of the array:** Iterate through the array and sum all its elements. Let this be `totalSum`.
3.  **Invert the array:** For each element `nums[i]`, replace it with `-nums[i]`.
4.  **Calculate the maximum sum of the inverted subarray:** Use Kadane's algorithm on the inverted array. Let this be `invertedMax`. This `invertedMax` is the negative of the minimum sum subarray of the original array.
5.  **Calculate the maximum sum of a circular subarray:** This is `totalSum + invertedMax`. Let this be `circularMax`.
6.  **Handle the all-negative case:** If `circularMax` is 0, it implies all numbers are negative, and the `normalMax` (which would be the largest negative number) is the correct answer. This happens when the minimum subarray sum is equal to the total sum, meaning the entire array is the minimum subarray. In this scenario, `totalSum + invertedMax` would be `totalSum + (-totalSum) = 0`.
7.  **Return the maximum:** Return `max(normalMax, circularMax)`.
## Concept to Remember
*   **Kadane's Algorithm:** An efficient dynamic programming approach to find the maximum sum of a contiguous subarray in a 1D array.
*   **Inversion Trick for Minimum Subarray:** The minimum sum subarray of an array can be found by inverting all elements and then finding the maximum sum subarray of the inverted array.
*   **Circular Array Properties:** Understanding how to decompose a circular maximum sum problem into non-circular and wrapping cases.
## Common Mistakes
*   **Forgetting the all-negative case:** If all numbers are negative, the `circularMax` calculation (`totalSum + invertedMax`) might incorrectly return 0 if not handled. The actual answer should be the largest negative number (found by `normalMax`).
*   **Incorrectly calculating `circularMax`:** Misunderstanding that `totalSum - minSubarraySum` is the circular max, and then incorrectly deriving `minSubarraySum` from the inverted array.
*   **Modifying the original array without restoring it (if needed elsewhere):** Although not an issue in this specific solution as it's self-contained, in larger programs, modifying input arrays can have side effects.
*   **Edge cases:** Not considering arrays with a single element.
## Complexity Analysis
- Time: O(N) - reason: We iterate through the array a constant number of times (once for `normalMax`, once for `totalSum` and inversion, and once for `invertedMax`). Kadane's algorithm itself is O(N).
- Space: O(1) - reason: We only use a few extra variables to store sums and maximums. The inversion is done in-place.
## Commented Code
```java
class Solution {
    // Main function to find the maximum sum of a circular subarray.
    public int maxSubarraySumCircular(int[] nums) {
        // Calculate the maximum sum of a non-circular subarray using Kadane's algorithm.
        int normalMax = kadane(nums); // This handles the case where the max subarray does not wrap around.

        // Initialize totalSum to keep track of the sum of all elements in the array.
        int totalSum = 0;
        // Iterate through the array to calculate the total sum and invert each element.
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i]; // Add the current element to the total sum.
            nums[i] = -nums[i]; // Invert the current element. This is done to find the minimum subarray sum later.
        }
        // Calculate the maximum sum of the inverted array. This is equivalent to the negative of the minimum subarray sum of the original array.
        int invertedMax = kadane(nums); // This finds the max subarray sum of the inverted array.
        // The maximum sum of a circular subarray is the total sum minus the minimum subarray sum.
        // Since invertedMax = -minSubarraySum, then minSubarraySum = -invertedMax.
        // So, circularMax = totalSum - (-invertedMax) = totalSum + invertedMax.
        int circularMax = totalSum + invertedMax; // This calculates the potential maximum sum for a circular subarray.

        // Handle the edge case where all numbers are negative.
        // In this scenario, the minimum subarray sum is the total sum itself.
        // So, totalSum + invertedMax would be totalSum + (-totalSum) = 0.
        // If circularMax is 0, it means the entire array was the minimum subarray, and thus the maximum circular sum is not applicable in the usual sense.
        // The actual answer in this all-negative case is the largest negative number, which is captured by normalMax.
        if (circularMax == 0) {
            return normalMax; // Return the normal maximum sum if all numbers are negative.
        }
        // Return the maximum of the non-circular maximum sum and the circular maximum sum.
        return Math.max(normalMax, circularMax); // The overall maximum is either the normal max or the circular max.
    }

    // Helper function implementing Kadane's algorithm to find the maximum sum of a contiguous subarray.
    private int kadane(int[] nums) {
        // Initialize max to the first element, as it's the smallest possible subarray sum initially.
        int max = nums[0];
        // Initialize sum to the first element, representing the current subarray sum ending at the current position.
        int sum = nums[0];
        // Iterate through the array starting from the second element.
        for (int i = 1; i < nums.length; i++) {
            // Decide whether to extend the current subarray or start a new one at the current element.
            // If the current element is greater than the current sum plus the current element, it's better to start a new subarray from the current element.
            sum = Math.max(nums[i], sum + nums[i]);
            // Update the overall maximum sum found so far.
            max = Math.max(max, sum);
        }
        // Return the maximum subarray sum found.
        return max;
    }
}
```
## Interview Tips
1.  **Explain Kadane's Algorithm First:** Before diving into the circular aspect, ensure you can clearly explain Kadane's algorithm for the linear case. This builds confidence.
2.  **Break Down the Problem:** Clearly articulate the two cases: non-circular and circular. Explain *why* the `totalSum - minSubarraySum` approach works for the circular case.
3.  **Address the All-Negative Edge Case:** This is a common pitfall. Be prepared to explain how your solution handles it and why `normalMax` is the correct answer in that specific scenario.
4.  **Walk Through an Example:** Use a small example array (e.g., `[5, -3, 5]`) to demonstrate how `normalMax`, `totalSum`, `invertedMax`, and `circularMax` are calculated.
## Revision Checklist
- [ ] Understand Kadane's Algorithm for maximum subarray sum.
- [ ] Understand how to find the minimum subarray sum using Kadane's on an inverted array.
- [ ] Recognize the two cases for circular maximum subarray: non-wrapping and wrapping.
- [ ] Implement the `totalSum - minSubarraySum` logic for the wrapping case.
- [ ] Correctly handle the edge case where all numbers are negative.
- [ ] Compare the non-wrapping and wrapping maximums to find the overall maximum.
## Similar Problems
*   Maximum Subarray (LeetCode 53)
*   Maximum Product Subarray (LeetCode 152)
*   K-th Largest Element in an Array (LeetCode 215) - *Conceptually related to finding extremes, though different algorithm.*
## Tags
`Array` `Dynamic Programming` `Kadane's Algorithm`
