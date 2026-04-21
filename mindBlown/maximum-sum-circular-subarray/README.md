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
// That's why:
// circularMax=totalSum−(−invertedMax)
// circularMax=totalSum+invertedMax
```

---

---
## Quick Revision
This problem asks for the maximum sum of a contiguous subarray in a circular array.
It can be solved by considering two cases: a normal subarray and a circular subarray.

## Intuition
The maximum sum subarray can either be a "normal" subarray (not wrapping around) or a "circular" subarray (wrapping around the ends).
For a normal subarray, Kadane's algorithm directly applies.
For a circular subarray, the maximum sum is achieved by taking the `total sum` of the array and subtracting the `minimum sum subarray`. The minimum sum subarray can be found by inverting all numbers in the array and then applying Kadane's algorithm to find the maximum sum of this inverted array. The negative of this maximum sum will be the minimum sum subarray of the original array.

## Algorithm
1.  **Calculate the maximum sum of a non-circular subarray:** Use Kadane's algorithm on the original array. Let this be `normalMax`.
2.  **Calculate the total sum of the array:** Iterate through the array and sum all its elements. Let this be `totalSum`.
3.  **Invert the array:** For each element `nums[i]`, replace it with `-nums[i]`.
4.  **Calculate the maximum sum of the inverted array:** Use Kadane's algorithm on the inverted array. Let this be `invertedMax`. This `invertedMax` is equivalent to the negative of the minimum sum subarray of the original array.
5.  **Calculate the maximum sum of a circular subarray:** This is `totalSum - (-invertedMax)`, which simplifies to `totalSum + invertedMax`. Let this be `circularMax`.
6.  **Handle the edge case where all numbers are negative:** If `circularMax` is 0, it implies that the minimum subarray sum was equal to the total sum (meaning the minimum subarray was the entire array). In this scenario, the maximum sum must be `normalMax` (which will be the largest single negative number).
7.  **Return the maximum of `normalMax` and `circularMax`:** The overall maximum sum is the greater of the two cases.

## Concept to Remember
*   **Kadane's Algorithm:** An efficient dynamic programming approach to find the maximum sum of a contiguous subarray in a linear array.
*   **Circular Array Properties:** Understanding how a subarray can wrap around the ends.
*   **Inversion Trick:** The relationship between the minimum subarray sum of an array and the maximum subarray sum of its inverted version.

## Common Mistakes
*   **Forgetting the all-negative numbers edge case:** If all numbers are negative, `totalSum + invertedMax` might incorrectly evaluate to 0 or a positive number if not handled. The correct answer should be the largest negative number.
*   **Incorrectly calculating the circular sum:** Misunderstanding that `circularMax = totalSum - minSubarraySum` and not correctly deriving `minSubarraySum` from the inverted array.
*   **Not handling empty arrays (though LeetCode constraints usually prevent this):** While not an issue for this specific problem's constraints, it's a general consideration for array problems.
*   **Modifying the original array without restoring it (if needed elsewhere):** In this solution, the array is modified, but it's not used again in its original form after inversion.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the array a constant number of times (once for `normalMax`, once for `totalSum` and inversion, and once for `invertedMax`). Kadane's algorithm itself is O(N).
- Space: O(1) - reason: We only use a few extra variables to store sums and maximums. The array is modified in-place, not requiring additional space proportional to N.

## Commented Code
```java
class Solution {
    // Main function to find the maximum sum circular subarray
    public int maxSubarraySumCircular(int[] nums) {
        // Calculate the maximum sum of a normal (non-circular) subarray using Kadane's algorithm
        int normalMax = kadane(nums); // This finds the max sum subarray that doesn't wrap around.

        // Initialize totalSum to calculate the sum of all elements in the array
        int totalSum = 0;
        // Iterate through the array to calculate totalSum and invert elements for the circular case
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i]; // Add current element to totalSum
            nums[i] = -nums[i]; // Invert the current element. This is done to find the minimum subarray sum later.
        }

        // Calculate the maximum sum of the inverted array using Kadane's algorithm.
        // This 'invertedMax' is actually the negative of the minimum subarray sum in the original array.
        int invertedMax = kadane(nums);
        // The maximum sum of a circular subarray is the total sum minus the minimum subarray sum.
        // Since invertedMax = -minSubarraySum, this becomes totalSum + invertedMax.
        int circularMax = totalSum + invertedMax;

        // Edge case: If circularMax is 0, it means all numbers were negative, and the minimum subarray was the entire array.
        // In this case, the maximum sum is simply the normalMax (which will be the largest single negative number).
        if (circularMax == 0) {
            return normalMax; // Return the result from the non-circular case.
        }

        // The final answer is the maximum of the normal subarray sum and the circular subarray sum.
        return Math.max(normalMax, circularMax);
    }

    // Helper function implementing Kadane's algorithm to find the maximum sum of a contiguous subarray
    private int kadane(int[] nums) {
        // Initialize max to the first element, as it's the maximum sum found so far.
        int max = nums[0];
        // Initialize sum to the first element, representing the current subarray sum ending at the current position.
        int sum = nums[0];
        // Iterate through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // Decide whether to extend the current subarray or start a new one at the current element.
            // If nums[i] is greater than sum + nums[i], it means starting a new subarray from nums[i] is better.
            sum = Math.max(nums[i], sum + nums[i]);
            // Update the overall maximum sum found so far if the current subarray sum is greater.
            max = Math.max(max, sum);
        }
        // Return the maximum sum found.
        return max;
    }
}
```

## Interview Tips
*   **Explain Kadane's Algorithm First:** Be prepared to explain Kadane's algorithm clearly, as it's the foundation for both parts of the solution.
*   **Break Down the Circular Case:** Clearly articulate the two possibilities for the maximum sum subarray (normal vs. circular) and how the inversion trick helps solve the circular case.
*   **Address the Edge Case:** Explicitly mention and explain the edge case where all numbers are negative and why `circularMax == 0` needs special handling.
*   **Think Aloud:** Walk through an example, especially one that involves wrapping around, to demonstrate your thought process.

## Revision Checklist
- [ ] Understand Kadane's algorithm.
- [ ] Recognize the two cases for circular subarrays.
- [ ] Understand how to find the minimum subarray sum using inversion.
- [ ] Implement the logic for both normal and circular maximum sums.
- [ ] Handle the edge case of all negative numbers.
- [ ] Compare the results from both cases.

## Similar Problems
*   Maximum Subarray (LeetCode 53)
*   Maximum Product Subarray (LeetCode 152)
*   Best Time to Buy and Sell Stock (LeetCode 121) - conceptually related to finding max difference/sum.

## Tags
`Array` `Dynamic Programming` `Kadane's Algorithm`
