# Sqrtx

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Math` `Binary Search`  
**Time:** O(log x)  
**Space:** O(log x)

---

## Solution (java)

```java
class Solution {
    public int mySqrt(int x) {
        if(x <= 1) return x;
        int l = 1;
        int r = x/2;
        int ans = 1;
        while(l <= r){
            int mid = l + (r-l)/2;
            if(mid <= x/mid){
                ans = mid; // if 2.8 is the answer, then 2 ko ans lena hai, 3 ko nhi lena hai.
                l = mid+1;
            }
            else r = mid-1;
        }
        return ans;
    }
}
```

---

---
## Quick Revision
Calculates the integer part of the square root of a non-negative integer.
Solves using binary search to efficiently find the largest integer whose square is less than or equal to the input.

## Intuition
The problem asks for the integer part of the square root. This means we're looking for the largest integer `k` such that `k * k <= x`. Since the square function is monotonically increasing for non-negative numbers, this is a perfect candidate for binary search. We can search for this `k` within a reasonable range. A good upper bound for `k` is `x/2` (for `x > 1`), because `(x/2)^2 = x^2/4`, which is greater than `x` for `x > 4`. For `x=0` and `x=1`, the square root is the number itself.

## Algorithm
1. Handle base cases: If `x` is 0 or 1, return `x` directly as its square root is itself.
2. Initialize search space: Set the left boundary `l` to 1 and the right boundary `r` to `x/2`. This range covers all possible integer square roots for `x > 1`.
3. Initialize `ans`: Set `ans` to 1. This will store the largest integer found so far whose square is less than or equal to `x`.
4. Perform binary search:
    a. While `l` is less than or equal to `r`:
        i. Calculate the middle element `mid = l + (r - l) / 2`. This prevents potential integer overflow compared to `(l + r) / 2`.
        ii. Check the condition: If `mid <= x / mid` (to avoid overflow from `mid * mid`), it means `mid` could be our answer or a smaller value.
            - Update `ans = mid` because `mid` is a potential candidate.
            - Move the left boundary to `l = mid + 1` to search for a larger possible square root.
        iii. Else (if `mid > x / mid`), it means `mid` is too large.
            - Move the right boundary to `r = mid - 1` to search in the lower half.
5. Return `ans`: After the loop terminates, `ans` will hold the largest integer whose square is less than or equal to `x`.

## Concept to Remember
*   **Binary Search:** Efficiently searching a sorted or monotonically increasing/decreasing range.
*   **Integer Overflow Prevention:** Using `mid <= x / mid` instead of `mid * mid <= x` to avoid potential overflow when `mid` is large.
*   **Monotonicity:** The square function `f(k) = k*k` is monotonically increasing for `k >= 0`.

## Common Mistakes
*   Not handling the base cases `x=0` and `x=1` correctly.
*   Using `mid * mid` which can lead to integer overflow for large `x`.
*   Incorrectly updating `l` and `r` boundaries in the binary search.
*   Not storing the potential answer (`ans = mid`) when `mid <= x/mid`, leading to the wrong result if the exact square root is not an integer.
*   Choosing an incorrect initial range for binary search (e.g., `r = x`).

## Complexity Analysis
- Time: O(log x) - reason: The binary search halves the search space in each iteration.
- Space: O(1) - reason: The algorithm uses a constant amount of extra space for variables.

## Commented Code
```java
class Solution {
    public int mySqrt(int x) {
        // Handle base cases: if x is 0 or 1, its square root is itself.
        if(x <= 1) return x;

        // Initialize the left boundary of the search space.
        int l = 1;
        // Initialize the right boundary of the search space. For x > 1, the square root cannot exceed x/2.
        int r = x/2;
        // Initialize 'ans' to store the largest integer whose square is <= x. Start with 1.
        int ans = 1;

        // Perform binary search within the range [l, r].
        while(l <= r){
            // Calculate the middle element to avoid potential integer overflow.
            int mid = l + (r-l)/2;

            // Check if mid squared is less than or equal to x.
            // Using mid <= x/mid to prevent potential overflow from mid * mid.
            if(mid <= x/mid){
                // If mid squared is <= x, then mid is a potential answer.
                // We store it and try to find a larger one by searching in the right half.
                ans = mid;
                // Move the left boundary to search for a larger potential square root.
                l = mid+1;
            }
            else {
                // If mid squared is > x, then mid is too large.
                // We need to search in the left half.
                r = mid-1;
            }
        }
        // After the loop, 'ans' holds the largest integer whose square is less than or equal to x.
        return ans;
    }
}
```

## Interview Tips
*   Clearly explain the binary search approach and why it's suitable.
*   Emphasize the integer overflow prevention technique (`mid <= x/mid`).
*   Walk through an example (e.g., `x = 8`) to demonstrate how the `l`, `r`, and `ans` variables change.
*   Discuss the edge cases (`x=0`, `x=1`) and how they are handled.

## Revision Checklist
- [ ] Understand the problem: find integer square root.
- [ ] Identify binary search as a suitable approach.
- [ ] Define the search space (e.g., `[1, x/2]`).
- [ ] Implement the binary search loop correctly.
- [ ] Use `mid <= x/mid` to prevent overflow.
- [ ] Correctly update `ans`, `l`, and `r`.
- [ ] Handle base cases `x=0` and `x=1`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Find Peak Element
*   Search in Rotated Sorted Array
*   First Bad Version
*   Median of Two Sorted Arrays

## Tags
`Binary Search` `Math`
