# Minimum Positive Sum Subarray

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Sliding Window` `Prefix Sum`  
**Time:** O(N \cdot R)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int min = Integer.MAX_VALUE;
        int n = nums.size();
        for(int i =0;i<n;i++){
            int sum = 0;
            for(int j = i;j<n && j<i+r;j++){
                sum += nums.get(j);
                int len = j-i+1;
                if(len >= l && sum >0  && sum < min) min = sum;
            }   
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
```

---

---
## Quick Revision
Finds the minimum sum among all subarrays whose length is between `l` and `r` (inclusive) and whose sum is strictly positive.
Iterate through all possible starting points and expand the right endpoint up to length `r`, maintaining and checking valid positive sums.

## Intuition
The "aha moment" in this problem is realizing that since the constraints on the array length are typically small or moderate for an Easy problem, we can exhaustively check all valid subarrays. By using a nested loop where the outer loop fixes the starting index `i` and the inner loop extends the ending index `j`, we can dynamically compute the subarray sum in real-time. As we add elements, we track the current length and ensure it falls within the range `[l, r]`. Whenever the sum is strictly greater than zero and smaller than our running minimum, we update our answer.

## Algorithm
1. Initialize a variable `min` to `Integer.MAX_VALUE` to track the smallest positive subarray sum found.
2. Get the size of the list `n`.
3. Loop through each possible starting index `i` from `0` to `n - 1`.
4. Initialize a running `sum` variable to `0` for the current starting index.
5. Loop through each possible ending index `j` starting from `i` up to `n - 1`, ensuring `j` does not exceed `i + r - 1` (or `j < i + r`).
6. Add the element `nums.get(j)` to the running `sum`.
7. Calculate the current subarray length as `len = j - i + 1`.
8. Check if `len >= l`, `sum > 0`, and `sum < min`. If all conditions are met, update `min = sum`.
9. After checking all subarrays, return `min` if it was updated, otherwise return `-1`.

## Concept to Remember
- Brute-force enumeration of subarrays with sliding or running sums.
- Constraint filtering (length bounds and positive value requirement).
- Sentinel value initialization and handling impossibility (`-1` return).

## Common Mistakes
- Forgetting to check if the sum is strictly positive (`sum > 0`), returning non-positive sums.
- Miscalculating the length boundaries (`l` and `r`), allowing subarrays shorter than `l` or longer than `r`.
- Not resetting or incorrectly calculating the running sum for each new starting index `i`.
- Failing to handle the case where no valid subarray exists, which should result in returning `-1` instead of `Integer.MAX_VALUE`.

## Complexity Analysis
- Time: $O(N \cdot R)$ - where $N$ is the size of the array and $R$ is the maximum allowed length of the subarray, because the inner loop runs at most $R$ times for each of the $N$ starting positions.
- Space: $O(1)$ - because we only use a few primitive variables (`min`, `sum`, `len`, indices) regardless of the input size.

## Commented Code
```java
class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        // Initialize min with max possible value to track the smallest positive sum found
        int min = Integer.MAX_VALUE;
        // Store the total number of elements in the list
        int n = nums.size();
        // Outer loop to fix the starting index of the subarray
        for(int i = 0; i < n; i++) {
            // Reset the running sum for each new starting index
            int sum = 0;
            // Inner loop to extend the ending index, bounded by array size and max length r
            for(int j = i; j < n && j < i + r; j++) {
                // Add the current element to the running sum
                sum += nums.get(j);
                // Calculate the current length of the subarray
                int len = j - i + 1;
                // Check if length meets the minimum requirement, sum is positive, and sum is smaller than min
                if(len >= l && sum > 0 && sum < min) {
                    // Update the minimum positive sum
                    min = sum; 
                }
            }   
        }
        // If min was never updated, return -1; otherwise, return the minimum positive sum
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
```

## Interview Tips
- Clarify the constraints on `l` and `r` early to see if an $O(N \cdot R)$ approach is sufficient (which it usually is for Easy problems).
- Mention optimization potentials, such as prefix sums or sliding window data structures, if constraints were significantly larger.
- Walk through a dry run with a small test case containing negative numbers to show how the `sum > 0` filter protects correctness.

## Revision Checklist
- [ ] Understand why a running sum inside the inner loop avoids $O(N^3)$ or $O(N^2)$ recalculations.
- [ ] Verify how length boundaries `l` and `r` are enforced.
- [ ] Check the strict positivity condition `sum > 0`.
- [ ] Confirm the fallback return value `-1` when no valid subarray exists.

## Similar Problems
- Minimum Size Subarray Sum
- Maximum Subarray
- Subarray Sum Equals K

## Tags
`Array` `Prefix Sum` `Sliding Window` `Enumeration`
