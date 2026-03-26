# First Bad Version

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Binary Search` `Interactive`  
**Time:** O(log n)  
**Space:** O(log n)

---

## Solution (java)

```java
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int first = 1; 
        int last = n;
        while(first<last){
            int m = first+(last-first)/2;
            if(isBadVersion(m)) {
                last = m;
            }
            else first = m+1 ;
        }
        return first;
    }
}
```

---

---
## Quick Revision
Given a system where versions are numbered sequentially and a "bad" version makes all subsequent versions bad.
Find the first bad version using a binary search approach.

## Intuition
The problem states that if a version is bad, all subsequent versions are also bad. This implies a sorted property: all versions before the first bad version are good, and all versions from the first bad version onwards are bad. This sorted nature is a strong indicator that binary search can be applied. We want to find the *transition point* from good to bad.

## Algorithm
1. Initialize two pointers, `first` to 1 (the first possible version) and `last` to `n` (the last possible version).
2. While `first` is less than `last`:
   a. Calculate the middle version `m` using `m = first + (last - first) / 2`. This prevents potential integer overflow.
   b. Call the `isBadVersion(m)` API.
   c. If `isBadVersion(m)` returns `true` (meaning `m` is a bad version):
      i. The first bad version could be `m` or an earlier version. So, we narrow our search space to the left half, including `m`. Set `last = m`.
   d. If `isBadVersion(m)` returns `false` (meaning `m` is a good version):
      i. The first bad version must be after `m`. So, we narrow our search space to the right half, excluding `m`. Set `first = m + 1`.
3. When the loop terminates, `first` will be equal to `last`, and this value will be the first bad version. Return `first`.

## Concept to Remember
*   **Binary Search:** Efficiently searching a sorted or monotonically increasing/decreasing data structure.
*   **Monotonicity:** The property where a sequence is either entirely non-increasing or entirely non-decreasing. In this case, versions are good up to a point, then bad.
*   **Edge Cases in Binary Search:** Careful handling of pointer updates (`last = m` vs. `last = m - 1`, `first = m` vs. `first = m + 1`) is crucial to avoid infinite loops or missing the target.

## Common Mistakes
*   **Integer Overflow:** Using `(first + last) / 2` can lead to overflow if `first` and `last` are very large. The correct way is `first + (last - first) / 2`.
*   **Incorrect Pointer Updates:** Setting `last = m - 1` when `isBadVersion(m)` is true, or `first = m` when `isBadVersion(m)` is false, can lead to incorrect results or infinite loops.
*   **Off-by-One Errors:** Mismanaging the `first` and `last` pointers, especially at the boundaries, can cause the algorithm to miss the first bad version.
*   **Not Handling the `first == last` Case:** The loop condition `first < last` ensures termination, and the final `first` (or `last`) correctly points to the answer.

## Complexity Analysis
*   **Time:** O(log n) - reason: Binary search halves the search space in each step, leading to logarithmic time complexity.
*   **Space:** O(1) - reason: The algorithm uses a constant amount of extra space for variables like `first`, `last`, and `m`.

## Commented Code
```java
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        // Initialize the lower bound of our search space to the first version.
        int first = 1;
        // Initialize the upper bound of our search space to the last version.
        int last = n;
        // Continue searching as long as our search space has more than one element.
        while(first < last){
            // Calculate the middle version to check.
            // Using first + (last - first) / 2 prevents potential integer overflow.
            int m = first + (last - first) / 2;
            // Check if the middle version is bad using the provided API.
            if(isBadVersion(m)) {
                // If 'm' is bad, it means the first bad version could be 'm' or any version before it.
                // So, we narrow our search space to the left half, including 'm'.
                last = m;
            }
            else {
                // If 'm' is good, it means the first bad version must be after 'm'.
                // So, we narrow our search space to the right half, excluding 'm'.
                first = m + 1;
            }
        }
        // When the loop terminates, 'first' and 'last' will be equal and point to the first bad version.
        return first;
    }
}
```

## Interview Tips
*   Clearly explain the monotonic property of the problem and why binary search is applicable.
*   Walk through an example with `n=5` and a specific first bad version (e.g., 4) to demonstrate how the pointers move.
*   Pay close attention to the `first < last` loop condition and the `last = m` vs. `first = m + 1` updates, as these are critical for correctness.
*   Mention the potential for integer overflow and how `first + (last - first) / 2` mitigates it.

## Revision Checklist
- [ ] Understand the problem statement and the `isBadVersion` API.
- [ ] Recognize the monotonic property of bad versions.
- [ ] Implement binary search correctly with appropriate pointer initialization.
- [ ] Use `first + (last - first) / 2` for middle calculation.
- [ ] Ensure correct pointer updates (`last = m` and `first = m + 1`).
- [ ] Verify the loop termination condition and the final return value.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Search in Rotated Sorted Array
*   Find Minimum in Rotated Sorted Array
*   Sqrt(x)
*   Median of Two Sorted Arrays

## Tags
`Binary Search` `Two Pointers`
