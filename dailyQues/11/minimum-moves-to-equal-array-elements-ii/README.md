# Minimum Moves To Equal Array Elements Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Math` `Sorting`  
**Time:** O(N \log N)  
**Space:** O(\log N)

---

## Solution (java)

```java
class Solution {
    public int minMoves2(int[] nums) {
        int sum = 0;
        int n = nums.length;
        Arrays.sort(nums);
        int median = nums[n/2];
        int op = 0;
        for(int i =0;i<n;i++)op+=Math.abs(nums[i]-median);
        return op;
    }
}

```

---

---

## Quick Revision
Given an integer array, find the minimum number of increments/decrements by 1 required to make all elements equal.
Sort the array and calculate the absolute difference between every element and the median, then sum them up.

## Intuition
The "aha moment" comes from translating the problem into a known mathematical property. We want to find a target number $X$ that minimizes the sum of absolute differences $\sum |nums[i] - X|$. In statistics, the value that minimizes the sum of absolute deviations from all points in a set is the median. If we chose the mean instead, it would minimize the sum of *squared* differences, but for absolute differences, the median is optimal. Sorting the array allows us to easily find this median at index `n/2`.

## Algorithm
1. Get the length of the array `n`.
2. Sort the input array `nums` in ascending order.
3. Find the median element located at the middle index `nums[n / 2]`.
4. Initialize a variable `op` to 0 to keep track of total operations.
5. Iterate through each element in the sorted array:
   - Calculate the absolute difference between the current element and the median.
   - Add this difference to `op`.
6. Return the total operations `op`.

## Concept to Remember
- **Median Minimization Property:** The median minimizes the L1 norm (sum of absolute distances), whereas the mean minimizes the L2 norm (sum of squared distances).
- **Sorting for Order Statistics:** Sorting transforms an unordered search problem into an index-access problem, running in $O(N \log N)$.
- **Selection Algorithm Optimization:** While sorting takes $O(N \log N)$, finding the median can actually be done in $O(N)$ expected time using Quickselect, though sorting is usually preferred in interviews for simplicity.

## Common Mistakes
- Trying to minimize the sum of absolute differences using the *average* (mean) instead of the *median*.
- Forgetting to sort the array before picking the median element.
- Integer overflow concerns when summing differences (though standard constraints usually keep it within limits).
- Off-by-one errors when accessing the median index on arrays with even lengths (either middle index works for the minimum moves).

## Complexity Analysis
- Time: $O(N \log N)$ - dominated by the time required to sort the array of size $N$.
- Space: $O(\log N)$ or $O(N)$ - depending on the language's sorting algorithm implementation space overhead on the call stack or auxiliary array.

## Commented Code
```java
class Solution {
    public int minMoves2(int[] nums) {
        // Initialize sum variable (unused in this approach, can be removed)
        int sum = 0;
        // Store the total number of elements in the array
        int n = nums.length;
        // Sort the array to easily locate the median element
        Arrays.sort(nums);
        // Find the median element at the middle index
        int median = nums[n / 2];
        // Initialize a variable to accumulate the total operations (moves)
        int op = 0;
        // Loop through each element in the sorted array
        for (int i = 0; i < n; i++) {
            // Add the absolute difference between the current element and the median to operations
            op += Math.abs(nums[i] - median);
        }
        // Return the accumulated minimum operations
        return op;
    }
}
```

## Interview Tips
- State the mathematical intuition clearly: explain *why* the median is chosen over the mean early in the discussion.
- Mention the $O(N \log N)$ sorting approach first, as it is concise and robust.
- Be prepared to discuss if an $O(N)$ solution is possible using Quickselect to find the median.
- Walk through a small test case with both odd and even array lengths to prove correctness.

## Revision Checklist
- [ ] Understand why median minimizes absolute distance.
- [ ] Remember to sort the array before finding the middle element.
- [ ] Know how to compute absolute differences using `Math.abs()`.
- [ ] Be aware of the $O(N \log N)$ time complexity.
- [ ] Consider the $O(N)$ Quickselect alternative for optimal interviewer brownie points.

## Similar Problems
- [Minimum Moves to Equal Array Elements](https://leetcode.com/problems/minimum-moves-to-equal-array-elements/)
- [Best Meeting Point](https://leetcode.com/problems/best-meeting-point/)
- [Wiggle Sort II](https://leetcode.com/problems/wiggle-sort-ii/)

## Tags
`Array` `Math` `Sorting` `Greedy`
