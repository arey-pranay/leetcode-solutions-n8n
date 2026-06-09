# Maximum Total Subarray Value I

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Greedy`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public long maxTotalValue(int[] nums, int k) {
        long min =Long.MAX_VALUE;
        long max =Long.MIN_VALUE;
        for(long i : nums){max = Math.max(max,i); min = Math.min(min,i);}
        return (max-min)*k;
    }
}
```

---

---
## Quick Revision
The problem asks for the maximum possible total value of a subarray of length `k`.
The solution involves finding the difference between the maximum and minimum elements in the array and multiplying it by `k`.

## Intuition
The "aha moment" comes from realizing that to maximize the sum of a subarray of a fixed length `k`, we want to pick `k` elements that have the largest possible difference between their maximum and minimum values. If we can choose *any* `k` elements, the maximum difference will always be achieved by picking the absolute maximum element in the entire array and the absolute minimum element in the entire array. The sum of these two elements, multiplied by `k` (since we can imagine picking the max `k` times or the min `k` times, or a mix, but the difference is what matters for the *total value* which is defined as (max_in_subarray - min_in_subarray) * k), will give us the maximum possible total value. The problem statement implies that we can select any `k` elements to form a "subarray" in a conceptual sense, not necessarily contiguous. If it were contiguous, the problem would be significantly harder. Given the simplicity of the provided solution, it strongly suggests the interpretation of selecting any `k` elements.

## Algorithm
1. Initialize two variables, `min_val` to `Long.MAX_VALUE` and `max_val` to `Long.MIN_VALUE`. These will store the minimum and maximum values found in the input array `nums`.
2. Iterate through each element `num` in the input array `nums`.
3. For each `num`, update `max_val` to be the maximum of its current value and `num`.
4. For each `num`, update `min_val` to be the minimum of its current value and `num`.
5. After iterating through all elements, calculate the difference between `max_val` and `min_val`.
6. Multiply this difference by `k`.
7. Return the result.

## Concept to Remember
*   Finding Minimum and Maximum: Efficiently determining the smallest and largest elements in a collection.
*   Greedy Approach: Making locally optimal choices (picking the absolute max and min) to achieve a globally optimal solution.
*   Understanding Problem Constraints/Definitions: Carefully interpreting what "subarray value" and "subarray" mean in the context of the problem.

## Common Mistakes
*   Assuming contiguous subarrays: If the problem intended contiguous subarrays, the solution would be incorrect. The provided solution implies non-contiguous selection.
*   Integer Overflow: Using `int` for `min_val` and `max_val` when the input numbers could be large, leading to incorrect comparisons or results. Using `long` is safer.
*   Incorrectly calculating the total value: Misinterpreting how `k` affects the total value (e.g., summing `k` times instead of using the difference).

## Complexity Analysis
- Time: O(n) - The algorithm iterates through the input array `nums` once to find the minimum and maximum elements.
- Space: O(1) - The algorithm uses a constant amount of extra space for variables `min_val`, `max_val`, and loop counters, regardless of the input array size.

## Commented Code
```java
class Solution {
    public long maxTotalValue(int[] nums, int k) {
        // Initialize min to the largest possible long value to ensure any number from nums will be smaller.
        long min = Long.MAX_VALUE;
        // Initialize max to the smallest possible long value to ensure any number from nums will be larger.
        long max = Long.MIN_VALUE;

        // Iterate through each number in the input array nums.
        // Using long for 'i' to avoid potential issues if nums contains values close to Integer.MAX_VALUE/MIN_VALUE
        // and to ensure consistent type for Math.max/min operations with 'max' and 'min'.
        for (long i : nums) {
            // Update 'max' if the current number 'i' is greater than the current maximum.
            max = Math.max(max, i);
            // Update 'min' if the current number 'i' is smaller than the current minimum.
            min = Math.min(min, i);
        }

        // The maximum total value is achieved by taking the difference between the overall maximum and minimum
        // elements in the array, and multiplying by k. This assumes we can pick any k elements.
        return (max - min) * k;
    }
}
```

## Interview Tips
*   Clarify the definition of "subarray" and "total value": Ask the interviewer if the subarray must be contiguous or if any `k` elements can be chosen. This is crucial for this problem.
*   Explain your intuition clearly: Articulate why picking the global maximum and minimum elements is the optimal strategy for maximizing the difference.
*   Consider edge cases: Think about arrays with all the same elements, arrays with only two elements, or `k=1`.
*   Discuss data types: Mention the importance of using `long` to prevent potential integer overflow issues.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the core task: maximizing (max_in_subarray - min_in_subarray) * k.
- [ ] Determine if subarrays are contiguous or non-contiguous.
- [ ] Implement finding the global minimum and maximum.
- [ ] Calculate the final result using the global min/max and k.
- [ ] Analyze time and space complexity.
- [ ] Test with various edge cases.

## Similar Problems
*   Maximum Subarray (LeetCode 53) - For contiguous subarrays.
*   Find Minimum in Rotated Sorted Array (LeetCode 153)
*   Maximum Product Subarray (LeetCode 152)

## Tags
`Array` `Math`
